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

    //<editor-fold default-state="collapsed" desc="Message Sample Constants">
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
    //</editor-fold>

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

    /**
     * Example of output:
     * <pre>{@code
     *      Название: Табу
     *      Жанр: Триллер/Драма
     *      Год: 2017
     *      Режиссёр: Андерс Энгстрем, Кристоффер Нюхольм
     *      В топ ролях: Том Харди, Дэвид Хейман, Джонатан Прайс, Уна Чаплин
     *      Сюжет: Искатель приключений Джеймс Кезайя Делейни строит свою собственную корабельную империю в начале XIX века.
     *
     *      больше информации... (https://www.imdb.com/title/tt3647998/?ref_=fn_al_tt_1)
     * }</pre>
     */
    @Override
    public @NotNull String getWeRecommendMovieMessage(@NotNull Movie movie) {
        return String.format(smartSearchRandomizer.getWeRecommendRusSampleMovieMessage(),
                movie.rusName(),
                movie.rusGenre(),
                movie.releaseYear(),
                movie.rusDirector(),
                movie.rusCast(),
                movie.rusStoryline(),
                movie.detailsLink());
    }
}
