package cz.vance.movieapp.managers.records;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.MovieRating;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.List;
//</editor-fold>

/**
 * Declares a set of methods for managing <b>movie ratings</b> from the appropriate DB table.
 */
public interface IMovieRatingRecord {

    DateTimeFormatter MOVIE_RATINGS_TABLE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Inserts a new movie rating into the DB.
     *
     * @param movieRate the <b>movie rating</b> object to be inserted.
     */
    void insertMovieRating(MovieRating movieRate);

    /**
     * Retrieves all the movie ratings from the DB.
     *
     * @return <b>List</b> of all the movie ratings.
     */
    @NotNull List<MovieRating> getMovieRatings();
}
