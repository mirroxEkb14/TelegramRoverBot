package cz.vance.movieapp.managers.updates;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cz.vance.movieapp.utils.UserMood;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;
//</editor-fold>

/**
 * Declares a set of basic operations for extracting information from an incoming bot update.
 */
public interface IUpdateExtractor {

    /**
     * Extracts the <b>chat id</b> from a message.
     *
     * @param update The bot's <b>update</b> object.
     *
     * @return A whole non-negative number of the <b>long</b> primitive type.
     */
    long getMessageChatId(@NotNull Update update);

    /**
     * Retrieves the <b>text</b> from a message.
     *
     * @param update The <b>update</b> object.
     *
     * @return The text of the <b>String</b> type.
     */
    String getMessageText(@NotNull Update update);

    /**
     * Extracts the <b>sticker file id</b> from a message.
     *
     * @param update The <b>update</b> object to extract the sticker from.
     *
     * @return <b>String</b> object containing the identifier.
     */
    String getStickerFileId(@NotNull Update update);

    /**
     * Extracts the <b>chat id</b> from the provided callback.
     *
     * @param update The <b>update</b> object.
     *
     * @return A whole non-negative number of the <b>long</b> primitive type.
     */
    long getCallbackChatId(@NotNull Update update);

    /**
     * Extracts the <b>message id</b> from the provided callback.
     *
     * @param update The <b>update</b> object.
     *
     * @return A whole non-negative number of the <b>long</b> primitive type.
     */
    long getCallbackMessageId(@NotNull Update update);

    /**
     * Checks if this update contains any <b>callback data</b>.
     *
     * @param update The <b>update</b> object.
     *
     * @return <b>true</b> if the incoming update contains some callback data; <b>false</b> otherwise.
     */
    boolean isCallbackQuery(@NotNull Update update);

    /**
     * Extracts the <b>callback query text</b> from the incoming update.
     *
     * @param update The incoming <b>update</b> object.
     *
     * @return A <b>string</b> object with the callback text.
     */
    String getCallbackQuery(@NotNull Update update);

    //<editor-fold defaultstate="collapsed" desc="Boolean Methods">
    static boolean hasText(@NotNull Update update) { return update.getMessage().getText() != null; }
    static boolean hasSticker(@NotNull Update update) { return update.getMessage().hasSticker(); }
    static boolean hasPhoto(@NotNull Update update) { return update.getMessage().hasPhoto(); }
    static boolean hasDocument(@NotNull Update update) { return update.getMessage().hasDocument(); }
    static boolean hasVideo(@NotNull Update update) { return update.getMessage().hasVideo(); }
    static boolean hasAudio(@NotNull Update update) { return update.getMessage().hasAudio(); }
    static boolean hasVoice(@NotNull Update update) { return update.getMessage().hasVoice(); }
    //</editor-fold>
}
