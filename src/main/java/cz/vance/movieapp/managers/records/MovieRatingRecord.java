package cz.vance.movieapp.managers.records;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.database.CinemaDatabase;
import cz.vance.movieapp.database.ICinemaDatabase;
import cz.vance.movieapp.models.MovieRating;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 * Implements a set of methods for managing <b>movie ratings</b> from the appropriate DB table.
 */
public final class MovieRatingRecord implements IMovieRatingRecord {

    private final ICinemaDatabase cinemaDB;

    /**
     * Contains <b>all the movie ratings</b> from the DB.
     */
    private static final List<MovieRating> movieRatings = new ArrayList<>();

    //<editor-fold default-state="collapsed" desc="Singleton">
    private static MovieRatingRecord instance;

    private MovieRatingRecord() {
        cinemaDB = CinemaDatabase.getInstance();
        loadMovieRates();
    }

    public static MovieRatingRecord getInstance() {
        if (instance == null)
            return new MovieRatingRecord();
        return instance;
    }
    //</editor-fold>

    private void loadMovieRates() {
        if (movieRatings.isEmpty())
            movieRatings.addAll(cinemaDB.getMovieRates());
    }

    @Override
    public void insertMovieRating(MovieRating movieRate) {
        cinemaDB.insertMovieRate(movieRate);
        movieRatings.add(movieRate);
    }

    @Override
    public @NotNull List<MovieRating> getMovieRatings() { return new ArrayList<>(movieRatings); }
}
