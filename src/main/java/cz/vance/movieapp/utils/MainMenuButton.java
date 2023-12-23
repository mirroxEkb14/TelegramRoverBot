package cz.vance.movieapp.utils;

import cz.vance.movieapp.keyboards.ReplyKeyboardBuilder;

/**
 * This <b>ReplyButton</b> enum class contains the text for buttons of <b>the main menu</b>
 * <p>
 * These texts are for buttons in <b>ReplyKeyboardMarkup</b>
 *
 * @see ReplyKeyboardBuilder
 */
public enum MainMenuButton {
    SMART_SEARCH_BUTTON("\ud83c\udfac Smart search"),
    OUR_CHOICE_BUTTON("\ud83c\udfaf Our choice"),
    NO_IDEA_BUTTON("\ud83d\udc40 No idea"),
    FEEDBACK_BUTTON("\ud83c\udfad Feedback");

    private final String content;

    MainMenuButton(String content) { this.content = content; }

    public String content() { return content; }
}
