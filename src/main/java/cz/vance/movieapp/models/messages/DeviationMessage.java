package cz.vance.movieapp.models.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import java.util.List;
//</editor-fold>

/**
 * Holds lists of deviations from the expected user behavior during different modes.
 */
public final class DeviationMessage {

    private List<String> onSmartSearchDeviation;
    private List<String> onWeRecommendDeviation;
    private List<String> onNoIdeaDeviation;
    private List<String> onSendFeedbackDeviation;

    //<editor-fold default-state="collapsed" desc="Getters">
    public List<String> getOnSmartSearchDeviation() { return onSmartSearchDeviation; }
    public List<String> getOnWeRecommendDeviation() { return onWeRecommendDeviation; }
    public List<String> getOnNoIdeaDeviation() { return onNoIdeaDeviation; }
    public List<String> getOnSendFeedbackDeviation() { return onSendFeedbackDeviation; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setOnSmartSearchDeviation(List<String> onSmartSearchDeviation) { this.onSmartSearchDeviation = onSmartSearchDeviation; }
    public void setOnWeRecommendDeviation(List<String> onWeRecommendDeviation) { this.onWeRecommendDeviation = onWeRecommendDeviation; }
    public void setOnNoIdeaDeviation(List<String> onNoIdeaDeviation) { this.onNoIdeaDeviation = onNoIdeaDeviation; }
    public void setOnSendFeedbackDeviation(List<String> onSendFeedbackDeviation) { this.onSendFeedbackDeviation = onSendFeedbackDeviation; }
    //</editor-fold>
}
