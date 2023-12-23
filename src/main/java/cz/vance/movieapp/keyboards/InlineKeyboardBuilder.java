package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.utils.UserMood;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
//</editor-fold>

/**
 * This <b>InlineKeyboardBuilder</b> class implements a set of basic operations for creating custom inline
 * keyboards
 *
 * @see IInlineKeyboardBuilder
 */
public final class InlineKeyboardBuilder implements IInlineKeyboardBuilder {

    /**
     * This <b>functional interface</b> builds an inline button:
     * <ol>
     * <li> sets the text;
     * <li> sets the callback data;
     * </ol>
     */
    private static final BiFunction<String, String, InlineKeyboardButton> inlineButtonBuilder = (text, callback) -> {
        final InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callback);
        return button;
    };

    @Override
    public @NotNull InlineKeyboardMarkup buildMoodKeyboard() {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> secondInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                UserMood.DEPRESSION_BUTTON.content(),
                UserMood.DEPRESSION_BUTTON.getCallback()));
        firstInlineRow.add(inlineButtonBuilder.apply(
                UserMood.CHEERFUL_BUTTON.content(),
                UserMood.CHEERFUL_BUTTON.getCallback()));
        firstInlineRow.add(inlineButtonBuilder.apply(
                UserMood.FIGHTING_BUTTON.content(),
                UserMood.FIGHTING_BUTTON.getCallback()));
        secondInlineRow.add(inlineButtonBuilder.apply(
                UserMood.FAMILY_BUTTON.content(),
                UserMood.FAMILY_BUTTON.getCallback()));
        secondInlineRow.add(inlineButtonBuilder.apply(
                UserMood.FRIENDS_BUTTON.content(),
                UserMood.FRIENDS_BUTTON.getCallback()));
        secondInlineRow.add(inlineButtonBuilder.apply(
                UserMood.LOVE_BUTTON.content(),
                UserMood.LOVE_BUTTON.getCallback()));

        inlineRows.add(firstInlineRow);
        inlineRows.add(secondInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }
}
