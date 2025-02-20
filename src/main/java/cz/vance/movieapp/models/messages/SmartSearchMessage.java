package cz.vance.movieapp.models.messages;

import java.util.List;

/**
 * Holds lists with <b>smart search</b> messages from the <b>.json file</b>.
 * <br>
 * The names of the properties are identical to the property names in the <b>.json file</b>.
 */
public final class SmartSearchMessage {

    private List<String> smartSearchAtLaunchGreetings, smartSearchMoodSelection, smartSearchCatalogueSelection,
            smartSearchGenreSelection, smartSearchVerifying, smartSearchVerified, smartSearchSampling,
            smartSearchOnFinish, smartSearchNoMovies, smartSearchOnFailure, onSmartSearchKeyboardRemoved,
            onSmartSearchOldKeyboardRemoved, onSmartSearchRepeatedKeyboardRemoved, onSmartSearchRebooted,
            onSmartSearchCatalogueBack, onSmartSearchGenreBack, onSmartSearchConfirmationBack;

    //<editor-fold default-state="collapsed" desc="Getters">
    public List<String> getSmartSearchAtLaunchGreetings() { return smartSearchAtLaunchGreetings; }
    public List<String> getSmartSearchMoodSelection() { return smartSearchMoodSelection; }
    public List<String> getSmartSearchCatalogueSelection() { return smartSearchCatalogueSelection; }
    public List<String> getSmartSearchGenreSelection() { return smartSearchGenreSelection; }
    public List<String> getSmartSearchVerifying() { return smartSearchVerifying; }
    public List<String> getSmartSearchVerified() { return smartSearchVerified; }
    public List<String> getSmartSearchSampling() { return smartSearchSampling; }
    public List<String> getSmartSearchOnFinish() { return smartSearchOnFinish; }
    public List<String> getSmartSearchNoMovies() { return smartSearchNoMovies; }
    public List<String> getSmartSearchOnFailure() { return smartSearchOnFailure; }
    public List<String> getOnSmartSearchKeyboardRemoved() { return onSmartSearchKeyboardRemoved; }
    public List<String> getOnSmartSearchOldKeyboardRemoved() { return onSmartSearchOldKeyboardRemoved; }
    public List<String> getOnSmartSearchRepeatedKeyboardRemoved() { return onSmartSearchRepeatedKeyboardRemoved; }
    public List<String> getOnSmartSearchRebooted() { return onSmartSearchRebooted; }
    public List<String> getOnSmartSearchCatalogueBack() { return onSmartSearchCatalogueBack; }
    public List<String> getOnSmartSearchGenreBack() { return onSmartSearchGenreBack; }
    public List<String> getOnSmartSearchConfirmationBack() { return onSmartSearchConfirmationBack; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setSmartSearchAtLaunchGreetings(List<String> smartSearchAtLaunchGreetings) { this.smartSearchAtLaunchGreetings = smartSearchAtLaunchGreetings; }
    public void setSmartSearchMoodSelection(List<String> smartSearchMoodSelection) { this.smartSearchMoodSelection = smartSearchMoodSelection; }
    public void setSmartSearchCatalogueSelection(List<String> smartSearchCatalogueSelection) { this.smartSearchCatalogueSelection = smartSearchCatalogueSelection; }
    public void setSmartSearchGenreSelection(List<String> smartSearchGenreSelection) { this.smartSearchGenreSelection = smartSearchGenreSelection; }
    public void setSmartSearchVerifying(List<String> smartSearchVerifying) { this.smartSearchVerifying = smartSearchVerifying; }
    public void setSmartSearchVerified(List<String> smartSearchVerified) { this.smartSearchVerified = smartSearchVerified; }
    public void setSmartSearchSampling(List<String> smartSearchSampling) { this.smartSearchSampling = smartSearchSampling; }
    public void setSmartSearchOnFinish(List<String> smartSearchOnFinish) { this.smartSearchOnFinish = smartSearchOnFinish; }
    public void setSmartSearchNoMovies(List<String> smartSearchNoMovies) { this.smartSearchNoMovies = smartSearchNoMovies; }
    public void setSmartSearchOnFailure(List<String> smartSearchOnFailure) { this.smartSearchOnFailure = smartSearchOnFailure; }
    public void setOnSmartSearchKeyboardRemoved(List<String> onSmartSearchKeyboardRemoved) { this.onSmartSearchKeyboardRemoved = onSmartSearchKeyboardRemoved; }
    public void setOnSmartSearchOldKeyboardRemoved(List<String> onSmartSearchOldKeyboardRemoved) { this.onSmartSearchOldKeyboardRemoved = onSmartSearchOldKeyboardRemoved; }
    public void setOnSmartSearchRepeatedKeyboardRemoved(List<String> onSmartSearchRepeatedKeyboardRemoved) { this.onSmartSearchRepeatedKeyboardRemoved = onSmartSearchRepeatedKeyboardRemoved; }
    public void setOnSmartSearchRebooted(List<String> onSmartSearchRebooted) { this.onSmartSearchRebooted = onSmartSearchRebooted; }
    public void setOnSmartSearchCatalogueBack(List<String> onSmartSearchCatalogueBack) { this.onSmartSearchCatalogueBack = onSmartSearchCatalogueBack; }
    public void setOnSmartSearchGenreBack(List<String> onSmartSearchGenreBack) { this.onSmartSearchGenreBack = onSmartSearchGenreBack; }
    public void setOnSmartSearchConfirmationBack(List<String> onSmartSearchConfirmationBack) { this.onSmartSearchConfirmationBack = onSmartSearchConfirmationBack; }
    //</editor-fold>
}
