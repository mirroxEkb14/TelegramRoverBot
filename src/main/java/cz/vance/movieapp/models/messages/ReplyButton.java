package cz.vance.movieapp.models.messages;

/**
 * Holds the texts for reply keyboard buttons from the <b>.json file</b>.
 */
public final class ReplyButton {

    public String smartSearchReplyButton;
    public String ourChoiceReplyButton;
    public String noIdeasReplyButton;
    public String feedbackReplyButton;

    //<editor-fold default-state="collapsed" desc="Getters">
    public String getSmartSearchReplyButton() { return smartSearchReplyButton; }
    public String getOurChoiceReplyButton() { return ourChoiceReplyButton; }
    public String getNoIdeasReplyButton() { return noIdeasReplyButton; }
    public String getFeedbackReplyButton() { return feedbackReplyButton; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setSmartSearchReplyButton(String smartSearchReplyButton) { this.smartSearchReplyButton = smartSearchReplyButton; }
    public void setOurChoiceReplyButton(String ourChoiceReplyButton) { this.ourChoiceReplyButton = ourChoiceReplyButton; }
    public void setNoIdeasReplyButton(String noIdeasReplyButton) { this.noIdeasReplyButton = noIdeasReplyButton; }
    public void setFeedbackReplyButton(String feedbackReplyButton) { this.feedbackReplyButton = feedbackReplyButton; }
    //</editor-fold>
}
