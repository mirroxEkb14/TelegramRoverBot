package cz.vance.movieapp.utils.columns;

/**
 * Contains column names for the <b>feedback table</b>.
 */
public enum FeedbackColumnLabel {
    ID("id"),
    TG_ID("tg_id"),
    FEEDBACK_TEXT("feedback_text");

    private final String content;

    FeedbackColumnLabel(String label) { this.content = label; }

    public String getContent() { return content; }
}
