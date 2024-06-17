package cz.vance.movieapp.managers.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.keyboards.IInlineKeyboardBuilder;
import cz.vance.movieapp.keyboards.IReplyKeyboardBuilder;
import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;
import cz.vance.movieapp.keyboards.ReplyKeyboardBuilder;
import cz.vance.movieapp.managers.builders.IMessageBuilder;
import cz.vance.movieapp.managers.builders.MessageBuilder;
import cz.vance.movieapp.managers.records.IMovieRecord;
import cz.vance.movieapp.managers.selections.IUserSelectionManager;
import cz.vance.movieapp.managers.selections.UserSelectionManager;
import cz.vance.movieapp.managers.updates.IUpdateExtractor;
import cz.vance.movieapp.managers.updates.UpdateExtractor;
import cz.vance.movieapp.models.UserSelection;
import cz.vance.movieapp.randomizers.ISmartSearchRandomizer;
import cz.vance.movieapp.randomizers.SmartSearchRandomizer;
import cz.vance.movieapp.managers.records.MovieRecord;
import cz.vance.movieapp.utils.BotMessage;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import cz.vance.movieapp.bot.TelegramRoverBot;
//</editor-fold>

/**
 * Implements a set of basic operations for handling messages.
 */
public final class MessageManager implements IMessageManager {

    /**
     * Instance of the {@link TelegramRoverBot} class needed to send messages.
     */
    private final TelegramLongPollingBot bot;

    private final IUserSelectionManager userSelectionManager;
    private final IMovieRecord movieRecord;

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
     * Instance of the {@link SmartSearchRandomizer} class that returns phrase strings during the <b>smart search</b>.
     */
    private final ISmartSearchRandomizer smartSearchRandomizer;

    public MessageManager(TelegramLongPollingBot bot) {
        userSelectionManager = UserSelectionManager.getInstance();
        movieRecord = MovieRecord.getInstance();

        replyKeyboardBuilder = new ReplyKeyboardBuilder();
        inlineKeyboardBuilder = new InlineKeyboardBuilder();

        messageBuilder = new MessageBuilder();
        updateExtractor = new UpdateExtractor();

        smartSearchRandomizer = new SmartSearchRandomizer();

        this.bot = bot;
    }

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
        final String stickerFileId = smartSearchRandomizer.getRandomWelcomeSticker();

        sendSticker(chatId, stickerFileId);
        sendWelcome(chatId, messageText);
    }

    @Override
    public void sendHelp(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String messageText = BotMessage.HELP_MESSAGE.getContent();
        sendHelp(chatId, messageText);
    }

    @Override
    public void sendMood(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String messageText = smartSearchRandomizer.getMoodMessage();
        final String stickerFileId = smartSearchRandomizer.getRandomSmartSearchBeginningSticker();

        userSelectionManager.initializeUserSelection(chatId);

        sendSticker(chatId, stickerFileId);
        sendMood(chatId, messageText);
    }

    @Override
    public void sendCatalogue(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);
        final String messageText = smartSearchRandomizer.getCatalogueMessage();

        final String mood = updateExtractor.getCallbackQuery(botUpdate);
        userSelectionManager.putMood(chatId, mood);

        sendCatalogue(chatId, messageId, messageText);
    }

    @Override
    public void sendGenre(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);
        final String messageText = smartSearchRandomizer.getGenreMessage();

        final String catalogue = updateExtractor.getCallbackQuery(botUpdate);
        userSelectionManager.putCatalogue(chatId, catalogue);

        sendGenre(chatId, messageId, messageText);
    }

    @Override
    public void sendMovie(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final String stickerFileId = smartSearchRandomizer.getRandomSmartSearchEndSticker();

        final String genre = updateExtractor.getCallbackQuery(botUpdate);
        userSelectionManager.putGenre(chatId, genre);

        final UserSelection userSelection = userSelectionManager.getUserSelection(chatId);

        sendSticker(chatId, stickerFileId);
        sendMovie(chatId, userSelection);
    }

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
    private static final String USER_USERNAME_PATTERN = "<b>%s</b>";

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
                BotMessage.WELCOME_MESSAGE.getContent(),
                String.format(USER_USERNAME_PATTERN, botUpdate.getMessage().getFrom().getFirstName()),
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

    //<editor-fold default-state="collapsed" desc="Mood Message Sender">
    private void sendMood(long chatId, String messageText) {
        try {
            bot.execute(messageBuilder.buildTelegramMessage(
                    chatId,
                    messageText,
                    inlineKeyboardBuilder.buildMoodKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Catalogue Message Sender">
    private void sendCatalogue(long chatId, long messageId, String messageText) {
        try {
            bot.execute(messageBuilder.buildTelegramMessage(
                    chatId,
                    messageId,
                    messageText,
                    inlineKeyboardBuilder.buildCatalogueKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Genre Message Sender">
    private void sendGenre(long chatId, long messageId, String messageText) {
        try {
            bot.execute(messageBuilder.buildTelegramMessage(
                    chatId,
                    messageId,
                    messageText,
                    inlineKeyboardBuilder.buildGenreKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Movie Message Sender">
    private void sendMovie(long chatId, @NotNull UserSelection userSelection) {
        try {
            bot.execute(messageBuilder.buildTelegramMessage(
                    chatId,
                    String.join("\n", userSelection.getMood(), userSelection.getCatalogue(), userSelection.getGenre())));
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
                    messageBuilder.buildTelegramMessage(chatId, BotMessage.UNKNOWN_INPUT.getContent()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>
}
