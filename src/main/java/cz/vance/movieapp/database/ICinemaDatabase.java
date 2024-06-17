package cz.vance.movieapp.database;

//<editor-fold default-state="collapsed" desc="Imports">
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
     * Retrieves a list of all the movies from the database from a related table.
     *
     * @return A <b>list object</b> with all the available movies.
     */
    @NotNull List<Movie> getMovies();
}
