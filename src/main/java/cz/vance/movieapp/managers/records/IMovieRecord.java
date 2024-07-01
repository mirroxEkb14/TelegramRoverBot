package cz.vance.movieapp.managers.records;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.Movie;
import cz.vance.movieapp.models.UserSelection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
//</editor-fold>

/**
 * Declares a set of basic operations for <b>managing the movies</b> from the appropriate <b>movie table</b> from the DB.
 */
public interface IMovieRecord {

    /**
     * Returns the list of movies that were sorted by the results of the last <b>smart search</b>.
     *
     * @return <b>List</b> of movies.
     */
    @Unmodifiable List<Movie> getSmartSearchMovies(@NotNull UserSelection userSelection);

    /**
     * Moves to the next movie during the <b>no idea</b> feature.
     */
    void moveNoIdeaToNextMovie();

    /**
     * Moves to the previous movie during the <b>no idea</b> feature.
     * <br>
     * If the beginning of the list is reached, it wraps around to the last movie.
     */
    void moveNoIdeaToPreviousMovie();

    /**
     * @return  <b>Boolean value</b>, whether the next movie to be displayed to the user is the first movie from DB or not.
     */
    boolean isNoIdeaPreviousMovieFirst();

    /**
     * @return <b>Boolean value</b>, whether the next movie to be displayed to the user is the last movie from DB or not.
     */
    boolean isNoIdeaNextMovieLast();

    /**
     * @return {@link Movie} to be displayed to the user for the <b>no idea</b> feature.
     */
    Movie getNoIdeaCurrentMovie();

    /**
     * Moves to the next movie during the <b>we recommend</b> feature.
     * <br>
     * If the end of the list is reached, it wraps around to the first movie.
     */
    void moveWeRecommendToNextMovie();

    /**
     * Moves to the previous movie during the <b>we recommend</b> feature.
     * <br>
     * If the beginning of the list is reached, it wraps around to the last movie.
     */
    void moveWeRecommendToPreviousMovie();

    /**
     * @return {@link Movie} to be displayed to the user for the <b>we recommend</b> feature.
     */
    Movie getWeRecommendCurrentMovie();

    /**
     * @return  <b>Boolean value</b>, whether the next movie to be displayed to the user is the first movie from the
     * recommended list or not.
     */
    boolean isWeRecommendPreviousMovieFirst();

    /**
     * @return <b>Boolean value</b>, whether the next movie to be displayed to the user is the last movie from the
     * recommended list or not.
     */
    boolean isWeRecommendNextMovieLast();
}
