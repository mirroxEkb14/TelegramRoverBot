package cz.vance.movieapp.managers;

//<editor-fold default-state="collapsed" desc="Imports">
import org.jetbrains.annotations.NotNull;
//</editor-fold>

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
     * Extracts the <b>callback data</b> from the given <b>callback query</b>.
     * <br>
     * Removes the prefix from the query.
     *
     * @param callbackQuery The callback query from an inline keyboard button.
     *
     * @return The extracted callback data.
     */
    public static @NotNull String extractCallbackQueryPrefix(@NotNull String callbackQuery) {
        return callbackQuery.substring(0, callbackQuery.indexOf(CALLBACK_QUERY_PREFIX));
    }
}
