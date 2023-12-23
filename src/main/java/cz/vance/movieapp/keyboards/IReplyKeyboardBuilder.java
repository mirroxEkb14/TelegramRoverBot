package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import cz.vance.movieapp.utils.MainMenuButton;
//</editor-fold>

/**
 * This <b>IReplyKeyboardBuilder</b> interface declares a set of basic operations for creating custom reply
 * keyboards
 */
public interface IReplyKeyboardBuilder {

    /**
     * Builds and returns <b>the main menu reply keyboard</b> containing the following buttons:
     * <ol>
     * <li> 🎬 Smart search
     * <li> 🎯 Our choice
     * <li> 👀 No idea
     * <li> 🎭 Feedback
     * </ol>
     *
     * @return Configured instance of <b>ReplyKeyboardMarkup</b>
     *
     * @see MainMenuButton
     */
    ReplyKeyboardMarkup buildMainMenuKeyboard();
}
