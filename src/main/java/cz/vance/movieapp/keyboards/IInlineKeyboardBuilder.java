package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.managers.CallbackQueryExtractor;
import static cz.vance.movieapp.managers.CallbackQueryExtractor.CALLBACK_QUERY_PREFIX;

import cz.vance.movieapp.managers.messages.BotMessageManager;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.function.BiFunction;
//</editor-fold>

/**
 * Declares a set of basic operations for creating custom inline keyboards.
 */
public interface IInlineKeyboardBuilder {

    //<editor-fold default-state="collapsed" desc="Validators">
    /**
     * Validator function checks if a button callback (that was clicked by the user) matches some of the
     * <b>user mood inline buttons</b>.
     */
    BiFunction<String, String, Boolean> userMoodValidator = (buttonCallback, mood) ->
        buttonCallback.equals(
                getCreatedCallback(mood));
    /**
     * Validator function checks if a button callback matches a <b>catalogue inline button</b>.
     */
    BiFunction<String, String, Boolean> catalogueValidator = (buttonCallback, catalogue) ->
            buttonCallback.equals(
                    getCreatedCallback(catalogue));
    /**
     * Validator function checks if a button callback matches a <b>genre inline button</b>.
     */
    BiFunction<String, String, Boolean> genreValidator = (buttonCallback, genre) ->
            buttonCallback.equals(
                    getCreatedCallback(genre));
    /**
     * Validator function checks if a button callback matches a <b>confirmation inline button</b> (yes/no button).
     */
    BiFunction<String, String, Boolean> smartSearchConfirmationValidator = (buttonCallback, confirmation) ->
            buttonCallback.equals(
                    getCreatedCallback(confirmation));
    /**
     * Validator function checks if a button callback matches a <b>back inline button</b> during the <b>smart search</b>.
     */
    BiFunction<String, String, Boolean> smartSearchBackValidator = (buttonCallback, back) ->
            buttonCallback.equals(
                    getCreatedCallback(back));
    /**
     * Validator function checks if a button callback matches a <b>previous movie inline button</b> during the <b>no idea</b>.
     */
    BiFunction<String, String, Boolean> noIdeaPreviousMovieValidator = (buttonCallback, previous) ->
            buttonCallback.equals(
                    getCreatedCallback(previous));
    /**
     * Validator function checks if a button callback matches a <b>next movie inline button</b> during the <b>no idea</b>.
     */
    BiFunction<String, String, Boolean> noIdeaNextMovieValidator = (buttonCallback, next) ->
            buttonCallback.equals(
                    getCreatedCallback(next));
    /**
     * Validator function checks if a button callback matches a <b>back to main menu inline button</b> during the <b>no idea</b>.
     */
    BiFunction<String, String, Boolean> noIdeaToMainMenuValidator = (buttonCallback, back) ->
            buttonCallback.equals(
                    getCreatedCallback(back));
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
     * Creates a callback data string for the passed inline button by concatenating the button text with the
     * {@link CallbackQueryExtractor#CALLBACK_QUERY_PREFIX}.
     *
     * @param inlineButtonText The text of the inline button.
     *
     * @return The created callback data string.
     */
    static @NotNull String getCreatedCallback(String inlineButtonText) { return inlineButtonText + CALLBACK_QUERY_PREFIX; }

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
        return userMoodValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getDepressionInlineButton());
    }

    default boolean isCheerfulButton(@NotNull Update botUpdate) {
        return userMoodValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getCheerfulInlineButton());
    }

    default boolean isFightingButton(@NotNull Update botUpdate) {
        return userMoodValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getFightingInlineButton());
    }

    default boolean isFamilyMoodButton(@NotNull Update botUpdate) {
        return userMoodValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getFamilyButton());
    }

    default boolean isFriendsButton(@NotNull Update botUpdate) {
        return userMoodValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getFriendsInlineButton());
    }

    default boolean isLoveButton(@NotNull Update botUpdate) {
        return userMoodValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getLoveInlineButton());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'Smart Search' Catalogue Boolean Methods">
    default boolean isSmartSearchCatalogueButton(@NotNull Update botUpdate) {
        return isMovieButton(botUpdate) ||
                isSeriesButton(botUpdate);
    }

    default boolean isMovieButton(@NotNull Update botUpdate) {
        return catalogueValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getMovieButton());
    }

    default boolean isSeriesButton(@NotNull Update botUpdate) {
        return catalogueValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getSeriesButton());
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
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getComedyButton());
    }

    default boolean isDramaButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getDramaButton());
    }

    default boolean isMelodramaButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getMelodramaButton());
    }

    default boolean isThrillerButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getThrillerButton());
    }

    default boolean isActionButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getActionButton());
    }

    default boolean isFictionButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getFictionButton());
    }

    default boolean isDetectiveButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getDetectiveButton());
    }

    default boolean isSportButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getSportButton());
    }

    default boolean isFamilyGenreButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getFamilyButton());
    }

    default boolean isFantasyButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getFantasyButton());
    }

    default boolean isAnimationButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getAnimationButton());
    }

    default boolean isAdventureButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getAdventureButton());
    }

    default boolean isBiographyButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getBiographyButton());
    }

    default boolean isCriminalButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getCriminalButton());
    }

    default boolean isDocumentaryButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getDocumentaryButton());
    }

    default boolean isWarButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getWarButton());
    }

    default boolean isMusicButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getMusicButton());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'Smart Search' Confirmation Boolean Methods">
    default boolean isSmartSearchConfirmationButton(@NotNull Update botUpdate) {
        return isSmartSearchYesButton(botUpdate) ||
                isSmartSearchNoButton(botUpdate);
    }

    default boolean isSmartSearchYesButton(@NotNull Update botUpdate) {
        return smartSearchConfirmationValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getSmartSearchYesButton());
    }

    default boolean isSmartSearchNoButton(@NotNull Update botUpdate) {
        return smartSearchConfirmationValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getSmartSearchNoButton());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'Smart Search' Back Boolean Methods">
    default boolean isSmartSearchBackButton(@NotNull Update botUpdate) {
        return smartSearchBackValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getSmartSearchBackButton());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'No Idea' Boolean Methods">
    default boolean isNoIdeaPreviousMovieButton(@NotNull Update botUpdate) {
        return noIdeaPreviousMovieValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getNoIdeaPreviousMovieButton());
    }

    default boolean isNoIdeaNextMovieButton(@NotNull Update botUpdate) {
        return noIdeaNextMovieValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getNoIdeaNextMovieButton());
    }

    default boolean isNoIdeaToMainMenuButton(@NotNull Update botUpdate) {
        return noIdeaToMainMenuValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                BotMessageManager.getInstance().getNoIdeaToMainMenuButton());
    }
    //</editor-fold>
}
