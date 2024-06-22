package cz.vance.movieapp.managers.formatters;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.Movie;
import org.jetbrains.annotations.NotNull;

import java.util.List;
//</editor-fold>

/**
 * Declares a set of methods for formatting messages the bot sends to the user.
 */
public interface IMessageFormatter {

    /**
     * Forms a reply to the user at the end of the <b>smart search</b>.
     *
     * @param smartSearchMovies the movies found during the <b>smart search</b>.
     *
     * @return The full output message with a sample text and a list of input movies.
     */
    @NotNull String getSmartSearchMovieMessage(@NotNull List<Movie> smartSearchMovies);

    /**
     * Forms a reply to the user when no movies are found during the <b>smart search</b> (the list is empty).
     *
     * @return The output message with a sample text.
     */
    @NotNull String getSmartSearchNoMoviesMessage();

    /**
     * Forms a reply to the user when the bot has to send a movie during the <b>no idea</b> mode.
     *
     * @return The output message with a sample text and the input movie.
     */
    @NotNull String getNoIdeaMovieMessage(@NotNull Movie movie);
}
