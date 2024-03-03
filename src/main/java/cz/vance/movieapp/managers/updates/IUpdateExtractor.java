package cz.vance.movieapp.managers.updates;

import cz.vance.movieapp.utils.UserMood;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * This <b>IUpdateExtractor</b> interface declares a set of basic operations for extracting information from an
 * incoming Telegram update
 *
 * @see Update
 */
public interface IUpdateExtractor {

    /**
     * Extracts the <b>chat ID</b> from the message
     *
     * @param update The <b>update</b> object
     *
     * @return A whole number of the <b>long</b> primitive type
     */
    long getMessageChatId(@NotNull Update update);

    /**
     * Retrieves <b>the text</b> from the message
     *
     * @param update The <b>update</b> object
     *
     * @return The text of the <b>String</b> type
     */
    String getMessageText(@NotNull Update update);

    /**
     * Extracts the <b>chat ID</b> from the provided callback
     *
     * @param update The <b>update</b> object
     *
     * @return A whole number of the <b>long</b> primitive type
     */
    long getCallbackChatId(@NotNull Update update);

    /**
     * Extracts the <b>message ID</b> from the provided callback
     *
     * @param update The <b>update</b> object
     *
     * @return A whole number of the <b>long</b> primitive type
     */
    long getCallbackMessageId(@NotNull Update update);

    /**
     * Checks if this update contains <b>callback data</b>
     *
     * @param update The <b>update</b> object
     *
     * @return <b>true</b> if this update contains callback data, <b>false</b> otherwise
     */
    boolean isCallbackQuery(@NotNull Update update);

    /**
     * Extracts the <b>callback query text</b> from an incoming update
     *
     * @param update The incoming <b>update</b> object
     *
     * @return A <b>string</b> object containing the callback text
     *
     * @see UserMood#getCallback()
     */
    String getCallbackQuery(@NotNull Update update);
}
