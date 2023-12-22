package cz.vance.movieapp.managers;

//<editor-fold default-state="collapsed" desc="Imports">
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import cz.vance.movieapp.bot.TelegramRoverBot;

import java.util.function.Predicate;
//</editor-fold>

/**
 * This <b>MessageManager</b> class implements a set of basic operations for handling messages
 *
 * @see IMessageManager
 */
public final class MessageManager implements IMessageManager {

    /**
     * This <b>constant</b> represents a validator implemented by default Java functional interface {@link Predicate}
     * for incoming updates that checks:
     * <ol>
     * <li> if the update has a message;
     * <li> if the message has text;
     * </ol>
     */
    private static final Predicate<Update> updateValidator = t ->
            t.hasMessage() && t.getMessage().hasText();
    /**
     * This <b>constant</b> is an instance of the {@link TelegramRoverBot} class that is needed to send
     * messages
     */
    private final TelegramLongPollingBot bot;

    public MessageManager(TelegramLongPollingBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendEcho(Update botUpdate) {
        if (updateValidator.test(botUpdate)) {
            final long chatId = getChatId(botUpdate);
            final String messageText = getMessageText(botUpdate);
            echo(chatId, messageText);
        }
    }

    /**
     * Sends an echo message to the specified <b>chat ID</b> with the given <b>message text</b>
     *
     * @param chatId A whole number representing a chat ID
     * @param messageText A string containing the text
     */
    private void echo(long chatId, String messageText) {
        try {
            bot.execute(buildTelegramMessage(chatId, messageText));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

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
        return message;
    }

    /**
     * Extracts the chat ID from the provided Telegram update
     *
     * @param update The Telegram update containing a message
     *
     * @return A whole number of the <b>long</b> primitive type
     */
    private long getChatId(@NotNull Update update) {
        return update.getMessage().getChatId();
    }

    /**
     * Retrieves the text from the message in the provided Telegram update
     *
     * @param update The Telegram update containing a message
     *
     * @return The text of the <b>String</b> type
     */
    private String getMessageText(@NotNull Update update) {
        return update.getMessage().getText();
    }
}
