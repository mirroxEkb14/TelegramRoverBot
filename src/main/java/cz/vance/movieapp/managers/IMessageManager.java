package cz.vance.movieapp.managers;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.utils.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.function.Predicate;
//</editor-fold>

/**
 * This <b>IMessageManager</b> interface declares a set of basic operations for handling messages based on incoming
 * updates from the Telegram Bot API
 */
public interface IMessageManager {

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
    /**
     * This <b>constant</b> represents a validator checking an incoming update, if the user entered the <b>/start</b>
     * command
     */
    Predicate<Update> startCommandValidator = t ->
            t.getMessage().getText().equals(
                    BotCommand.START_COMMAND.content());
    /**
     * This <b>constant</b> validates an incoming update and checks for the <b>/help</b> command
     */
    Predicate<Update> helpCommandValidator = t ->
            t.getMessage().getText().equals(
                    BotCommand.HELP_COMMAND.content());

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
     * Sends a welcome message when a user <b>starts the bot for the first time</b>
     *
     * @param botUpdate The incoming update
     */
    void sendWelcome(Update botUpdate);

    /**
     * Sends a help message when a user enters <b>/help</b>
     *
     * @param botUpdate The incoming update
     */
    void sendHelp(Update botUpdate);

    /**
     * Checks if the provided update contains a valid message, i.e. <b>there is a message</b> and this <b>message
     * contains some text</b>
     *
     * @param botUpdate The received update
     *
     * @return {@code true} if the update contains a valid message, {@code false} otherwise
     */
    default boolean isMessage(Update botUpdate) {
        return messageExistenceValidator.test(botUpdate);
    }

    /**
     * Checks if the provided update contains a message that represents the <b>start command</b>
     *
     * @param botUpdate The received update
     *
     * @return {@code true} if the update message is a <b>start command</b>, {@code false} otherwise
     *
     * @see BotCommand#START_COMMAND
     */
    default boolean isStartCommand(Update botUpdate) {
        return startCommandValidator.test(botUpdate);
    }

    /**
     * Checks the message text for the <b>/help</b> command
     *
     * @param botUpdate The received update
     *
     * @return {@code true} if the update message is a <b>help command</b>, {@code false} otherwise
     *
     * @see BotCommand#HELP_COMMAND
     */
    default boolean isHelpCommand(Update botUpdate) {
        return helpCommandValidator.test(botUpdate);
    }
}
