package cz.vance.movieapp.managers.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.keyboards.IInlineKeyboardBuilder;
import cz.vance.movieapp.keyboards.IReplyKeyboardBuilder;
import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;
import cz.vance.movieapp.keyboards.ReplyKeyboardBuilder;
import cz.vance.movieapp.managers.ProgramSleeper;
import cz.vance.movieapp.managers.builders.IMessageBuilder;
import cz.vance.movieapp.managers.builders.MessageBuilder;
import cz.vance.movieapp.managers.formatters.IMessageFormatter;
import cz.vance.movieapp.managers.formatters.MessageFormatter;
import cz.vance.movieapp.managers.records.IMovieRecord;
import cz.vance.movieapp.managers.selections.IUserSelectionManager;
import cz.vance.movieapp.managers.selections.UserSelectionManager;
import cz.vance.movieapp.managers.updates.IUpdateExtractor;
import cz.vance.movieapp.managers.updates.UpdateExtractor;
import cz.vance.movieapp.models.BotMessage;
import cz.vance.movieapp.models.Movie;
import cz.vance.movieapp.models.UserSelection;
import cz.vance.movieapp.managers.randomizers.IMessageRandomizer;
import cz.vance.movieapp.managers.randomizers.MessageRandomizer;
import cz.vance.movieapp.managers.records.MovieRecord;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import cz.vance.movieapp.bot.TelegramRoverBot;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
//</editor-fold>

/**
 * Implements a set of basic operations for handling messages.
 * <br>
 * Contains a {@link IMessageRandomizer} instance providing all the possible bot messages.
 * <p>
 * All the bot messages are stored within the {@link BotMessage} class.
 * <br>
 * The {@link BotMessageManager} class provides getters for the {@link BotMessage}.
 * <br>
 * The {@link MessageRandomizer} class provides random selection for the {@link BotMessageManager}.
 */
public final class MessageManager implements IMessageManager {

    //<editor-fold default-state="collapsed" desc="Fields">
    /**
     * Instance of the {@link TelegramRoverBot} class needed to send messages.
     */
    private final TelegramLongPollingBot bot;

    private final IUserSelectionManager userSelectionManager;
    private final IMovieRecord movieRecord;
    private final IMessageFormatter messageFormatter;

    private final IReplyKeyboardBuilder replyKeyboardBuilder;
    private final IInlineKeyboardBuilder inlineKeyboardBuilder;

    /**
     * Instance of the {@link MessageBuilder} class that returns the <b>SendMessage</b> objects with the provided
     * message parameter, such as <b>chat id</b>, <b>message text</b>, and <b>keyboard</b>.
     */
    private final IMessageBuilder messageBuilder;
    /**
     * Instance of the {@link UpdateExtractor} class that extracts information from an incoming Telegram update
     * (including <b>chat id</b>, <b>message text</b>, <b>message id</b> etc.).
     */
    private final IUpdateExtractor updateExtractor;

    /**
     * Instance of the {@link MessageRandomizer} class that returns phrase strings during the <b>smart search</b>.
     */
    private final IMessageRandomizer messageRandomizer;

    /**
     * Stores the latest message id for each chat id.
     * <br>
     * Is used to determine if the user interacts with inline keyboards that were sent to him earlier and should be
     * removed.
     */
    private final HashMap<Long, Integer> latestMessageIds = new HashMap<>();
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Latest Message Handler">
    private final Consumer<Message> latestMessageHandler = message -> {
        if (IUpdateExtractor.messagePresenceChecker.apply(message)) {
            final long chatId = message.getChatId();
            final int messageId = message.getMessageId();
            latestMessageIds.put(chatId, messageId);
        }
    };
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Constructor">
    public MessageManager(TelegramLongPollingBot bot) {
        userSelectionManager = UserSelectionManager.getInstance();
        movieRecord = MovieRecord.getInstance();

        messageFormatter = new MessageFormatter();

        replyKeyboardBuilder = new ReplyKeyboardBuilder();
        inlineKeyboardBuilder = new InlineKeyboardBuilder();

        messageBuilder = new MessageBuilder();
        updateExtractor = new UpdateExtractor();

        messageRandomizer = new MessageRandomizer();

        this.bot = bot;
    }
    //</editor-fold>

