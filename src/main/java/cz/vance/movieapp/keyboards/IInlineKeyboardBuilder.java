package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.utils.UserMood;
import cz.vance.movieapp.utils.Catalogue;
import cz.vance.movieapp.utils.Genre;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.function.BiFunction;
//</editor-fold>

/**
 * This <b>IInlineKeyboardBuilder</b> interface declares a set of basic operations for creating custom inline
 * keyboards
 */
public interface IInlineKeyboardBuilder {

    BiFunction<String, UserMood, Boolean> userMoodValidator = (buttonCallback, mood) ->
        buttonCallback.equals(
                mood.getCallback());
    BiFunction<String, Catalogue, Boolean> catalogueValidator = (buttonCallback, catalogue) ->
            buttonCallback.equals(
                    catalogue.getCallback());
    BiFunction<String, Genre, Boolean> genreValidator = (buttonCallback, genre) ->
            buttonCallback.equals(
                    genre.getCallback());

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
     * This keyboard is triggered when the <b>🎬 Smart search</b> button from <b>the main menu keyboard</b> was clicked
     * on
     *
     * @return Configured instance of <b>InlineKeyboardMarkup</b>
     *
     * @see UserMood
     */
    InlineKeyboardMarkup buildMoodKeyboard();

    /**
     * Builds and returns <b>the movie/series selection inline keyboard</b> containing the following buttons:
     * <ol>
     * <li> 📼 Movie
     * <li> 🎞 Series
     * </ol>
     * This keyboard is triggered when one of the <b>mood selection</b> buttons was clicked on
     *
     * @return Configured instance of <b>InlineKeyboardMarkup</b>
     *
     * @see Catalogue
     */
    InlineKeyboardMarkup buildCatalogueKeyboard();

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
     * This keyboard is triggered when one of the <b>movies/series selection</b> buttons was clicked on
     *
     * @return Configured instance of <b>InlineKeyboardMarkup</b>
     *
     * @see Genre
     */
    InlineKeyboardMarkup buildGenreKeyboard();

//<editor-fold default-state="collapsed" desc="Mood Boolean Methods">
    default boolean isMoodButton(@NotNull Update botUpdate) {
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
                UserMood.DEPRESSION_BUTTON);
    }

    default boolean isCheerfulButton(@NotNull Update botUpdate) {
        return userMoodValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                UserMood.CHEERFUL_BUTTON);
    }

    default boolean isFightingButton(@NotNull Update botUpdate) {
        return userMoodValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                UserMood.FIGHTING_BUTTON);
    }

    default boolean isFamilyMoodButton(@NotNull Update botUpdate) {
        return userMoodValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                UserMood.FAMILY_BUTTON);
    }

    default boolean isFriendsButton(@NotNull Update botUpdate) {
        return userMoodValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                UserMood.FRIENDS_BUTTON);
    }

    default boolean isLoveButton(@NotNull Update botUpdate) {
        return userMoodValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                UserMood.LOVE_BUTTON);
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="Catalogue Boolean Methods">
    default boolean isCatalogueButton(@NotNull Update botUpdate) {
        return isMovieButton(botUpdate) ||
                isSeriesButton(botUpdate);
    }

    default boolean isMovieButton(@NotNull Update botUpdate) {
        return catalogueValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Catalogue.MOVIE_BUTTON);
    }

    default boolean isSeriesButton(@NotNull Update botUpdate) {
        return catalogueValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Catalogue.SERIES_BUTTON);
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="Genre Boolean Methods">
    default boolean isGenreButton(@NotNull Update botUpdate) {
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
                Genre.COMEDY_BUTTON);
    }

    default boolean isDramaButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.DRAMA_BUTTON);
    }

    default boolean isMelodramaButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.MELODRAMA_BUTTON);
    }

    default boolean isThrillerButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.THRILLER_BUTTON);
    }

    default boolean isActionButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.ACTION_BUTTON);
    }

    default boolean isFictionButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.FICTION_BUTTON);
    }

    default boolean isDetectiveButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.DETECTIVE_BUTTON);
    }

    default boolean isSportButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.SPORT_BUTTON);
    }

    default boolean isFamilyGenreButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.FAMILY_BUTTON);
    }

    default boolean isFantasyButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.FANTASY_BUTTON);
    }

    default boolean isAnimationButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.ANIMATION_BUTTON);
    }

    default boolean isAdventureButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.ADVENTURE_BUTTON);
    }

    default boolean isBiographyButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.BIOGRAPHY_BUTTON);
    }

    default boolean isCriminalButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.CRIMINAL_BUTTON);
    }

    default boolean isDocumentaryButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.DOCUMENTARY_BUTTON);
    }

    default boolean isWarButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.WAR_BUTTON);
    }

    default boolean isMusicButton(@NotNull Update botUpdate) {
        return genreValidator.apply(
                botUpdate.getCallbackQuery().getData(),
                Genre.MUSIC_BUTTON);
    }
//</editor-fold>
}
