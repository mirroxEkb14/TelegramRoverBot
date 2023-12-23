package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.utils.UserMood;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
//</editor-fold>

/**
 * This <b>IInlineKeyboardBuilder</b> interface declares a set of basic operations for creating custom inline
 * keyboards
 */
public interface IInlineKeyboardBuilder {

    /**
     * Builds and returns <b>the mood selection inline keyboard</b> containing the following buttons:
     * <ol>
     * <li> 🌧 Depressed
     * <li> 💃 Сheerful
     * <li> 😼 Fighting
     * <li> 👨‍👩‍👧‍👦 Family
     * <li> 🍻 Friends
     * <li> 💋 Love
     * </ol>
     * This keyboard is triggered when the <b>🎬 Smart search</b> button from <b>the main menu keyboard</b> was clicked
     * on
     *
     * @return Configured instance of <b>InlineKeyboardMarkup</b>
     *
     * @see UserMood
     */
    InlineKeyboardMarkup buildMoodKeyboard();
}
