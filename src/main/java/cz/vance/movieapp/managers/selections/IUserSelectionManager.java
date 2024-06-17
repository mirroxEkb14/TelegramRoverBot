package cz.vance.movieapp.managers.selections;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.UserSelection;
import cz.vance.movieapp.utils.MainMenuButton;
import cz.vance.movieapp.utils.UserMood;
import cz.vance.movieapp.utils.Catalogue;
import cz.vance.movieapp.utils.Genre;
//</editor-fold>

/**
 * Declares a set of methods for managing the user's selections (mood, catalogue, genre) during the <b>smart search</b>.
 */
public interface IUserSelectionManager {

    /**
     * Initializes the user's selections for the <b>smart search</b> based on the chat ID.
     * <br>
     * Is called when the user starts the <b>smart search</b> by pressing the {@link MainMenuButton#SMART_SEARCH_BUTTON}.
     *
     * @param chatId Whole non-negative number that uniquely identifies the chat.
     */
    void initializeUserSelection(long chatId);

    /**
     * Puts the selected user's mood into the user's selections.
     * <br>
     * Is called when the user presses one of the {@link UserMood} buttons.
     *
     * @param chatId Whole non-negative number that uniquely identifies the chat.
     * @param mood The user's mood selection.
     */
    void putMood(long chatId, String mood);

    /**
     * Puts the selected catalogue into the user's selections.
     * <br>
     * Is called when the user presses one of the {@link Catalogue} buttons.
     *
     * @param chatId Whole non-negative number that uniquely identifies the chat.
     * @param catalogue The catalogue selection.
     */
    void putCatalogue(long chatId, String catalogue);

    /**
     * Puts the selected genre into the user's selections.
     * <br>
     * Is called when the user presses one of the {@link Genre} buttons.
     *
     * @param chatId Whole non-negative number that uniquely identifies the chat.
     * @param genre The genre selection.
     */
    void putGenre(long chatId, String genre);

    /**
     * Returns the user's selections for the <b>smart search</b>.
     * <br>
     * Is called when the user presses one of the {@link Genre} buttons (the last step of the <b>smart search</b>).
     *
     * @param chatId Whole non-negative number that uniquely identifies the chat.
     *
     * @return The <b>UserSelection</b> object holding user's selections during <b>smart search</b>.
     */
    UserSelection getUserSelection(long chatId);
}
