package cz.vance.movieapp.managers.messages;

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
 * Declares a set of basic operations for handling messages based on incoming updates from the bot.
 */
public interface IMessageManager {

    //<editor-fold default-state="collapsed" desc="Validators">
    /**
     * Validator checks if the incoming update has a message.
     * <br>
     * Checks if this message has some text.
     */
    Predicate<Update> messageExistenceValidator = Update::hasMessage;
    /**
     * Validator checks if the text of the incoming update represents the <b>start command</b>.
     *
     * @see BotCommand#START_COMMAND
     */
    Predicate<Update> startCommandValidator = t ->
            t.getMessage().getText() != null && t.getMessage().getText().equals(
                    BotCommand.START_COMMAND.content());
    /**
     * Validator checks if the text of the incoming update represents the <b>help command</b>.
     *
     * @see BotCommand#HELP_COMMAND
     */
    Predicate<Update> helpCommandValidator = t ->
            t.getMessage().getText() != null &&  t.getMessage().getText().equals(
                    BotCommand.HELP_COMMAND.content());
    /**
     * Validator tests if the incoming update's message text is equal to the <b>smart search button</b> content.
     *
     * @see IMessageManager#isSmartSearchButton(Update)
     */
    BiPredicate<String, String> smartSearchValidator = String::equalsIgnoreCase;
    //</editor-fold>

    /**
     * Sends an echo message to the user.
     * <p>
     * An <b>update</b> — bears information about various events that occur in a chat, such as <b>new messages</b>,
     * <b>edited messages</b>, <b>channel posts</b>, and more.
     *
     * @param botUpdate The received update.
     */
    void sendEcho(Update botUpdate);

    /**
     * Sends a welcome message when a user <b>starts the bot for the first time</b> with the <b>main menu keyboard</b>.
     *
     * @param botUpdate The incoming update.
     *
     * @see IReplyKeyboardBuilder#buildMainMenuKeyboard()
     */
    void sendWelcome(Update botUpdate);

    /**
     * Sends a help message when a user enters the <b>/help</b> command.
     *
     * @param botUpdate The incoming update.
     */
    void sendHelp(Update botUpdate);

    /**
     * Sends a message with <b>user's mood selection</b>.
     *
     * @param botUpdate The received update.
     *
     * @see IInlineKeyboardBuilder#buildMoodKeyboard()
     */
    void sendMood(Update botUpdate);

    /**
     * Sends a message with <b>movie/series selection</b>.
     *
     * @param botUpdate The received update.
     *
     * @see IInlineKeyboardBuilder#buildCatalogueKeyboard()
     */
    void sendCatalogue(Update botUpdate);

    /**
     * Sends a message with <b>genre selection</b>.
     *
     * @param botUpdate The received update.
     *
     * @see IInlineKeyboardBuilder#buildGenreKeyboard()
     */
    void sendGenre(Update botUpdate);

    /**
     * Sends a message with the <b>next movie</b> from the DB.
     *
     * @param botUpdate The received update.
     */
    void sendMovie(Update botUpdate);

    //<editor-fold default-state="collapsed" desc="Boolean Methods">
    default boolean isMessage(Update botUpdate) { return messageExistenceValidator.test(botUpdate); }

    default boolean isSmartSearchButton(@NotNull Update botUpdate) {
        String messageText = botUpdate.getMessage().getText();
        if (messageText == null)
            return false;

        return smartSearchValidator.test(
                messageText,
                MainMenuButton.SMART_SEARCH_BUTTON.getContent());
    }

    default boolean isStartCommand(Update botUpdate) { return startCommandValidator.test(botUpdate); }

    default boolean isHelpCommand(Update botUpdate) { return helpCommandValidator.test(botUpdate); }
    //</editor-fold>
}