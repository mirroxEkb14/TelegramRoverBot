package cz.vance.movieapp.database;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.Feedback;
import cz.vance.movieapp.models.Movie;
import org.jetbrains.annotations.NotNull;

import java.util.List;
//</editor-fold>

/**
 * Declares a set of basic operations for interacting with the database.
 * <p>
 * The <b>"connect()"</b> method should be implemented as private and be called inside the constructor.
 */
public interface ICinemaDatabase {

    /**
     * Closes the connection to the database.
     */
    void close();

    /**
     * Retrieves a list of <b>all the movies</b> from the DB from a related table.
     *
     * @return A <b>list object</b> with all the available movies.
     */
    @NotNull List<Movie> getMovies();

    /**
     * Inserts a new <b>feedback</b> object into the DB.
     *
     * @param feedback A <b>feedback instance</b> containing all the necessary DB field values.
     */
    void insertFeedback(@NotNull Feedback feedback);

    /**
     * Retrieves a list of <b>all the feedbacks</b> from the DB from a related table.
     *
     * @return A <b>list object</b> with all the available feedbacks.
     */
    @NotNull List<Feedback> getFeedbacks();
}
