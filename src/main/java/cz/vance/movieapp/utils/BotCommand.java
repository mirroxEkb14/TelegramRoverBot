package cz.vance.movieapp.utils;

/**
 * Contains all the texts fpr all the possible bot commands.
 */
public enum BotCommand {
    START_COMMAND("/start"),
    HELP_COMMAND("/help");

    private final String content;

    BotCommand(String content) { this.content = content; }

    public String content() { return content; }
}
