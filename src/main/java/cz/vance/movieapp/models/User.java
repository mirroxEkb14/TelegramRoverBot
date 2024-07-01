package cz.vance.movieapp.models;

/**
 * Represents a <b>user</b> object from the database.
 */
public final class User {

    private static int idCounter = 0;

    private int id;
    private int tgId;
    private String username;
    private String joinDate;

    //<editor-fold default-state="collapsed" desc="Constructors">
    public User(int id, int tgId, String username, String joinDate) {
        this.id = id;
        this.tgId = tgId;
        this.username = username;
        this.joinDate = joinDate;

        idCounter++;
    }

    public User(int tgId, String username, String joinDate) {
        this.id = ++idCounter;
        this.tgId = tgId;
        this.username = username;
        this.joinDate = joinDate;
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Getters">
    public int getId() { return id; }
    public int getTgId() { return tgId; }
    public String getUsername() { return username; }
    public String getJoinDate() { return joinDate; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setId(int id) { this.id = id; }
    public void setTgId(int tgId) { this.tgId = tgId; }
    public void setUsername(String username) { this.username = username; }
    public void setJoinDate(String joinDate) { this.joinDate = joinDate; }
    //</editor-fold}
}
