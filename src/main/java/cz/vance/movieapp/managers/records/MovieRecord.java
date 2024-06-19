package cz.vance.movieapp.managers.records;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.database.CinemaDatabase;
import cz.vance.movieapp.database.ICinemaDatabase;
import cz.vance.movieapp.models.Movie;
import cz.vance.movieapp.models.UserSelection;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 * Implements a set of basic methods for managing movies from the DB.
 * <br>
 * Interacts with the database via {@link ICinemaDatabase}.
 */
public final class MovieRecord implements IMovieRecord {

    private final ICinemaDatabase cinemaDB;

    /**
     * Contains all the movies from the DB.
     */
    private static final List<Movie> movies = new ArrayList<>();
    /**
     * Contains the movies that were sorted by the results of the last <b>smart search</b>.
     */
    private static final List<Movie> currentMovies = new ArrayList<>();

//    private static int currentMovieIndex = 0;

    //<editor-fold default-state="collapsed" desc="Singleton">
    private static MovieRecord instance;

    private MovieRecord() {
        cinemaDB = CinemaDatabase.getInstance();
        loadMovies();
    }

    public static MovieRecord getInstance() {
        if (instance == null)
            return new MovieRecord();
        return instance;
    }
    //</editor-fold>

    /**
     * Loads the all the movies from the DB into the {@link #movies} list.
     * <br>
     * Is called only once when the class is instantiated.
     */
    private void loadMovies() {
        if (movies.isEmpty())
            movies.addAll(cinemaDB.getMovies());
    }

//    /**
//     * Updates the {@link MovieRecord#currentMovies} list with the filtered results.
//     * <br>
//     * Alternative with the lambda expressions:
//     * <pre>{@code
//     *      movies.stream()
//     *          .filter(movie -> isAppropriateMovie(movie, mood, catalogue, genre))
//     *          .forEach(currentMovies::add);
//     *      }
//     * </pre>
//     */
//    @Override
//    public void filterMovies(@NotNull String mood,
//                             @NotNull String catalogue,
//                             @NotNull String genre) {
//        currentMovies.clear();
//        for (Movie movie : movies) {
//            if (isAppropriateMovie(movie, mood, catalogue, genre))
//                currentMovies.add(movie);
//        }
//        resetCurrentMovieIndex();
//    }
//
//    @Override
//    public void moveToNextMovie() {
//        if (!currentMovies.isEmpty())
//            currentMovieIndex = (currentMovieIndex + 1) % currentMovies.size();
//    }
//
//    @Override
//    public void moveToPreviousMovie() {
//        if (!currentMovies.isEmpty())
//            currentMovieIndex = (currentMovieIndex - 1 + currentMovies.size()) % currentMovies.size();
//    }
//
//    @Override
//    public Movie getCurrentMovie() { return currentMovies.get(currentMovieIndex); }

    /**
     * Alternative with the lambda expressions:
     * <pre>{@code
     *      movies.stream()
     *          .filter(movie -> isAppropriateMovie(movie, mood, catalogue, genre))
     *          .forEach(currentMovies::add);
     *      }
     * </pre>
     */
    @Override
    public List<Movie> getSmartSearchMovies(@NotNull UserSelection userSelection) {
        final String mood = userSelection.getMood();
        final String catalogue = userSelection.getCatalogue();
        final String genre = userSelection.getGenre();

        currentMovies.clear();
        for (Movie movie : movies) {
            if (isAppropriateMovie(movie, mood, catalogue, genre))
                currentMovies.add(movie);
        }
        return List.copyOf(currentMovies);
    }

    //<editor-fold default-state="collapsed" desc="Private Boolean Methods">
    /**
     * Alternative with the lambda expressions:
     * <pre>{@code
     *         return movie.rusMood().equalsIgnoreCase(mood) &&
     *                 movie.rusCatalogue().equalsIgnoreCase(catalogue) &&
     *                 Arrays.stream(movie.rusGenre().split("/"))
     *                         .map(String::trim)
     *                         .anyMatch(movieGenre -> movieGenre.equalsIgnoreCase(genre));
     *      }</pre>
     */
    private boolean isAppropriateMovie(@NotNull Movie movie,
                                       String mood,
                                       String catalogue,
                                       String genre) {
        return mood.contains(movie.rusMood()) &&
                catalogue.contains(movie.rusCatalogue()) &&
                genre.contains(movie.rusGenre());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Private 'currentMovieIndex' Methods">
//    private void resetCurrentMovieIndex() { currentMovieIndex = 0; }
    //</editor-fold>
}
