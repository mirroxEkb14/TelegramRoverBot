package cz.vance.movieapp.utils;

/**
 * Contains texts for buttons of the <b>user mood selection</b>.
 *
 * @see cz.vance.movieapp.keyboards.InlineKeyboardBuilder
 */
public enum UserMood {
    DEPRESSION_BUTTON("\ud83c\udf27 Depressed"),
    CHEERFUL_BUTTON("\ud83d\udc83 Сheerful"),
    FIGHTING_BUTTON("\ud83d\ude3c Fighting"),
    FAMILY_BUTTON("\ud83d\udc68\u200d\ud83d\udc69\u200d\ud83d\udc67\u200d\ud83d\udc66 Family"),
    FRIENDS_BUTTON("\ud83c\udf7b Friends"),
    LOVE_BUTTON("\ud83d\udc8b Love");

    private final String content;

    UserMood(String content) { this.content = content; }

    public String getContent() { return content; }
}
