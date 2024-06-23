package cz.vance.movieapp.managers.records;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.database.CinemaDatabase;
import cz.vance.movieapp.database.ICinemaDatabase;
import cz.vance.movieapp.models.Feedback;

import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 * Implements a set of methods for interacting with the <b>feedback table</b> from the DB.
 */
public class FeedbackRecord implements IFeedbackRecord {

    private final ICinemaDatabase cinemaDB;

    /**
     * Contains <b>all the feedbacks</b> from the DB.
     */
    private static final List<Feedback> feedbacks = new ArrayList<>();

    //<editor-fold default-state="collapsed" desc="Singleton">
    private static FeedbackRecord instance;

    private FeedbackRecord() {
        cinemaDB = CinemaDatabase.getInstance();
        loadFeedbacks();
    }

    public static FeedbackRecord getInstance() {
        if (instance == null)
            return new FeedbackRecord();
        return instance;
    }
    //</editor-fold>

    private void loadFeedbacks() {
        if (feedbacks.isEmpty())
            feedbacks.addAll(cinemaDB.getFeedbacks());
    }

    @Override
    public void insertFeedback(Feedback feedback) {
        cinemaDB.insertFeedback(feedback);
        feedbacks.add(feedback);
    }
}
