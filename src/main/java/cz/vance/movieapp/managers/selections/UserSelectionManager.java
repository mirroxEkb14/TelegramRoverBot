package cz.vance.movieapp.managers.selections;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.UserSelection;

import java.util.HashMap;
import java.util.Map;
//</editor-fold>

/**
 * Implements a set of basic methods for managing the user's selections during the <b>smart search</b>.
 */
public final class UserSelectionManager implements IUserSelectionManager {

    /**
     * Stores user's selections during the <b>smart search</b>.
     */
    private final Map<Long, UserSelection> userSelections;

    //<editor-fold default-state="collapsed" desc="Singleton">
    private static UserSelectionManager instance;

    private UserSelectionManager() { userSelections = new HashMap<>(); }

    public static UserSelectionManager getInstance() {
        if (instance == null)
            return new UserSelectionManager();
        return instance;
    }
    //</editor-fold>

    @Override
    public void initializeUserSelection(long chatId) { userSelections.put(chatId, new UserSelection()); }

    @Override
    public void putMood(long chatId, String mood) { userSelections.get(chatId).setMood(mood); }

    @Override
    public void putCatalogue(long chatId, String catalogue) { userSelections.get(chatId).setCatalogue(catalogue); }

    @Override
    public void putGenre(long chatId, String genre) { userSelections.get(chatId).setGenre(genre); }

    @Override
    public UserSelection getUserSelection(long chatId) { return userSelections.get(chatId); }
}
