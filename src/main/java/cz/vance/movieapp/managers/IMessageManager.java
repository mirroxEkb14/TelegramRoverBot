package cz.vance.movieapp.managers;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.keyboards.IReplyKeyboardBuilder;
import cz.vance.movieapp.keyboards.IInlineKeyboardBuilder;
import cz.vance.movieapp.utils.BotCommand;
import cz.vance.movieapp.utils.MainMenuButton;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
//</editor-fold>

/**
 * This <b>IMessageManager</b> interface declares a set of basic operations for handling messages based on incoming
 * updates from the Telegram Bot API
 */
public interface IMessageManager {

//<editor-fold default-state="collapsed" desc="Validators">
    /**
     * This <b>constant</b> represents a validator implemented by default Java functional interface {@link Predicate}
     * for incoming updates that checks:
     * <ol>
     * <li> if the update has a message;
     * <li> if the message has text;
     * </ol>
     */
    Predicate<Update> messageExistenceValidator = t ->
            t.hasMessage() && t.getMessage().hasText();

     Predicate<Update> startCommandValidator = t ->
            t.getMessage().getText().equals(
                    BotCommand.START_COMMAND.content());

     Predicate<Update> helpCommandValidator = t ->
            t.getMessage().getText().equals(
                    BotCommand.HELP_COMMAND.content());

    BiPredicate<String, String> smartSearchValidator = String::equalsIgnoreCase;
//</editor-fold>

    /**
     * Sends an echo message based on the provided bot update
     * <p>
     * An <b>update</b> bears information about various events that occur in a chat, such as <b>new messages</b>,
     * <b>edited messages</b>, <b>channel posts</b>, and more
     *
     * @param botUpdate The received update
     *
     * @see Update
     */
    void sendEcho(Update botUpdate);

    /**
     * Sends a welcome message when a user <b>starts the bot for the first time</b> with the <b>main menu keyboard</b>
     *
     * @param botUpdate The incoming update
     *
     * @see IReplyKeyboardBuilder#buildMainMenuKeyboard()
     */
    void sendWelcome(Update botUpdate);

    /**
     * Sends a help message when a user enters <b>/help</b>
     *
     * @param botUpdate The incoming update
     */
    void sendHelp(Update botUpdate);

    /**
     * Sends a message with user's mood selection
     *
     * @param botUpdate The received update
     *
     * @see IInlineKeyboardBuilder#buildMoodKeyboard()
     */
    void sendMood(Update botUpdate);

//<editor-fold default-state="collapsed" desc="Boolean Methods">
    default boolean isMessage(Update botUpdate) {
        return messageExistenceValidator.test(botUpdate);
    }

    default boolean isSmartSearchButton(@NotNull Update botUpdate) {
        return smartSearchValidator.test(
                botUpdate.getMessage().getText(),
                MainMenuButton.SMART_SEARCH_BUTTON.content());
    }

    default boolean isStartCommand(Update botUpdate) {
        return startCommandValidator.test(botUpdate);
    }

    default boolean isHelpCommand(Update botUpdate) {
        return helpCommandValidator.test(botUpdate);
    }
//</editor-fold>
}
