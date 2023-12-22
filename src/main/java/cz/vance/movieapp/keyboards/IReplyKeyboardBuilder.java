package cz.vance.movieapp.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import cz.vance.movieapp.utils.ReplyButton;

/**
 * This <b>IReplyKeyboardBuilder</b> interface declares a set of basic operations for creating custom reply
 * keyboards
 */
public interface IReplyKeyboardBuilder {

    /**
     * Builds and returns the main menu reply keyboard containing the following buttons:
     * <ol>
     * <li> 🎬 Smart search
     * <li> 🎯 Our choice
     * <li> 👀 No idea
     * <li> 🎭 Feedback
     * </ol>
     *
     * @return Configured instance of <b>ReplyKeyboardMarkup</b>
     *
     * @see ReplyButton
     */
    ReplyKeyboardMarkup buildMainMenuKeyboard();
}
