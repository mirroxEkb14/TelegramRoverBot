package cz.vance.movieapp.database;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.Feedback;
import cz.vance.movieapp.models.Movie;
import cz.vance.movieapp.utils.columns.FeedbackColumnLabel;
import cz.vance.movieapp.utils.columns.MovieColumnLabel;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 * Implements a set of methods for interacting with the SQLite database.
 */
public final class CinemaDatabase implements ICinemaDatabase {

    private static final String DB_PATH = "jdbc:sqlite:cinema.db";
    private static final String DRIVER = "org.sqlite.JDBC";
    private Connection conn;

    //<editor-fold default-state="collapsed" desc="Singleton">
    private static CinemaDatabase instance;

    private CinemaDatabase() { connect(); }

    public static CinemaDatabase getInstance() {
        if (instance == null)
            return new CinemaDatabase();
        return instance;
    }
    //</editor-fold>

    private void connect() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_PATH);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public @NotNull List<Movie> getMovies() {
        final List<Movie> movies = new ArrayList<>();
        final String query = "SELECT * FROM movies";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             final ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                    rs.getInt(MovieColumnLabel.ID.getContent()),
                    rs.getString(MovieColumnLabel.RUS_NAME.getContent()),
                    rs.getString(MovieColumnLabel.RUS_MOOD.getContent()),
                    rs.getString(MovieColumnLabel.RUS_CATALOGUE.getContent()),
                    rs.getString(MovieColumnLabel.RUS_GENRE.getContent()),
                    rs.getInt(MovieColumnLabel.RELEASE_YEAR.getContent()),
                    rs.getString(MovieColumnLabel.RUS_DIRECTOR.getContent()),
                    rs.getString(MovieColumnLabel.RUS_CAST.getContent()),
                    rs.getString(MovieColumnLabel.RUS_STORYLINE.getContent()),
                    rs.getString(MovieColumnLabel.RUS_LINK.getContent()),
                    rs.getString(MovieColumnLabel.ENG_NAME.getContent()),
                    rs.getString(MovieColumnLabel.ENG_MOOD.getContent()),
                    rs.getString(MovieColumnLabel.ENG_CATALOGUE.getContent()),
                    rs.getString(MovieColumnLabel.ENG_GENRE.getContent()),
                    rs.getString(MovieColumnLabel.ENG_DIRECTOR.getContent()),
                    rs.getString(MovieColumnLabel.ENG_CAST.getContent()),
                    rs.getString(MovieColumnLabel.ENG_STORYLINE.getContent()),
                    rs.getString(MovieColumnLabel.ENG_LINK.getContent()),
                    rs.getString(MovieColumnLabel.DETAILS_LINK.getContent()),
                    rs.getString(MovieColumnLabel.POSTER_LINK.getContent())
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public void insertFeedback(@NotNull Feedback feedback) {
        final String query = "INSERT INTO feedbacks (tg_id, feedback_text) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, feedback.getTgId());
            stmt.setString(2, feedback.getFeedbackText());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public @NotNull List<Feedback> getFeedbacks() {
        final List<Feedback> feedbacks = new ArrayList<>();
        final String query = "SELECT * FROM feedbacks";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             final ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                feedbacks.add(new Feedback(
                        rs.getInt(FeedbackColumnLabel.ID.getContent()),
                        rs.getInt(FeedbackColumnLabel.TG_ID.getContent()),
                        rs.getString(FeedbackColumnLabel.FEEDBACK_TEXT.getContent())
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }
}
