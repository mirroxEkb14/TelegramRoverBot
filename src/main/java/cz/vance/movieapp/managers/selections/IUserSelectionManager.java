package cz.vance.movieapp.managers.selections;

import cz.vance.movieapp.models.UserSelection;

/**
 * Declares a set of methods for managing the user's selections (mood, catalogue, genre) during the <b>smart search</b>.
 */
public interface IUserSelectionManager {

    /**
     * Initializes the user's selections for the <b>smart search</b> based on the chat ID.
     * <br>
     * Is called when the user starts the <b>smart search</b> by pressing the <b>smart search reply keyboard button</b>.
     *
     * @param chatId whole non-negative number that uniquely identifies the chat.
     */
    void initializeUserSelection(long chatId);

    /**
     * Puts the selected user's mood into the user's selections.
     * <br>
     * Is called when the user presses one of the <b>user mood buttons</b>.
     *
     * @param chatId whole non-negative number that uniquely identifies the chat.
     * @param mood the user's mood selection.
     */
    void putMood(long chatId, String mood);

    /**
     * Puts the selected catalogue into the user's selections.
     * <br>
     * Is called when the user presses one of the <b>catalogue buttons</b>.
     *
     * @param chatId whole non-negative number that uniquely identifies the chat.
     * @param catalogue the catalogue selection.
     */
    void putCatalogue(long chatId, String catalogue);

    /**
     * Puts the selected genre into the user's selections.
     * <br>
     * Is called when the user presses one of the <b>genre buttons</b>.
     *
     * @param chatId whole non-negative number that uniquely identifies the chat.
     * @param genre the genre selection.
     */
    void putGenre(long chatId, String genre);

    /**
     * Returns the user's selections for the <b>smart search</b>.
     * <br>
     * Is called when the user presses one of the <b>genre buttons</b> (the last step of the <b>smart search</b>).
     *
     * @param chatId whole non-negative number that uniquely identifies the chat.
     *
     * @return {@link UserSelection} object holding user's selections during <b>smart search</b>.
     */
    UserSelection getUserSelection(long chatId);

    /**
     * Removes the user's selection object by the <b>key</b>.
     * <br>
     * Is called when the user finishes the <b>smart search</b>.
     *
     * @param chatId whole non-negative number that uniquely identifies the chat (the <b>key</b> for the map).
     */
    void removeUserSelection(long chatId);
}
