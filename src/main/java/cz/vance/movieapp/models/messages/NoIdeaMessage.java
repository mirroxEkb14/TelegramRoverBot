package cz.vance.movieapp.models.messages;

import java.util.List;

/**
 * Holds lists with <b>no idea</b> feature messages from the <b>.json file</b>.
 */
public final class NoIdeaMessage {

    private List<String> noIdeaAtLaunchGreetings,
            noIdeaNoMoviesLeft,
            noIdeaToMainMenu;

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
