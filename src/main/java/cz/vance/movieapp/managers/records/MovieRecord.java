package cz.vance.movieapp.managers.records;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.database.CinemaDatabase;
import cz.vance.movieapp.database.ICinemaDatabase;
import cz.vance.movieapp.models.Movie;
import cz.vance.movieapp.models.UserSelection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

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
     * Contains <b>all the movies</b> from the DB.
     */
    private static final List<Movie> movies = new ArrayList<>();
    /**
     * Contains the movies that were sorted by the results of the last <b>smart search</b>.
     */
    private static final List<Movie> smartSearchCurrentMovies = new ArrayList<>();
    /**
     * Contains the indexes of the movies that <b>are recommended</b> to the user.
     */
    private static final List<Integer> weRecommendMovieIndexes = List.of(
            109, 110, 111, 112, 113, 114, 115, 116, 117, 118);

    /**
     * Contains the index of the current movie displayed to the user from the {@link #movies} list.
     */
    private static int noIdeaCurrentMovieIndex = 0;
    /**
     * Contains the index of the current movie displayed to the user from the {@link #weRecommendMovieIndexes} list.
     */
    private static int weRecommendCurrentMovieIndex = 0;

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
    public @Unmodifiable List<Movie> getSmartSearchMovies(@NotNull UserSelection userSelection) {
        final String mood = userSelection.getMood();
        final String catalogue = userSelection.getCatalogue();
        final String genre = userSelection.getGenre();

        smartSearchCurrentMovies.clear();
        for (Movie movie : movies) {
            if (isAppropriateMovie(movie, mood, catalogue, genre))
                smartSearchCurrentMovies.add(movie);
        }
        return List.copyOf(smartSearchCurrentMovies);
    }

    @Override
    public void moveNoIdeaToNextMovie() { noIdeaCurrentMovieIndex++; }

    @Override
    public void moveNoIdeaToPreviousMovie() { noIdeaCurrentMovieIndex--; }

    @Override
    public boolean isNoIdeaPreviousMovieFirst() { return noIdeaCurrentMovieIndex - 1 == 0; }

    @Override
    public boolean isNoIdeaNextMovieLast() { return noIdeaCurrentMovieIndex + 1 == movies.size(); }

    @Override
    public Movie getNoIdeaCurrentMovie() { return movies.get(noIdeaCurrentMovieIndex); }

    @Override
    public void moveWeRecommendToNextMovie() {
        if (weRecommendCurrentMovieIndex + 1 == weRecommendMovieIndexes.size())
            weRecommendCurrentMovieIndex = 0;
        else
            weRecommendCurrentMovieIndex++;
    }

    @Override
    public void moveWeRecommendToPreviousMovie() {
        if (weRecommendCurrentMovieIndex - 1 == -1)
            weRecommendCurrentMovieIndex = weRecommendMovieIndexes.size() - 1;
        else
            weRecommendCurrentMovieIndex--;
    }

    @Override
    public Movie getWeRecommendCurrentMovie() { return movies.get(weRecommendMovieIndexes.get(weRecommendCurrentMovieIndex)); }

    @Override
    public boolean isWeRecommendPreviousMovieFirst() { return weRecommendCurrentMovieIndex - 1 == 0; }

    @Override
    public boolean isWeRecommendNextMovieLast() { return weRecommendCurrentMovieIndex + 1 == weRecommendMovieIndexes.size(); }

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
                                       @NotNull String mood,
                                       @NotNull String catalogue,
                                       @NotNull String genre) {
        return mood.contains(movie.rusMood()) &&
                catalogue.contains(movie.rusCatalogue()) &&
                genre.contains(movie.rusGenre());
    }
    //</editor-fold>
}
