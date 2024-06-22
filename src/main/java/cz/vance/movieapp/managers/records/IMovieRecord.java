package cz.vance.movieapp.managers.records;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.Movie;
import cz.vance.movieapp.models.UserSelection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
//</editor-fold>

/**
 * Declares a set of basic operations for managing the movies from the appropriate movie table from the DB
 */
public interface IMovieRecord {

    /**
     * Returns the list of movies that were sorted by the results of the last <b>smart search</b>.
     *
     * @return The <b>list</b> of movies.
     */
    @Unmodifiable List<Movie> getSmartSearchMovies(@NotNull UserSelection userSelection);

    /**
     * Moves to the next movie during the <b>no idea</b> feature.
     * <br>
     * If the end of the list is reached, it wraps around to the first movie.
     */
    void moveToNextNoIdeaMovie();

    /**
     * Moves to the previous movie during the <b>no idea</b> feature.
     * <br>
     * If the beginning of the list is reached, it wraps around to the last movie.
     */
    void moveToPreviousNoIdeaMovie();

    /**
     * Returns a <b>boolean value</b>, whether the next movie to be displayed to the user is the first movie from DB or not.
     */
    boolean isNoIdeaPreviousMovieFirst();

    /**
     * Returns a <b>boolean value</b>, whether the next movie to be displayed to the user is the last movie from DB or not.
     */
    boolean isNoIdeaNextMovieLast();

    /**
     * Returns the movie to be displayed to the user for the <b>no idea</b> feature.
     */
    Movie getCurrentNoIdeaMovie();
}
