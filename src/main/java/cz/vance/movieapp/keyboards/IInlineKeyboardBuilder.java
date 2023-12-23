package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.utils.UserMood;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.function.BiFunction;
import java.util.function.Predicate;
//</editor-fold>

/**
 * This <b>IInlineKeyboardBuilder</b> interface declares a set of basic operations for creating custom inline
 * keyboards
 */
public interface IInlineKeyboardBuilder {

    BiFunction<String, UserMood, Boolean> userMoodValidator = (buttonCallback, mood) ->
        buttonCallback.equals(
                mood.getCallback());

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

//<editor-fold default-state="collapsed" desc="Mood Boolean Methods">
    default boolean isMoodButton(@NotNull Update botUpdate) {
        return isDepressedButton(botUpdate) ||
                isCheerfulButton(botUpdate) ||
                isFightingButton(botUpdate) ||
                isFamilyButton(botUpdate) ||
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

    default boolean isFamilyButton(@NotNull Update botUpdate) {
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
}
