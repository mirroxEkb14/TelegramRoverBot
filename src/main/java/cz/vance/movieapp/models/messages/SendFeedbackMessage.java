package cz.vance.movieapp.models.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import java.util.List;
//</editor-fold>

/**
 * Holds lists with messages the bot sends during the <b>send feedback</b> feature.
 */
public final class SendFeedbackMessage {

    private String sendFeedbackAtLaunchGreetings;
    private List<String> sendFeedbackConfirmation;
    private List<String> sendFeedbackNoConfirmationMessage;
    private List<String> sendFeedbackYesConfirmationMessage;

    //<editor-fold default-state="collapsed" desc="Getters">
    public String getSendFeedbackAtLaunchGreetings() { return sendFeedbackAtLaunchGreetings; }
    public List<String> getSendFeedbackConfirmation() { return sendFeedbackConfirmation; }
    public List<String> getSendFeedbackNoConfirmationMessage() { return sendFeedbackNoConfirmationMessage; }
    public List<String> getSendFeedbackYesConfirmationMessage() { return sendFeedbackYesConfirmationMessage; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setSendFeedbackAtLaunchGreetings(String sendFeedbackAtLaunchGreetings) { this.sendFeedbackAtLaunchGreetings = sendFeedbackAtLaunchGreetings; }
    public void setSendFeedbackConfirmation(List<String> sendFeedbackConfirmation) { this.sendFeedbackConfirmation = sendFeedbackConfirmation; }
    public void setSendFeedbackNoConfirmationMessage(List<String> sendFeedbackNoConfirmationMessage) { this.sendFeedbackNoConfirmationMessage = sendFeedbackNoConfirmationMessage; }
    public void setSendFeedbackYesConfirmationMessage(List<String> sendFeedbackYesConfirmationMessage) { this.sendFeedbackYesConfirmationMessage = sendFeedbackYesConfirmationMessage; }
    //</editor-fold>
}
