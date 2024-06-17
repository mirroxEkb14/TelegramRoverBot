package cz.vance.movieapp.utils;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.keyboards.IInlineKeyboardBuilder;
import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;
import org.jetbrains.annotations.NotNull;
//</editor-fold>

/**
 * Contains texts for the <b>back/next</b> buttons.
 *
 * @see InlineKeyboardBuilder
 */
public enum NavigationButton {
    LEFT_ARROW("\ud83c\udf00"), // "«" left-pointing double angle quotation mark
    RIGHT_ARROW("\ud83c\udf01"); // "»" right-pointing double angle quotation mark

    private final String content;

    NavigationButton(String content) { this.content = content; }

    public String getContent() { return content; }

    public @NotNull String getCallback() { return this + IInlineKeyboardBuilder.CALLBACK_PREFIX; }
}
