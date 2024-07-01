package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.managers.CallbackQueryExtractor;

import cz.vance.movieapp.managers.messages.BotMessageManager;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.function.BiFunction;

import static cz.vance.movieapp.managers.CallbackQueryExtractor.*;
//</editor-fold>

/**
 * Declares a set of basic operations for creating custom inline keyboards.
 */
public interface IInlineKeyboardBuilder {

    //<editor-fold default-state="collapsed" desc="Callback Data Validator Functions">
    /**
     * @see #getCreatedCallback(String)
     */
    BiFunction<String, String, Boolean> callbackDataStringValidator = (buttonCallback, buttonText) ->
            buttonCallback.equals(
                    getCreatedCallback(buttonText));
    /**
     * @see #getNoIdeaCreatedCallback(String)
     */
    BiFunction<String, String, Boolean> noIdeasCallbackDataStringValidator = (buttonCallback, buttonText) ->
            buttonCallback.equals(
                    getNoIdeaCreatedCallback(buttonText));
    /**
     * @see #getWeRecommendCreatedCallback(String)
     */
    BiFunction<String, String, Boolean> weRecommendCallbackDataStringValidator = (buttonCallback, buttonText) ->
            buttonCallback.equals(
                    getWeRecommendCreatedCallback(buttonText));
    /**
     * @see #getSendFeedbackCreatedCallback(String)
     */
    BiFunction<String, String, Boolean> sendFeedbackCallbackDataStringValidator = (buttonCallback, buttonText) ->
            buttonCallback.equals(
                    getSendFeedbackCreatedCallback(buttonText));
    /**
     * @see #getMovieRatingCreatedCallback(String)
     */
    BiFunction<String, String, Boolean> movieRatingCallbackDataStringValidator = (buttonCallback, buttonText) ->
            buttonCallback.equals(
                    getMovieRatingCreatedCallback(buttonText));
    //</editor-fold>

    /**
     * Builds and returns <b>the mood selection inline keyboard</b> containing the following buttons:
     * <ol>
     * <li> 🌧 Depressed
     * <li> 💃 Сheerful
     * <li> 😼 Fighting
     * <li> 👨‍👩‍👧‍👦 Family
     * <li> 🍻 Friends
     * <li> 💋 Love
     * </ol>
     * This keyboard is triggered when the <b>🎬 Smart search</b> button from <b>the main menu keyboard</b> was clicked.
     *
     * @return Configured instance of the {@link InlineKeyboardMarkup}.
     */
    @NotNull InlineKeyboardMarkup buildSmartSearchMoodKeyboard();

    /**
     * Builds and returns <b>the movie/series selection inline keyboard</b> containing the following buttons:
     * <ol>
     * <li> 📼 Movie
     * <li> 🎞 Series
     * </ol>
     * This keyboard is triggered when one of the <b>mood selection</b> buttons was clicked.
     *
     * @return Configured instance of {@link InlineKeyboardMarkup}.
     */
    @NotNull InlineKeyboardMarkup buildSmartSearchCatalogueKeyboard();

    /**
     * Builds and returns <b>the genre selection inline keyboard</b> containing the following buttons:
     * <ol>
     * <li> 🕺 Comedy
     * <li> 💔 Drama
     * <li> 💘 Melodrama
     * <li> 🔫 Thriller
     * <li> 🔥 Action
     * <li> 🧙🏻 Fiction
     * <li> 🕵🏼 Detective
     * <li> 👨‍👩‍👦 Family
     * <li> 🏀 Sport
     * <li> 🔮 Fantasy
     * <li> 🧸 Animation
     * <li> 🎒 Adventure
     * <li> 📖 Biography
     * <li> 👥 Criminal
     * <li> 🎥 Documentary
     * <li> 🪖 War
     * <li> 🎻 Music
     * </ol>
     * This keyboard is triggered when one of the <b>movies/series selection</b> buttons was clicked.
     *
     * @return Configured instance of {@link InlineKeyboardMarkup}.
     */
    @NotNull InlineKeyboardMarkup buildSmartSearchGenreKeyboard();

