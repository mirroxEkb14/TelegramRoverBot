package cz.vance.movieapp.managers.selections;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.managers.CallbackQueryExtractor;
import cz.vance.movieapp.models.UserSelection;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
//</editor-fold>

/**
 * Implements a set of basic methods for managing the user's selections during the <b>smart search</b>.
 */
public final class UserSelectionManager implements IUserSelectionManager {

    /**
     * Stores user's selections during the <b>smart search</b>.
     */
    private final Map<Long, UserSelection> userSelections;
    /**
     * Function extracts the text from the button that contains not only the text, but also an emoji with a white space
     * between.
     */
    private final Function<String, String> emojiExtractor = (buttonText) ->
        buttonText.substring(buttonText.indexOf(" ") + 1);

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
    public void putMood(long chatId, String mood) {
        final String moodTextWithEmoji = CallbackQueryExtractor.extractCallbackQueryPrefix(mood);
        final String moodText = emojiExtractor.apply(moodTextWithEmoji);
        userSelections.get(chatId).setMood(moodText);
    }

    @Override
    public void putCatalogue(long chatId, String catalogue) {
        final String catalogueTextWithEmoji = CallbackQueryExtractor.extractCallbackQueryPrefix(catalogue);
        final String catalogueText = emojiExtractor.apply(catalogueTextWithEmoji);
        userSelections.get(chatId).setCatalogue(catalogueText);
    }

    @Override
    public void putGenre(long chatId, String genre) {
        final String genreTextWithEmoji = CallbackQueryExtractor.extractCallbackQueryPrefix(genre);
        final String genreText = emojiExtractor.apply(genreTextWithEmoji);
        userSelections.get(chatId).setGenre(genreText);
    }

    @Override
    public UserSelection getUserSelection(long chatId) { return userSelections.get(chatId); }

    @Override
    public void removeUserSelection(long chatId) { userSelections.remove(chatId); }
}
