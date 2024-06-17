package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.utils.MainMenuButton;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 * Implements a set of basic operations for creating custom reply keyboards.
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
                MainMenuButton.SMART_SEARCH_BUTTON.getContent()));
        firstRow.add(new KeyboardButton(
                MainMenuButton.OUR_CHOICE_BUTTON.getContent()));
        firstRow.add(new KeyboardButton(
                MainMenuButton.NO_IDEA_BUTTON.getContent()));
        secondRow.add(new KeyboardButton(
                MainMenuButton.FEEDBACK_BUTTON.getContent()));

        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);

        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }
}