    /**
     * Builds and returns <b>the confirmation inline keyboard</b> containing the following buttons:
     * <ol>
     * <li> ✅ Yes
     * <li> ❌ No
     * </ol>
     * This keyboard is triggered when one of the <b>genre selection</b> buttons was clicked.
     *
     * @return Configured instance of {@link InlineKeyboardMarkup}.
     */
    @NotNull InlineKeyboardMarkup buildSmartSearchConfirmationKeyboard();

    /**
     * Builds and returns <b>the 'no idea' first movie inline keyboard</b> containing the following button:
     * <ol>
     *     <li> Следующий ➡
     *     <li> ↙️ В главное меню
     * </ol>
     * This keyboard is triggered when the user interacts with the <b>'no idea' reply keyboard button</b>.
     *
     * @return Configured instance of {@link InlineKeyboardMarkup}.
     */
    @NotNull InlineKeyboardMarkup buildNoIdeaFirstMovieKeyboard();

    /**
     * Builds and returns <b>the 'no idea' interim movie inline keyboard</b> containing the following button:
     * <ol>
     *     <li> ⬅ Предыдущий
     *     <li> Следующий ➡
     *     <li> ↙️ В главное меню
     * </ol>
     * This keyboard is triggered when the user interacts with one of the <b>inline keyboard buttons during 'no idea'</b>,
     * representing <b>the previous movie</b> or <b>the next movie</b>.
     *
     * @return Configured instance of {@link InlineKeyboardMarkup}.
     */
    @NotNull InlineKeyboardMarkup buildNoIdeaInterimMovieKeyboard();

    /**
     * Builds and returns <b>the 'no idea' last movie inline keyboard</b> containing the following button:
     * <ol>
     *     <li> ⬅ Предыдущий
     *     <li> ↙️ В главное меню
     * </ol>
     * This keyboard is triggered when the user interacts with the <b>next movie inline keyboard button during the
     * 'no idea'</b>.
     *
     * @return Configured instance of {@link InlineKeyboardMarkup}.
     */
    @NotNull InlineKeyboardMarkup buildNoIdeaLastMovieKeyboard();

    /**
     * Builds and returns <b>the 'we recommend' interim movie inline keyboard</b> containing the following button:
     * <ol>
     *     <li> ⬅ Предыдущий
     *     <li> Следующий ➡
     *     <li> 📺 Смотреть
     *     <li> ↙️ В главное меню
     * </ol>
     * This keyboard is triggered when the user interacts with one of the <b>inline keyboard buttons during 'we recommend'</b>,
     * representing <b>the previous movie</b> or <b>the next movie</b>.
     *
     * @param watchUrl The URL to the recommended movie.
     *
     * @return Configured instance of {@link InlineKeyboardMarkup}.
     */
    @NotNull InlineKeyboardMarkup buildWeRecommendInterimMovieKeyboard(String watchUrl);

    /**
     * Builds and returns <b>the 'send feedback' confirmation inline keyboard</b> containing the following button:
     * <ol>
     *      <li> ✅ Yes
     *      <li> ❌ No
     * </ol>
     * This keyboard is triggered when the user interacts with the <b>'send feedback' reply keyboard button</b> and sends
     * the feedback message.
     *
     * @return Configured instance of {@link InlineKeyboardMarkup}.
     */
    @NotNull InlineKeyboardMarkup buildSendFeedbackConfirmationKeyboard();

    /**
     * Builds and returns <b>the movie rating inline keyboard</b> containing the following buttons:
     * <ol>
     *      <li> ✅ Yes
     *      <li> ❌ No
     * </ol>
     * This keyboard is triggered when the user enters the <b>/rating</b> command, enters his message and now the bot
     * sends this keyboard for confirmation purposes.
     *
     * @return Configured instance of {@link InlineKeyboardMarkup}.
     */
    @NotNull InlineKeyboardMarkup buildMovieRatingKeyboard();

