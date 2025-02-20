package cz.vance.movieapp.models.messages;

import java.util.List;

/**
 * Holds the texts that are sent to the user after using some bot commands.
 */
public final class CommandMessage {

    private List<String> welcomes;
    private List<String> helps;
    private List<String> langMessage;
    private List<String> unknownInputs;

    //<editor-fold default-state="collapsed" desc="Getters">
    public List<String> getWelcomes() { return welcomes; }
    public List<String> getHelps() { return helps; }
    public List<String> getLangMessage() { return langMessage; }
    public List<String> getUnknownInputs() { return unknownInputs; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setWelcomes(List<String> welcomes) { this.welcomes = welcomes; }
    public void setHelps(List<String> helps) { this.helps = helps; }
    public void setLangMessage(List<String> langMessage) { this.langMessage = langMessage; }
    public void setUnknownInputs(List<String> unknownInputs) { this.unknownInputs = unknownInputs; }
    //</editor-fold>
}
