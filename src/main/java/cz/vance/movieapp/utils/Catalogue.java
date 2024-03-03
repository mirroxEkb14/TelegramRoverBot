package cz.vance.movieapp.utils;

import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;

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

    private final String content;

    Catalogue(String content) { this.content = content; }

    public String content() { return content; }
}
