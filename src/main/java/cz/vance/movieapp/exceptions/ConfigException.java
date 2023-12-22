package cz.vance.movieapp.exceptions;

import cz.vance.movieapp.bot.TelegramRoverBot;

/**
 * This <b>ConfigException</b> represents a custom exception that is used when the path to the <b>config.yml</b>
 * configuration file is incorrect
 *
 * @see TelegramRoverBot#TelegramRoverBot()
 */
public final class ConfigException extends RuntimeException {

    private static final String configErrorMessage =
            "Error while loading configuration on path: %s";

    public ConfigException(String path) {
        super(String.format(configErrorMessage, path));
    }
}
