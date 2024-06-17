package cz.vance.movieapp.randomizers;

/**
 * Declares a set of basic operations for getting messages that are sent to the user while <b>SmartSearch</b>.
 */
public interface ISmartSearchRandomizer {

    /**
     * Extracts a random element from the <b>moodSelection</b> list that contains all the possible texts when the
     * user clicks the <b>🎬 Smart search</b> button.
     * <br>
     * The bot sends this random message before the <b>mood selection keyboard</b> appearing.
     *
     * @return Element from the <b>moodSelection</b> list.
     */
    String getMoodMessage();

    /**
     * Extracts a random element from the <b>catalogueSelection</b> list that contains all the possible texts when the
     * user clicks one of the <b>mood selection</b> buttons.
     * <br>
     * The bot sends this random message before the <b>movie/series selection keyboard</b> appearing.
     *
     * @return Element from the <b>catalogueSelection</b> list.
     */
    String getCatalogueMessage();

    /**
     * Extracts a random element from the <b>genreSelection</b> list that contains all the possible texts when the
     * user clicks one of the <b>catalogue selection</b> buttons.
     * <br>
     * The bot sends this random message before the <b>genre selection keyboard</b> appearing.
     *
     * @return Element from the <b>genreSelection</b> list.
     */
    String getGenreMessage();

    /**
     * Extracts a random sticker from the <b>.json sticker file</b> for the <b>smart search</b> feature, when
     * the user presses the <b>smart search</b> reply button.
     *
     * @return The file id of a random sticker.
     */
    String getRandomSmartSearchBeginningSticker();

    /**
     * Extracts a random sticker from the <b>.json sticker file</b> for the <b>end</b> of the <b>smart search</b> feature,
     * when the user has pressed some of the <b>genre selection</b> buttons (the last step of the <b>smart search</b>).
     *
     * @return The file id of a random sticker.
     */
    String getRandomSmartSearchEndSticker();

    /**
     * Extracts a random sticker from the <b>.json sticker file</b> for the <b>welcome</b> greeting.
     *
     * @return The file id of a random sticker.
     */
    String getRandomWelcomeSticker();
}
