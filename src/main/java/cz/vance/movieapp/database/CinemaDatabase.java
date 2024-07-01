package cz.vance.movieapp.database;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.Feedback;
import cz.vance.movieapp.models.Movie;
import cz.vance.movieapp.models.User;
import cz.vance.movieapp.utils.columns.FeedbacksColumnLabel;
import cz.vance.movieapp.utils.columns.MoviesColumnLabel;
import cz.vance.movieapp.utils.columns.UsersColumnTable;
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
    public void insertUser(@NotNull User user) {
        connect();

        final String query = "INSERT INTO Users (" +
                UsersColumnTable.TG_ID.getContent() + ", " +
                UsersColumnTable.USERNAME.getContent() + ", " +
                UsersColumnTable.JOIN_DATE.getContent() +
                ") VALUES (?, ?, ?)";

        try (final PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, user.getTgId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getJoinDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public @NotNull List<User> getUsers() {
        connect();

        final List<User> users = new ArrayList<>();
        final String query = "SELECT * FROM Users";

        try (final PreparedStatement stmt = conn.prepareStatement(query);
             final ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                    rs.getInt(UsersColumnTable.TG_ID.getContent()),
                    rs.getString(UsersColumnTable.USERNAME.getContent()),
                    rs.getString(UsersColumnTable.JOIN_DATE.getContent())
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return users;
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
        connect();

        final List<Movie> movies = new ArrayList<>();
        final String query = "SELECT * FROM movies";

        try (final PreparedStatement stmt = conn.prepareStatement(query);
             final ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                    rs.getInt(MoviesColumnLabel.ID.getContent()),
                    rs.getString(MoviesColumnLabel.RUS_NAME.getContent()),
                    rs.getString(MoviesColumnLabel.RUS_MOOD.getContent()),
                    rs.getString(MoviesColumnLabel.RUS_CATALOGUE.getContent()),
                    rs.getString(MoviesColumnLabel.RUS_GENRE.getContent()),
                    rs.getInt(MoviesColumnLabel.RELEASE_YEAR.getContent()),
                    rs.getString(MoviesColumnLabel.RUS_DIRECTOR.getContent()),
                    rs.getString(MoviesColumnLabel.RUS_CAST.getContent()),
                    rs.getString(MoviesColumnLabel.RUS_STORYLINE.getContent()),
                    rs.getString(MoviesColumnLabel.RUS_LINK.getContent()),
                    rs.getString(MoviesColumnLabel.ENG_NAME.getContent()),
                    rs.getString(MoviesColumnLabel.ENG_MOOD.getContent()),
                    rs.getString(MoviesColumnLabel.ENG_CATALOGUE.getContent()),
                    rs.getString(MoviesColumnLabel.ENG_GENRE.getContent()),
                    rs.getString(MoviesColumnLabel.ENG_DIRECTOR.getContent()),
                    rs.getString(MoviesColumnLabel.ENG_CAST.getContent()),
                    rs.getString(MoviesColumnLabel.ENG_STORYLINE.getContent()),
                    rs.getString(MoviesColumnLabel.ENG_LINK.getContent()),
                    rs.getString(MoviesColumnLabel.DETAILS_LINK.getContent()),
                    rs.getString(MoviesColumnLabel.POSTER_LINK.getContent())
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return movies;
    }

    @Override
    public void insertFeedback(@NotNull Feedback feedback) {
        connect();

        final String query = "INSERT INTO feedbacks (tg_id, feedback_text) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, feedback.getTgId());
            stmt.setString(2, feedback.getFeedbackText());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public @NotNull List<Feedback> getFeedbacks() {
        connect();

        final List<Feedback> feedbacks = new ArrayList<>();
        final String query = "SELECT * FROM feedbacks";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             final ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                feedbacks.add(new Feedback(
                        rs.getInt(FeedbacksColumnLabel.ID.getContent()),
                        rs.getInt(FeedbacksColumnLabel.TG_ID.getContent()),
                        rs.getString(FeedbacksColumnLabel.FEEDBACK_TEXT.getContent())
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return feedbacks;
    }
}
