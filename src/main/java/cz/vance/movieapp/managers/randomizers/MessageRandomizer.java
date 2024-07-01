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
    public String getSmartSearchAtLaunchGreetingsMessage() {
        return botMessageManager.getSmartSearchAtLaunchGreetings().get(
                getRandomIndex(botMessageManager.getSmartSearchAtLaunchGreetingsSize()));
    }

    @Override
     public String getSmartSearchMoodMessage() {
        return botMessageManager.getSmartSearchMoodSelection().get(
                getRandomIndex(botMessageManager.getSmartSearchMoodSelectionSize()));
    }

    @Override
    public String getSmartSearchCatalogueMessage() {
        return botMessageManager.getSmartSearchCatalogueSelection().get(
                getRandomIndex(botMessageManager.getSmartSearchCatalogueSelectionSize()));
    }

    @Override
    public String getSmartSearchGenreMessage() {
        return botMessageManager.getSmartSearchGenreSelection().get(
                getRandomIndex(botMessageManager.getSmartSearchGenreSelectionSize()));
    }

    @Override
    public String getSmartSearchVerifyingMessage(@NotNull UserSelection userSelection) {
        return String.format(
                botMessageManager.getSmartSearchVerifying().get(
                        getRandomIndex(botMessageManager.getSmartSearchVerifyingSize())),
                userSelection.getMood(), userSelection.getCatalogue(), userSelection.getGenre());
    }

    @Override
    public String getOnSmartSearchRebooted() {
        return botMessageManager.getOnSmartSearchRebooted().get(
                getRandomIndex(botMessageManager.getOnSmartSearchRebootedSize()));
    }

    @Override
    public String getSmartSearchVerifiedMessage() {
        return botMessageManager.getSmartSearchVerified().get(
                getRandomIndex(botMessageManager.getSmartSearchVerifiedSize()));
    }

    @Override
    public String getSmartSearchSamplingMessage() {
        return botMessageManager.getSmartSearchSampling().get(
                getRandomIndex(botMessageManager.getSmartSearchSamplingSize()));
    }

    @Override
    public String getSmartSearchOnFinishMessage() {
        return String.format(
                botMessageManager.getSmartSearchOnFinish().get(
                        getRandomIndex(botMessageManager.getSmartSearchOnFinishSize())),
                botMessageManager.getSendFeedbackReplyButton());
    }

    @Override
    public String getSmartSearchNoMoviesMessage() {
        return botMessageManager.getSmartSearchNoMovies().get(
                getRandomIndex(botMessageManager.getSmartSearchNoMoviesSize()));
    }

    @Override
    public String getSmartSearchOnFailureMessage() {
        return botMessageManager.getSmartSearchOnFailure().get(
                getRandomIndex(botMessageManager.getSmartSearchOnFailureSize()));
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
    public String getOnSmartSearchCatalogueBackMessage() {
        return botMessageManager.getOnSmartSearchCatalogueBack().get(
                getRandomIndex(botMessageManager.getOnSmartSearchCatalogueBackSize()));
    }

    @Override
    public String getOnSmartSearchGenreBackMessage() {
        return botMessageManager.getOnSmartSearchGenreBack().get(
                getRandomIndex(botMessageManager.getOnSmartSearchGenreBackSize()));
    }

    @Override
    public String getOnSmartSearchConfirmationBackMessage() {
        return botMessageManager.getOnSmartSearchConfirmationBack().get(
                getRandomIndex(botMessageManager.getOnSmartSearchConfirmationBackSize()));
    }

    @Override
    public String getNoIdeaAtLaunchGreetingsMessage() {
        return botMessageManager.getNoIdeaAtLaunchGreetings().get(
                getRandomIndex(botMessageManager.getNoIdeaAtLaunchGreetingsSize()));
    }

    @Override
    public String getNoIdeaNoMoviesLeftMessage() {
        return botMessageManager.getNoIdeaNoMoviesLeft().get(
                getRandomIndex(botMessageManager.getNoIdeaNoMoviesLeftSize()));
    }

    @Override
    public String getNoIdeaToMainMenuMessage() {
        return botMessageManager.getNoIdeaToMainMenu().get(
                getRandomIndex(botMessageManager.getNoIdeaToMainMenuSize()));
    }

    @Override
    public String getWeRecommendAtLaunchGreetingsMessage() {
        return botMessageManager.getWeRecommendAtLaunchGreetings().get(
                getRandomIndex(botMessageManager.getWeRecommendAtLaunchGreetingsSize()));
    }

    @Override
    public String getWeRecommendPleasantViewingMessage() {
        return botMessageManager.getWeRecommendPleasantViewing().get(
                getRandomIndex(botMessageManager.getWeRecommendPleasantViewingSize()));
    }

    @Override
    public String getWeRecommendSampleMovieMessage() {
        return botMessageManager.getWeRecommendSampleMovieMessage();
    }

    @Override
    public String getWeRecommendToMainMenuMessage() {
        return botMessageManager.getWeRecommendToMainMenu().get(
                getRandomIndex(botMessageManager.getWeRecommendToMainMenuSize()));
    }

    @Override
    public String getSendFeedbackAtLaunchGreetingsMessage() {
        return botMessageManager.getSendFeedbackAtLaunchGreetings();
    }

    @Override
    public String getSendFeedbackConfirmationMessage(String userFeedback) {
        return String.format(
                botMessageManager.getSendFeedbackConfirmation().get(
                        getRandomIndex(botMessageManager.getSendFeedbackConfirmationSize())),
                userFeedback);
    }

    @Override
    public String getSendFeedbackNoConfirmationMessage() {
        return botMessageManager.getSendFeedbackNoConfirmationMessage().get(
                getRandomIndex(botMessageManager.getSendFeedbackNoConfirmationMessageSize()));
    }

    @Override
    public String getSendFeedbackYesConfirmationMessage() {
        return botMessageManager.getSendFeedbackYesConfirmationMessage().get(
                getRandomIndex(botMessageManager.getSendFeedbackYesConfirmationMessageSize()));
    }

    @Override
    public String getWelcomeMessage() {
        return botMessageManager.getWelcomes().get(
                getRandomIndex(botMessageManager.getWelcomesSize()));
    }

    @Override
    public String getHelpMessage() { return botMessageManager.getHelps().get(0); }

    @Override
    public String getLangMessage() { return botMessageManager.getLangMessage().get(
            getRandomIndex(botMessageManager.getLangMessageSize())); }

    @Override
    public String getUnknownInputMessage() { return botMessageManager.getUnknownInputs().get(0); }

    @Override
    public String getOnSmartSearchDeviationMessage() {
        return botMessageManager.getOnSmartSearchDeviation().get(
                getRandomIndex(botMessageManager.getOnSmartSearchDeviationSize()));
    }

    @Override
    public String getOnWeRecommendDeviationMessage() {
        return botMessageManager.getOnWeRecommendDeviation().get(
                getRandomIndex(botMessageManager.getOnWeRecommendDeviationSize()));
    }

    @Override
    public String getOnNoIdeaDeviationMessage() {
        return botMessageManager.getOnNoIdeaDeviation().get(
                getRandomIndex(botMessageManager.getOnNoIdeaDeviationSize()));
    }

    @Override
    public String getOnSendFeedbackDeviationMessage() {
        return botMessageManager.getOnSendFeedbackDeviation().get(
                getRandomIndex(botMessageManager.getOnSendFeedbackDeviationSize()));
    }

    @Override
    public String getMovieRatingAtLaunchGreetingsMessage() {
        return botMessageManager.getMovieRatingAtLaunchGreetings().get(
                getRandomIndex(botMessageManager.getMovieRatingAtLaunchGreetingsSize()));
    }

    @Override
    public String getMovieRatingConfirmationMessage(String userMovieRating) {
        return String.format(botMessageManager.getMovieRatingConfirmation().get(
                    getRandomIndex(botMessageManager.getMovieRatingConfirmationSize())),
                userMovieRating);
    }

    @Override
    public String getMovieRatingSampleMessage() { return botMessageManager.getMovieRatingSampleMessage(); }

    @Override
    public String getMovieRatingNoConfirmationMessage() {
        return botMessageManager.getMovieRatingNoConfirmationMessage().get(
                getRandomIndex(botMessageManager.getMovieRatingNoConfirmationMessageSize()));
    }

    @Override
    public String getMovieRatingYesConfirmationMessage() {
        return botMessageManager.getMovieRatingYesConfirmationMessage().get(
                getRandomIndex(botMessageManager.getMovieRatingYesConfirmationMessageSize()));
    }

    @Override
    public String getMovieRatingWrongSampleFormatMessage() {
        return botMessageManager.getMovieRatingWrongSampleFormatMessage().get(
                getRandomIndex(botMessageManager.getMovieRatingWrongSampleFormatMessageSize()));
    }

    @Override
    public String getOnBotLaunchMessage() {
        return botMessageManager.getOnBotLaunchMessage().get(
                getRandomIndex(botMessageManager.getOnBotLaunchMessageSize()));
    }

    @Override
    public String getOnBotTerminatedMessage() {
        return botMessageManager.getOnBotTerminatedMessage().get(
                getRandomIndex(botMessageManager.getOnBotTerminatedMessageSize()));
    }

    @Override
    public String getSmartSearchBeginningSticker() {
        final List<Sticker> smartSearchStickers = stickerManager.getStickersByCategory(StickerCategory.SMART_SEARCH_BEGINNING);
        final int randomIndex = getRandomIndex(smartSearchStickers.size());
        return smartSearchStickers.get(randomIndex).fileId();
    }

    @Override
    public String getSmartSearchEndSticker() {
        final List<Sticker> smartSearchStickers = stickerManager.getStickersByCategory(StickerCategory.SMART_SEARCH_END);
        final int randomIndex = getRandomIndex(smartSearchStickers.size());
        return smartSearchStickers.get(randomIndex).fileId();
    }

    @Override
    public String getNoIdeaBeginningSticker() {
        final List<Sticker> noIdeaStickers = stickerManager.getStickersByCategory(StickerCategory.NO_IDEA_BEGINNING);
        final int randomIndex = getRandomIndex(noIdeaStickers.size());
        return noIdeaStickers.get(randomIndex).fileId();
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
