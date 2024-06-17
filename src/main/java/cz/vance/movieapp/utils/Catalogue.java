package cz.vance.movieapp.utils;

/**
 * Contains texts for the <b>movies/series selection</b> buttons.
 * <br>
 * The keyboard with these texts is triggered when one of the <b>user mood buttons</b> was clicked.
 *
 * @see cz.vance.movieapp.keyboards.InlineKeyboardBuilder
 * @see UserMood
 */
public enum Catalogue {
    MOVIE_BUTTON("\ud83d\udcfc Movie"),
    SERIES_BUTTON("\ud83c\udf9e\ufe0f Series");

    private final String content;

    Catalogue(String content) { this.content = content; }

    public String getContent() { return content; }
}
