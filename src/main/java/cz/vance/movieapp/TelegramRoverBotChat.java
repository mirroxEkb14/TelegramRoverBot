package cz.vance.movieapp;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.bot.TelegramRoverBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
//</editor-fold>

/**
 * Entry point for the application containing the <b>main</b> method.
 * <p>
 * Initializes the Telegram bot API.
 * <br>
 * Registers the TelegramRoverBot.
 *
 * @see TelegramRoverBot
 */
public final class TelegramRoverBotChat {

    public static void main(String[] args) {
        try {
            final TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramRoverBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
