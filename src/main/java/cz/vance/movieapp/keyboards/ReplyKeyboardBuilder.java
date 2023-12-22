package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.utils.ReplyButton;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 * This <b>ReplyKeyboardBuilder</b> class implements a set of basic operations for creating custom reply
 * keyboards
 *
 * @see IReplyKeyboardBuilder
 */
public final class ReplyKeyboardBuilder implements IReplyKeyboardBuilder {

    @Override
    public @NotNull ReplyKeyboardMarkup buildMainMenuKeyboard() {
        final ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);

        final List<KeyboardRow> keyboardRows = new ArrayList<>();
        final KeyboardRow firstRow = new KeyboardRow();
        final KeyboardRow secondRow = new KeyboardRow();

        firstRow.add(new KeyboardButton(
                ReplyButton.SMART_SEARCH_BUTTON.content()));
        firstRow.add(new KeyboardButton(
                ReplyButton.OUR_CHOICE_BUTTON.content()));
        firstRow.add(new KeyboardButton(
                ReplyButton.NO_IDEA_BUTTON.content()));
        secondRow.add(new KeyboardButton(
                ReplyButton.FEEDBACK_BUTTON.content()));

        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);

        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }
}
