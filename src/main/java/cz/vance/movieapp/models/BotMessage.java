package cz.vance.movieapp.models;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.messages.*;
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

    //<editor-fold default-state="collapsed" desc="Singleton">
    private static BotMessage instance;

    private BotMessage() {}

    public static BotMessage getInstance() {
        if (instance == null)
            instance = new BotMessage();
        return instance;
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Getters">
    public CommandMessage getCommandMessage() { return commandMessage; }
    public ReplyButton getReplyButton() { return replyButton; }
    public InlineButton getInlineButton() { return inlineButton; }
    public SmartSearchMessage getSmartSearchMessage() { return smartSearchMessage; }
    public NoIdeaMessage getNoIdeaMessage() { return noIdeaMessage; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setCommandMessage(CommandMessage commandMessage) { this.commandMessage = commandMessage; }
    public void setReplyButton(ReplyButton replyButton) { this.replyButton = replyButton; }
    public void setInlineButton(InlineButton inlineButton) { this.inlineButton = inlineButton; }
    public void setSmartSearchMessage(SmartSearchMessage smartSearchMessage) { this.smartSearchMessage = smartSearchMessage; }
    public void setNoIdeaMessage(NoIdeaMessage noIdeaMessage) { this.noIdeaMessage = noIdeaMessage; }
    //</editor-fold>
}
