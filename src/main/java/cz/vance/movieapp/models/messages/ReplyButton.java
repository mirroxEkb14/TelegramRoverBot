package cz.vance.movieapp.models.messages;

/**
 * Holds the texts for reply keyboard buttons from the <b>.json file</b>.
 */
public final class ReplyButton {

    public String smartSearchReplyButton,
            ourChoiceReplyButton,
            noIdeaReplyButton,
            sendFeedbackReplyButton;

    //<editor-fold default-state="collapsed" desc="Getters">
    public String getSmartSearchReplyButton() { return smartSearchReplyButton; }
    public String getWeRecommendReplyButton() { return ourChoiceReplyButton; }
    public String getNoIdeaReplyButton() { return noIdeaReplyButton; }
    public String getSendFeedbackReplyButton() { return sendFeedbackReplyButton; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setSmartSearchReplyButton(String smartSearchReplyButton) { this.smartSearchReplyButton = smartSearchReplyButton; }
    public void setOurChoiceReplyButton(String ourChoiceReplyButton) { this.ourChoiceReplyButton = ourChoiceReplyButton; }
    public void setNoIdeaReplyButton(String noIdeaReplyButton) { this.noIdeaReplyButton = noIdeaReplyButton; }
    public void setSendFeedbackReplyButton(String sendFeedbackReplyButton) { this.sendFeedbackReplyButton = sendFeedbackReplyButton; }
    //</editor-fold>
}
