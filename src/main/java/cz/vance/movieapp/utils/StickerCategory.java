package cz.vance.movieapp.utils;

/**
 * Represents the possible categories of the stickers the bot can send to the user.
 */
public enum StickerCategory {
    WELCOME("Welcome"),
    SMART_SEARCH_BEGINNING("SmartSearchBeginning"),
    SMART_SEARCH_END("SmartSearchEnd");

    private final String content;

    StickerCategory(String content) { this.content = content; }

    public String getContent() { return content; }
}
