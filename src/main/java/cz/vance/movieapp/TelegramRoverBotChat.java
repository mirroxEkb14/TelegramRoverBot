package cz.vance.movieapp;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.bot.TelegramRoverBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
//</editor-fold>

/**
 * This <b>TelegramRoverBotChat</b> class is an entry point into this program that calls the <b>main()</b> method
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
