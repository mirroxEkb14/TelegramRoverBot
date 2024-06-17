package cz.vance.movieapp.utils;

/**
 * Contains texts for the <b>main menu</b> buttons.
 *
 * @see cz.vance.movieapp.keyboards.ReplyKeyboardBuilder
 */
public enum MainMenuButton {
    SMART_SEARCH_BUTTON("\ud83c\udfac Smart search"),
    OUR_CHOICE_BUTTON("\ud83c\udfaf Our choice"),
    NO_IDEA_BUTTON("\ud83d\udc40 No idea"),
    FEEDBACK_BUTTON("\ud83c\udfad Feedback");

    private final String content;

    MainMenuButton(String content) { this.content = content; }

    public String getContent() { return content; }
}
