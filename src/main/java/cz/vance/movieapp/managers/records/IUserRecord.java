package cz.vance.movieapp.managers.records;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.User;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;
import cz.vance.movieapp.managers.updates.IUpdateExtractor;
import cz.vance.movieapp.managers.messages.IMessageManager;

import java.time.format.DateTimeFormatter;
import java.util.List;
//</editor-fold>

/**
 * Declares a set of methods for interacting with <b>users table</b> from the DB.
 */
public interface IUserRecord {

    DateTimeFormatter USER_TABLE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Checks if the user is already in the DB.
     *
     * @param tgId the <b>telegram id</b> of the user to be inserted.
     *
     * @return <b>true</b> if the user is in the DB, <b>false</b> otherwise.
     */
    boolean isUser(int tgId);

    /**
     * Inserts a new user into the DB.
     *
     * @param user the user to be inserted.
     */
    void insertUser(User user);

    /**
     * Inserts a new user into the DB if he does not exist.
     * <br>
     * Is used in all the mode methods ({@link IMessageManager#sendSmartSearchMood}, etc.), because there is a chance that
     * some user already has a chat with bot, so it wouldn't be effective to call the method only in the
     * {@link IMessageManager#sendWelcome(Update)} method.
     *
     * @param botUpdate the update from the bot to extract the {@link User} object using {@link IUpdateExtractor#getDBUserFromUpdate(Update)}.
     */
    void insertUserIfNotExists(Update botUpdate);

    /**
     * Retrieves all the users from the DB.
     *
     * @return <b>List</b> of all the users.
     */
    @NotNull List<User> getUsers();
}
