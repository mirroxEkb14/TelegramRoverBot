package cz.vance.movieapp.utils;

import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * This <b>Catalogue</b> enum class contains the text for buttons of <b>movies/series selection</b>
 * <p>
 * The keyboard with these texts is triggered when one of the <b>UserMood buttons</b> was clicked on
 * <p>
 * These texts are for buttons in <b>InlineKeyboardBuilder</b>
 *
 * @see InlineKeyboardBuilder
 * @see UserMood
 */
public enum Catalogue {
    MOVIE_BUTTON("\ud83d\udcfc Movie"),
    SERIES_BUTTON("\ud83c\udf9e\ufe0f Series");

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

    Catalogue(String content) { this.content = content; }

    public String content() { return content; }
}
