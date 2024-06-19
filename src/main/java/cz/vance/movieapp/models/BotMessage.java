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

    private SmartSearchMessage smartSearchMessage;
    private ReplyButton replyButton;
    private InlineButton inlineButton;
    private CommandMessage commandMessage;

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
    public SmartSearchMessage getSmartSearchMessage() { return smartSearchMessage; }
    public ReplyButton getReplyButton() { return replyButton; }
    public InlineButton getInlineButton() { return inlineButton; }
    public CommandMessage getCommandMessage() { return commandMessage; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setSmartSearchMessage(SmartSearchMessage smartSearchMessage) { this.smartSearchMessage = smartSearchMessage; }
    public void setReplyButton(ReplyButton replyButton) { this.replyButton = replyButton; }
    public void setInlineButton(InlineButton inlineButton) { this.inlineButton = inlineButton; }
    public void setCommandMessage(CommandMessage commandMessage) { this.commandMessage = commandMessage; }
    //</editor-fold>
}
