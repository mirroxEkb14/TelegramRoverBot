package cz.vance.movieapp.models;

//<editor-fold default-state="collapsed" desc="Imports">
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;
//</editor-fold>

/**
 * Represents a single log entry for the <b>.log file</b>.
 */
public final class LogEntry {

    private String date;
    private String userFirstName;
    private String userLastName;
    private String userId;
    private String messageText;

    @JsonIgnore
    private final String DATE_FORMAT_PATTERN = "yyyy/MM/dd HH:mm:ss";

    //<editor-fold default-state="collapsed" desc="Constructor">
    public LogEntry(String userFirstName, String userLastName, String userId, String messageText) {
        this.date = new SimpleDateFormat(DATE_FORMAT_PATTERN).format(new Date());
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userId = userId;
        this.messageText = messageText;
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Getters">
    public String getDate() { return date; }
    public String getUserFirstName() { return userFirstName; }
    public String getUserLastName() { return userLastName; }
    public String getUserId() { return userId; }
    public String getMessageText() { return messageText; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setDate(String date) { this.date = date; }
    public void setUserFirstName(String userFirstName) { this.userFirstName = userFirstName; }
    public void setUserLastName(String userLastName) { this.userLastName = userLastName; }
    public void setUserId(String userId) { this.userId = userId; }
    public void setMessageText(String messageText) { this.messageText = messageText; }
    //</editor-fold>
}
