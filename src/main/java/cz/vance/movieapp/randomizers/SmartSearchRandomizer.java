package cz.vance.movieapp.randomizers;

import java.util.List;
import java.util.Random;

/**
 * This <b>SmartSearchRandomizer</b> class implements a set of basic operations for getting texts for message the bot
 * sends while <b>SmartSearch</b>
 *
 * @see ISmartSearchRandomizer
 */
public final class SmartSearchRandomizer implements ISmartSearchRandomizer {

    public static final Random random = new Random();

//<editor-fold default-state="collapsed" desc="Lists">
    private static final List<String> atLaunch = List.of(
            "It's a movie time! 3.. 2.. 1..",
            "Time to eat some popcorn then...");

    private static final List<String> moodSelection = List.of(
            "Now, let me know your spirit condition",
            "Share your mood with me");

    private static final List<String> catalogueSelection = List.of(
            "Understood, now you'd want to watch...",
            "Roger that, now would you rather watch a movie or start watching some cool series?");

    private static final List<String> genreSelection = List.of(
            "Ok then, here is maybe the most difficult part... choose a genre:",
            "Well-well, what about a genre?");
//</editor-fold>

    @Override
     public String getMoodMessage() {
        return moodSelection.get(
                random.nextInt(moodSelection.size()));
    }

    @Override
    public String getCatalogueMessage() {
        return catalogueSelection.get(
                random.nextInt(catalogueSelection.size()));
    }

    @Override
    public String getGenreMessage() {
        return genreSelection.get(
                random.nextInt(genreSelection.size()));
    }
}
