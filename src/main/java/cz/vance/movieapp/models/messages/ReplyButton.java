package cz.vance.movieapp.models.messages;

/**
 * Holds the texts for reply keyboard buttons from the <b>.json file</b>.
 */
public final class ReplyButton {

    public String smartSearchReplyButton;
    public String ourChoiceReplyButton;
    public String noIdeaReplyButton;
    public String feedbackReplyButton;

    //<editor-fold default-state="collapsed" desc="Getters">
    public String getSmartSearchReplyButton() { return smartSearchReplyButton; }
    public String getWeRecommendReplyButton() { return ourChoiceReplyButton; }
    public String getNoIdeaReplyButton() { return noIdeaReplyButton; }
    public String getFeedbackReplyButton() { return feedbackReplyButton; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setSmartSearchReplyButton(String smartSearchReplyButton) { this.smartSearchReplyButton = smartSearchReplyButton; }
    public void setOurChoiceReplyButton(String ourChoiceReplyButton) { this.ourChoiceReplyButton = ourChoiceReplyButton; }
    public void setNoIdeaReplyButton(String noIdeaReplyButton) { this.noIdeaReplyButton = noIdeaReplyButton; }
    public void setFeedbackReplyButton(String feedbackReplyButton) { this.feedbackReplyButton = feedbackReplyButton; }
    //</editor-fold>
}
