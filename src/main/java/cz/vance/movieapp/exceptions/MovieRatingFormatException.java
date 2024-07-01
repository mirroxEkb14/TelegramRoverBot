package cz.vance.movieapp.exceptions;

/**
 * Represents a custom exception that is used when the movie rating format is incorrect during the <b>/rating</b>
 */
public final class MovieRatingFormatException extends RuntimeException {

    public MovieRatingFormatException() { super(); }
}
