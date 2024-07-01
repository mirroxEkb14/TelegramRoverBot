package cz.vance.movieapp.utils.columns;

/**
 * Contains names of columns in the <b>users table</b>.
 */
public enum UsersColumnTable {
    ID("id"),
    TG_ID("tgId"),
    USERNAME("username"),
    JOIN_DATE("joinDate");

    private final String content;

    UsersColumnTable(String content) { this.content = content; }

    public String getContent() { return content; }
}
