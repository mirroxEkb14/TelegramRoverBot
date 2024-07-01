package cz.vance.movieapp.managers.records;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.database.CinemaDatabase;
import cz.vance.movieapp.database.ICinemaDatabase;
import cz.vance.movieapp.managers.updates.IUpdateExtractor;
import cz.vance.movieapp.managers.updates.UpdateExtractor;
import cz.vance.movieapp.models.User;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 * Implements basic methods for interacting with the <b>users table</b> from the DB.
 */
public final class UserRecord implements IUserRecord {

    private final ICinemaDatabase cinemaDB;
    /**
     * Is used in the {@link #insertUserIfNotExists(Update)} method to extract the {@link User} object from the
     * incoming {@link Update} object.
     */
    private final IUpdateExtractor updateExtractor;

    /**
     * Contains <b>all the users</b> from the DB.
     */
    private static final List<User> users = new ArrayList<>();

    //<editor-fold default-state="collapsed" desc="Singleton">
    private static UserRecord instance;

    private UserRecord() {
        cinemaDB = CinemaDatabase.getInstance();
        updateExtractor = new UpdateExtractor();
        loadUsers();
    }

    public static UserRecord getInstance() {
        if (instance == null)
            return new UserRecord();
        return instance;
    }
    //</editor-fold>

    private void loadUsers() {
        if (users.isEmpty())
            users.addAll(cinemaDB.getUsers());
    }

    @Override
    public boolean isUser(int tdId) { return users.stream().anyMatch(user -> user.getTgId() == tdId); }

    @Override
    public void insertUser(User user) {
        cinemaDB.insertUser(user);
        users.add(user);
    }

    @Override
    public void insertUserIfNotExists(Update botUpdate) {
        final int tgId = (int)updateExtractor.getUserId(botUpdate);
        if (!isUser(tgId))
            insertUser(
                    updateExtractor.getDBUserFromUpdate(botUpdate));
    }

    @Override
    public @NotNull List<User> getUsers() { return new ArrayList<>(users); }

    @Override
    public @NotNull List<Integer> getTgIds() {
        final List<Integer> tgIds = new ArrayList<>();
        users.forEach(user -> tgIds.add(user.getTgId()));
        return tgIds;
    }
}
