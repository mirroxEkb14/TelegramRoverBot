package cz.vance.movieapp.models.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import java.util.List;
//</editor-fold>

/**
 * Holds lists with <b>smart search</b> messages from the <b>.json file</b>.
 * <br>
 * The names of the properties are identical to the property names in the <b>.json file</b>.
 */
public final class SmartSearchMessage {

    private List<String> atLaunchGreetings;
    private List<String> moodSelection;
    private List<String> catalogueSelection;
    private List<String> genreSelection;
    private List<String> verifying;
    private List<String> verified;
    private List<String> sampling;
    private List<String> onFinish;
    private List<String> noMovies;
    private List<String> onFailure;
    private List<String> onSmartSearchKeyboardRemoved;
    private List<String> onSmartSearchOldKeyboardRemoved;
    private List<String> onSmartSearchRepeatedKeyboardRemoved;
    private List<String> onSmartSearchRebooted;
    private List<String> onSmartSearchCatalogueBack;
    private List<String> onSmartSearchGenreBack;
    private List<String> onSmartSearchConfirmationBack;

    //<editor-fold default-state="collapsed" desc="Getters">
    public List<String> getAtLaunchGreetings() { return atLaunchGreetings; }
    public List<String> getMoodSelection() { return moodSelection; }
    public List<String> getCatalogueSelection() { return catalogueSelection; }
    public List<String> getGenreSelection() { return genreSelection; }
    public List<String> getVerifying() { return verifying; }
    public List<String> getVerified() { return verified; }
    public List<String> getSampling() { return sampling; }
    public List<String> getOnFinish() { return onFinish; }
    public List<String> getNoMovies() { return noMovies; }
    public List<String> getOnFailure() { return onFailure; }
    public List<String> getOnSmartSearchKeyboardRemoved() { return onSmartSearchKeyboardRemoved; }
    public List<String> getOnSmartSearchOldKeyboardRemoved() { return onSmartSearchOldKeyboardRemoved; }
    public List<String> getOnSmartSearchRepeatedKeyboardRemoved() { return onSmartSearchRepeatedKeyboardRemoved; }
    public List<String> getOnSmartSearchRebooted() { return onSmartSearchRebooted; }
    public List<String> getOnSmartSearchCatalogueBack() { return onSmartSearchCatalogueBack; }
    public List<String> getOnSmartSearchGenreBack() { return onSmartSearchGenreBack; }
    public List<String> getOnSmartSearchConfirmationBack() { return onSmartSearchConfirmationBack; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setAtLaunchGreetings(List<String> atLaunchGreetings) { this.atLaunchGreetings = atLaunchGreetings; }
    public void setMoodSelection(List<String> moodSelection) { this.moodSelection = moodSelection; }
    public void setCatalogueSelection(List<String> catalogueSelection) { this.catalogueSelection = catalogueSelection; }
    public void setGenreSelection(List<String> genreSelection) { this.genreSelection = genreSelection; }
    public void setVerifying(List<String> verifying) { this.verifying = verifying; }
    public void setVerified(List<String> verified) { this.verified = verified; }
    public void setSampling(List<String> sampling) { this.sampling = sampling; }
    public void setOnFinish(List<String> onFinish) { this.onFinish = onFinish; }
    public void setNoMovies(List<String> noMovies) { this.noMovies = noMovies; }
    public void setOnFailure(List<String> onFailure) { this.onFailure = onFailure; }
    public void setOnSmartSearchKeyboardRemoved(List<String> onSmartSearchKeyboardRemoved) { this.onSmartSearchKeyboardRemoved = onSmartSearchKeyboardRemoved; }
    public void setOnSmartSearchOldKeyboardRemoved(List<String> onSmartSearchOldKeyboardRemoved) { this.onSmartSearchOldKeyboardRemoved = onSmartSearchOldKeyboardRemoved; }
    public void setOnSmartSearchRepeatedKeyboardRemoved(List<String> onSmartSearchRepeatedKeyboardRemoved) { this.onSmartSearchRepeatedKeyboardRemoved = onSmartSearchRepeatedKeyboardRemoved; }
    public void setOnSmartSearchRebooted(List<String> onSmartSearchRebooted) { this.onSmartSearchRebooted = onSmartSearchRebooted; }
    public void setOnSmartSearchCatalogueBack(List<String> onSmartSearchCatalogueBack) { this.onSmartSearchCatalogueBack = onSmartSearchCatalogueBack; }
    public void setOnSmartSearchGenreBack(List<String> onSmartSearchGenreBack) { this.onSmartSearchGenreBack = onSmartSearchGenreBack; }
    public void setOnSmartSearchConfirmationBack(List<String> onSmartSearchConfirmationBack) { this.onSmartSearchConfirmationBack = onSmartSearchConfirmationBack; }
    //</editor-fold>
}
