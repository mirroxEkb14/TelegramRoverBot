package cz.vance.movieapp.models;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.messages.*;

import java.util.List;
//</editor-fold>

/**
 * Holds instances of classes that hold lists with bot messages from the <b>.json file</b>.
 * <br>
 * The property names are identical to the block names in the <b>.json file</b>.
 */
public final class BotMessage {

    private CommandMessage commandMessage;
    private ReplyButton replyButton;
    private InlineButton inlineButton;
    private SmartSearchMessage smartSearchMessage;
    private NoIdeaMessage noIdeaMessage;
    private WeRecommendMessage weRecommendMessage;
    private SendFeedbackMessage sendFeedbackMessage;
    private List<String> onBotTerminatedMessage;

    //<editor-fold default-state="collapsed" desc="Getters">
    public CommandMessage getCommandMessage() { return commandMessage; }
    public ReplyButton getReplyButton() { return replyButton; }
    public InlineButton getInlineButton() { return inlineButton; }
    public SmartSearchMessage getSmartSearchMessage() { return smartSearchMessage; }
    public NoIdeaMessage getNoIdeaMessage() { return noIdeaMessage; }
    public WeRecommendMessage getWeRecommendMessage() { return weRecommendMessage; }
    public SendFeedbackMessage getSendFeedbackMessage() { return sendFeedbackMessage; }
    public List<String> getOnBotTerminatedMessage() { return onBotTerminatedMessage; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setCommandMessage(CommandMessage commandMessage) { this.commandMessage = commandMessage; }
    public void setReplyButton(ReplyButton replyButton) { this.replyButton = replyButton; }
    public void setInlineButton(InlineButton inlineButton) { this.inlineButton = inlineButton; }
    public void setSmartSearchMessage(SmartSearchMessage smartSearchMessage) { this.smartSearchMessage = smartSearchMessage; }
    public void setNoIdeaMessage(NoIdeaMessage noIdeaMessage) { this.noIdeaMessage = noIdeaMessage; }
    public void setWeRecommendMessage(WeRecommendMessage weRecommendMessage) { this.weRecommendMessage = weRecommendMessage; }
    public void setSendFeedbackMessage(SendFeedbackMessage sendFeedbackMessage) { this.sendFeedbackMessage = sendFeedbackMessage; }
    public void setOnBotTerminatedMessage(List<String> onBotTerminatedMessage) { this.onBotTerminatedMessage = onBotTerminatedMessage; }
    //</editor-fold>
}
