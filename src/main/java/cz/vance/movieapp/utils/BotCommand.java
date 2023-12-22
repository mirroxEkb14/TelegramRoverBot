package cz.vance.movieapp.utils;

/**
 * This <b>BotCommand</b> enum class contains all the possible bot commands
 */
public enum BotCommand {
    START_COMMAND("/start");

    private final String content;

    BotCommand(String content) { this.content = content; }

    public String content() { return content; }
}
