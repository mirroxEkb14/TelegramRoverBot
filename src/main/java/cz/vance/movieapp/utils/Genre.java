package cz.vance.movieapp.utils;

import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;

/**
 * This <b>Genre</b> enum class contains the text for buttons of <b>genre selection</b>
 * <p>
 * The keyboard with these texts is triggered when one of the <b>Catalogue buttons</b> was clicked on
 * <p>
 * These texts are for buttons in <b>InlineKeyboardBuilder</b>
 *
 * @see InlineKeyboardBuilder
 * @see Catalogue
 */
public enum Genre {
    COMEDY_BUTTON("\ud83d\udd7a Comedy"),
    DRAMA_BUTTON("\ud83d\udc94 Drama"),
    MELODRAMA_BUTTON("\ud83d\udc98 Melodrama"),
    THRILLER_BUTTON("\ud83d\udd2b Thriller"),
    ACTION_BUTTON("\ud83d\udd25 Action"),
    FICTION_BUTTON("\ud83e\uddd9\ud83c\udffb Fiction"),
    DETECTIVE_BUTTON("\ud83d\udd75\ud83c\udffc Detective"),
    FAMILY_BUTTON("\ud83d\udc68\u200d\ud83d\udc69\u200d\ud83d\udc66 Family"),
    SPORT_BUTTON("\ud83c\udfc0 Sport"),
    FANTASY_BUTTON("\ud83d\udd2e Fantasy"),
    ANIMATION_BUTTON("\ud83e\uddf8 Animation"),
    ADVENTURE_BUTTON("\ud83c\udf92 Adventure"),
    BIOGRAPHY_BUTTON("\ud83d\udcd6 Biography"),
    CRIMINAL_BUTTON("\ud83d\udc65 Criminal"),
    DOCUMENTARY_BUTTON("\ud83c\udfa5 Documentary"),
    WAR_BUTTON("\ud83e\ude96 War"),
    MUSIC_BUTTON("\ud83c\udfbb Music");

    private final String content;

    Genre(String content) { this.content = content; }

    public String content() { return content; }
}
