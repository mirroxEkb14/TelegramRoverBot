package cz.vance.movieapp.models;

/**
 * Stores user selections during the <b>smart search</b>.
 */
public final class UserSelection {

    private String mood;
    private String catalogue;
    private String genre;

    public UserSelection(long chatId) {}

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
}
