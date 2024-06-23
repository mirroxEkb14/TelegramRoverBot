package cz.vance.movieapp.managers.records;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.Feedback;
//</editor-fold>

/**
 * Declares a set of basic operations for <b>managing the feedbacks</b> from the appropriate <b>feedback table</b> from the DB.
 */
public interface IFeedbackRecord {

    /**
     * Inserts a new feedback into the feedback table.
     *
     * @param feedback the feedback to be inserted.
     */
    void insertFeedback(Feedback feedback);
}
