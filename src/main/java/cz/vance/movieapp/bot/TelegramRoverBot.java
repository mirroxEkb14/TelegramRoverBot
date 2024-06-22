package cz.vance.movieapp.bot;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.exceptions.ConfigException;
import cz.vance.movieapp.keyboards.IInlineKeyboardBuilder;
import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;
import cz.vance.movieapp.managers.MessageLogger;
import cz.vance.movieapp.managers.messages.IMessageManager;
import cz.vance.movieapp.managers.messages.MessageManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
//</editor-fold>

/**
 * Handles incoming updates from Telegram (messages and callback queries).
 * <br>
 * Sends responses to the user (via {@link MessageManager}).
 *
 * @see <a href="https://github.com/rubenlagus/TelegramBots#telegram-bot-java-library">TelegramBots</a>
 */
public final class TelegramRoverBot extends TelegramLongPollingBot {

    private static final String CONFIG_FILE = "/config.yml";
    private static TelegramRoverBotConfig botConfig;

    private final IMessageManager messageManager = new MessageManager(this);
    private final IInlineKeyboardBuilder inlineKeyboardBuilder = new InlineKeyboardBuilder();

    /**
     * Calls the superclass constructor with the bot token obtained from the configuration file.
     */
    public TelegramRoverBot() { super(getConfig()); }

    /**
     * Loads the bot settings from the configuration file.
     *
     * @return The string representation of the bot token.
     *
     * @throws ConfigException if the configuration file cannot be found or read.
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

    /**
     * Determines whether the update is a <b>message</b> or a <b>callback query</b> and handles it accordingly.
     *
     * @param update The update received from Telegram.
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (messageManager.isMessage(update))
            handleMessage(update);
        else if (update.hasCallbackQuery())
            handleCallback(update);
    }

    /**
     * Delegates the processing of the incoming messages (based on their content) to the {@link MessageManager}.
     *
     * @param update The update containing the message.
     */
    private void handleMessage(Update update) {
        if (messageManager.isStartCommand(update))
            messageManager.sendWelcome(update);
        else if (messageManager.isHelpCommand(update))
            messageManager.sendHelp(update);
        else if (messageManager.isSmartSearchButton(update))
            messageManager.sendSmartSearchMood(update);
        else if (messageManager.isNoIdeaButton(update))
            messageManager.sendNoIdeaFirstMovie(update);
        else
            messageManager.sendEcho(update);

        MessageLogger.log(update);
    }

    /**
     * Delegates the processing of the callback queries to the {@link MessageManager}.
     * <br>
     * The triggered inline keyboard buttons are tested via the {@link InlineKeyboardBuilder}.
     *
     * @param update The update containing the callback query.
     */
    private void handleCallback(Update update) {
        if (inlineKeyboardBuilder.isSmartSearchMoodButton(update))
            messageManager.sendSmartSearchCatalogue(update);
        else if (inlineKeyboardBuilder.isSmartSearchCatalogueButton(update))
            messageManager.sendSmartSearchGenre(update);
        else if (inlineKeyboardBuilder.isSmartSearchGenreButton(update))
            messageManager.sendSmartSearchConfirmation(update);
        else if (inlineKeyboardBuilder.isSmartSearchConfirmationButton(update))
            messageManager.sendSmartSearchMovie(update);
        else if (inlineKeyboardBuilder.isSmartSearchBackButton(update))
            messageManager.handleSmartSearchBack(update);
        else if (inlineKeyboardBuilder.isNoIdeaPreviousMovieButton(update))
            messageManager.sendNoIdeaPreviousMovie(update);
        else if (inlineKeyboardBuilder.isNoIdeaNextMovieButton(update))
            messageManager.sendNoIdeaNextMovie(update);
        else if (inlineKeyboardBuilder.isNoIdeaToMainMenuButton(update))
            messageManager.handleNoIdeaToMainMenu(update);
    }
}
