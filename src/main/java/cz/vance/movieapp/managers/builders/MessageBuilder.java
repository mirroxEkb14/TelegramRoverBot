package cz.vance.movieapp.managers.builders;

//<editor-fold default-state="collapsed" desc="Imports">
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//</editor-fold>

/**
 * This <b>MessageBuilder</b> class implements a set of basic operations for building Telegram messages
 *
 * @see IMessageBuilder
 */
public final class MessageBuilder implements IMessageBuilder {

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
    public @NotNull SendMessage buildTelegramMessage(long chatId,
                                                     String messageText,
                                                     ReplyKeyboardMarkup keyboardMarkup) {
        final SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        message.setReplyMarkup(keyboardMarkup);
        message.setParseMode(PARSE_MODE);
        return message;
    }

    @Override
    public @NotNull SendMessage buildTelegramMessage(long chatId,
                                                     String messageText,
                                                     InlineKeyboardMarkup keyboardMarkup) {
        final SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageText);
        message.setReplyMarkup(keyboardMarkup);
        message.setParseMode(PARSE_MODE);
        return message;
    }

    @Override
    public @NotNull EditMessageText buildTelegramMessage(long callbackChatId,
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
}