    //<editor-fold default-state="collapsed" desc="Created Callbacks Getters">
    /**
     * Creates a callback data string for the passed inline button by concatenating the button text with the
     * {@link CallbackQueryExtractor#CALLBACK_QUERY_PREFIX}.
     *
     * @param inlineButtonText the text of the inline button.
     */
    static @NotNull String getCreatedCallback(String inlineButtonText) { return inlineButtonText + CALLBACK_QUERY_PREFIX; }
    /**
     * Creates a callback data string for the <b>'no idea' inline button</b> by concatenating the button text with the
     * {@link CallbackQueryExtractor#NO_IDEA_CALLBACK_QUERY_PREFIX}.
     *
     * @param inlineButtonText the text of the inline button.
     */
    static @NotNull String getNoIdeaCreatedCallback(String inlineButtonText) { return inlineButtonText + NO_IDEA_CALLBACK_QUERY_PREFIX; }
    /**
     * Creates a callback data string for the <b>'we recommend' inline button</b> by concatenating the button text with
     * the {@link CallbackQueryExtractor#WE_RECOMMEND_CALLBACK_QUERY_PREFIX}.
     *
     * @param inlineButtonText the text of the inline button.
     */
    static @NotNull String getWeRecommendCreatedCallback(String inlineButtonText) { return inlineButtonText + WE_RECOMMEND_CALLBACK_QUERY_PREFIX; }
    /**
     * Creates a callback data string for the <b>'send feedback' inline button</b> by concatenating the button text with
     * the {@link CallbackQueryExtractor#SEND_FEEDBACK_CALLBACK_QUERY_PREFIX}.
     *
     * @param inlineButtonText the text of the inline button.
     */
    static @NotNull String getSendFeedbackCreatedCallback(String inlineButtonText) { return inlineButtonText + SEND_FEEDBACK_CALLBACK_QUERY_PREFIX; }
    /**
     * Creates a callback data string for the <b>'movie rating' inline button</b> by concatenating the button text with
     * the {@link CallbackQueryExtractor#MOVIE_RATING_COMMAND_CALLBACK_QUERY_PREFIX}.
     *
     * @param inlineButtonText the text of the inline button.
     */
    static @NotNull String getMovieRatingCreatedCallback(String inlineButtonText) { return inlineButtonText + MOVIE_RATING_COMMAND_CALLBACK_QUERY_PREFIX; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'Smart Search' Mood Boolean Methods">
    default boolean isSmartSearchMoodButton(@NotNull Update botUpdate) {
        return isDepressedButton(botUpdate) ||
                isCheerfulButton(botUpdate) ||
                isFightingButton(botUpdate) ||
                isFamilyMoodButton(botUpdate) ||
                isFriendsButton(botUpdate) ||
                isLoveButton(botUpdate);
    }

    default boolean isDepressedButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getDepressionMoodInlineButton());
    }

    default boolean isCheerfulButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getCheerfulMoodInlineButton());
    }

    default boolean isFightingButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getFightingMoodInlineButton());
    }

    default boolean isFamilyMoodButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getFamilyMoodInlineButton());
    }

    default boolean isFriendsButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getFriendsMoodInlineButton());
    }

    default boolean isLoveButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getLoveMoodInlineButton());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'Smart Search' Catalogue Boolean Methods">
    default boolean isSmartSearchCatalogueButton(@NotNull Update botUpdate) {
        return isMovieButton(botUpdate) ||
                isSeriesButton(botUpdate);
    }

    default boolean isMovieButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getMovieInlineButton());
    }

    default boolean isSeriesButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getSeriesInlineButton());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'Smart Search' Genre Boolean Methods">
    default boolean isSmartSearchGenreButton(@NotNull Update botUpdate) {
        return isComedyButton(botUpdate) ||
                isDramaButton(botUpdate) ||
                isMelodramaButton(botUpdate) ||
                isThrillerButton(botUpdate) ||
                isActionButton(botUpdate) ||
                isFictionButton(botUpdate) ||
                isDetectiveButton(botUpdate) ||
                isFamilyGenreButton(botUpdate) ||
                isSportButton(botUpdate) ||
                isFantasyButton(botUpdate) ||
                isAnimationButton(botUpdate) ||
                isAdventureButton(botUpdate) ||
                isBiographyButton(botUpdate) ||
                isCriminalButton(botUpdate) ||
                isDocumentaryButton(botUpdate) ||
                isWarButton(botUpdate) ||
                isMusicButton(botUpdate);
    }

    default boolean isComedyButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getComedyInlineButton());
    }

    default boolean isDramaButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getDramaInlineButton());
    }

    default boolean isMelodramaButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getMelodramaInlineButton());
    }

    default boolean isThrillerButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getThrillerInlineButton());
    }

    default boolean isActionButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getActionInlineButton());
    }

    default boolean isFictionButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getFictionInlineButton());
    }

    default boolean isDetectiveButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getDetectiveInlineButton());
    }

    default boolean isSportButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getSportInlineButton());
    }

    default boolean isFamilyGenreButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getFamilyInlineButton());
    }

    default boolean isFantasyButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getFantasyInlineButton());
    }

    default boolean isAnimationButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getAnimationInlineButton());
    }

    default boolean isAdventureButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getAdventureInlineButton());
    }

    default boolean isBiographyButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getBiographyInlineButton());
    }

    default boolean isCriminalButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getCriminalInlineButton());
    }

    default boolean isDocumentaryButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getDocumentaryInlineButton());
    }

    default boolean isWarButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getWarInlineButton());
    }

    default boolean isMusicButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getMusicInlineButton());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'Smart Search' Confirmation Boolean Methods">
    default boolean isSmartSearchConfirmationButton(@NotNull Update botUpdate) {
        return isSmartSearchYesButton(botUpdate) ||
                isSmartSearchNoButton(botUpdate);
    }

    default boolean isSmartSearchYesButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getSmartSearchYesInlineButton());
    }

    default boolean isSmartSearchNoButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getSmartSearchNoInlineButton());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'Smart Search' Back Boolean Methods">
    default boolean isSmartSearchBackButton(@NotNull Update botUpdate) {
        return callbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getSmartSearchBackInlineButton());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'No Idea' Boolean Methods">
    default boolean isNoIdeaPreviousMovieButton(@NotNull Update botUpdate) {
        return noIdeasCallbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getNoIdeaPreviousMovieInlineButton());
    }

    default boolean isNoIdeaNextMovieButton(@NotNull Update botUpdate) {
        return noIdeasCallbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getNoIdeaNextMovieInlineButton());
    }

    default boolean isNoIdeaToMainMenuButton(@NotNull Update botUpdate) {
        return noIdeasCallbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getNoIdeaToMainMenuInlineButton());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'We Recommend' Boolean Methods">
    default boolean isWeRecommendPreviousMovieButton(@NotNull Update botUpdate) {
        return weRecommendCallbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getWeRecommendPreviousMovieInlineButton());
    }

    default boolean isWeRecommendNextMovieButton(@NotNull Update botUpdate) {
        return weRecommendCallbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getWeRecommendNextMovieInlineButton());
    }

    default boolean isWeRecommendToMainMenuButton(@NotNull Update botUpdate) {
        return weRecommendCallbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getWeRecommendToMainMenuInlineButton());
    }

    default boolean isWeRecommendWatchButton(@NotNull Update botUpdate) {
        return weRecommendCallbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getWeRecommendWatchInlineButton());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'Send Feedback' Boolean Methods">
    default boolean isSendFeedbackConfirmationButton(@NotNull Update botUpdate) {
        return isSendFeedbackYesButton(botUpdate) ||
                isSendFeedbackNoButton(botUpdate);
    }

    default boolean isSendFeedbackNoButton(@NotNull Update botUpdate) {
        return sendFeedbackCallbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getSendFeedbackNoInlineButton());
    }

    default boolean isSendFeedbackYesButton(@NotNull Update botUpdate) {
        return sendFeedbackCallbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getSendFeedbackYesInlineButton());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'Movie Rating' Boolean Methods">
    default boolean isMovieRatingConfirmationButton(@NotNull Update botUpdate) {
        return isMovieRatingYesButton(botUpdate) ||
                isMovieRatingNoButton(botUpdate);
    }

    default boolean isMovieRatingYesButton(@NotNull Update botUpdate) {
        return movieRatingCallbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getMovieRatingYesInlineButton());
    }

    default boolean isMovieRatingNoButton(@NotNull Update botUpdate) {
        return movieRatingCallbackDataStringValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getMovieRatingNoInlineButton());
    }
    //</editor-fold>
}
