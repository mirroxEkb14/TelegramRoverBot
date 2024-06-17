package cz.vance.movieapp.exceptions;

/**
 * Represents a custom exception that is used when the path to the <b>config.yml</b> configuration file is incorrect.
 */
public final class ConfigException extends RuntimeException {

    private static final String configErrorMessage =
            "Error while loading configuration on path: %s";

    public ConfigException(String path) { super(String.format(configErrorMessage, path)); }
}
