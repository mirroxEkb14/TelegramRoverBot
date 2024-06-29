package cz.vance.movieapp.managers.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.vance.movieapp.models.BotMessage;

import java.io.IOException;
import java.util.List;
//</editor-fold>

/**
 * Implements methods for getting bot messages from the {@link BotMessage} instance.
 * <br>
 * Contains a {@link BotMessage} instance providing all the possible texts for the bot messages.
 */
public final class BotMessageManager implements IBotMessageManager {

    private static final String JSON_RUS_FILE_PATH = "/bot-messages_rus.json";
    private static final String JSON_ENG_FILE_PATH = "/bot-messages_eng.json";
    private BotMessage botMessage;

    /**
     * Indicates the language of the bot messages.
     * <br>
     * By default (when the bot is launched), the language is set to Russian.
     */
    private static boolean isEngLanguage;

    //<editor-fold default-state="collapsed" desc="Singleton">
    private static BotMessageManager instance;

    private BotMessageManager() { loadBotMessages(isEngLanguage); }

    public static BotMessageManager getInstance() {
        if (instance == null)
            instance = new BotMessageManager();
        return instance;
    }
    //</editor-fold>

    private void loadBotMessages(boolean isEngFile) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            botMessage = mapper.readValue(
                    BotMessageManager.class.getResourceAsStream(isEngFile ? JSON_ENG_FILE_PATH : JSON_RUS_FILE_PATH),
                    BotMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeBotMessageLanguage() {
        updateLanguageTracker();
        loadBotMessages(isEngLanguage);
    }

    @Override
    public boolean isEngLanguage() { return isEngLanguage; }

    //<editor-fold default-state="collapsed" desc="'Smart Search' Getters">
    @Override
    public List<String> getSmartSearchAtLaunchGreetings() { return botMessage.getSmartSearchMessage().getSmartSearchAtLaunchGreetings(); }
    @Override
    public List<String> getSmartSearchMoodSelection() { return botMessage.getSmartSearchMessage().getSmartSearchMoodSelection(); }
    @Override
    public List<String> getSmartSearchCatalogueSelection() { return botMessage.getSmartSearchMessage().getSmartSearchCatalogueSelection(); }
    @Override
    public List<String> getSmartSearchGenreSelection() { return botMessage.getSmartSearchMessage().getSmartSearchGenreSelection(); }
    @Override
    public List<String> getSmartSearchVerifying() { return botMessage.getSmartSearchMessage().getSmartSearchVerifying(); }
    @Override
    public List<String> getOnSmartSearchRebooted() { return botMessage.getSmartSearchMessage().getOnSmartSearchRebooted(); }
    @Override
    public List<String> getSmartSearchVerified() { return botMessage.getSmartSearchMessage().getSmartSearchVerified(); }
    @Override
    public List<String> getSmartSearchSampling() { return botMessage.getSmartSearchMessage().getSmartSearchSampling(); }
    @Override
    public List<String> getSmartSearchOnFinish() { return botMessage.getSmartSearchMessage().getSmartSearchOnFinish(); }
    @Override
    public List<String> getSmartSearchNoMovies() { return botMessage.getSmartSearchMessage().getSmartSearchNoMovies(); }
    @Override
    public List<String> getSmartSearchOnFailure() { return botMessage.getSmartSearchMessage().getSmartSearchOnFailure(); }
    @Override
    public List<String> getOnSmartSearchKeyboardRemoved() { return botMessage.getSmartSearchMessage().getOnSmartSearchKeyboardRemoved(); }
    @Override
    public List<String> getOnSmartSearchOldKeyboardRemoved() { return botMessage.getSmartSearchMessage().getOnSmartSearchOldKeyboardRemoved(); }
    @Override
    public List<String> getOnSmartSearchRepeatedKeyboardRemoved() { return botMessage.getSmartSearchMessage().getOnSmartSearchRepeatedKeyboardRemoved(); }
    @Override
    public List<String> getOnSmartSearchCatalogueBack() { return botMessage.getSmartSearchMessage().getOnSmartSearchCatalogueBack(); }
    @Override
    public List<String> getOnSmartSearchGenreBack() { return botMessage.getSmartSearchMessage().getOnSmartSearchGenreBack(); }
    @Override
    public List<String> getOnSmartSearchConfirmationBack() { return botMessage.getSmartSearchMessage().getOnSmartSearchConfirmationBack(); }

    @Override
    public int getSmartSearchAtLaunchGreetingsSize() { return botMessage.getSmartSearchMessage().getSmartSearchAtLaunchGreetings().size(); }
    @Override
    public int getSmartSearchMoodSelectionSize() { return botMessage.getSmartSearchMessage().getSmartSearchMoodSelection().size(); }
    @Override
    public int getSmartSearchCatalogueSelectionSize() { return botMessage.getSmartSearchMessage().getSmartSearchCatalogueSelection().size(); }
    @Override
    public int getSmartSearchGenreSelectionSize() { return botMessage.getSmartSearchMessage().getSmartSearchGenreSelection().size(); }
    @Override
    public int getSmartSearchVerifyingSize() { return botMessage.getSmartSearchMessage().getSmartSearchVerifying().size(); }
    @Override
    public int getOnSmartSearchRebootedSize() { return botMessage.getSmartSearchMessage().getOnSmartSearchRebooted().size(); }
    @Override
    public int getSmartSearchVerifiedSize() { return botMessage.getSmartSearchMessage().getSmartSearchVerified().size(); }
    @Override
    public int getSmartSearchSamplingSize() { return botMessage.getSmartSearchMessage().getSmartSearchSampling().size(); }
    @Override
    public int getSmartSearchOnFinishSize() { return botMessage.getSmartSearchMessage().getSmartSearchOnFinish().size(); }
    @Override
    public int getSmartSearchNoMoviesSize() { return botMessage.getSmartSearchMessage().getSmartSearchNoMovies().size(); }
    @Override
    public int getSmartSearchOnFailureSize() { return botMessage.getSmartSearchMessage().getSmartSearchOnFailure().size(); }
    @Override
    public int getOnSmartSearchKeyboardRemovedSize() { return botMessage.getSmartSearchMessage().getOnSmartSearchKeyboardRemoved().size(); }
    @Override
    public int getOnSmartSearchOldKeyboardRemovedSize() { return botMessage.getSmartSearchMessage().getOnSmartSearchOldKeyboardRemoved().size(); }
    @Override
    public int getOnSmartSearchRepeatedKeyboardRemovedSize() { return botMessage.getSmartSearchMessage().getOnSmartSearchRepeatedKeyboardRemoved().size(); }
    @Override
    public int getOnSmartSearchCatalogueBackSize() { return botMessage.getSmartSearchMessage().getOnSmartSearchCatalogueBack().size(); }
    @Override
    public int getOnSmartSearchGenreBackSize() { return botMessage.getSmartSearchMessage().getOnSmartSearchGenreBack().size(); }
    @Override
    public int getOnSmartSearchConfirmationBackSize() { return botMessage.getSmartSearchMessage().getOnSmartSearchConfirmationBack().size(); }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'No Idea' Getters">
    public List<String> getNoIdeaAtLaunchGreetings() { return botMessage.getNoIdeaMessage().getNoIdeaAtLaunchGreetings(); }
    public List<String> getNoIdeaNoMoviesLeft() { return botMessage.getNoIdeaMessage().getNoIdeaNoMoviesLeft(); }
    public List<String> getNoIdeaToMainMenu() { return botMessage.getNoIdeaMessage().getNoIdeaToMainMenu(); }

    public int getNoIdeaAtLaunchGreetingsSize() { return botMessage.getNoIdeaMessage().getNoIdeaAtLaunchGreetings().size(); }
    public int getNoIdeaNoMoviesLeftSize() { return botMessage.getNoIdeaMessage().getNoIdeaNoMoviesLeft().size(); }
    public int getNoIdeaToMainMenuSize() { return botMessage.getNoIdeaMessage().getNoIdeaToMainMenu().size(); }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'We Recommend' Getters">
    public List<String> getWeRecommendAtLaunchGreetings() { return botMessage.getWeRecommendMessage().getWeRecommendAtLaunchGreetings(); }
    public List<String> getWeRecommendPleasantViewing() { return botMessage.getWeRecommendMessage().getWeRecommendPleasantViewing(); }
    public String getWeRecommendSampleMovieMessage() { return botMessage.getWeRecommendMessage().getWeRecommendSampleMovieMessage(); }
    public List<String> getWeRecommendToMainMenu() { return botMessage.getWeRecommendMessage().getWeRecommendToMainMenu(); }

    public int getWeRecommendAtLaunchGreetingsSize() { return botMessage.getWeRecommendMessage().getWeRecommendAtLaunchGreetings().size(); }
    public int getWeRecommendPleasantViewingSize() { return botMessage.getWeRecommendMessage().getWeRecommendPleasantViewing().size(); }
    public int getWeRecommendToMainMenuSize() { return botMessage.getWeRecommendMessage().getWeRecommendToMainMenu().size(); }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'Send Feedback' Getters">
    public String getSendFeedbackAtLaunchGreetings() { return botMessage.getSendFeedbackMessage().getSendFeedbackAtLaunchGreetings(); }
    public List<String> getSendFeedbackConfirmation() { return botMessage.getSendFeedbackMessage().getSendFeedbackConfirmation(); }
    public List<String> getSendFeedbackNoConfirmationMessage() { return botMessage.getSendFeedbackMessage().getSendFeedbackNoConfirmationMessage(); }
    public List<String> getSendFeedbackYesConfirmationMessage() { return botMessage.getSendFeedbackMessage().getSendFeedbackYesConfirmationMessage(); }

    public int getSendFeedbackConfirmationSize() { return botMessage.getSendFeedbackMessage().getSendFeedbackConfirmation().size(); }
    public int getSendFeedbackNoConfirmationMessageSize() { return botMessage.getSendFeedbackMessage().getSendFeedbackNoConfirmationMessage().size(); }
    public int getSendFeedbackYesConfirmationMessageSize() { return botMessage.getSendFeedbackMessage().getSendFeedbackYesConfirmationMessage().size(); }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Welcome Getters">
    @Override
    public List<String> getWelcomes() { return botMessage.getCommandMessage().getWelcomes(); }
    @Override
    public int getWelcomesSize() { return botMessage.getCommandMessage().getWelcomes().size(); }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Help Getters">
    @Override
    public List<String> getHelps() { return botMessage.getCommandMessage().getHelps(); }
    @Override
    public int getHelpsSize() { return botMessage.getCommandMessage().getHelps().size(); }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Lang Getters">
    @Override
    public List<String> getLangMessage() { return botMessage.getCommandMessage().getLangMessage(); }
    @Override
    public int getLangMessageSize() { return botMessage.getCommandMessage().getLangMessage().size(); }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Unknown Input Getters">
    @Override
    public List<String> getUnknownInputs() { return botMessage.getCommandMessage().getUnknownInputs(); }
    @Override
    public int getUnknownInputsSize() { return botMessage.getCommandMessage().getUnknownInputs().size(); }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="On Bot Terminated Getters">
    @Override
    public List<String> getOnBotTerminatedMessage() { return botMessage.getOnBotTerminatedMessage(); }
    @Override
    public int getOnBotTerminatedMessageSize() { return botMessage.getOnBotTerminatedMessage().size(); }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Reply Keyboard Button Getters">
    @Override
    public String getSmartSearchReplyButton() { return botMessage.getReplyButton().getSmartSearchReplyButton(); }
    @Override
    public String getNoIdeaReplyButton() { return botMessage.getReplyButton().getNoIdeaReplyButton(); }
    @Override
    public String getWeRecommendReplyButton() { return botMessage.getReplyButton().getWeRecommendReplyButton(); }
    @Override
    public String getSendFeedbackReplyButton() { return botMessage.getReplyButton().getSendFeedbackReplyButton(); }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Inline Keyboard Button Getters">
    @Override
    public String getDepressionMoodInlineButton() { return botMessage.getInlineButton().getDepressionMoodInlineButton(); }
    @Override
    public String getCheerfulMoodInlineButton() { return botMessage.getInlineButton().getCheerfulMoodInlineButton(); }
    @Override
    public String getFightingMoodInlineButton() { return botMessage.getInlineButton().getFightingMoodInlineButton(); }
    @Override
    public String getFamilyMoodInlineButton() { return botMessage.getInlineButton().getFamilyMoodInlineButton(); }
    @Override
    public String getFriendsMoodInlineButton() { return botMessage.getInlineButton().getFriendsMoodInlineButton(); }
    @Override
    public String getLoveMoodInlineButton() { return botMessage.getInlineButton().getLoveMoodInlineButton(); }
    @Override
    public String getMovieInlineButton() { return botMessage.getInlineButton().getMovieInlineButton(); }
    @Override
    public String getSeriesInlineButton() { return botMessage.getInlineButton().getSeriesInlineButton(); }
    @Override
    public String getComedyInlineButton() { return botMessage.getInlineButton().getComedyInlineButton(); }
    @Override
    public String getDramaInlineButton() { return botMessage.getInlineButton().getDramaInlineButton(); }
    @Override
    public String getMelodramaInlineButton() { return botMessage.getInlineButton().getMelodramaInlineButton(); }
    @Override
    public String getThrillerInlineButton() { return botMessage.getInlineButton().getThrillerInlineButton(); }
    @Override
    public String getActionInlineButton() { return botMessage.getInlineButton().getActionInlineButton(); }
    @Override
    public String getFictionInlineButton() { return botMessage.getInlineButton().getFictionInlineButton(); }
    @Override
    public String getDetectiveInlineButton() { return botMessage.getInlineButton().getDetectiveInlineButton(); }
    @Override
    public String getFamilyInlineButton() { return botMessage.getInlineButton().getFamilyInlineButton(); }
    @Override
    public String getSportInlineButton() { return botMessage.getInlineButton().getSportInlineButton(); }
    @Override
    public String getFantasyInlineButton() { return botMessage.getInlineButton().getFantasyInlineButton(); }
    @Override
    public String getAnimationInlineButton() { return botMessage.getInlineButton().getAnimationInlineButton(); }
    @Override
    public String getAdventureInlineButton() { return botMessage.getInlineButton().getAdventureInlineButton(); }
    @Override
    public String getBiographyInlineButton() { return botMessage.getInlineButton().getBiographyInlineButton(); }
    @Override
    public String getCriminalInlineButton() { return botMessage.getInlineButton().getCriminalInlineButton(); }
    @Override
    public String getDocumentaryInlineButton() { return botMessage.getInlineButton().getDocumentaryInlineButton(); }
    @Override
    public String getWarInlineButton() { return botMessage.getInlineButton().getWarInlineButton(); }
    @Override
    public String getMusicInlineButton() { return botMessage.getInlineButton().getMusicInlineButton(); }
    @Override
    public String getSmartSearchYesInlineButton() { return botMessage.getInlineButton().getSmartSearchYesInlineButton(); }
    @Override
    public String getSmartSearchNoInlineButton() { return botMessage.getInlineButton().getSmartSearchNoInlineButton(); }
    @Override
    public String getSmartSearchBackInlineButton() { return botMessage.getInlineButton().getSmartSearchBackInlineButton(); }
    @Override
    public String getLeftArrowInlineButton() { return botMessage.getInlineButton().getLeftArrowInlineButton(); }
    @Override
    public String getRightArrowInlineButton() { return botMessage.getInlineButton().getRightArrowInlineButton(); }
    @Override
    public String getNoIdeaNextMovieInlineButton() { return botMessage.getInlineButton().getNoIdeaNextMovieInlineButton(); }
    @Override
    public String getNoIdeaPreviousMovieInlineButton() { return botMessage.getInlineButton().getNoIdeaPreviousMovieInlineButton(); }
    @Override
    public String getNoIdeaToMainMenuInlineButton() { return botMessage.getInlineButton().getNoIdeaToMainMenuInlineButton(); }
    @Override
    public String getWeRecommendNextMovieInlineButton() { return botMessage.getInlineButton().getWeRecommendNextMovieInlineButton(); }
    @Override
    public String getWeRecommendPreviousMovieInlineButton() { return botMessage.getInlineButton().getWeRecommendPreviousMovieInlineButton(); }
    @Override
    public String getWeRecommendToMainMenuInlineButton() { return botMessage.getInlineButton().getWeRecommendToMainMenuInlineButton(); }
    @Override
    public String getWeRecommendWatchInlineButton() { return botMessage.getInlineButton().getWeRecommendWatchInlineButton(); }
    @Override
    public String getSendFeedbackYesInlineButton() { return botMessage.getInlineButton().getSendFeedbackYesInlineButton(); }
    @Override
    public String getSendFeedbackNoInlineButton() { return botMessage.getInlineButton().getSendFeedbackNoInlineButton(); }
    //</editor-fold>

    private void updateLanguageTracker() { isEngLanguage = !isEngLanguage; }
}
