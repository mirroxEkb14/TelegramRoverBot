package cz.vance.movieapp.models;

/**
 * Represents a <b>movie object</b> from the database.
 */
public record Movie(int id,
                    String rusName,
                    String rusMood,
                    String rusCatalogue,
                    String rusGenre,
                    int releaseYear,
                    String rusDirector,
                    String rusCast,
                    String rusStoryline,
                    String rusLink,
                    String engName,
                    String engMood,
                    String engCatalogue,
                    String engGenre,
                    String engDirector,
                    String engCast,
                    String engStoryline,
                    String engLink,
                    String detailsLink,
                    String posterLink) {}
