package cz.vance.movieapp.managers.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import java.util.List;
import cz.vance.movieapp.models.BotMessage;
//</editor-fold>

/**
 * Declares methods working with message lists from the {@link BotMessage} class.
 */
public interface IBotMessageManager {

    List<String> getAtLaunchGreetings();
    List<String> getMoodSelection();
    List<String> getCatalogueSelection();
    List<String> getGenreSelection();
    List<String> getVerifying();
    List<String> getOnSmartSearchRebooted();
    List<String> getVerified();
    List<String> getSampling();
    List<String> getOnFinish();
    List<String> getNoMovies();
    List<String> getOnFailure();
    List<String> getOnSmartSearchKeyboardRemoved();
    List<String> getOnSmartSearchOldKeyboardRemoved();
    List<String> getOnSmartSearchRepeatedKeyboardRemoved();
    List<String> getOnSmartSearchCatalogueBack();
    List<String> getOnSmartSearchGenreBack();
    List<String> getOnSmartSearchConfirmationBack();

    List<String> getWelcomes();
    List<String> getHelps();
    List<String> getUnknownInputs();

    int getAtLaunchGreetingsSize();
    int getMoodSelectionSize();
    int getCatalogueSelectionSize();
    int getGenreSelectionSize();
    int getVerifyingSize();
    int getOnSmartSearchRebootedSize();
    int getVerifiedSize();
    int getSamplingSize();
    int getOnFinishSize();
    int getNoMoviesSize();
    int getOnFailureSize();
    int getOnSmartSearchKeyboardRemovedSize();
    int getOnSmartSearchOldKeyboardRemovedSize();
    int getOnSmartSearchRepeatedKeyboardRemovedSize();
    int getOnSmartSearchCatalogueBackSize();
    int getOnSmartSearchGenreBackSize();
    int getOnSmartSearchConfirmationBackSize();

    int getWelcomesSize();
    int getHelpsSize();
    int getUnknownInputsSize();

    String getSmartSearchReplyButton();
    String getOurChoiceReplyButton();
    String getNoIdeasReplyButton();
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
    String getLeftArrowButton();
    String getRightArrowButton();
    String getSmartSearchYesButton();
    String getSmartSearchNoButton();
    String getSmartSearchBackButton();
}
