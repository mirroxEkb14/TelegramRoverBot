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

    private static final String JSON_FILE_PATH = "/bot-messages.json";
    private BotMessage botMessage;

    //<editor-fold default-state="collapsed" desc="Singleton">
    private static BotMessageManager instance;

    private BotMessageManager() { loadBotMessages(); }

    public static BotMessageManager getInstance() {
        if (instance == null)
            instance = new BotMessageManager();
        return instance;
    }
    //</editor-fold>

    private void loadBotMessages() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            botMessage = mapper.readValue(
                    BotMessageManager.class.getResourceAsStream(JSON_FILE_PATH),
                    BotMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //<editor-fold default-state="collapsed" desc="Smart Search Getters">
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

    //<editor-fold default-state="collapsed" desc="No Idea Getters">
    public List<String> getNoIdeaAtLaunchGreetings() { return botMessage.getNoIdeaMessage().getNoIdeaAtLaunchGreetings(); }
    public List<String> getNoIdeaNoMoviesLeft() { return botMessage.getNoIdeaMessage().getNoIdeaNoMoviesLeft(); }
    public List<String> getNoIdeaToMainMenu() { return botMessage.getNoIdeaMessage().getNoIdeaToMainMenu(); }

    public int getNoIdeaAtLaunchGreetingsSize() { return botMessage.getNoIdeaMessage().getNoIdeaAtLaunchGreetings().size(); }
    public int getNoIdeaNoMoviesLeftSize() { return botMessage.getNoIdeaMessage().getNoIdeaNoMoviesLeft().size(); }
    public int getNoIdeaToMainMenuSize() { return botMessage.getNoIdeaMessage().getNoIdeaToMainMenu().size(); }
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

    //<editor-fold default-state="collapsed" desc="Unknown Input Getters">
    @Override
    public List<String> getUnknownInputs() { return botMessage.getCommandMessage().getUnknownInputs(); }
    @Override
    public int getUnknownInputsSize() { return botMessage.getCommandMessage().getUnknownInputs().size(); }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Reply Keyboard Button Getters">
    @Override
    public String getSmartSearchReplyButton() { return botMessage.getReplyButton().getSmartSearchReplyButton(); }
    @Override
    public String getOurChoiceReplyButton() { return botMessage.getReplyButton().getOurChoiceReplyButton(); }
    @Override
    public String getNoIdeaReplyButton() { return botMessage.getReplyButton().getNoIdeaReplyButton(); }
    @Override
    public String getFeedbackReplyButton() { return botMessage.getReplyButton().getFeedbackReplyButton(); }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Inline Keyboard Button Getters">
    @Override
    public String getDepressionInlineButton() { return botMessage.getInlineButton().getDepressionInlineButton(); }
    @Override
    public String getCheerfulInlineButton() { return botMessage.getInlineButton().getCheerfulInlineButton(); }
    @Override
    public String getFightingInlineButton() { return botMessage.getInlineButton().getFightingInlineButton(); }
    @Override
    public String getFamilyInlineButton() { return botMessage.getInlineButton().getFamilyInlineButton(); }
    @Override
    public String getFriendsInlineButton() { return botMessage.getInlineButton().getFriendsInlineButton(); }
    @Override
    public String getLoveInlineButton() { return botMessage.getInlineButton().getLoveInlineButton(); }
    @Override
    public String getMovieButton() { return botMessage.getInlineButton().getMovieButton(); }
    @Override
    public String getSeriesButton() { return botMessage.getInlineButton().getSeriesButton(); }
    @Override
    public String getComedyButton() { return botMessage.getInlineButton().getComedyButton(); }
    @Override
    public String getDramaButton() { return botMessage.getInlineButton().getDramaButton(); }
    @Override
    public String getMelodramaButton() { return botMessage.getInlineButton().getMelodramaButton(); }
    @Override
    public String getThrillerButton() { return botMessage.getInlineButton().getThrillerButton(); }
    @Override
    public String getActionButton() { return botMessage.getInlineButton().getActionButton(); }
    @Override
    public String getFictionButton() { return botMessage.getInlineButton().getFictionButton(); }
    @Override
    public String getDetectiveButton() { return botMessage.getInlineButton().getDetectiveButton(); }
    @Override
    public String getFamilyButton() { return botMessage.getInlineButton().getFamilyButton(); }
    @Override
    public String getSportButton() { return botMessage.getInlineButton().getSportButton(); }
    @Override
    public String getFantasyButton() { return botMessage.getInlineButton().getFantasyButton(); }
    @Override
    public String getAnimationButton() { return botMessage.getInlineButton().getAnimationButton(); }
    @Override
    public String getAdventureButton() { return botMessage.getInlineButton().getAdventureButton(); }
    @Override
    public String getBiographyButton() { return botMessage.getInlineButton().getBiographyButton(); }
    @Override
    public String getCriminalButton() { return botMessage.getInlineButton().getCriminalButton(); }
    @Override
    public String getDocumentaryButton() { return botMessage.getInlineButton().getDocumentaryButton(); }
    @Override
    public String getWarButton() { return botMessage.getInlineButton().getWarButton(); }
    @Override
    public String getMusicButton() { return botMessage.getInlineButton().getMusicButton(); }
    @Override
    public String getSmartSearchYesButton() { return botMessage.getInlineButton().getSmartSearchYesButton(); }
    @Override
    public String getSmartSearchNoButton() { return botMessage.getInlineButton().getSmartSearchNoButton(); }
    @Override
    public String getSmartSearchBackButton() { return botMessage.getInlineButton().getSmartSearchBackButton(); }
    @Override
    public String getLeftArrowButton() { return botMessage.getInlineButton().getLeftArrowButton(); }
    @Override
    public String getRightArrowButton() { return botMessage.getInlineButton().getRightArrowButton(); }
    @Override
    public String getNoIdeaNextMovieButton() { return botMessage.getInlineButton().getNoIdeaNextMovieButton(); }
    @Override
    public String getNoIdeaPreviousMovieButton() { return botMessage.getInlineButton().getNoIdeaPreviousMovieButton(); }
    @Override
    public String getNoIdeaToMainMenuButton() { return botMessage.getInlineButton().getNoIdeaToMainMenuButton(); }
    //</editor-fold>
}
