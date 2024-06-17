package cz.vance.movieapp.bot;

/**
 * Represents the configuration object holding the bot's settings from the <b>/src/main/resources/config.yml</b> file.
 *
 * @see <a href="https://mvnrepository.com/artifact/org.yaml/snakeyaml">SnakeYAML</a>
 */
public final class TelegramRoverBotConfig {

    private String telegramBotToken;
    private String telegramBotUsername;
    private String telegramAdminId;

    //<editor-fold default-state="collapsed" desc="Getters">
    public String getTelegramBotToken() { return telegramBotToken; }
    public String getTelegramBotUsername() { return telegramBotUsername; }
    public String getTelegramAdminId() { return telegramAdminId; }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Setters">
    public void setTelegramBotToken(String telegramBotToken) { this.telegramBotToken = telegramBotToken; }
    public void setTelegramBotUsername(String telegramBotUsername) { this.telegramBotUsername = telegramBotUsername; }
    public void setTelegramAdminId(String telegramAdminId) { this.telegramAdminId = telegramAdminId; }
    //</editor-fold>
}