    @Override
    public void sendEcho(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);

        if (IUpdateExtractor.hasText(botUpdate)) {
            final String messageText = updateExtractor.getMessageText(botUpdate);
            sendEchoText(chatId, messageText);
        } else if (IUpdateExtractor.hasSticker(botUpdate)) {
            final String stickerFileId = updateExtractor.getStickerFileId(botUpdate);
            sendEchoSticker(chatId, stickerFileId);
        } else if (IUpdateExtractor.hasPhoto(botUpdate) || IUpdateExtractor.hasDocument(botUpdate) ||
                IUpdateExtractor.hasVideo(botUpdate) || IUpdateExtractor.hasAudio(botUpdate) ||
                IUpdateExtractor.hasVoice(botUpdate)) {
            sendUnknownInput(chatId);
        }
    }

    @Override
    public void sendWelcome(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String messageText = getFormattedWelcomeText(botUpdate);
        final String stickerFileId = messageRandomizer.getWelcomeSticker();

        sendSticker(chatId, stickerFileId);
        sendWelcome(chatId, messageText);
    }

    @Override
    public void sendHelp(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String messageText = messageRandomizer.getHelpMessage();

        sendHelp(chatId, messageText);
    }

    @Override
    public void sendMood(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String atLaunchText = messageRandomizer.getAtLaunchMessage();
        final String messageText = messageRandomizer.getMoodMessage();
        final String stickerFileId = messageRandomizer.getBeginningSticker();

        if (!latestMessageIds.isEmpty()){
            handleRepeatedInlineKeyboard(chatId, latestMessageIds.get(chatId));
            latestMessageIds.remove(chatId);
        }

        userSelectionManager.initializeUserSelection(chatId);

        sendMessage(chatId, atLaunchText);
        ProgramSleeper.pauseSmartSearchBeforeSendingMood();

        sendSticker(chatId, stickerFileId);

        final Message moodMessage = sendMessage(chatId, messageText, inlineKeyboardBuilder.buildMoodKeyboard());
        latestMessageHandler.accept(moodMessage);
    }

    @Override
    public void sendCatalogue(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        final String messageText = messageRandomizer.getCatalogueMessage();

        final String mood = updateExtractor.getCallbackQuery(botUpdate);
        userSelectionManager.putMood(chatId, mood);

        sendMessage(chatId, messageId, messageText, inlineKeyboardBuilder.buildCatalogueKeyboard());
    }

    @Override
    public void sendGenre(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);
        final String messageText = messageRandomizer.getGenreMessage();

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        final String catalogue = updateExtractor.getCallbackQuery(botUpdate);
        userSelectionManager.putCatalogue(chatId, catalogue);

        sendMessage(chatId, messageId, messageText, inlineKeyboardBuilder.buildGenreKeyboard());
    }

    @Override
    public void sendConfirmation(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        final String genre = updateExtractor.getCallbackQuery(botUpdate);
        userSelectionManager.putGenre(chatId, genre);

        final String messageText = messageRandomizer.getVerifyingMessage(
                userSelectionManager.getUserSelection(chatId));
        sendMessage(chatId, messageId, messageText, inlineKeyboardBuilder.buildConfirmationKeyboard());
    }

    //<editor-fold default-state="collapsed" desc="Overridden 'sendMovie' Method">
    @Override
    public void sendMovie(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);
        final String stickerFileId = messageRandomizer.getEndSticker();

        if (isSmartSearchNoButtonPressed(botUpdate)) {
            handleSmartSearchNoConfirmationButton(chatId, messageId, botUpdate);
            return;
        }

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        processSmartSearch(chatId, messageId, stickerFileId);
    }

    /**
     * Checks if the user has pressed the <b>no confirmation</b> button in the <b>smart search</b> inline keyboard.
     */
    private boolean isSmartSearchNoButtonPressed(Update botUpdate) { return inlineKeyboardBuilder.isSmartSearchNoButton(botUpdate); }

    /**
     * In case the user has pressed the <b>no confirmation</b> button in the <b>smart search</b> inline keyboard:
     * <ul>
     *     <li>Removes the inline keyboard;</li>
     *     <li>Sends a message to the user that the <b>smart search</b> will be rebooted;</li>
     *     <li>Removes the user's latest message id, because the process will be started again;</li>
     *     <li>Removes the user's selection;</li>
     *     <li>Pauses the program before sending the mood message;</li>
     *     <li>Sends the mood message;</li>
     * </ul>
     */
    private void handleSmartSearchNoConfirmationButton(long chatId,
                                                       long messageId,
                                                       Update botUpdate) {
        removeMessageInlineKeyboard(chatId, messageId);
        sendMessage(chatId, messageRandomizer.getOnSmartSearchRebooted());

        latestMessageIds.remove(chatId);
        userSelectionManager.removeUserSelection(chatId);

        ProgramSleeper.pauseSmartSearchBeforeReboot();
        sendMood(botUpdate);
    }

    /**
     * Processes the <b>smart search</b> by:
     * <ul>
     *     <li>Getting the <b>user's selection</b> object;</li>
     *     <li>Getting the <b>smart search movies</b>;</li>
     *     <li>Building the <b>movie message</b>;</li>
     *     <li>Sending a <b>sticker</b>;</li>
     *     <li>Pausing the program before sending the <b>movie message</b> to imitate the process of search;</li>
     *     <li>Sending the <b>movie message</b>;</li>
     *     <li>Removing the <b>inline keyboard</b>;</li>
     *     <li>Editing the <b>message text</b> to inform the user that the inline keyboard has been removed;</li>
     *     <li>Removing the user's latest message id;</li>
     *     <li>Removing the <b>user's selection</b>;</li>
     *     <li>Pausing the program before sending a new mood message;</li>
     *     <li>Sending the <b>mood message</b>;</li>
     * </ul>
     */
    private void processSmartSearch(long chatId,
                                    long messageId,
                                    String stickerFileId) {
        final UserSelection userSelection = userSelectionManager.getUserSelection(chatId);
        final List<Movie> smartSearchMovies = movieRecord.getSmartSearchMovies(userSelection);

        final String movieMessageText = smartSearchMovies.isEmpty() ?
                messageFormatter.getSmartSearchNoMoviesMessage() :
                messageFormatter.getSmartSearchMovieMessage(smartSearchMovies);

        sendSticker(chatId, stickerFileId);
        ProgramSleeper.pauseSmartSearchBeforeSendingMovie();
        sendMessage(chatId, movieMessageText);

        removeMessageInlineKeyboard(chatId, messageId);
        editMessageText(
                chatId,
                messageId,
                messageRandomizer.getOnSmartSearchRepeatedKeyboardRemovedMessage());

        latestMessageIds.remove(chatId);
        userSelectionManager.removeUserSelection(chatId);
    }
    //</editor-fold>

    /* ---------------- Private Helper Methods -------------- */

    //<editor-fold default-state="collapsed" desc="Echo Message Sender">
    private void sendEchoText(long chatId, String messageText) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(chatId, messageText));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendEchoSticker(long chatId, String stickerFileId) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramSticker(chatId, stickerFileId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Welcome Message Sender">
    private void sendWelcome(long chatId, String messageText) {
        try {
            bot.execute(messageBuilder.buildTelegramMessage(
                    chatId,
                    messageText,
                    replyKeyboardBuilder.buildMainMenuKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats and returns the welcome text by adding <b>user's username</b> and <b>the username of the bot</b>.
     *
     * @param botUpdate The incoming update to extract the <b>user's surname</b>.
     *
     * @return The formatted text for a welcome message.
     */
    private String getFormattedWelcomeText(@NotNull Update botUpdate) {
        return String.format(
                messageRandomizer.getWelcomeMessage(),
                botUpdate.getMessage().getFrom().getFirstName(),
                bot.getBotUsername());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Help Message Sender">
    private void sendHelp(long chatId, String messageText) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(chatId, messageText));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Message Senders">
    private @Nullable Message sendMessage(long chatId,
                                          String messageText,
                                          InlineKeyboardMarkup inlineMarkup) {
        try {
            return bot.execute(
                    messageBuilder.buildTelegramMessage(
                            chatId,
                            messageText,
                            inlineMarkup));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendMessage(long chatId,
                             long messageId,
                             String messageText,
                             InlineKeyboardMarkup inlineMarkup) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(
                            chatId,
                            messageId,
                            messageText,
                            inlineMarkup));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(long chatId, String messageText) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(
                            chatId,
                            messageText));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Sticker Message Sender">
    private void sendSticker(long chatId, String stickerFileId) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramSticker(
                            chatId,
                            stickerFileId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Unknown Input Message Sender">
    private void sendUnknownInput(long chatId) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(
                            chatId,
                            messageRandomizer.getUnknownInputMessage()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Message Editors">
    /**
     * Removes the inline keyboard from the specified message.
     *
     * @param chatId The chat ID where the message is located.
     * @param messageId The message ID of the message to update.
     */
    private void removeMessageInlineKeyboard(long chatId, long messageId) {
        final EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        editMessageReplyMarkup.setChatId(chatId);
        editMessageReplyMarkup.setMessageId((int) messageId);
        editMessageReplyMarkup.setReplyMarkup(null);

        try {
            bot.execute(editMessageReplyMarkup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void editMessageText(long chatId,
                                 long messageId,
                                 String messageText) {
        final EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId((int) messageId);
        editMessageText.setText(messageText);

        try {
            bot.execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Old Keyboard Handlers">
    private void handleOldInlineKeyboard(long chatId, long messageId) {
        if (isOldInlineKeyboard(chatId, messageId)) {
            removeMessageInlineKeyboard(chatId, messageId);
            editMessageText(
                    chatId,
                    messageId,
                    messageRandomizer.getOnSmartSearchOldKeyboardRemovedMessage());
        }
    }

    private void handleRepeatedInlineKeyboard(long chatId, long messageId) {
        if (isRepeatedInlineKeyboard(chatId, messageId)) {
            removeMessageInlineKeyboard(chatId, messageId);
            editMessageText(
                    chatId,
                    messageId,
                    messageRandomizer.getOnSmartSearchRepeatedKeyboardRemovedMessage());
        }
    }

    /**
     * Checks if the passed message is repeated, meaning the bot sent a message with an inline keyboard, but then
     * the user triggers the bot to send a new message with some inline keyboard. The previously sent keyboard
     * should be removed.
     */
    private boolean isRepeatedInlineKeyboard(long chatId, long messageId) {
        return latestMessageIds.containsKey(chatId) && latestMessageIds.get(chatId) == (int)messageId;
    }

    /**
     * Checks if the passed message is old, meaning the attached inline keyboard is outdated.
     * <br>
     * The old keyboard — is an inline keyboard attached to a message that was sent before the latest message. For
     * instance, the user could interact with the bot and the last message the bot sent was a message with an inline
     * keyboard. Then the bot was rebooted. This last message is now outdated and the inline keyboard should be removed.
     */
    private boolean isOldInlineKeyboard(long chatId, long messageId) {
        return !latestMessageIds.containsKey(chatId) || latestMessageIds.get(chatId) != (int)messageId;
    }
    //</editor-fold>

    /* ------------------------------------------------------ */
}
