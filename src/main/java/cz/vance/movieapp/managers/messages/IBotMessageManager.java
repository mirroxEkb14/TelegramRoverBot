package cz.vance.movieapp.managers.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import java.util.List;
import cz.vance.movieapp.models.BotMessage;
//</editor-fold>

/**
 * Declares methods working with message lists from the {@link BotMessage} class.
 */
public interface IBotMessageManager {

    List<String> getSmartSearchAtLaunchGreetings();
    List<String> getSmartSearchMoodSelection();
    List<String> getSmartSearchCatalogueSelection();
    List<String> getSmartSearchGenreSelection();
    List<String> getSmartSearchVerifying();
    List<String> getOnSmartSearchRebooted();
    List<String> getSmartSearchVerified();
    List<String> getSmartSearchSampling();
    List<String> getSmartSearchOnFinish();
    List<String> getSmartSearchNoMovies();
    List<String> getSmartSearchOnFailure();
    List<String> getOnSmartSearchKeyboardRemoved();
    List<String> getOnSmartSearchOldKeyboardRemoved();
    List<String> getOnSmartSearchRepeatedKeyboardRemoved();
    List<String> getOnSmartSearchCatalogueBack();
    List<String> getOnSmartSearchGenreBack();
    List<String> getOnSmartSearchConfirmationBack();

    List<String> getNoIdeaAtLaunchGreetings();
    List<String> getNoIdeaNoMoviesLeft();
    List<String> getNoIdeaToMainMenu();

    List<String> getWelcomes();
    List<String> getHelps();
    List<String> getUnknownInputs();

    int getSmartSearchAtLaunchGreetingsSize();
    int getSmartSearchMoodSelectionSize();
    int getSmartSearchCatalogueSelectionSize();
    int getSmartSearchGenreSelectionSize();
    int getSmartSearchVerifyingSize();
    int getOnSmartSearchRebootedSize();
    int getSmartSearchVerifiedSize();
    int getSmartSearchSamplingSize();
    int getSmartSearchOnFinishSize();
    int getSmartSearchNoMoviesSize();
    int getSmartSearchOnFailureSize();
    int getOnSmartSearchKeyboardRemovedSize();
    int getOnSmartSearchOldKeyboardRemovedSize();
    int getOnSmartSearchRepeatedKeyboardRemovedSize();
    int getOnSmartSearchCatalogueBackSize();
    int getOnSmartSearchGenreBackSize();
    int getOnSmartSearchConfirmationBackSize();

    int getNoIdeaAtLaunchGreetingsSize();
    int getNoIdeaNoMoviesLeftSize();
    int getNoIdeaToMainMenuSize();

    int getWelcomesSize();
    int getHelpsSize();
    int getUnknownInputsSize();

    String getSmartSearchReplyButton();
    String getOurChoiceReplyButton();
    String getNoIdeaReplyButton();
    String getFeedbackReplyButton();

    String getDepressionInlineButton();
    String getCheerfulInlineButton();
    String getFightingInlineButton();
    String getFamilyInlineButton();
    String getFriendsInlineButton();
    String getLoveInlineButton();

    String getMovieButton();
    String getSeriesButton();
    String getComedyButton();
    String getDramaButton();
    String getMelodramaButton();
    String getThrillerButton();
    String getActionButton();
    String getFictionButton();
    String getDetectiveButton();
    String getFamilyButton();
    String getSportButton();
    String getFantasyButton();
    String getAnimationButton();
    String getAdventureButton();
    String getBiographyButton();
    String getCriminalButton();
    String getDocumentaryButton();
    String getWarButton();
    String getMusicButton();
    String getSmartSearchYesButton();
    String getSmartSearchNoButton();
    String getSmartSearchBackButton();

    String getLeftArrowButton();
    String getRightArrowButton();

    String getNoIdeaNextMovieButton();
    String getNoIdeaPreviousMovieButton();
    String getNoIdeaToMainMenuButton();
}
