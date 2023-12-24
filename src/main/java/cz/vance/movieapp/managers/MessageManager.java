package cz.vance.movieapp.managers;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;
import cz.vance.movieapp.keyboards.ReplyKeyboardBuilder;
import cz.vance.movieapp.randomizers.SmartSearchRandomizer;
import cz.vance.movieapp.utils.BotMessage;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import cz.vance.movieapp.bot.TelegramRoverBot;
//</editor-fold>

/**
 * This <b>MessageManager</b> class implements a set of basic operations for handling messages
 *
 * @see IMessageManager
 */
public final class MessageManager implements IMessageManager {

    /**
     * This <b>constant</b> is an instance of the {@link TelegramRoverBot} class that is needed to send
     * messages
     */
    private final TelegramLongPollingBot bot;
    /**
     * These <b>constants</b> are instances of the custom keyboard builders that provide with different custom
     * keyboards
     */
    private final ReplyKeyboardBuilder replyKeyboardBuilder = new ReplyKeyboardBuilder();
    private final InlineKeyboardBuilder inlineKeyboardBuilder = new InlineKeyboardBuilder();

    public MessageManager(TelegramLongPollingBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendEcho(Update botUpdate) {
        final long chatId = getMessageChatId(botUpdate);
        final String messageText = getMessageText(botUpdate);
        sendEcho(chatId, messageText);
    }

    @Override
    public void sendWelcome(Update botUpdate) {
        final long chatId = getMessageChatId(botUpdate);
        final String messageText = getFormattedWelcomeText(botUpdate);
        sendWelcome(chatId, messageText);
    }

    @Override
    public void sendHelp(Update botUpdate) {
        final long chatId = getMessageChatId(botUpdate);
        final String messageText = BotMessage.HELP_MESSAGE.content();
        sendHelp(chatId, messageText);
    }

    @Override
    public void sendMood(Update botUpdate) {
        final long chatId = getMessageChatId(botUpdate);
        final String messageText = SmartSearchRandomizer.getMoodMessage();
        sendMood(chatId, messageText);
    }

    @Override
    public void sendCatalogue(Update botUpdate) {
        final long chatId = getCallbackChatId(botUpdate);
        final long messageId = getCallbackMessageId(botUpdate);
        final String messageText = SmartSearchRandomizer.getCatalogueMessage();
        sendCatalogue(chatId, messageId, messageText);
    }

    @Override
    public void sendGenre(Update botUpdate) {
        final long chatId = getCallbackChatId(botUpdate);
        final long messageId = getCallbackMessageId(botUpdate);
        final String messageText = SmartSearchRandomizer.getGenreMessage();
        sendGenre(chatId, messageId, messageText);
    }

//<editor-fold default-state="collapsed" desc="Echo Message">
    /**
     * Sends an echo message to the specified <b>chat ID</b> with the given <b>message text</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageText A string containing the text
     */
    private void sendEcho(long chatId, String messageText) {
        try {
            bot.execute(buildTelegramMessage(chatId, messageText));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="Welcome Message">
    private static final String USER_USERNAME_PATTERN = "<b>%s</b>";

    /**
     * Sends a welcome message to the specified <b>chat ID</b> with the given <b>message text</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageText A string containing the text
     */
    private void sendWelcome(long chatId, String messageText) {
        try {
            bot.execute(buildTelegramMessage(
                    chatId,
                    messageText,
                    replyKeyboardBuilder.buildMainMenuKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats and returns the welcome text by adding <b>user's username</b> and <b>the username of the bot</b>
     *
     * @param botUpdate The bot update to extract <b>user's surname</b>
     *
     * @return The formatted text for a welcome message
     */
    private String getFormattedWelcomeText(@NotNull Update botUpdate) {
        return String.format(
                BotMessage.WELCOME_MESSAGE.content(),
                String.format(USER_USERNAME_PATTERN, botUpdate.getMessage().getFrom().getFirstName()),
                bot.getBotUsername());
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="sendHelp">
    /**
     * Sends a help message to the specified <b>chat ID</b> with the given <b>message text</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageText A string containing the text
     */
    private void sendHelp(long chatId, String messageText) {
        try {
            bot.execute(buildTelegramMessage(chatId, messageText));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="sendMood">
    /**
     * Sends a message with <b>the mood selection</b> to the specified <b>chat ID</b> with the given <b>message text</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageText A string containing the text
     */
    private void sendMood(long chatId, String messageText) {
        try {
            bot.execute(buildTelegramMessage(
                    chatId,
                    messageText,
                    inlineKeyboardBuilder.buildMoodKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="sendCatalogue">
    /**
     * Sends a message with <b>the movie/series selection</b> to the specified <b>chat ID</b> with the given
     * <b>message ID</b> and <b>message text</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageId A whole number representing a message ID
     * @param messageText A string containing the text
     */
    private void sendCatalogue(long chatId, long messageId, String messageText) {
        try {
            bot.execute(buildTelegramMessage(
                    chatId,
                    messageId,
                    messageText,
                    inlineKeyboardBuilder.buildCatalogueKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="sendGenre">
    /**
     * Sends a message with <b>the genre selection</b> to the specified <b>chat ID</b> with the given
     * <b>message ID</b> and <b>message text</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageId A whole number representing a message ID
     * @param messageText A string containing the text
     */
    private void sendGenre(long chatId, long messageId, String messageText) {
        try {
            bot.execute(buildTelegramMessage(
                    chatId,
                    messageId,
                    messageText,
                    inlineKeyboardBuilder.buildGenreKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="Message Builders">
    private static final String PARSE_MODE = "HTML";

    /**
     * Builds a Telegram message with the specified <b>chat ID</b> and <b>message text</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageText A string containing the text
     *
     * @return A configured <b>SendMessage</b> instance representing the Telegram message
     *
     * @see SendMessage
     */
    private @NotNull SendMessage buildTelegramMessage(long chatId, String messageText) {
        final SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        message.setParseMode(PARSE_MODE);
        return message;
    }

    /**
     * Builds a Telegram message with the specified <b>chat ID</b>, <b>message text</b> and <b>reply keyboard</b>
     *
     * Builds an edited Telegram message with the specified <b>chat ID</b>, <b>message ID</b>, <b>message text</b> and
     * <b>inline keyboard</b>
     *
     * @param callbackChatId A whole number representing a chat ID
     * @param callbackMessageId A whole number representing a message ID
     * @param messageText A string containing the text
     * @param keyboardMarkup An inline keyboard instance
     *
     * @return A configured <b>EditMessageText</b> instance with custom buttons representing the edited Telegram message
     *
     * @see EditMessageText
     */
    private @NotNull EditMessageText buildTelegramMessage(long callbackChatId,
                                                      long callbackMessageId,
                                                      String messageText,
                                                      InlineKeyboardMarkup keyboardMarkup) {
        final EditMessageText editedMessage = new EditMessageText();
        editedMessage.setChatId(callbackChatId);
        editedMessage.setMessageId(Math.toIntExact(callbackMessageId));
        editedMessage.setText(messageText);
        editedMessage.setReplyMarkup(keyboardMarkup);
        editedMessage.setParseMode(PARSE_MODE);
        return editedMessage;
    }

    /**
     * Builds a Telegram message with the specified <b>chat ID</b>, <b>message text</b> and <b>inline keyboard</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageText A string containing the text
     * @param keyboardMarkup An inline keyboard instance
     *
     * @return A configured <b>SendMessage</b> instance with custom buttons representing the Telegram message
     *
     * @see SendMessage
     */
    private @NotNull SendMessage buildTelegramMessage(long chatId,
                                                      String messageText,
                                                      InlineKeyboardMarkup keyboardMarkup) {
        final SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        message.setReplyMarkup(keyboardMarkup);
        message.setParseMode(PARSE_MODE);
        return message;
    }

    /**
     * Builds a Telegram message with the specified <b>chat ID</b>, <b>message text</b> and <b>reply keyboard</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageText A string containing the text
     * @param keyboardMarkup A reply keyboard instance
     *
     * @return A configured <b>SendMessage</b> instance with custom buttons representing the Telegram message
     *
     * @see SendMessage
     */
    private @NotNull SendMessage buildTelegramMessage(long chatId,
                                                      String messageText,
                                                      ReplyKeyboardMarkup keyboardMarkup) {
        final SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        message.setReplyMarkup(keyboardMarkup);
        message.setParseMode(PARSE_MODE);
        return message;
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="Update Parameters Getters">
    /**
     * Extracts the <b>chat ID</b> from the provided Telegram update message
     *
     * @param update The Telegram update containing a message
     *
     * @return A whole number of the <b>long</b> primitive type
     */
    private long getMessageChatId(@NotNull Update update) {
        return update.getMessage().getChatId();
    }

    /**
     * Extracts the <b>chat ID</b> from the provided Telegram update callback
     *
     * @param update The Telegram update containing a callback
     *
     * @return A whole number of the <b>long</b> primitive type
     */
    private long getCallbackChatId(@NotNull Update update) {
        return update.getCallbackQuery().getMessage().getChatId();
    }

    /**
     * Extracts the <b>message ID</b> from the provided Telegram callback
     *
     * @param update The Telegram update containing a callback
     *
     * @return A whole number of the <b>long</b> primitive type
     */
    private long getCallbackMessageId(@NotNull Update update) {
        return update.getCallbackQuery().getMessage().getMessageId();
    }

    /**
     * Retrieves <b>the text</b> from the message in the provided Telegram update
     *
     * @param update The Telegram update containing a message
     *
     * @return The text of the <b>String</b> type
     */
    private String getMessageText(@NotNull Update update) {
        return update.getMessage().getText();
    }
//</editor-fold>
}
