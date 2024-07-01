package cz.vance.movieapp.utils.columns;

/**
 * Holds the column labels for the <b>movieRates</b> table in the DB.
 */
public enum MovieRatesColumnLabel {
    ID("id"),
    TG_ID("tgId"),
    RATING_DATE("ratingDate"),
    MOVIE_NAME("movieName"),
    MODE_NAME("modeName"),
    RATING_TEXT("ratingText"),
    SMART_SEARCH_RATING_TEXT("smartSearchRatingText");

    private final String content;

    MovieRatesColumnLabel(String content) { this.content = content; }

    public String getContent() { return content; }
}
