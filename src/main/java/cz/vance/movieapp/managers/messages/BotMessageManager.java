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
 *
 * @see cz.vance.movieapp.managers.randomizers.MessageRandomizer
 * @see cz.vance.movieapp.managers.messages.MessageManager
 */
public final class BotMessageManager implements IBotMessageManager {

    private static final String JSON_FILE_PATH = "/bot-messages.json";
    private BotMessage botMessage;

    /**
     * Indicates the language of the bot messages.
     * <br>
     * By default (when the bot is launched), the language is set to Russian.
     */
    private static boolean isEngLanguage;

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
            final ObjectMapper mapper = new ObjectMapper();
            botMessage = mapper.readValue(
                    BotMessageManager.class.getResourceAsStream(JSON_FILE_PATH),
                    BotMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeBotMessageLanguage() { updateLanguageTracker(); }

    @Override
    public boolean isEngLanguage() { return isEngLanguage; }

    //<editor-fold default-state="collapsed" desc="'Smart Search' Getters">
    @Override
    public List<String> getSmartSearchAtLaunchGreetings() {
        return isEngLanguage() ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchAtLaunchGreetings() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchAtLaunchGreetings();
    }
    @Override
    public List<String> getSmartSearchMoodSelection() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchMoodSelection() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchMoodSelection();
    }
    @Override
    public List<String> getSmartSearchCatalogueSelection() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchCatalogueSelection() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchCatalogueSelection();
    }
    @Override
    public List<String> getSmartSearchGenreSelection() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchGenreSelection() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchGenreSelection();
    }
    @Override
    public List<String> getSmartSearchVerifying() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchVerifying() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchVerifying();
    }
    @Override
    public List<String> getOnSmartSearchRebooted() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchRebooted() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchRebooted();
    }
    @Override
    public List<String> getSmartSearchVerified() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchVerified() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchVerified();
    }
    @Override
    public List<String> getSmartSearchSampling() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchSampling() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchSampling();
    }
    @Override
    public List<String> getSmartSearchOnFinish() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchOnFinish() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchOnFinish();
    }
    @Override
    public List<String> getSmartSearchNoMovies() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchNoMovies() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchNoMovies();
    }
    @Override
    public List<String> getSmartSearchOnFailure() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchOnFailure() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchOnFailure();
    }
    @Override
    public List<String> getOnSmartSearchKeyboardRemoved() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchKeyboardRemoved() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchKeyboardRemoved();
    }
    @Override
    public List<String> getOnSmartSearchOldKeyboardRemoved() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchOldKeyboardRemoved() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchOldKeyboardRemoved();
    }
    @Override
    public List<String> getOnSmartSearchRepeatedKeyboardRemoved() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchRepeatedKeyboardRemoved() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchRepeatedKeyboardRemoved();
    }
    @Override
    public List<String> getOnSmartSearchCatalogueBack() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchCatalogueBack() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchCatalogueBack();
    }
    @Override
    public List<String> getOnSmartSearchGenreBack() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchGenreBack() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchGenreBack();
    }
    @Override
    public List<String> getOnSmartSearchConfirmationBack() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchConfirmationBack() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchConfirmationBack();
    }

    @Override
    public int getSmartSearchAtLaunchGreetingsSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchAtLaunchGreetings().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchAtLaunchGreetings().size();
    }
    @Override
    public int getSmartSearchMoodSelectionSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchMoodSelection().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchMoodSelection().size();
    }
    @Override
    public int getSmartSearchCatalogueSelectionSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchCatalogueSelection().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchCatalogueSelection().size();
    }
    @Override
    public int getSmartSearchGenreSelectionSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchGenreSelection().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchGenreSelection().size();
    }
    @Override
    public int getSmartSearchVerifyingSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchVerifying().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchVerifying().size();
    }
    @Override
    public int getOnSmartSearchRebootedSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchRebooted().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchRebooted().size();
    }
    @Override
    public int getSmartSearchVerifiedSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchVerified().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchVerified().size();
    }
    @Override
    public int getSmartSearchSamplingSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchSampling().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchSampling().size();
    }
    @Override
    public int getSmartSearchOnFinishSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchOnFinish().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchOnFinish().size();
    }
    @Override
    public int getSmartSearchNoMoviesSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchNoMovies().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchNoMovies().size();
    }
    @Override
    public int getSmartSearchOnFailureSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getSmartSearchOnFailure().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getSmartSearchOnFailure().size();
    }
    @Override
    public int getOnSmartSearchKeyboardRemovedSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchKeyboardRemoved().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchKeyboardRemoved().size();
    }
    @Override
    public int getOnSmartSearchOldKeyboardRemovedSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchOldKeyboardRemoved().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchOldKeyboardRemoved().size();
    }
    @Override
    public int getOnSmartSearchRepeatedKeyboardRemovedSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchRepeatedKeyboardRemoved().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchRepeatedKeyboardRemoved().size();
    }
    @Override
    public int getOnSmartSearchCatalogueBackSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchCatalogueBack().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchCatalogueBack().size();
    }
    @Override
    public int getOnSmartSearchGenreBackSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchGenreBack().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchGenreBack().size();
    }
    @Override
    public int getOnSmartSearchConfirmationBackSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSmartSearchMessage().getOnSmartSearchConfirmationBack().size() :
                botMessage.getRusMessage().getSmartSearchMessage().getOnSmartSearchConfirmationBack().size();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'No Idea' Getters">
    public List<String> getNoIdeaAtLaunchGreetings() {
        return isEngLanguage ?
                botMessage.getEngMessage().getNoIdeaMessage().getNoIdeaAtLaunchGreetings() :
                botMessage.getRusMessage().getNoIdeaMessage().getNoIdeaAtLaunchGreetings();
    }
    public List<String> getNoIdeaNoMoviesLeft() {
        return isEngLanguage ?
                botMessage.getEngMessage().getNoIdeaMessage().getNoIdeaNoMoviesLeft() :
                botMessage.getRusMessage().getNoIdeaMessage().getNoIdeaNoMoviesLeft();
    }
    public List<String> getNoIdeaToMainMenu() {
        return isEngLanguage ?
                botMessage.getEngMessage().getNoIdeaMessage().getNoIdeaToMainMenu() :
                botMessage.getRusMessage().getNoIdeaMessage().getNoIdeaToMainMenu();
    }

    public int getNoIdeaAtLaunchGreetingsSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getNoIdeaMessage().getNoIdeaAtLaunchGreetings().size() :
                botMessage.getRusMessage().getNoIdeaMessage().getNoIdeaAtLaunchGreetings().size();
    }
    public int getNoIdeaNoMoviesLeftSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getNoIdeaMessage().getNoIdeaNoMoviesLeft().size() :
                botMessage.getRusMessage().getNoIdeaMessage().getNoIdeaNoMoviesLeft().size();
    }
    public int getNoIdeaToMainMenuSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getNoIdeaMessage().getNoIdeaToMainMenu().size() :
                botMessage.getRusMessage().getNoIdeaMessage().getNoIdeaToMainMenu().size();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'We Recommend' Getters">
    public List<String> getWeRecommendAtLaunchGreetings() {
        return isEngLanguage ?
                botMessage.getEngMessage().getWeRecommendMessage().getWeRecommendAtLaunchGreetings() :
                botMessage.getRusMessage().getWeRecommendMessage().getWeRecommendAtLaunchGreetings();
    }
    public List<String> getWeRecommendPleasantViewing() {
        return isEngLanguage ?
                botMessage.getEngMessage().getWeRecommendMessage().getWeRecommendPleasantViewing() :
                botMessage.getRusMessage().getWeRecommendMessage().getWeRecommendPleasantViewing();
    }
    public String getWeRecommendSampleMovieMessage() {
        return isEngLanguage ?
                botMessage.getEngMessage().getWeRecommendMessage().getWeRecommendSampleMovieMessage() :
                botMessage.getRusMessage().getWeRecommendMessage().getWeRecommendSampleMovieMessage();
    }
    public List<String> getWeRecommendToMainMenu() {
        return isEngLanguage ?
                botMessage.getEngMessage().getWeRecommendMessage().getWeRecommendToMainMenu() :
                botMessage.getRusMessage().getWeRecommendMessage().getWeRecommendToMainMenu();
    }

    public int getWeRecommendAtLaunchGreetingsSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getWeRecommendMessage().getWeRecommendAtLaunchGreetings().size() :
                botMessage.getRusMessage().getWeRecommendMessage().getWeRecommendAtLaunchGreetings().size();
    }
    public int getWeRecommendPleasantViewingSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getWeRecommendMessage().getWeRecommendPleasantViewing().size() :
                botMessage.getRusMessage().getWeRecommendMessage().getWeRecommendPleasantViewing().size();
    }
    public int getWeRecommendToMainMenuSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getWeRecommendMessage().getWeRecommendToMainMenu().size() :
                botMessage.getRusMessage().getWeRecommendMessage().getWeRecommendToMainMenu().size();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="'Send Feedback' Getters">
    public String getSendFeedbackAtLaunchGreetings() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSendFeedbackMessage().getSendFeedbackAtLaunchGreetings() :
                botMessage.getRusMessage().getSendFeedbackMessage().getSendFeedbackAtLaunchGreetings();
    }
    public List<String> getSendFeedbackConfirmation() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSendFeedbackMessage().getSendFeedbackConfirmation() :
                botMessage.getRusMessage().getSendFeedbackMessage().getSendFeedbackConfirmation();
    }
    public List<String> getSendFeedbackNoConfirmationMessage() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSendFeedbackMessage().getSendFeedbackNoConfirmationMessage() :
                botMessage.getRusMessage().getSendFeedbackMessage().getSendFeedbackNoConfirmationMessage();
    }
    public List<String> getSendFeedbackYesConfirmationMessage() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSendFeedbackMessage().getSendFeedbackYesConfirmationMessage() :
                botMessage.getRusMessage().getSendFeedbackMessage().getSendFeedbackYesConfirmationMessage();
    }

    public int getSendFeedbackConfirmationSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSendFeedbackMessage().getSendFeedbackConfirmation().size() :
                botMessage.getRusMessage().getSendFeedbackMessage().getSendFeedbackConfirmation().size();
    }
    public int getSendFeedbackNoConfirmationMessageSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSendFeedbackMessage().getSendFeedbackNoConfirmationMessage().size() :
                botMessage.getRusMessage().getSendFeedbackMessage().getSendFeedbackNoConfirmationMessage().size();
    }
    public int getSendFeedbackYesConfirmationMessageSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getSendFeedbackMessage().getSendFeedbackYesConfirmationMessage().size() :
                botMessage.getRusMessage().getSendFeedbackMessage().getSendFeedbackYesConfirmationMessage().size();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Welcome Getters">
    @Override
    public List<String> getWelcomes() {
        return isEngLanguage ?
                botMessage.getEngMessage().getCommandMessage().getWelcomes() :
                botMessage.getRusMessage().getCommandMessage().getWelcomes();
    }
    @Override
    public int getWelcomesSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getCommandMessage().getWelcomes().size() :
                botMessage.getRusMessage().getCommandMessage().getWelcomes().size();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Help Getters">
    @Override
    public List<String> getHelps() {
        return isEngLanguage ?
                botMessage.getEngMessage().getCommandMessage().getHelps() :
                botMessage.getRusMessage().getCommandMessage().getHelps();
    }
    @Override
    public int getHelpsSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getCommandMessage().getHelps().size() :
                botMessage.getRusMessage().getCommandMessage().getHelps().size();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Lang Getters">
    @Override
    public List<String> getLangMessage() {
        return isEngLanguage ?
                botMessage.getEngMessage().getCommandMessage().getLangMessage() :
                botMessage.getRusMessage().getCommandMessage().getLangMessage();
    }
    @Override
    public int getLangMessageSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getCommandMessage().getLangMessage().size() :
                botMessage.getRusMessage().getCommandMessage().getLangMessage().size();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Unknown Input Getters">
    @Override
    public List<String> getUnknownInputs() {
        return isEngLanguage ?
                botMessage.getEngMessage().getCommandMessage().getUnknownInputs() :
                botMessage.getRusMessage().getCommandMessage().getUnknownInputs();
    }
    @Override
    public int getUnknownInputsSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getCommandMessage().getUnknownInputs().size() :
                botMessage.getRusMessage().getCommandMessage().getUnknownInputs().size();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Deviation Message Getters">
    @Override
    public List<String> getOnSmartSearchDeviation() {
        return isEngLanguage ?
                botMessage.getEngMessage().getDeviationMessage().getOnSmartSearchDeviation() :
                botMessage.getRusMessage().getDeviationMessage().getOnSmartSearchDeviation();
    }
    @Override
    public List<String> getOnWeRecommendDeviation() {
        return isEngLanguage ?
                botMessage.getEngMessage().getDeviationMessage().getOnWeRecommendDeviation() :
                botMessage.getRusMessage().getDeviationMessage().getOnWeRecommendDeviation();
    }
    @Override
    public List<String> getOnNoIdeaDeviation() {
        return isEngLanguage ?
                botMessage.getEngMessage().getDeviationMessage().getOnNoIdeaDeviation() :
                botMessage.getRusMessage().getDeviationMessage().getOnNoIdeaDeviation();
    }
    @Override
    public List<String> getOnSendFeedbackDeviation() {
        return isEngLanguage ?
                botMessage.getEngMessage().getDeviationMessage().getOnSendFeedbackDeviation() :
                botMessage.getRusMessage().getDeviationMessage().getOnSendFeedbackDeviation();
    }

    @Override
    public int getOnSmartSearchDeviationSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getDeviationMessage().getOnSmartSearchDeviation().size() :
                botMessage.getRusMessage().getDeviationMessage().getOnSmartSearchDeviation().size();
    }
    @Override
    public int getOnWeRecommendDeviationSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getDeviationMessage().getOnWeRecommendDeviation().size() :
                botMessage.getRusMessage().getDeviationMessage().getOnWeRecommendDeviation().size();
    }
    @Override
    public int getOnNoIdeaDeviationSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getDeviationMessage().getOnNoIdeaDeviation().size() :
                botMessage.getRusMessage().getDeviationMessage().getOnNoIdeaDeviation().size();
    }
    @Override
    public int getOnSendFeedbackDeviationSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getDeviationMessage().getOnSendFeedbackDeviation().size() :
                botMessage.getRusMessage().getDeviationMessage().getOnSendFeedbackDeviation().size();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Movie Rating Getters">
    @Override
    public List<String> getMovieRatingAtLaunchGreetings() {
        return isEngLanguage ?
                botMessage.getEngMessage().getMovieRatingMessage().getMovieRatingAtLaunchGreetings() :
                botMessage.getRusMessage().getMovieRatingMessage().getMovieRatingAtLaunchGreetings();
    }
    @Override
    public List<String> getMovieRatingConfirmation() {
        return isEngLanguage ?
                botMessage.getEngMessage().getMovieRatingMessage().getMovieRatingConfirmation() :
                botMessage.getRusMessage().getMovieRatingMessage().getMovieRatingConfirmation();
    }
    @Override
    public String getMovieRatingSampleMessage() {
        return isEngLanguage ?
                botMessage.getEngMessage().getMovieRatingMessage().getMovieRatingSampleMessage() :
                botMessage.getRusMessage().getMovieRatingMessage().getMovieRatingSampleMessage();
    }
    @Override
    public List<String> getMovieRatingNoConfirmationMessage() {
        return isEngLanguage ?
                botMessage.getEngMessage().getMovieRatingMessage().getMovieRatingNoConfirmationMessage() :
                botMessage.getRusMessage().getMovieRatingMessage().getMovieRatingNoConfirmationMessage();
    }
    @Override
    public List<String> getMovieRatingYesConfirmationMessage() {
        return isEngLanguage ?
                botMessage.getEngMessage().getMovieRatingMessage().getMovieRatingYesConfirmationMessage() :
                botMessage.getRusMessage().getMovieRatingMessage().getMovieRatingYesConfirmationMessage();
    }
    @Override
    public List<String> getMovieRatingWrongSampleFormatMessage() {
        return isEngLanguage ?
                botMessage.getEngMessage().getMovieRatingMessage().getMovieRatingWrongSampleFormatMessage() :
                botMessage.getRusMessage().getMovieRatingMessage().getMovieRatingWrongSampleFormatMessage();
    }

    @Override
    public int getMovieRatingAtLaunchGreetingsSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getMovieRatingMessage().getMovieRatingAtLaunchGreetings().size() :
                botMessage.getRusMessage().getMovieRatingMessage().getMovieRatingAtLaunchGreetings().size();
    }
    @Override
    public int getMovieRatingConfirmationSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getMovieRatingMessage().getMovieRatingConfirmation().size() :
                botMessage.getRusMessage().getMovieRatingMessage().getMovieRatingConfirmation().size();
    }
    @Override
    public int getMovieRatingNoConfirmationMessageSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getMovieRatingMessage().getMovieRatingNoConfirmationMessage().size() :
                botMessage.getRusMessage().getMovieRatingMessage().getMovieRatingNoConfirmationMessage().size();
    }
    @Override
    public int getMovieRatingYesConfirmationMessageSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getMovieRatingMessage().getMovieRatingYesConfirmationMessage().size() :
                botMessage.getRusMessage().getMovieRatingMessage().getMovieRatingYesConfirmationMessage().size();
    }
    @Override
    public int getMovieRatingWrongSampleFormatMessageSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getMovieRatingMessage().getMovieRatingWrongSampleFormatMessage().size() :
                botMessage.getRusMessage().getMovieRatingMessage().getMovieRatingWrongSampleFormatMessage().size();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Bot Launch Getters">
    @Override
    public List<String> getOnBotLaunchMessage() {
        return isEngLanguage ?
                botMessage.getEngMessage().getOnBotLaunchMessage() :
                botMessage.getRusMessage().getOnBotLaunchMessage();
    }
    @Override
    public int getOnBotLaunchMessageSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getOnBotLaunchMessage().size() :
                botMessage.getRusMessage().getOnBotLaunchMessage().size();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="On Bot Terminated Getters">
    @Override
    public List<String> getOnBotTerminatedMessage() {
        return isEngLanguage ?
                botMessage.getEngMessage().getOnBotTerminatedMessage() :
                botMessage.getRusMessage().getOnBotTerminatedMessage();
    }
    @Override
    public int getOnBotTerminatedMessageSize() {
        return isEngLanguage ?
                botMessage.getEngMessage().getOnBotTerminatedMessage().size() :
                botMessage.getRusMessage().getOnBotTerminatedMessage().size();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Reply Keyboard Button Getters">
    @Override
    public String getSmartSearchReplyButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getReplyButton().getSmartSearchReplyButton() :
                botMessage.getRusMessage().getReplyButton().getSmartSearchReplyButton();
    }
    @Override
    public String getNoIdeaReplyButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getReplyButton().getNoIdeaReplyButton() :
                botMessage.getRusMessage().getReplyButton().getNoIdeaReplyButton();
    }
    @Override
    public String getWeRecommendReplyButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getReplyButton().getWeRecommendReplyButton() :
                botMessage.getRusMessage().getReplyButton().getWeRecommendReplyButton();
    }
    @Override
    public String getSendFeedbackReplyButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getReplyButton().getSendFeedbackReplyButton() :
                botMessage.getRusMessage().getReplyButton().getSendFeedbackReplyButton();
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Inline Keyboard Button Getters">
    @Override
    public String getDepressionMoodInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getDepressionMoodInlineButton() :
                botMessage.getRusMessage().getInlineButton().getDepressionMoodInlineButton();
    }
    @Override
    public String getCheerfulMoodInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getCheerfulMoodInlineButton() :
                botMessage.getRusMessage().getInlineButton().getCheerfulMoodInlineButton();
    }
    @Override
    public String getFightingMoodInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getFightingMoodInlineButton() :
                botMessage.getRusMessage().getInlineButton().getFightingMoodInlineButton();
    }
    @Override
    public String getFamilyMoodInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getFamilyMoodInlineButton() :
                botMessage.getRusMessage().getInlineButton().getFamilyMoodInlineButton();
    }
    @Override
    public String getFriendsMoodInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getFriendsMoodInlineButton() :
                botMessage.getRusMessage().getInlineButton().getFriendsMoodInlineButton();
    }
    @Override
    public String getLoveMoodInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getLoveMoodInlineButton() :
                botMessage.getRusMessage().getInlineButton().getLoveMoodInlineButton();
    }
    @Override
    public String getMovieInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getMovieInlineButton() :
                botMessage.getRusMessage().getInlineButton().getMovieInlineButton();
    }
    @Override
    public String getSeriesInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getSeriesInlineButton() :
                botMessage.getRusMessage().getInlineButton().getSeriesInlineButton();
    }
    @Override
    public String getComedyInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getComedyInlineButton() :
                botMessage.getRusMessage().getInlineButton().getComedyInlineButton();
    }
    @Override
    public String getDramaInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getDramaInlineButton() :
                botMessage.getRusMessage().getInlineButton().getDramaInlineButton();
    }
    @Override
    public String getMelodramaInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getMelodramaInlineButton() :
                botMessage.getRusMessage().getInlineButton().getMelodramaInlineButton();
    }
    @Override
    public String getThrillerInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getThrillerInlineButton() :
                botMessage.getRusMessage().getInlineButton().getThrillerInlineButton();
    }
    @Override
    public String getActionInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getActionInlineButton() :
                botMessage.getRusMessage().getInlineButton().getActionInlineButton();
    }
    @Override
    public String getFictionInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getFictionInlineButton() :
                botMessage.getRusMessage().getInlineButton().getFictionInlineButton();
    }
    @Override
    public String getDetectiveInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getDetectiveInlineButton() :
                botMessage.getRusMessage().getInlineButton().getDetectiveInlineButton();
    }
    @Override
    public String getFamilyInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getFamilyInlineButton() :
                botMessage.getRusMessage().getInlineButton().getFamilyInlineButton();
    }
    @Override
    public String getSportInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getSportInlineButton() :
                botMessage.getRusMessage().getInlineButton().getSportInlineButton();
    }
    @Override
    public String getFantasyInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getFantasyInlineButton() :
                botMessage.getRusMessage().getInlineButton().getFantasyInlineButton();
    }
    @Override
    public String getAnimationInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getAnimationInlineButton() :
                botMessage.getRusMessage().getInlineButton().getAnimationInlineButton();
    }
    @Override
    public String getAdventureInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getAdventureInlineButton() :
                botMessage.getRusMessage().getInlineButton().getAdventureInlineButton();
    }
    @Override
    public String getBiographyInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getBiographyInlineButton() :
                botMessage.getRusMessage().getInlineButton().getBiographyInlineButton();
    }
    @Override
    public String getCriminalInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getCriminalInlineButton() :
                botMessage.getRusMessage().getInlineButton().getCriminalInlineButton();
    }
    @Override
    public String getDocumentaryInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getDocumentaryInlineButton() :
                botMessage.getRusMessage().getInlineButton().getDocumentaryInlineButton();
    }
    @Override
    public String getWarInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getWarInlineButton() :
                botMessage.getRusMessage().getInlineButton().getWarInlineButton();
    }
    @Override
    public String getMusicInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getMusicInlineButton() :
                botMessage.getRusMessage().getInlineButton().getMusicInlineButton();
    }
    @Override
    public String getSmartSearchYesInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getSmartSearchYesInlineButton() :
                botMessage.getRusMessage().getInlineButton().getSmartSearchYesInlineButton();
    }
    @Override
    public String getSmartSearchNoInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getSmartSearchNoInlineButton() :
                botMessage.getRusMessage().getInlineButton().getSmartSearchNoInlineButton();
    }
    @Override
    public String getSmartSearchBackInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getSmartSearchBackInlineButton() :
                botMessage.getRusMessage().getInlineButton().getSmartSearchBackInlineButton();
    }
    @Override
    public String getLeftArrowInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getLeftArrowInlineButton() :
                botMessage.getRusMessage().getInlineButton().getLeftArrowInlineButton();
    }
    @Override
    public String getRightArrowInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getRightArrowInlineButton() :
                botMessage.getRusMessage().getInlineButton().getRightArrowInlineButton();
    }
    @Override
    public String getNoIdeaNextMovieInlineButton() {
            return isEngLanguage ?
                    botMessage.getEngMessage().getInlineButton().getNoIdeaNextMovieInlineButton() :
                    botMessage.getRusMessage().getInlineButton().getNoIdeaNextMovieInlineButton();
    }
    @Override
    public String getNoIdeaPreviousMovieInlineButton() {
            return isEngLanguage ?
                    botMessage.getEngMessage().getInlineButton().getNoIdeaPreviousMovieInlineButton() :
                    botMessage.getRusMessage().getInlineButton().getNoIdeaPreviousMovieInlineButton();
    }
    @Override
    public String getNoIdeaToMainMenuInlineButton() {
            return isEngLanguage ?
                    botMessage.getEngMessage().getInlineButton().getNoIdeaToMainMenuInlineButton() :
                    botMessage.getRusMessage().getInlineButton().getNoIdeaToMainMenuInlineButton();
    }
    @Override
    public String getWeRecommendNextMovieInlineButton() {
            return isEngLanguage ?
                    botMessage.getEngMessage().getInlineButton().getWeRecommendNextMovieInlineButton() :
                    botMessage.getRusMessage().getInlineButton().getWeRecommendNextMovieInlineButton();
    }
    @Override
    public String getWeRecommendPreviousMovieInlineButton() {
            return isEngLanguage ?
                    botMessage.getEngMessage().getInlineButton().getWeRecommendPreviousMovieInlineButton() :
                    botMessage.getRusMessage().getInlineButton().getWeRecommendPreviousMovieInlineButton();
    }
    @Override
    public String getWeRecommendToMainMenuInlineButton() {
            return isEngLanguage ?
                    botMessage.getEngMessage().getInlineButton().getWeRecommendToMainMenuInlineButton() :
                    botMessage.getRusMessage().getInlineButton().getWeRecommendToMainMenuInlineButton();
    }
    @Override
    public String getWeRecommendWatchInlineButton() {
            return isEngLanguage ?
                    botMessage.getEngMessage().getInlineButton().getWeRecommendWatchInlineButton() :
                    botMessage.getRusMessage().getInlineButton().getWeRecommendWatchInlineButton();
    }
    @Override
    public String getSendFeedbackYesInlineButton() {
            return isEngLanguage ?
                    botMessage.getEngMessage().getInlineButton().getSendFeedbackYesInlineButton() :
                    botMessage.getRusMessage().getInlineButton().getSendFeedbackYesInlineButton();
    }
    @Override
    public String getSendFeedbackNoInlineButton() {
            return isEngLanguage ?
                    botMessage.getEngMessage().getInlineButton().getSendFeedbackNoInlineButton() :
                    botMessage.getRusMessage().getInlineButton().getSendFeedbackNoInlineButton();
    }
    @Override
    public String getMovieRatingYesInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getMovieRatingYesInlineButton() :
                botMessage.getRusMessage().getInlineButton().getMovieRatingYesInlineButton();
    }
    @Override
    public String getMovieRatingNoInlineButton() {
        return isEngLanguage ?
                botMessage.getEngMessage().getInlineButton().getMovieRatingNoInlineButton() :
                botMessage.getRusMessage().getInlineButton().getMovieRatingNoInlineButton();
    }
    //</editor-fold>

    private void updateLanguageTracker() { isEngLanguage = !isEngLanguage; }
}
