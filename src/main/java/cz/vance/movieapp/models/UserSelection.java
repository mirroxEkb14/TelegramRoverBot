package cz.vance.movieapp.models;

/**
 * Stores user selections during the <b>smart search</b>.
 */
public final class UserSelection {

    private String mood;
    private String catalogue;
    private String genre;

    public UserSelection() {}

    //<editor-fold default-state="collapsed" desc="Getters">
    public String getMood() { return mood; }
    public String getCatalogue() { return catalogue; }
    public String getGenre() { return genre; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setMood(String mood) { this.mood = mood; }
    public void setCatalogue(String catalogue) { this.catalogue = catalogue; }
    public void setGenre(String genre) { this.genre = genre; }
    //</editor-fold>

    public boolean isOnlyMoodSelected() { return mood != null && catalogue == null && genre == null; }
    public boolean isOnlyCatalogueSelected() { return catalogue != null && mood != null && genre == null; }
    public boolean isOnlyGenreSelected() { return genre != null && mood != null && catalogue != null; }

    public void nullifyMood() { mood = null; }
    public void nullifyCatalogue() { catalogue = null; }
    public void nullifyGenre() { genre = null; }
}
