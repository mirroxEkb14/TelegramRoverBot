package cz.vance.movieapp.utils;

import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * This <b>UserMood</b> enum class contains the text for buttons of <b>user mood selection</b>
 * <p>
 * These texts are for buttons in <b>InlineKeyboardBuilder</b>
 *
 * @see InlineKeyboardBuilder
 */
public enum UserMood {
    DEPRESSION_BUTTON("\ud83c\udf27 Depressed"),
    CHEERFUL_BUTTON("\ud83d\udc83 Сheerful"),
    FIGHTING_BUTTON("\ud83d\ude3c Fighting"),
    FAMILY_BUTTON("\ud83d\udc68\u200d\ud83d\udc69\u200d\ud83d\udc67\u200d\ud83d\udc66 Family"),
    FRIENDS_BUTTON("\ud83c\udf7b Friends"),
    LOVE_BUTTON("\ud83d\udc8b Love");

//<editor-fold default-state="collapsed" desc="Callback Data">
    /**
     * The <b>callback data</b> is a piece of information that will be sent to the bot when the user interacts with
     * the button, which is typically used to identify which button was pressed
     */
    private static final String CALLBACK_PREFIX = "_cb";

    public @NotNull String getCallback() {
        return this + CALLBACK_PREFIX;
    }
//</editor-fold>

    private final String content;

    UserMood(String content) { this.content = content; }

    public String content() { return content; }
}
