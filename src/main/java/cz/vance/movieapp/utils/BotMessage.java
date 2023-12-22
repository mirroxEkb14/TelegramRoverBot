package cz.vance.movieapp.utils;

/**
 * This <b>BotMessage</b> enum class provides with all the messages the bot can send to the user
 */
public enum BotMessage {
    WELCOME_MESSAGE("Welcome %s!\nI am <b>%s</b>, created to be your own movie-guide");

    private final String content;

    BotMessage(String content) { this.content = content; }

    public String content() { return content; }
}
