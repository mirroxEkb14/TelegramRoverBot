package cz.vance.movieapp.models.messages;

import java.util.List;

/**
 * Holds the texts for the <b>movie rating</b> messages.
 */
public final class MovieRatingMessage {

    private List<String> movieRatingAtLaunchGreetings;
    private List<String> movieRatingConfirmation;
    private String movieRatingSampleMessage;
    private List<String> movieRatingNoConfirmationMessage;
    private List<String> movieRatingYesConfirmationMessage;
    private List<String> movieRatingWrongSampleFormatMessage;

    //<editor-fold default-state="collapsed" desc="Getters">
    public List<String> getMovieRatingAtLaunchGreetings() { return movieRatingAtLaunchGreetings; }
    public String getMovieRatingSampleMessage() { return movieRatingSampleMessage; }
    public List<String> getMovieRatingConfirmation() { return movieRatingConfirmation; }
    public List<String> getMovieRatingNoConfirmationMessage() { return movieRatingNoConfirmationMessage; }
    public List<String> getMovieRatingYesConfirmationMessage() { return movieRatingYesConfirmationMessage; }
    public List<String> getMovieRatingWrongSampleFormatMessage() { return movieRatingWrongSampleFormatMessage; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setMovieRatingAtLaunchGreetings(List<String> movieRatingAtLaunchGreetings) { this.movieRatingAtLaunchGreetings = movieRatingAtLaunchGreetings; }
    public void setMovieRatingConfirmation(List<String> movieRatingConfirmation) { this.movieRatingConfirmation = movieRatingConfirmation; }
    public void setMovieRatingSampleMessage(String movieRatingSampleMessage) { this.movieRatingSampleMessage = movieRatingSampleMessage; }
    public void setMovieRatingNoConfirmationMessage(List<String> movieRatingNoConfirmationMessage) { this.movieRatingNoConfirmationMessage = movieRatingNoConfirmationMessage; }
    public void setMovieRatingYesConfirmationMessage(List<String> movieRatingYesConfirmationMessage) { this.movieRatingYesConfirmationMessage = movieRatingYesConfirmationMessage; }
    public void setMovieRatingWrongSampleFormatMessage(List<String> movieRatingWrongSampleFormatMessage) { this.movieRatingWrongSampleFormatMessage = movieRatingWrongSampleFormatMessage; }
    //</editor-fold>
}
