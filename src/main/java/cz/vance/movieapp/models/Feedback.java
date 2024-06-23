package cz.vance.movieapp.models;

/**
 * Represents a <b>feedback</b> object from the database.
 */
public final class Feedback {

    private static int idCounter = 0;

    private int id;
    private int tgId;
    private String feedbackText;

    public Feedback(int id, int tgId, String feedbackText) {
        this.id = id;
        this.tgId = tgId;
        this.feedbackText = feedbackText;

        idCounter++;
    }

    public Feedback(int tgId, String feedbackText) {
        this.id = ++idCounter;
        this.tgId = tgId;
        this.feedbackText = feedbackText;
    }

    //<editor-fold default-state="collapsed" desc="Getters">
    public int getId() { return id; }
    public int getTgId() { return tgId; }
    public String getFeedbackText() { return feedbackText; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setId(int id) { this.id = id; }
    public void setTgId(int tgId) { this.tgId = tgId; }
    public void setFeedbackText(String feedbackText) { this.feedbackText = feedbackText; }
    //</editor-fold>
}
