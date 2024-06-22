package cz.vance.movieapp.managers.builders;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.UserPhoto;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//</editor-fold>

/**
 * Declares a set of basic operations that build Telegram messages (<b>SendMessage</b> objects).
 */
public interface IMessageBuilder {

    String PARSE_MODE = "HTML";

    /**
     * Builds a message with the specified <b>chat id</b> and <b>message text</b>.
     *
     * @param chatId Whole non-negative number representing the chat identifier.
     * @param messageText String containing the message text.
     *
     * @return Configured <b>SendMessage</b> instance.
     */
    @NotNull SendMessage buildTelegramMessage(long chatId,
                                              String messageText);

    /**
     * Builds a sticker with the specified <b>chat id</b> and <b>sticker file id</b>.
     *
     * @param chatId Whole non-negative number representing the chat identifier.
     * @param stickerFileId String containing the sticker file identifier.
     *
     * @return Configured <b>SendSticker</b> instance.
     */
    @NotNull SendSticker buildTelegramSticker(long chatId,
                                              String stickerFileId);

    /**
     * Builds a photo with the specified <b>chat id</b> and the <b>user photo</b> object containing all the necessary
     * data about the photo.
     */
    @NotNull SendPhoto buildTelegramPhoto(long chatId,
                                          @NotNull UserPhoto userPhoto);

    /**
     * Builds a message with the specified <b>chat id</b>, <b>message text</b> and <b>reply keyboard</b>.
     *
     * @param chatId Whole non-negative number representing the chat identifier.
     * @param messageText String containing the text.
     * @param keyboardMarkup Reply keyboard instance.
     *
     * @return Configured <b>SendMessage</b> instance with custom buttons (keyboard).
     */
    @NotNull SendMessage buildTelegramMessage(long chatId,
                                              String messageText,
                                              ReplyKeyboardMarkup keyboardMarkup);

    /**
     * Builds a message with the specified <b>chat id</b>, <b>message text</b> and <b>inline keyboard</b>.
     *
     * @param chatId Whole non-negative number representing the chat identifier.
     * @param messageText String containing the text.
     * @param keyboardMarkup Inline keyboard instance.
     *
     * @return Configured <b>SendMessage</b> instance with a keyboard.
     */
    @NotNull SendMessage buildTelegramMessage(long chatId,
                                              String messageText,
                                              InlineKeyboardMarkup keyboardMarkup);

    /**
     * Builds an edited message with the specified <b>chat id</b>, <b>message id</b>, <b>message text</b> and
     * <b>inline keyboard</b>.
     *
     * @param callbackChatId Whole non-negative number representing the chat id.
     * @param callbackMessageId Whole non-negative number representing the message identifier.
     * @param messageText String containing the text.
     * @param keyboardMarkup Inline keyboard instance.
     *
     * @return Configured <b>EditMessageText</b> instance with custom keyboard.
     */
    @NotNull EditMessageText buildTelegramMessage(long callbackChatId,
                                                  long callbackMessageId,
                                                  String messageText,
                                                  InlineKeyboardMarkup keyboardMarkup);
}
