package cz.vance.movieapp.managers.randomizers;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.managers.messages.BotMessageManager;
import cz.vance.movieapp.managers.messages.IBotMessageManager;
import cz.vance.movieapp.managers.stickers.IStickerManager;
import cz.vance.movieapp.managers.stickers.StickerManager;
import cz.vance.movieapp.models.Sticker;
import cz.vance.movieapp.models.UserSelection;
import cz.vance.movieapp.utils.StickerCategory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;
//</editor-fold>

/**
 * Implements a set of basic operations for getting texts for messages the bot sends while the <b>SmartSearch</b>.
 * <br>
 * Contains a {@link IBotMessageManager} instance providing all the possible texts for the bot messages.
 */
public final class MessageRandomizer implements IMessageRandomizer {

    private static final Random random = new Random();
    private static final IBotMessageManager botMessageManager = BotMessageManager.getInstance();
    private static final IStickerManager stickerManager = StickerManager.getInstance();

    @Override
    public String getAtLaunchMessage() {
        return botMessageManager.getAtLaunchGreetings().get(
                getRandomIndex(botMessageManager.getAtLaunchGreetingsSize()));
    }

    @Override
     public String getMoodMessage() {
        return botMessageManager.getMoodSelection().get(
                getRandomIndex(botMessageManager.getMoodSelectionSize()));
    }

    @Override
    public String getCatalogueMessage() {
        return botMessageManager.getCatalogueSelection().get(
                getRandomIndex(botMessageManager.getCatalogueSelectionSize()));
    }

    @Override
    public String getGenreMessage() {
        return botMessageManager.getGenreSelection().get(
                getRandomIndex(botMessageManager.getGenreSelectionSize()));
    }

    @Override
    public String getVerifyingMessage(@NotNull UserSelection userSelection) {
        return String.format(
                botMessageManager.getVerifying().get(
                        getRandomIndex(botMessageManager.getVerifyingSize())),
                userSelection.getMood(), userSelection.getCatalogue(), userSelection.getGenre());
    }

    @Override
    public String getOnSmartSearchRebooted() {
        return botMessageManager.getOnSmartSearchRebooted().get(
                getRandomIndex(botMessageManager.getOnSmartSearchRebootedSize()));
    }

    @Override
    public String getVerifiedMessage() {
        return botMessageManager.getVerified().get(
                getRandomIndex(botMessageManager.getVerifiedSize()));
    }

    @Override
    public String getSamplingMessage() {
        return botMessageManager.getSampling().get(
                getRandomIndex(botMessageManager.getSamplingSize()));
    }

    @Override
    public String getOnFinishMessage() {
        return botMessageManager.getOnFinish().get(
                getRandomIndex(botMessageManager.getOnFinishSize()));
    }

    @Override
    public String getNoMoviesMessage() {
        return botMessageManager.getNoMovies().get(
                getRandomIndex(botMessageManager.getNoMoviesSize()));
    }

    @Override
    public String getOnFailureMessage() {
        return botMessageManager.getOnFailure().get(
                getRandomIndex(botMessageManager.getOnFailureSize()));
    }

    @Override
    public String getOnSmartSearchKeyboardRemovedMessage() {
        return botMessageManager.getOnSmartSearchKeyboardRemoved().get(
                getRandomIndex(botMessageManager.getOnSmartSearchKeyboardRemovedSize()));
    }

    @Override
    public String getOnSmartSearchOldKeyboardRemovedMessage() {
        return botMessageManager.getOnSmartSearchOldKeyboardRemoved().get(
                getRandomIndex(botMessageManager.getOnSmartSearchOldKeyboardRemovedSize()));
    }

    @Override
    public String getOnSmartSearchRepeatedKeyboardRemovedMessage() {
        return botMessageManager.getOnSmartSearchRepeatedKeyboardRemoved().get(
                getRandomIndex(botMessageManager.getOnSmartSearchRepeatedKeyboardRemovedSize()));
    }

    @Override
    public String getWelcomeMessage() {
        return botMessageManager.getWelcomes().get(
                getRandomIndex(botMessageManager.getWelcomesSize()));
    }

    @Override
    public String getHelpMessage() { return botMessageManager.getHelps().get(0); }

    @Override
    public String getUnknownInputMessage() { return botMessageManager.getUnknownInputs().get(0); }

    @Override
    public String getBeginningSticker() {
        final List<Sticker> smartSearchStickers = stickerManager.getStickersByCategory(StickerCategory.SMART_SEARCH_BEGINNING);
        final int randomIndex = getRandomIndex(smartSearchStickers.size());
        return smartSearchStickers.get(randomIndex).fileId();
    }

    @Override
    public String getEndSticker() {
        final List<Sticker> smartSearchStickers = stickerManager.getStickersByCategory(StickerCategory.SMART_SEARCH_END);
        final int randomIndex = getRandomIndex(smartSearchStickers.size());
        return smartSearchStickers.get(randomIndex).fileId();
    }

    @Override
    public String getWelcomeSticker() {
        final List<Sticker> welcomeStickers = stickerManager.getStickersByCategory(StickerCategory.WELCOME);
        final int randomIndex = getRandomIndex(welcomeStickers.size());
        return welcomeStickers.get(randomIndex).fileId();
    }

    //<editor-fold default-state="collapsed" desc="Private Methods">
    private int getRandomIndex(int size) { return random.nextInt(size); }
    //</editor-fold>
}
