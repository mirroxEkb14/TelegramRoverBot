package cz.vance.movieapp.managers.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.keyboards.IReplyKeyboardBuilder;
import cz.vance.movieapp.keyboards.IInlineKeyboardBuilder;
import cz.vance.movieapp.utils.BotCommand;
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
     * Validator checks if the text of the incoming update represents the <b>lang command</b>.
     *
     * @see BotCommand#LANG_COMMAND
     */
    Predicate<Update> langCommandValidator = t ->
            t.getMessage().getText() != null &&  t.getMessage().getText().equals(
                    BotCommand.LANG_COMMAND.content());

    /**
     * Validator tests if the incoming update's message text is equal to the <b>smart search/no idea/our choice/feedback
     * button</b> content (other string value).
     */
    BiPredicate<String, String> stringValidator = String::equalsIgnoreCase;
    //</editor-fold>

    /**
     * Sends an echo message to the user.
     * <p>
     * An <b>update</b> — bears information about various events that occur in a chat, such as <b>new messages</b>,
     * <b>edited messages</b>, <b>channel posts</b>, and more.
     */
    void sendEcho(Update botUpdate);

    /**
     * Sends a welcome message when a user <b>starts the bot for the first time</b> with the <b>main menu keyboard</b>.
     *
     * @see IReplyKeyboardBuilder#buildMainMenuKeyboard()
     */
    void sendWelcome(Update botUpdate);

    /**
     * Sends a help message when a user enters the <b>/help</b> command.
     */
    void sendHelp(Update botUpdate);

    /**
     * Handles the language change when the user changes the bot messages language, i.e. <b>/lang</b> command.
     */
    void sendLang(Update botUpdate);

    /**
     * Sends a message with <b>user's mood selection</b>.
     *
     * @see IInlineKeyboardBuilder#buildSmartSearchMoodKeyboard()
     */
    void sendSmartSearchMood(Update botUpdate);

    /**
     * Sends a message with <b>movie/series selection</b>.
     *
     * @see IInlineKeyboardBuilder#buildSmartSearchCatalogueKeyboard()
     */
    void sendSmartSearchCatalogue(Update botUpdate);

    /**
     * Sends a message with <b>genre selection</b>.
     *
     * @see IInlineKeyboardBuilder#buildSmartSearchGenreKeyboard()
     */
    void sendSmartSearchGenre(Update botUpdate);

    /**
     * Sends a message with <b>confirmation</b> of the user's choice.
     *
     * @see IInlineKeyboardBuilder#buildSmartSearchConfirmationKeyboard()
     */
    void sendSmartSearchConfirmation(Update botUpdate);

    /**
     * Sends a message with the <b>movies</b> from the DB at the end of the <b>smart search</b>.
     * <br>
     * Removes inline keyboard with the help of which the user was selecting mood, catalogue, and genre.
     */
    void sendSmartSearchMovie(Update botUpdate);

    /**
     * Handles the <b>back button</b> during the <b>smart search</b>.
     */
    void handleSmartSearchBack(Update botUpdate);

    /**
     * Sends a message with the <b>'no idea' first movie</b> text.
     *
     * @see IInlineKeyboardBuilder#buildNoIdeaFirstMovieKeyboard()
     */
    void sendNoIdeaFirstMovie(Update botUpdate);

    /**
     * Sends a message with the <b>'no idea' previous movie</b> text.
     *
     * @see IInlineKeyboardBuilder#buildNoIdeaInterimMovieKeyboard()
     */
    void sendNoIdeaPreviousMovie(Update botUpdate);

    /**
     * Sends a message with the <b>'no idea' next movie</b> text.
     *
     * @see IInlineKeyboardBuilder#buildNoIdeaInterimMovieKeyboard()
     */
    void sendNoIdeaNextMovie(Update botUpdate);

    /**
     * Sends a message indicating that the user is going from the <b>no idea mode</b> to the <b>main menu</b>.
     * <br>
     * The inline keyboard is removed.
     */
    void handleNoIdeaToMainMenu(Update botUpdate);

    /**
     * Sends a message with the <b>'we recommend' first movie</b> text.
     *
     * @see IInlineKeyboardBuilder#buildWeRecommendInterimMovieKeyboard(String)
     */
    void sendWeRecommendFirstMovie(Update botUpdate);

    /**
     * Sends a message with the <b>'our choice' previous movie</b> text.
     *
     * @see IInlineKeyboardBuilder#buildWeRecommendInterimMovieKeyboard(String)
     */
    void sendWeRecommendPreviousMovie(Update botUpdate);

    /**
     * Sends a message with the <b>'our choice' next movie</b> text.
     *
     * @see IInlineKeyboardBuilder#buildWeRecommendInterimMovieKeyboard(String)
     */
    void sendWeRecommendNextMovie(Update botUpdate);

    /**
     * Sends a message indicating that the user selected to watch the current movie and the search has ended.
     * <br>
     * The inline keyboard is removed.
     */
    void sendWeRecommendWatch(Update botUpdate);

    /**
     * Sends a message indicating that the user is going from the <b>'our choice' mode</b> to the <b>main menu</b>.
     * <br>
     * The inline keyboard is removed.
     */
    void handleWeRecommendToMainMenu(Update botUpdate);

    /**
     * Sends a message asking the user to send a message with his <b>feedback</b>.
     */
    void sendFeedbackAtLaunchGreetings(Update botUpdate);

    /**
     * Sends a message with the <b>confirmation inline keyboard buttons</b> for the user to choose, if he wants to save
     * the message he sent as feedback.
     */
    void sendFeedbackConfirmation(Update botUpdate);

    /**
     * Sends a message with the <b>farewell message</b> at the end of the <b>send feedback</b> process.
     */
    void sendFeedbackAtEndFarewell(Update botUpdate);

    /**
     * Return a <b>boolean value</b> indicating if the user has pressed the <b>send feedback reply keyboard button</b>.
     */
    boolean isSendFeedbackPressed();

    /**
     * Sends a new messages with the removed <b>reply keyboard</b> when the bot is terminated.
     */
    void removeReplyKeyboard();

    /**
     * Adds the <b>chat id</b> to the list to keep track of the users who have interacted with the bot.
     */
    void addChatId(Update botUpdate);

    //<editor-fold default-state="collapsed" desc="Boolean Methods">
    default boolean isMessage(Update botUpdate) { return messageExistenceValidator.test(botUpdate); }

    default boolean isSmartSearchButton(@NotNull Update botUpdate) {
        String messageText = botUpdate.getMessage().getText();
        if (messageText == null)
            return false;

        return stringValidator.test(
                messageText,
                BotMessageManager.getInstance().getSmartSearchReplyButton());
    }

    default boolean isStartCommand(Update botUpdate) { return startCommandValidator.test(botUpdate); }

    default boolean isHelpCommand(Update botUpdate) { return helpCommandValidator.test(botUpdate); }

    default boolean isLangCommand(Update botUpdate) { return langCommandValidator.test(botUpdate); }

    default boolean isNoIdeaButton(@NotNull Update botUpdate) {
        String messageText = botUpdate.getMessage().getText();
        if (messageText == null)
            return false;

        return stringValidator.test(
                messageText,
                BotMessageManager.getInstance().getNoIdeaReplyButton());
    }

    default boolean isWeRecommendButton(@NotNull Update botUpdate) {
        String messageText = botUpdate.getMessage().getText();
        if (messageText == null)
            return false;

        return stringValidator.test(
                messageText,
                BotMessageManager.getInstance().getWeRecommendReplyButton());
    }

    default boolean isSendFeedbackButton(@NotNull Update botUpdate) {
        String messageText = botUpdate.getMessage().getText();
        if (messageText == null)
            return false;

        return stringValidator.test(
                messageText,
                BotMessageManager.getInstance().getSendFeedbackReplyButton());
    }
    //</editor-fold>
}
