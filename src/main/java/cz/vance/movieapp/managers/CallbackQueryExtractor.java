package cz.vance.movieapp.managers;

import org.jetbrains.annotations.NotNull;

/**
 * Handles general cased related to the <b>callback query</b> extraction from inline keyboard buttons.
 */
public final class CallbackQueryExtractor {

    /**
     * Prefix appended to each callback data (for each inline button).
     * <p>
     * The <b>callback data</b> — is a piece of information that will be sent to the bot when the user interacts with
     * the inline button, which is typically used to identify which button was pressed.
     */
    public static final String CALLBACK_QUERY_PREFIX = "_cb";
    /**
     * Unique prefix for the <b>no idea</b> callback query.
     * <br>
     * The <b>next/previous inline keyboard buttons</b> during the <b>no idea</b> and <b>we recommend</b> scenarios have
     * the same texts, so the callback queries, in case of the {@link #CALLBACK_QUERY_PREFIX}, would be the same. That
     * is why it is necessary to have a unique prefix for each scenario.
     */
    public static final String NO_IDEA_CALLBACK_QUERY_PREFIX = "_cbNoIdea";
    /**
     * Unique prefix for the <b>we recommend</b> callback query.
     */
    public static final String WE_RECOMMEND_CALLBACK_QUERY_PREFIX = "_cbWeRecommend";
    /**
     * Unique prefix for the <b>send feedback</b> callback query.
     */
    public static final String SEND_FEEDBACK_CALLBACK_QUERY_PREFIX = "_cbSendFeedback";
    /**
     * Unique prefix for the <b>/rating command</b> callback query.
     */
    public static final String MOVIE_RATING_COMMAND_CALLBACK_QUERY_PREFIX = "_cbMovieRating";

    /**
     * Extracts the <b>callback data</b> from the given <b>callback query</b>.
     * <br>
     * Removes the prefix from the query.
     *
     * @param callbackQuery the <b>callback query</b> from an inline keyboard button.
     *
     * @return Extracted callback data wrapped in {@link String}.
     */
    public static @NotNull String extractCallbackQueryPrefix(@NotNull String callbackQuery) {
        return callbackQuery.substring(0, callbackQuery.indexOf(CALLBACK_QUERY_PREFIX));
    }
}
