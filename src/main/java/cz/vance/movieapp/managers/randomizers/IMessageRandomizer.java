package cz.vance.movieapp.managers.randomizers;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.UserSelection;
import org.jetbrains.annotations.NotNull;
//</editor-fold>

/**
 * Declares a set of basic operations for getting messages that are sent to the user while <b>SmartSearch</b>.
 */
public interface IMessageRandomizer {

    /**
     * Extracts a random element from the <b>atLaunch</b> list that contains the texts that are sent right after the
     * user clicks the <b>🎬 Smart search</b> button.
     */
    String getSmartSearchAtLaunchGreetingsMessage();

    /**
     * Extracts a random element from the <b>moodSelection</b> list that contains all the possible texts when the
     * user clicks the <b>🎬 Smart search</b> button.
     * <br>
     * The bot sends this random message before the <b>mood selection keyboard</b> appearing.
     */
    String getSmartSearchMoodMessage();

    /**
     * Extracts a random element from the <b>catalogueSelection</b> list that contains all the possible texts when the
     * user clicks one of the <b>mood selection</b> buttons.
     * <br>
     * The bot sends this random message before the <b>movie/series selection keyboard</b> appearing.
     */
    String getSmartSearchCatalogueMessage();

    /**
     * Extracts a random element from the <b>genreSelection</b> list that contains all the possible texts when the
     * user clicks one of the <b>catalogue selection</b> buttons.
     * <br>
     * The bot sends this random message before the <b>genre selection keyboard</b> appearing.
     */
    String getSmartSearchGenreMessage();

    /**
     * Extracts a random element from the <b>verifying</b> list that contains all the possible texts when the
     * user clicks one of the <b>genre selection</b> buttons, meaning the last step of the <b>smart search</b>.
     * <br>
     * The bot sends this random message right after the <b>genre selection</b> for the user to check if the
     * data he entered is correct.
     *
     * @param userSelection the user's selection instance containing the data from the <b>smart search</b>.
     */
    String getSmartSearchVerifyingMessage(@NotNull UserSelection userSelection);

    /**
     * Extracts a random element from the <b>onSmartSearchRebooted</b> list that contains all the possible texts when the
     * user clicks the <b>no confirmation</b> inline button.
     */
    String getOnSmartSearchRebooted();

    /**
     * Extracts a random element from the <b>verified</b> list that contains all the possible texts when the
     * user clicks one of the <b>verifying</b> inline buttons, on the <b>verifying</b> message.
     * <br>
     * The bot sends this random message right after he interacts with some inline buttons on the <b>verifying</b>
     * message.
     */
    String getSmartSearchVerifiedMessage();

    /**
     * Extracts a random element from the <b>sampling</b> list that contains all the possible texts when the
     * user clicks one of the <b>genre selection</b> buttons, meaning the last step of the <b>smart search</b>.
     * <br>
     * The message contains a list of found movies/series, apart from the default sampling message from the
     * the <b>.json file</b>.
     * <br>
     * The bot sends this random message right after the pause after the genre selection has passed.
     */
    String getSmartSearchSamplingMessage();

    /**
     * Extracts a random element from the <b>onFinish</b> list that contains all the possible texts when the
     * <b>smart search</b> has finished successfully/unsuccessfully.
     */
    String getSmartSearchOnFinishMessage();

    /**
     * Extracts a random element from the <b>noMovies</b> list that contains all the possible texts when the
     * user clicks one of the <b>genre selection</b> buttons, meaning the last step of the <b>smart search</b>.
     * <br>
     * The bot sends this random message right after the pause after the genre selection has passed.
     */
    String getSmartSearchNoMoviesMessage();

    /**
     * Extracts a random element from the <b>onFailure</b> list that contains all the possible texts when the
     * <b>no movies</b> message is sent.
     */
    String getSmartSearchOnFailureMessage();

    /**
     * Extracts a random element from the <b>onSmartSearchKeyboardRemoved</b> list that contains all the possible texts
     * when the <b>smart search</b> is completed, the inline keyboard is removed.
     */
    String getOnSmartSearchKeyboardRemovedMessage();

    /**
     * Extracts a random element from the <b>onSmartSearchOldKeyboardRemoved</b> list that contains all the possible texts
     * when the user tries to interact with the <b>smart search</b> inline keyboard that has been already sent to him.
     */
    String getOnSmartSearchOldKeyboardRemovedMessage();

    /**
     * Extracts a random element from the <b>onSmartSearchRepeatedKeyboardRemoved</b> list that contains all the possible
     * texts when the user triggers the bot to send a new message with an inline keyboard, while the chat already
     * contains a message with an inline keyboard.
     */
    String getOnSmartSearchRepeatedKeyboardRemovedMessage();

    /**
     * Extracts a random element from the <b>onSmartSearchCatalogueBack</b> list that contains all the possible texts
     * when the user clicks the <b>back</b> inline button when he selects <b>movies/series</b> (catalogue); and now
     * goes back to the <b>mood</b> selection.
     */
    String getOnSmartSearchCatalogueBackMessage();

    /**
     * Extracts a random element from the <b>onSmartSearchGenreBack</b> list that contains all the possible texts
     * when the user clicks the <b>back</b> inline button when he selects <b>genre</b>; and now goes back to the
     * <b>movies/series</b> selection.
     */
    String getOnSmartSearchGenreBackMessage();

