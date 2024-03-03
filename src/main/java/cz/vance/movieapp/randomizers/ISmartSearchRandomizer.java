package cz.vance.movieapp.randomizers;

/**
 * This <b>ISmartSearchRandomizer</b> class declares a set of basic operations for getting messages that are sent to
 * the user while <b>SmartSearch</b>
 */
public interface ISmartSearchRandomizer {

    /**
     * Extracts a random element from the <b>moodSelection</b> list that contains all the possible texts when the
     * user clicks <b>🎬 Smart search</b> and now a <b>mood selection keyboard</b> should appear
     *
     * @return A randomly chosen element from the <b>moodSelection</b> list
     */
    String getMoodMessage();

    /**
     * Extracts a random element from the <b>catalogueSelection</b> list that contains all the possible texts when the
     * user clicks one of the <b>SmartSearch</b> buttons and now a <b>movie/series selection keyboard</b> should appear
     *
     * @return A randomly chosen element from the <b>catalogueSelection</b> list
     */
    String getCatalogueMessage();

    /**
     * Extracts a random element from the <b>genreSelection</b> list that contains all the possible texts when the
     * user clicks one of the <b>movie/series selection</b> buttons and now a <b>genre selection keyboard</b> should
     * appear
     *
     * @return A randomly chosen element from the <b>genreSelection</b> list
     */
    String getGenreMessage();
}
