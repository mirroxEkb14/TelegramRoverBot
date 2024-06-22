package cz.vance.movieapp.managers.builders;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.UserPhoto;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//</editor-fold>

/**
 * Implements a set of basic operations for building Telegram messages (<b>SendMessage</b> objects).
 */
public final class MessageBuilder implements IMessageBuilder {

    /**
     * Alternative implementation using <b>builder</b>:
     * <pre>{@code
     *      return SendMessage
     *                 .builder()
     *                 .chatId(chatId)
     *                 .text(messageText)
     *                 .parseMode(PARSE_MODE)
     *                 .build();
     * }</pre>
     */
    @Override
    public @NotNull SendMessage buildTelegramMessage(long chatId,
                                                     String messageText) {
        final SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        message.setParseMode(PARSE_MODE);
        return message;
    }

    @Override
    public @NotNull SendSticker buildTelegramSticker(long chatId,
                                                     String stickerFileId) {
        return SendSticker
                .builder()
                .chatId(chatId)
                .sticker(new InputFile(stickerFileId))
                .build();
    }

    @Override
    public @NotNull SendPhoto buildTelegramPhoto(long chatId,
                                                 @NotNull UserPhoto userPhoto) {
        return SendPhoto
                .builder()
                .chatId(chatId)
                .photo(new InputFile(userPhoto.fileId()))
                .caption(userPhoto.outputCaption())
                .parseMode(PARSE_MODE)
                .build();
    }

    @Override
    public @NotNull SendMessage buildTelegramMessage(long chatId,
                                                     String messageText,
                                                     ReplyKeyboardMarkup keyboardMarkup) {
        return SendMessage
                .builder()
                .chatId(chatId)
                .text(messageText)
                .replyMarkup(keyboardMarkup)
                .parseMode(PARSE_MODE)
                .build();
    }

    @Override
    public @NotNull SendMessage buildTelegramMessage(long chatId,
                                                     String messageText,
                                                     InlineKeyboardMarkup keyboardMarkup) {
        return SendMessage
                .builder()
                .chatId(chatId)
                .text(messageText)
                .replyMarkup(keyboardMarkup)
                .parseMode(PARSE_MODE)
                .build();
    }

    @Override
    public @NotNull EditMessageText buildTelegramMessage(long callbackChatId,
                                                         long callbackMessageId,
                                                         String messageText,
                                                         InlineKeyboardMarkup keyboardMarkup) {
        return EditMessageText
                .builder()
                .chatId(callbackChatId)
                .messageId(Math.toIntExact(callbackMessageId))
                .text(messageText)
                .replyMarkup(keyboardMarkup)
                .parseMode(PARSE_MODE)
                .build();
    }
}
