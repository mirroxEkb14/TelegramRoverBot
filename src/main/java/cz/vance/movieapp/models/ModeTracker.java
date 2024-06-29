package cz.vance.movieapp.models;

/**
 * Holds <b>boolean</b> values of the current active modes.
 * <br>
 * Is used to track the current active modes in the application and prohibit the user from calling the <b>/lang</b>
 * command when some mode is active.
 */
public final class ModeTracker {

    private boolean isSmartSearchActive;
    private boolean isWeRecommendActive;
    private boolean isNoIdeaActive;
    private boolean isSendFeedbackActive;

    //<editor-fold default-state="collapsed" desc="Singleton">
    private static ModeTracker instance;

    private ModeTracker() {
        isSmartSearchActive = false;
        isWeRecommendActive = false;
        isNoIdeaActive = false;
        isSendFeedbackActive = false;
    }

    public static ModeTracker getInstance() {
        if (instance == null)
            instance = new ModeTracker();
        return instance;
    }
    //</editor-fold>

    public boolean isSmartSearchActive() { return isSmartSearchActive; }
    public boolean isWeRecommendActive() { return isWeRecommendActive; }
    public boolean isNoIdeaActive() { return isNoIdeaActive; }
    public boolean isSendFeedbackActive() { return isSendFeedbackActive; }

    public void activateSmartSearch() { if (!isSmartSearchActive) isSmartSearchActive = true; }
    public void activateWeRecommend() { if (!isWeRecommendActive) isWeRecommendActive = true; }
    public void activateNoIdea() { if (!isNoIdeaActive) isNoIdeaActive = true; }
    public void activateSendFeedback() { if (!isSendFeedbackActive) isSendFeedbackActive = true; }

    public void deactivateSmartSearch() { if (isSmartSearchActive) isSmartSearchActive = false; }
    public void deactivateWeRecommend() { if (isWeRecommendActive) isWeRecommendActive = false; }
    public void deactivateNoIdea() { if (isNoIdeaActive) isNoIdeaActive = false; }
    public void deactivateSendFeedback() { if (isSendFeedbackActive) isSendFeedbackActive = false; }
}
