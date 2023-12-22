package cz.vance.movieapp.managers;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;
import cz.vance.movieapp.keyboards.ReplyKeyboardBuilder;
import cz.vance.movieapp.utils.BotMessage;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
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

    private static final String PARSE_MODE = "HTML";
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
        final long chatId = getChatId(botUpdate);
        final String messageText = getMessageText(botUpdate);
        sendEcho(chatId, messageText);
    }

    @Override
    public void sendWelcome(Update botUpdate) {
        final long chatId = getChatId(botUpdate);
        final String messageText = getFormattedWelcomeText(botUpdate);
        sendWelcome(chatId, messageText);
    }

//<editor-fold default-state="collapsed" desc="Welcome Message">
    /**
     * Sends a welcome message to the specified <b>chat ID</b> with the given <b>message text</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageText A string containing the text
     */
    private void sendWelcome(long chatId, String messageText) {
        try {
            bot.execute(buildTelegramMessage(chatId,
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
                botUpdate.getMessage().getFrom().getFirstName(),
                bot.getBotUsername());
    }
//</editor-fold>

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

//<editor-fold default-state="collapsed" desc="Message Builders">
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
     * Extracts the <b>chat ID</b> from the provided Telegram update
     *
     * @param update The Telegram update containing a message
     *
     * @return A whole number of the <b>long</b> primitive type
     */
    private long getChatId(@NotNull Update update) {
        return update.getMessage().getChatId();
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