    /**
     * Extracts a random element from the <b>onSmartSearchConfirmationBack</b> list that contains all the possible texts
     * when the user clicks the <b>back</b> inline button when he verifies the data he entered; and now goes back to the
     * <b>genre</b> selection.
     */
    String getOnSmartSearchConfirmationBackMessage();

    /**
     * Extracts a random element from the <b>noIdeaAtLaunchGreetings</b> list that contains all the possible texts
     * when the user clicks the <b>no idea</b> reply button in the <b>main menu</b>.
     */
    String getNoIdeaAtLaunchGreetingsMessage();

    /**
     * Extracts a random element from the <b>noIdeaNoMoviesLeft</b> list that contains all the possible texts
     * when the user during the <b>no idea</b> went through all the movies/series and there are no more left in the DB.
     */
    String getNoIdeaNoMoviesLeftMessage();

    /**
     * Extracts a random element from the <b>noIdeaToMainMenu</b> list that contains all the possible texts
     * when the user during the <b>no idea</b> goes back to the <b>main menu</b>.
     */
    String getNoIdeaToMainMenuMessage();

    /**
     * Extracts a random element from the <b>weRecommendAtLaunchGreetings</b> list that contains all the possible texts
     * when the user clicks the <b>we recommend</b> reply button in the <b>main menu</b>.
     */
    String getWeRecommendAtLaunchGreetingsMessage();

    /**
     * Extracts a random element from the <b>weRecommendPleasantViewing</b> list that contains all the possible texts
     * when the user during the <b>we recommend</b> presses the <b>watch inline keyboard button</b>.
     */
    String getWeRecommendPleasantViewingMessage();

    /**
     * Extracts the <b>weRecommendRusSampleMovie</b> string that contains the sample text to display to the user during
     * the <b>we recommend</b> mode.
     */
    String getWeRecommendSampleMovieMessage();

    /**
     * Extracts a random element from the <b>weRecommendToMainMenu</b> list that contains all the possible texts
     * when the user during the <b>we recommend</b> goes back to the <b>main menu</b>.
     */
    String getWeRecommendToMainMenuMessage();

    /**
     * Extract the <b>sendFeedbackAtLaunchGreetings</b> string that contains the text when the user clicks the <b>send
     * feedback</b> reply button in the <b>main menu</b>.
     */
    String getSendFeedbackAtLaunchGreetingsMessage();

    /**
     * Extracts a random element from the <b>sendFeedbackConfirmation</b> list that contains all the possible texts
     * when the user during the <b>send feedback</b> presses the <b>confirmation</b> inline keyboard button.
     *
     * @param userFeedback <b>String</b> object representing user's feedback message.
     */
    String getSendFeedbackConfirmationMessage(String userFeedback);

    /**
     * Extracts a random element from the <b>sendFeedbackNoConfirmation</b> list that contains all the possible texts
     * when the user during the <b>send feedback</b> presses the <b>no confirmation</b> inline keyboard button.
     */
    String getSendFeedbackNoConfirmationMessage();

    /**
     * Extracts the <b>sendFeedbackYesConfirmation</b> string that contains the text when the user during the <b>send
     * feedback</b> presses the <b>yes confirmation</b> inline keyboard button.
     */
    String getSendFeedbackYesConfirmationMessage();

    /**
     * Extracts a random element from the <b>welcomes</b> list that contains all the possible texts when the
     * user starts the conversation with the bot for the first time or when enters the appropriate <b>/start</b> command.
     */
    String getWelcomeMessage();

    /**
     * Extracts the only one element from the <b>helps</b> list that contains the text when the user enters the
     * appropriate <b>/help</b> command.
     */
    String getHelpMessage();

    /**
     * Extracts a random element from the <b>langMessage</b> list that contains all the possible texts when the user
     * changes the language of the bot using the <b>/lang</b> command.
     */
    String getLangMessage();

    /**
     * Extracts the only one element from the <b>unknownInput</b> list that contains the text when the user enters
     * some unknown input.
     */
    String getUnknownInputMessage();

    /**
     * Extracts the only one element from the <b>onBotTerminatedMessage</b> list that contains the text for the user
     * when the bot is terminated.
     */
    String getOnBotTerminatedMessage();

    /**
     * Extracts a random sticker from the <b>.json sticker file</b> for the <b>smart search</b> feature, when
     * the user presses the <b>smart search</b> reply button.
     *
     * @return The file id of a random sticker.
     */
    String getSmartSearchBeginningSticker();

    /**
     * Extracts a random sticker from the <b>.json sticker file</b> for the <b>end</b> of the <b>smart search</b> feature,
     * when the user has pressed some of the <b>genre selection</b> buttons (the last step of the <b>smart search</b>).
     *
     * @return The file id of a random sticker.
     */
    String getSmartSearchEndSticker();

    /**
     * Extracts a random sticker from the <b>.json sticker file</b> for the <b>no idea</b> feature, when the user
     * presses the <b>no idea</b> reply button.
     *
     * @return The file id of a random sticker.
     */
    String getNoIdeaBeginningSticker();

    /**
     * Extracts a random sticker from the <b>.json sticker file</b> for the <b>welcome</b> greeting.
     *
     * @return The file id of a random sticker.
     */
    String getWelcomeSticker();
}
