package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
//</editor-fold>

/**
 * Declares a set of basic operations for creating custom reply keyboards.
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
     * @return Configured instance of the <b>ReplyKeyboardMarkup</b>.
     */
    ReplyKeyboardMarkup buildMainMenuKeyboard();
}
