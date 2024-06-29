package cz.vance.movieapp.managers.formatters;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.managers.messages.BotMessageManager;
import cz.vance.movieapp.managers.messages.IBotMessageManager;
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

    private final IMessageRandomizer smartSearchRandomizer;
    private final IBotMessageManager botMessageManager;

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

    //<editor-fold default-state="collapsed" desc="Constructor">
    public MessageFormatter() {
        smartSearchRandomizer = new MessageRandomizer();
        botMessageManager = BotMessageManager.getInstance();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Overridden 'getSmartSearchMovieMessage' Method">
    @Override
    public @NotNull String getSmartSearchMovieMessage(@NotNull List<Movie> smartSearchMovies) {
        return botMessageManager.isEngLanguage() ?
                getSmartSearchEngMovieMessage(smartSearchMovies) :
                getSmartSearchRusMovieMessage(smartSearchMovies);
    }

    /**
     * @return {@link String} representation of messages containing the <b>name</b>, <b>release year</b> and <b>link</b>
     * of movies found during the <b>smart search</b>, taking into account the <b>Russian language</b>.
     */
    private @NotNull String getSmartSearchRusMovieMessage(@NotNull List<Movie> smartSearchMovies) {
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

    /**
     * @return Same as the {@link #getSmartSearchRusMovieMessage(List)} method, but for the <b>English language</b>.
     */
    private @NotNull String getSmartSearchEngMovieMessage(@NotNull List<Movie> smartSearchMovies) {
        final StringBuilder movieMessage = new StringBuilder(
                smartSearchRandomizer.getSmartSearchSamplingMessage());

        for (Movie movie : smartSearchMovies) {
            movieMessage.append(String.format(SMART_SEARCH_MOVIE_MESSAGE_SAMPLE,
                    movie.engName(),
                    movie.releaseYear(),
                    movie.engLink()));
        }
        return movieMessage.toString();
    }
    //</editor-fold>

    @Override
    public @NotNull String getSmartSearchNoMoviesMessage() { return smartSearchRandomizer.getSmartSearchNoMoviesMessage(); }

    //<editor-fold default-state="collapsed" desc="Overridden 'getNoIdeaMovieMessage' Method">
    @Override
    public @NotNull String getNoIdeaMovieMessage(@NotNull Movie movie) {
        return botMessageManager.isEngLanguage() ?
                getNoIdeaEngMovieMessage(movie) :
                getNoIdeaRusMovieMessage(movie);
    }

    /**
     * @return {@link String} representation of a message containing the <b>name</b>, <b>release year</b> and <b>link</b>
     * of a movie revealed during the <b>no idea</b> feature, taking into account the <b>Russian language</b>.
     */
    private String getNoIdeaRusMovieMessage(@NotNull Movie movie) {
        return String.format(NO_IDEA_MOVIE_MESSAGE_SAMPLE,
                movie.rusName(),
                movie.releaseYear(),
                movie.rusLink());
    }

    /**
     * @return Same as the {@link #getNoIdeaRusMovieMessage(Movie)} method, but for the <b>English language</b>.
     */
    private String getNoIdeaEngMovieMessage(@NotNull Movie movie) {
        return String.format(NO_IDEA_MOVIE_MESSAGE_SAMPLE,
                movie.engName(),
                movie.releaseYear(),
                movie.engLink());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Overridden 'getWeRecommendMovieMessage' Method">
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
        return botMessageManager.isEngLanguage() ?
                getWeRecommendEngMovieMessage(movie) :
                getWeRecommendRusMovieMessage(movie);
    }

    /**
     * @return {@link String} representation of a message containing the <b>name</b>, <b>genre</b>, <b>release year</b>,
     * <b>director</b>, <b>cast</b>, <b>storyline</b> and <b>link</b> of a movie recommended by the bot, taking into
     * account the <b>Russian language</b>.
     */
    private @NotNull String getWeRecommendRusMovieMessage(@NotNull Movie movie) {
        return String.format(smartSearchRandomizer.getWeRecommendSampleMovieMessage(),
                movie.rusName(),
                movie.rusGenre(),
                movie.releaseYear(),
                movie.rusDirector(),
                movie.rusCast(),
                movie.rusStoryline(),
                movie.detailsLink());
    }

    /**
     * @return Same as the {@link #getWeRecommendRusMovieMessage(Movie)} method, but for the <b>English language</b>.
     */
    private @NotNull String getWeRecommendEngMovieMessage(@NotNull Movie movie) {
        return String.format(smartSearchRandomizer.getWeRecommendSampleMovieMessage(),
                movie.engName(),
                movie.engGenre(),
                movie.releaseYear(),
                movie.engDirector(),
                movie.engCast(),
                movie.engStoryline(),
                movie.detailsLink());
    }
    //</editor-fold>
}
