package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.managers.messages.BotMessageManager;
import cz.vance.movieapp.managers.messages.IBotMessageManager;
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

    private final IBotMessageManager botMessageManager = BotMessageManager.getInstance();

    @Override
    public @NotNull ReplyKeyboardMarkup buildMainMenuKeyboard() {
        final ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);

        final List<KeyboardRow> keyboardRows = new ArrayList<>();
        final KeyboardRow firstRow = new KeyboardRow();
        final KeyboardRow secondRow = new KeyboardRow();

        firstRow.add(new KeyboardButton(
                botMessageManager.getSmartSearchReplyButton()));
        firstRow.add(new KeyboardButton(
                botMessageManager.getOurChoiceReplyButton()));
        firstRow.add(new KeyboardButton(
                botMessageManager.getNoIdeasReplyButton()));
        secondRow.add(new KeyboardButton(
                botMessageManager.getFeedbackReplyButton()));

        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);

        keyboardMarkup.setKeyboard(keyboardRows);
        return keyboardMarkup;
    }
}
