package cz.vance.movieapp.bot;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.config.TelegramRoverBotConfig;
import cz.vance.movieapp.exceptions.ConfigException;
import cz.vance.movieapp.keyboards.IInlineKeyboardBuilder;
import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;
import cz.vance.movieapp.managers.messages.IMessageManager;
import cz.vance.movieapp.managers.messages.MessageManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
//</editor-fold>

/**
 * This <b>TelegramRoverBot</b> class represents the main implementation of a Telegram bot
 *
 * @see TelegramLongPollingBot
 * @see <a href="https://github.com/rubenlagus/TelegramBots#telegram-bot-java-library">TelegramBots</a>
 */
public final class TelegramRoverBot extends TelegramLongPollingBot {

    private static final String CONFIG_FILE = "/config.yml";
    private static TelegramRoverBotConfig botConfig;

    private final IMessageManager messageManager = new MessageManager(this);
    private final IInlineKeyboardBuilder inlineKeyboardBuilder = new InlineKeyboardBuilder();

    /**
     * Constructor
     * <p>
     * Loads configuration when creating the bot instance
     */
    public TelegramRoverBot() {
        super(getConfig());
    }

    /**
     * Loads the <b>YAML</b> configuration from the <b>config.yml</b> file
     *
     * @throws ConfigException When the resource <b>CONFIG_FILE</b> cannot be found or loaded while
     * the {@link Class#getResourceAsStream(String)} method attempts to locate and open it
     */
    private static String getConfig() throws ConfigException {
        try (InputStream input = TelegramRoverBot.class.getResourceAsStream(CONFIG_FILE)) {
            if (input == null)
                throw new ConfigException(CONFIG_FILE);

            final Yaml yaml = new Yaml();
            botConfig = yaml.loadAs(input, TelegramRoverBotConfig.class);

        } catch (IOException e) { e.printStackTrace(); }
        return botConfig.getTelegramBotToken();
    }

    @Override
    public String getBotUsername() { return botConfig.getTelegramBotUsername(); }

    @Override
    public void onUpdateReceived(Update update) {
        if (messageManager.isMessage(update)) {
            handleMessage(update);
        } else if (update.hasCallbackQuery()) {
            handleCallback(update);
        }
    }

    /**
     * Sends responses to the user based on the incoming user's message
     *
     * @param update The instance of a Telegram update object
     */
    private void handleMessage(Update update) {
        if (messageManager.isStartCommand(update)) {
            messageManager.sendWelcome(update);
        } else if (messageManager.isHelpCommand(update)) {
            messageManager.sendHelp(update);
        } else if (messageManager.isSmartSearchButton(update)) {
            messageManager.sendMood(update);
        } else {
            messageManager.sendEcho(update);
        }
    }

    /**
     * Sends responses to the user based on the incoming callbacks that are held in inline buttons
     *
     * @param update The instance of a Telegram update object
     */
    private void handleCallback(Update update) {
        if (inlineKeyboardBuilder.isMoodButton(update)) {
            messageManager.sendCatalogue(update);
        } else if (inlineKeyboardBuilder.isCatalogueButton(update)) {
            messageManager.sendGenre(update);
        } else if (inlineKeyboardBuilder.isGenreButton(update)) {

        }
    }
}

