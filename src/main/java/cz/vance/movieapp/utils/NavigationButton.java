package cz.vance.movieapp.utils;

import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * This <b>NavigationButton</b> enum class contains the text for <b>back/next</b> buttons
 * <p>
 * These texts are for buttons in <b>InlineKeyboardBuilder</b>
 *
 * @see InlineKeyboardBuilder
 */
public enum NavigationButton {
    LEFT_ARROW("«"),
    RIGHT_ARROW("»");

//<editor-fold default-state="collapsed" desc="Callback Data">
    /**
     * The <b>callback data</b> is a piece of information that will be sent to the bot when the user interacts with
     * the button, which is typically used to identify which button was pressed
     */
    private static final String CALLBACK_PREFIX = "_cb";

    public @NotNull String getCallback() {
        return this + CALLBACK_PREFIX;
    }
//</editor-fold>

    private final String content;

    NavigationButton(String content) { this.content = content; }

    public String content() { return content; }
}
