package cz.vance.movieapp.models;

/**
 * Represents a DB entity of a <b>movie rate</b>.
 */
public final class MovieRating {

    private static int idCounter = 0;

    private int id;
    private int tgId;
    private String ratingDate;
    private String movieName;
    private String modeName;
    private String ratingText;
    private String smartSearchRatingText;

    //<editor-fold default-state="collapsed" desc="Constructors">
    public MovieRating(int id,
                       int tgId,
                       String ratingDate,
                       String movieName,
                       String modeName,
                       String ratingText,
                       String smartSearchRatingText) {
        this.id = id;
        this.tgId = tgId;
        this.ratingDate = ratingDate;
        this.movieName = movieName;
        this.modeName = modeName;
        this.ratingText = ratingText;
        this.smartSearchRatingText = smartSearchRatingText;
    }

    public MovieRating(int tgId,
                       String ratingDate,
                       String movieName,
                       String modeName,
                       String ratingText,
                       String smartSearchRatingText) {
        this.id = ++idCounter;
        this.tgId = tgId;
        this.ratingDate = ratingDate;
        this.movieName = movieName;
        this.modeName = modeName;
        this.ratingText = ratingText;
        this.smartSearchRatingText = smartSearchRatingText;
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Getters">
    public int getId() { return id; }
    public int getTgId() { return tgId; }
    public String getRatingDate() { return ratingDate; }
    public String getMovieName() { return movieName; }
    public String getModeName() { return modeName; }
    public String getRatingText() { return ratingText; }
    public String getSmartSearchRatingText() { return smartSearchRatingText; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setId(int id) { this.id = id; }
    public void setTgId(int tgId) { this.tgId = tgId; }
    public void setRatingDate(String ratingDate) { this.ratingDate = ratingDate; }
    public void setMovieName(String movieName) { this.movieName = movieName; }
    public void setModeName(String modeName) { this.modeName = modeName; }
    public void setRatingText(String ratingText) { this.ratingText = ratingText; }
    public void setSmartSearchRatingText(String smartSearchRatingText) { this.smartSearchRatingText = smartSearchRatingText; }
    //</editor-fold>
}
