package cz.vance.movieapp.managers.builders;

//<editor-fold default-state="collapsed" desc="Imports">
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//</editor-fold>

/**
 * This <b>IMessageBuilder</b> interface declares a set of basic operations that build Telegram messages
 *
 * @see SendMessage
 * @see EditMessageText
 */
public interface IMessageBuilder {

    String PARSE_MODE = "HTML";

    /**
     * Builds a message with the specified <b>chat ID</b> and <b>message text</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageText A string containing the text
     *
     * @return A configured <b>SendMessage</b> instance with custom buttons
     */
    @NotNull SendMessage buildTelegramMessage(long chatId,
                                              String messageText);

    /**
     * Builds a message with the specified <b>chat ID</b>, <b>message text</b> and <b>reply keyboard</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageText A string containing the text
     * @param keyboardMarkup A reply keyboard instance
     *
     * @return A configured <b>SendMessage</b> instance with custom buttons
     */
    @NotNull SendMessage buildTelegramMessage(long chatId,
                                              String messageText,
                                              ReplyKeyboardMarkup keyboardMarkup);

    /**
     * Builds a message with the specified <b>chat ID</b>, <b>message text</b> and <b>inline keyboard</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageText A string containing the text
     * @param keyboardMarkup An inline keyboard instance
     *
     * @return A configured <b>SendMessage</b> instance with custom buttons
     */
    @NotNull SendMessage buildTelegramMessage(long chatId,
                                              String messageText,
                                              InlineKeyboardMarkup keyboardMarkup);

    /**
     * Builds an edited message with the specified <b>chat ID</b>, <b>message ID</b>, <b>message text</b> and
     * <b>inline keyboard</b>
     *
     * @param callbackChatId A whole number representing a chat ID
     * @param callbackMessageId A whole number representing a message ID
     * @param messageText A string containing the text
     * @param keyboardMarkup An inline keyboard instance
     *
     * @return A configured <b>EditMessageText</b> instance with custom buttons
     */
    @NotNull EditMessageText buildTelegramMessage(long callbackChatId,
                                                  long callbackMessageId,
                                                  String messageText,
                                                  InlineKeyboardMarkup keyboardMarkup);
}
