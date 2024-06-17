package cz.vance.movieapp.randomizers;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.managers.stickers.IStickerManager;
import cz.vance.movieapp.managers.stickers.StickerManager;
import cz.vance.movieapp.models.Sticker;
import cz.vance.movieapp.utils.StickerCategory;

import java.util.List;
import java.util.Random;
//</editor-fold>

/**
 * Implements a set of basic operations for getting texts for messages the bot sends while the <b>SmartSearch</b>.
 *
 * @see ISmartSearchRandomizer
 */
public final class SmartSearchRandomizer implements ISmartSearchRandomizer {

    private static final Random random = new Random();
    private static final IStickerManager stickerManager = StickerManager.getInstance();

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
                getRandomIndex(moodSelection.size()));
    }

    @Override
    public String getCatalogueMessage() {
        return catalogueSelection.get(
                getRandomIndex(catalogueSelection.size()));
    }

    @Override
    public String getGenreMessage() {
        return genreSelection.get(
                getRandomIndex(genreSelection.size()));
    }

    @Override
    public String getRandomSmartSearchBeginningSticker() {
        final List<Sticker> smartSearchStickers = stickerManager.getStickersByCategory(StickerCategory.SMART_SEARCH_BEGINNING);
        final int randomIndex = getRandomIndex(smartSearchStickers.size());
        return smartSearchStickers.get(randomIndex).fileId();
    }

    @Override
    public String getRandomSmartSearchEndSticker() {
        final List<Sticker> smartSearchStickers = stickerManager.getStickersByCategory(StickerCategory.SMART_SEARCH_END);
        final int randomIndex = getRandomIndex(smartSearchStickers.size());
        return smartSearchStickers.get(randomIndex).fileId();
    }

    @Override
    public String getRandomWelcomeSticker() {
        final List<Sticker> welcomeStickers = stickerManager.getStickersByCategory(StickerCategory.WELCOME);
        final int randomIndex = getRandomIndex(welcomeStickers.size());
        return welcomeStickers.get(randomIndex).fileId();
    }

    //<editor-fold default-state="collapsed" desc="Private Methods">
    private int getRandomIndex(int size) { return random.nextInt(size); }
    //</editor-fold>
}
