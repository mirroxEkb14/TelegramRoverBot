package cz.vance.movieapp.managers.records;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.Movie;
import org.jetbrains.annotations.NotNull;
//</editor-fold>

/**
 * Declares a set of basic operations for managing the movies from the appropriate movie table from the DB
 */
public interface IMovieRecord {

    /**
     * Filters the movies based on selected user's mood, catalogue, and genre (the result of the <b>smart search</b>).
     */
    void filterMovies(@NotNull String mood,
                      @NotNull String catalogue,
                      @NotNull String genre);

    /**
     * Moves to the next movie in the filtered list.
     * <br>
     * If the end of the list is reached, it wraps around to the first movie.
     */
    void moveToNextMovie();

    /**
     * Moves to the previous movie in the filtered list.
     * <br>
     * If the beginning of the list is reached, it wraps around to the last movie.
     */
    void moveToPreviousMovie();

    /**
     * Returns the current movie being displayed.
     *
     * @return The current movie instance.
     */
    Movie getCurrentMovie();
}
