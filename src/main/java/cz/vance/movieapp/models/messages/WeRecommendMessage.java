package cz.vance.movieapp.models.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import java.util.List;
//</editor-fold>

/**
 * Holds lists with <b>we recommend</b> messages from the <b>.json file</b>.
 */
public final class WeRecommendMessage {

    private List<String> weRecommendAtLaunchGreetings;
    private List<String> weRecommendPleasantViewing;
    private String weRecommendSampleMovieMessage;
    private List<String> weRecommendToMainMenu;

    //<editor-fold default-state="collapsed" desc="Getters">
    public List<String> getWeRecommendAtLaunchGreetings() { return weRecommendAtLaunchGreetings; }
    public List<String> getWeRecommendPleasantViewing() { return weRecommendPleasantViewing; }
    public String getWeRecommendSampleMovieMessage() { return weRecommendSampleMovieMessage; }
    public List<String> getWeRecommendToMainMenu() { return weRecommendToMainMenu; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setWeRecommendAtLaunchGreetings(List<String> weRecommendAtLaunchGreetings) { this.weRecommendAtLaunchGreetings = weRecommendAtLaunchGreetings; }
    public void setWeRecommendPleasantViewing(List<String> weRecommendPleasantViewing) { this.weRecommendPleasantViewing = weRecommendPleasantViewing; }
    public void setWeRecommendSampleMovieMessage(String weRecommendSampleMovieMessage) { this.weRecommendSampleMovieMessage = weRecommendSampleMovieMessage; }
    public void setWeRecommendToMainMenu(List<String> weRecommendToMainMenu) { this.weRecommendToMainMenu = weRecommendToMainMenu; }
    //</editor-fold>
}
