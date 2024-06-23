package cz.vance.movieapp.utils;

/**
 * Contains the texts for all the possible bot commands.
 */
public enum BotCommand {
    START_COMMAND("/start"),
    HELP_COMMAND("/help"),
    LANG_COMMAND("/lang");

    private final String content;

    BotCommand(String content) { this.content = content; }

    public String content() { return content; }
}
