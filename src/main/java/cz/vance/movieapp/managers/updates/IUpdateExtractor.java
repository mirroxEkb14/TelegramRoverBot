package cz.vance.movieapp.managers.updates;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.exceptions.MovieRatingFormatException;
import cz.vance.movieapp.models.MovieRating;
import cz.vance.movieapp.models.User;
import cz.vance.movieapp.models.UserPhoto;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;
import java.util.function.Function;
//</editor-fold>

/**
 * Declares a set of basic operations for extracting information from an incoming bot update.
 */
public interface IUpdateExtractor {

    Function<Message, Boolean> messagePresenceChecker = Objects::nonNull;
    String LINE_SEPARATOR = "\n";
    String LINE_FORMAT_SEPARATOR = "\n\n";
    int DEFAULT_ID = -1;

    /**
     * Extracts the <b>chat id</b> from a message.
     * <br>
     * In case the <b>message chat id</b> is <b>null</b>, the method checks the <b>callback message chat id</b>. If it
     * is also <b>null</b>, the method returns the <b>default id</b>.
     *
     * @param update The bot's <b>update</b> object.
     *
     * @return A whole non-negative number of the <b>long</b> primitive type.
     */
    long getMessageChatId(@NotNull Update update);

    /**
     * Extracts the <b>message id</b> from a message.
     *
     * @param update The bot's <b>update</b> object.
     *
     * @return A whole non-negative number of the <b>long</b> primitive type.
     */
    int getMessageId(@NotNull Update update);

    /**
     * Extracts the <b>user id</b> from a message.
     *
     * @param update The bot's <b>update</b> object.
     *
     * @return A whole non-negative number of the <b>long</b> primitive type.
     */
    long getUserId(@NotNull Update update);

    /**
     * Extracts the <b>db user</b> from a message.
     *
     * @param update The bot's <b>update</b> object.
     *
     * @return The user of the {@link User} type.
     */
    @NotNull User getDBUserFromUpdate(@NotNull Update update);

    /**
     * Extracts the <b>first name</b> of the user from a message.
     *
     * @param update The bot's <b>update</b> object.
     *
     * @return The first name of the user of the <b>String</b> type.
     */
    String getUserFirstName(@NotNull Update update);

    /**
     * Extracts the <b>last name</b> of the user from a message.
     *
     * @param update The bot's <b>update</b> object.
     *
     * @return The last name of the user of the <b>String</b> type.
     */
    String getUserLastName(@NotNull Update update);

    /**
     * Retrieves the <b>text</b> from a message.
     *
     * @param update The <b>update</b> object.
     *
     * @return The text of the <b>String</b> type.
     */
    String getMessageText(@NotNull Update update);

    /**
     * Extracts the <b>callback message text</b> from a message.
     *
     * @param update The <b>update</b> object to extract the callback message from.
     *
     * @return <b>String</b> object containing the callback message text.
     */
    String getCallbackMessageText(@NotNull Update update);

    /**
     * Extracts the <b>send feedback callback message text</b> from a message that represents the user's feedback message.
     * <br>
     * As the message also contains the bot's default message, the method extracts only user's feedback message.
     *
     * @param update The <b>update</b> object to extract the callback message from.
     *
     * @return <b>String</b> object containing the callback message text.
     */
    @NotNull String getSendFeedbackCallbackMessageText(@NotNull Update update);

    /**
     * Extracts the <b>movie rating</b> from a message.
     *
     * @throws MovieRatingFormatException if the user entered the rating in the wrong format.
     */
    @NotNull MovieRating getMovieRatingCallbackObject(@NotNull Update update) throws MovieRatingFormatException;

    /**
     * Extracts the <b>sticker file id</b> from a message.
     *
     * @param update The <b>update</b> object to extract the sticker from.
     *
     * @return <b>String</b> object containing the identifier.
     */
    String getStickerFileId(@NotNull Update update);

    /**
     * Forms a {@link UserPhoto} object from the incoming update.
     * <br>
     * It is the biggest photo from the array with photo objects with different sizes.
     *
     * @param update The <b>update</b> object to extract the photo from.
     *
     * @return {@link UserPhoto} object containing all the necessary data of the photo that was sent to the bot.
     */
    @NotNull UserPhoto getUserPhoto(@NotNull Update update);

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
