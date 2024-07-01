package cz.vance.movieapp.models;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.messages.EngMessage;
import cz.vance.movieapp.models.messages.RusMessage;
//</editor-fold>

/**
 * Holds instances of classes holding English/Russian messages for the bot.
 * <br>
 * The property names are identical to the block names in the <b>.json file</b>.
 */
public final class BotMessage {

    private EngMessage engMessage;
    private RusMessage rusMessage;

    //<editor-fold default-state="collapsed" desc="Getters and Setters">
    public EngMessage getEngMessage() { return engMessage; }
    public RusMessage getRusMessage() { return rusMessage; }

    public void setEngMessage(EngMessage engMessage) { this.engMessage = engMessage; }
    public void setRusMessage(RusMessage rusMessage) { this.rusMessage = rusMessage; }
    //</editor-fold>
}
