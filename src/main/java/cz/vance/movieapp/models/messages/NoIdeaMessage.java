package cz.vance.movieapp.models.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import java.util.List;
//</editor-fold>

/**
 * Holds lists with <b>no idea</b> feature messages from the <b>.json file</b>.
 */
public final class NoIdeaMessage {

    private List<String> noIdeaAtLaunchGreetings;
    private List<String> noIdeaNoMoviesLeft;
    private List<String> noIdeaToMainMenu;

    //<editor-fold default-state="collapsed" desc="Getters">
    public List<String> getNoIdeaAtLaunchGreetings() { return noIdeaAtLaunchGreetings; }
    public List<String> getNoIdeaNoMoviesLeft() { return noIdeaNoMoviesLeft; }
    public List<String> getNoIdeaToMainMenu() { return noIdeaToMainMenu; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setNoIdeaAtLaunchGreetings(List<String> noIdeaAtLaunchGreetings) { this.noIdeaAtLaunchGreetings = noIdeaAtLaunchGreetings; }
    public void setNoIdeaNoMoviesLeft(List<String> noIdeaNoMoviesLeft) { this.noIdeaNoMoviesLeft = noIdeaNoMoviesLeft; }
    public void setNoIdeaToMainMenu(List<String> noIdeaToMainMenu) { this.noIdeaToMainMenu = noIdeaToMainMenu; }
    //</editor-fold>
}
