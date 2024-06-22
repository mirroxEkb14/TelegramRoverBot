package cz.vance.movieapp.managers.formatters;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.Movie;
import cz.vance.movieapp.managers.randomizers.IMessageRandomizer;
import cz.vance.movieapp.managers.randomizers.MessageRandomizer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
//</editor-fold>

/**
 * Implements a set of methods for formatting messages sent to the user by the bot.
 */
public final class MessageFormatter implements IMessageFormatter {

    private final IMessageRandomizer smartSearchRandomizer = new MessageRandomizer();

    /**
     * Example of output:
     * <pre>{@code
     *      *smart search sampling message*
     *
     *      Интерстеллар, 2014
     *      https://rezka.ag/films/fiction/2259-interstellar-2014.html
     *
     *      Призрачная красота, 2016
     *      https://rezka.ag/films/drama/20446-prizrachnaya-krasota-2016.html
     *
     *      ...
     * }</pre>
     */
    private final String SMART_SEARCH_MOVIE_MESSAGE_SAMPLE = "\n\n<b>%s</b>, %d\n%s";

    /**
     * Example of output:
     * <pre>{@code
     *      Призрачная красота, 2016
     *
     *      https://rezka.ag/films/drama/20446-prizrachnaya-krasota-2016.html
     * }</pre>
     */
    private final String NO_IDEA_MOVIE_MESSAGE_SAMPLE = "%s, %d\n\n%s";

    @Override
    public @NotNull String getSmartSearchMovieMessage(@NotNull List<Movie> smartSearchMovies) {
        final StringBuilder movieMessage = new StringBuilder(
                smartSearchRandomizer.getSmartSearchSamplingMessage());
        for (Movie movie : smartSearchMovies) {
            movieMessage.append(String.format(SMART_SEARCH_MOVIE_MESSAGE_SAMPLE,
                    movie.rusName(),
                    movie.releaseYear(),
                    movie.rusLink()));
        }
        return movieMessage.toString();
    }

    @Override
    public @NotNull String getSmartSearchNoMoviesMessage() { return smartSearchRandomizer.getSmartSearchNoMoviesMessage(); }

    @Override
    public @NotNull String getNoIdeaMovieMessage(@NotNull Movie movie) {
        return String.format(NO_IDEA_MOVIE_MESSAGE_SAMPLE,
                movie.rusName(),
                movie.releaseYear(),
                movie.rusLink());
    }
}
