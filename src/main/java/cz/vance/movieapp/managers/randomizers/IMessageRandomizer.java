package cz.vance.movieapp.managers.randomizers;

/**
 * Declares a set of basic operations for getting messages that are sent to the user while <b>SmartSearch</b>.
 */
public interface IMessageRandomizer {

    /**
     * Extracts a random element from the <b>atLaunch</b> list that contains the texts that are sent right after the
     * user clicks the <b>🎬 Smart search</b> button.
     */
    String getAtLaunchMessage();

    /**
     * Extracts a random element from the <b>moodSelection</b> list that contains all the possible texts when the
     * user clicks the <b>🎬 Smart search</b> button.
     * <br>
     * The bot sends this random message before the <b>mood selection keyboard</b> appearing.
     */
    String getMoodMessage();

    /**
     * Extracts a random element from the <b>catalogueSelection</b> list that contains all the possible texts when the
     * user clicks one of the <b>mood selection</b> buttons.
     * <br>
     * The bot sends this random message before the <b>movie/series selection keyboard</b> appearing.
     */
    String getCatalogueMessage();

    /**
     * Extracts a random element from the <b>genreSelection</b> list that contains all the possible texts when the
     * user clicks one of the <b>catalogue selection</b> buttons.
     * <br>
     * The bot sends this random message before the <b>genre selection keyboard</b> appearing.
     */
    String getGenreMessage();

    /**
     * Extracts a random element from the <b>verifying</b> list that contains all the possible texts when the
     * user clicks one of the <b>genre selection</b> buttons, meaning the last step of the <b>smart search</b>.
     * <br>
     * The bot sends this random message right after the <b>genre selection</b> for the user to check if the
     * data he entered is correct.
     */
    String getVerifyingMessage();

    /**
     * Extracts a random element from the <b>verified</b> list that contains all the possible texts when the
     * user clicks one of the <b>verifying</b> inline buttons, on the <b>verifying</b> message.
     * <br>
     * The bot sends this random message right after he interacts with some inline buttons on the <b>verifying</b>
     * message.
     */
    String getVerifiedMessage();

    /**
     * Extracts a random element from the <b>sampling</b> list that contains all the possible texts when the
     * user clicks one of the <b>genre selection</b> buttons, meaning the last step of the <b>smart search</b>.
     * <br>
     * The message contains a list of found movies/series, apart from the default sampling message from the
     * the <b>.json file</b>.
     * <br>
     * The bot sends this random message right after the pause after the genre selection has passed.
     */
    String getSamplingMessage();

    /**
     * Extracts a random element from the <b>onFinish</b> list that contains all the possible texts when the
     * <b>smart search</b> has finished successfully/unsuccessfully.
     */
    String getOnFinishMessage();

    /**
     * Extracts a random element from the <b>noMovies</b> list that contains all the possible texts when the
     * user clicks one of the <b>genre selection</b> buttons, meaning the last step of the <b>smart search</b>.
     * <br>
     * The bot sends this random message right after the pause after the genre selection has passed.
     */
    String getNoMoviesMessage();

    /**
     * Extracts a random element from the <b>onFailure</b> list that contains all the possible texts when the
     * <b>no movies</b> message is sent.
     */
    String getOnFailureMessage();

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
     * Extracts the only one element from the <b>unknownInput</b> list that contains the text when the user enters
     * some unknown input.
     */
    String getUnknownInputMessage();

    /**
     * Extracts a random sticker from the <b>.json sticker file</b> for the <b>smart search</b> feature, when
     * the user presses the <b>smart search</b> reply button.
     *
     * @return The file id of a random sticker.
     */
    String getBeginningSticker();

    /**
     * Extracts a random sticker from the <b>.json sticker file</b> for the <b>end</b> of the <b>smart search</b> feature,
     * when the user has pressed some of the <b>genre selection</b> buttons (the last step of the <b>smart search</b>).
     *
     * @return The file id of a random sticker.
     */
    String getEndSticker();

    /**
     * Extracts a random sticker from the <b>.json sticker file</b> for the <b>welcome</b> greeting.
     *
     * @return The file id of a random sticker.
     */
    String getWelcomeSticker();
}
