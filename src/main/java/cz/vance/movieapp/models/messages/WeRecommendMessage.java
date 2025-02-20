package cz.vance.movieapp.models.messages;

import java.util.List;

/**
 * Holds lists with <b>we recommend</b> messages from the <b>.json file</b>.
 */
public final class WeRecommendMessage {

    private List<String> weRecommendAtLaunchGreetings,
            weRecommendPleasantViewing,
            weRecommendToMainMenu;
    private String weRecommendSampleMovieMessage;

    //<editor-fold default-state="collapsed" desc="Getters">
    public List<String> getWeRecommendAtLaunchGreetings() { return weRecommendAtLaunchGreetings; }
    public List<String> getWeRecommendPleasantViewing() { return weRecommendPleasantViewing; }
    public List<String> getWeRecommendToMainMenu() { return weRecommendToMainMenu; }
    public String getWeRecommendSampleMovieMessage() { return weRecommendSampleMovieMessage; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setWeRecommendAtLaunchGreetings(List<String> weRecommendAtLaunchGreetings) { this.weRecommendAtLaunchGreetings = weRecommendAtLaunchGreetings; }
    public void setWeRecommendPleasantViewing(List<String> weRecommendPleasantViewing) { this.weRecommendPleasantViewing = weRecommendPleasantViewing; }
    public void setWeRecommendToMainMenu(List<String> weRecommendToMainMenu) { this.weRecommendToMainMenu = weRecommendToMainMenu; }
    public void setWeRecommendSampleMovieMessage(String weRecommendSampleMovieMessage) { this.weRecommendSampleMovieMessage = weRecommendSampleMovieMessage; }
    //</editor-fold>
}
