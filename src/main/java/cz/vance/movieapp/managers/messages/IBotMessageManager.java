package cz.vance.movieapp.managers.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import java.util.List;
import cz.vance.movieapp.models.BotMessage;
//</editor-fold>

/**
 * Declares methods working with message lists from the {@link BotMessage} class.
 */
public interface IBotMessageManager {

    /**
     * Loads a new {@link BotMessage} instance with another language.
     */
    void changeBotMessageLanguage();

    /**
     * Returns a <b>boolean value</b>, whether the current language is English.
     */
    boolean isEngLanguage();

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

    List<String> getWeRecommendAtLaunchGreetings();
    List<String> getWeRecommendPleasantViewing();
    String getWeRecommendRusSampleMovieMessage();
    List<String> getWeRecommendToMainMenu();

    String getSendFeedbackAtLaunchGreetings();
    List<String> getSendFeedbackConfirmation();
    List<String> getSendFeedbackNoConfirmationMessage();
    List<String> getSendFeedbackYesConfirmationMessage();

    List<String> getWelcomes();
    List<String> getHelps();
    List<String> getLangMessage();
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

    int getWeRecommendAtLaunchGreetingsSize();
    int getWeRecommendPleasantViewingSize();
    int getWeRecommendToMainMenuSize();

    int getSendFeedbackConfirmationSize();
    int getSendFeedbackNoConfirmationMessageSize();
    int getSendFeedbackYesConfirmationMessageSize();

    int getWelcomesSize();
    int getHelpsSize();
    int getLangMessageSize();
    int getUnknownInputsSize();

    String getSmartSearchReplyButton();
    String getWeRecommendReplyButton();
    String getNoIdeaReplyButton();
    String getSendFeedbackReplyButton();

    String getDepressionMoodInlineButton();
    String getCheerfulMoodInlineButton();
    String getFightingMoodInlineButton();
    String getFamilyMoodInlineButton();
    String getFriendsMoodInlineButton();
    String getLoveMoodInlineButton();

    String getMovieInlineButton();
    String getSeriesInlineButton();
    String getComedyInlineButton();
    String getDramaInlineButton();
    String getMelodramaInlineButton();
    String getThrillerInlineButton();
    String getActionInlineButton();
    String getFictionInlineButton();
    String getDetectiveInlineButton();
    String getFamilyInlineButton();
    String getSportInlineButton();
    String getFantasyInlineButton();
    String getAnimationInlineButton();
    String getAdventureInlineButton();
    String getBiographyInlineButton();
    String getCriminalInlineButton();
    String getDocumentaryInlineButton();
    String getWarInlineButton();
    String getMusicInlineButton();
    String getSmartSearchYesInlineButton();
    String getSmartSearchNoInlineButton();
    String getSmartSearchBackInlineButton();

    String getLeftArrowInlineButton();
    String getRightArrowInlineButton();

    String getNoIdeaNextMovieInlineButton();
    String getNoIdeaPreviousMovieInlineButton();
    String getNoIdeaToMainMenuInlineButton();

    String getWeRecommendNextMovieInlineButton();
    String getWeRecommendPreviousMovieInlineButton();
    String getWeRecommendToMainMenuInlineButton();
    String getWeRecommendWatchInlineButton();

    String getSendFeedbackYesInlineButton();
    String getSendFeedbackNoInlineButton();
}
