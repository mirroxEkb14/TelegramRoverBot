package cz.vance.movieapp.utils.columns;

/**
 * Contains column names of the <b>movies table</b>.
 */
public enum MoviesColumnLabel {
    ID("id"),
    RUS_NAME("rus_name"),
    RUS_MOOD("rus_mood"),
    RUS_CATALOGUE("rus_catalogue"),
    RUS_GENRE("rus_genre"),
    RELEASE_YEAR("release_year"),
    RUS_DIRECTOR("rus_director"),
    RUS_CAST("rus_cast"),
    RUS_STORYLINE("rus_storyline"),
    RUS_LINK("rus_link"),
    ENG_NAME("eng_name"),
    ENG_MOOD("eng_mood"),
    ENG_CATALOGUE("eng_catalogue"),
    ENG_GENRE("eng_genre"),
    ENG_DIRECTOR("eng_director"),
    ENG_CAST("eng_cast"),
    ENG_STORYLINE("eng_storyline"),
    ENG_LINK("eng_link"),
    DETAILS_LINK("details_link"),
    POSTER_LINK("poster_link");

    private final String content;

    MoviesColumnLabel(String content) { this.content = content; }

    public String getContent() { return content; }
}
