package cz.vance.movieapp.utils.columns;

/**
 * Contains column names for the <b>feedback table</b>.
 */
public enum FeedbacksColumnLabel {
    ID("id"),
    TG_ID("tg_id"),
    FEEDBACK_TEXT("feedback_text");

    private final String content;

    FeedbacksColumnLabel(String label) { this.content = label; }

    public String getContent() { return content; }
}
