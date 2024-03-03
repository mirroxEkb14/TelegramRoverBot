package cz.vance.movieapp.managers.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;
import cz.vance.movieapp.keyboards.ReplyKeyboardBuilder;
import cz.vance.movieapp.managers.builders.IMessageBuilder;
import cz.vance.movieapp.managers.builders.MessageBuilder;
import cz.vance.movieapp.managers.updates.IUpdateExtractor;
import cz.vance.movieapp.managers.updates.UpdateExtractor;
import cz.vance.movieapp.randomizers.ISmartSearchRandomizer;
import cz.vance.movieapp.randomizers.SmartSearchRandomizer;
import cz.vance.movieapp.utils.BotMessage;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import cz.vance.movieapp.bot.TelegramRoverBot;
//</editor-fold>

/**
 * This <b>MessageManager</b> class implements a set of basic operations for handling messages
 *
 * @see IMessageManager
 */
public final class MessageManager implements IMessageManager {

    /**
     * This <b>constant</b> is an instance of the {@link TelegramRoverBot} class that is needed to send
     * messages
     */
    private final TelegramLongPollingBot bot;
    /**
     * These <b>constants</b> are instances of the custom keyboard builders that provide with different custom
     * keyboards
     */
    private final ReplyKeyboardBuilder replyKeyboardBuilder = new ReplyKeyboardBuilder();
    private final InlineKeyboardBuilder inlineKeyboardBuilder = new InlineKeyboardBuilder();
    /**
     * This <b>constant</b> is an instance of a class that builds messages with the provided message parameters
     */
    private final IMessageBuilder messageBuilder = new MessageBuilder();
    /**
     * This <b>constant</b> holds an instance of a class that extracts information from an incoming Telegram update
     */
    private final IUpdateExtractor updateExtractor = new UpdateExtractor();
    /**
     * This <b>constant</b> holds an instance of class that contains methods for phrase selection during the
     * <b>Smart Search</b>
     */
    private final ISmartSearchRandomizer smartSearchRandomizer = new SmartSearchRandomizer();

    /**
     * Constructor
     * <p>
     * Initializes the <b>bot</b> constant variable
     *
     * @param bot An instance of the {@link TelegramRoverBot} object
     */
    public MessageManager(TelegramLongPollingBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendEcho(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String messageText = updateExtractor.getMessageText(botUpdate);
        sendEcho(chatId, messageText);
    }

    @Override
    public void sendWelcome(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String messageText = getFormattedWelcomeText(botUpdate);
        sendWelcome(chatId, messageText);
    }

    @Override
    public void sendHelp(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String messageText = BotMessage.HELP_MESSAGE.content();
        sendHelp(chatId, messageText);
    }

    @Override
    public void sendMood(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String messageText = smartSearchRandomizer.getMoodMessage();
        sendMood(chatId, messageText);
    }

    @Override
    public void sendCatalogue(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);
        final String messageText = smartSearchRandomizer.getCatalogueMessage();
        sendCatalogue(chatId, messageId, messageText);
    }

    @Override
    public void sendGenre(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);
        final String messageText = smartSearchRandomizer.getGenreMessage();
        sendGenre(chatId, messageId, messageText);
    }

//<editor-fold default-state="collapsed" desc="Echo Message">
    /**
     * @see IMessageBuilder#buildTelegramMessage(long, String)
     */
    private void sendEcho(long chatId, String messageText) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(chatId, messageText));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="Welcome Message">
    private static final String USER_USERNAME_PATTERN = "<b>%s</b>";

    /**
     * @see IMessageBuilder#buildTelegramMessage(long, String, ReplyKeyboardMarkup)
     */
    private void sendWelcome(long chatId, String messageText) {
        try {
            bot.execute(messageBuilder.buildTelegramMessage(
                    chatId,
                    messageText,
                    replyKeyboardBuilder.buildMainMenuKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats and returns the welcome text by adding <b>user's username</b> and <b>the username of the bot</b>
     *
     * @param botUpdate The bot update to extract <b>user's surname</b>
     *
     * @return The formatted text for a welcome message
     */
    private String getFormattedWelcomeText(@NotNull Update botUpdate) {
        return String.format(
                BotMessage.WELCOME_MESSAGE.content(),
                String.format(USER_USERNAME_PATTERN, botUpdate.getMessage().getFrom().getFirstName()),
                bot.getBotUsername());
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="sendHelp">
    /**
     * @see IMessageBuilder#buildTelegramMessage(long, String)
     */
    private void sendHelp(long chatId, String messageText) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(chatId, messageText));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="sendMood">
    /**
     * @see IMessageBuilder#buildTelegramMessage(long, String)
     */
    private void sendMood(long chatId, String messageText) {
        try {
            bot.execute(messageBuilder.buildTelegramMessage(
                    chatId,
                    messageText,
                    inlineKeyboardBuilder.buildMoodKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="sendCatalogue">
    /**
     * @see IMessageBuilder#buildTelegramMessage(long, long, String, InlineKeyboardMarkup)
     */
    private void sendCatalogue(long chatId, long messageId, String messageText) {
        try {
            bot.execute(messageBuilder.buildTelegramMessage(
                    chatId,
                    messageId,
                    messageText,
                    inlineKeyboardBuilder.buildCatalogueKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//</editor-fold>

//<editor-fold default-state="collapsed" desc="sendGenre">
    /**
     * @see IMessageBuilder#buildTelegramMessage(long, long, String, InlineKeyboardMarkup)
     */
    private void sendGenre(long chatId, long messageId, String messageText) {
        try {
            bot.execute(messageBuilder.buildTelegramMessage(
                    chatId,
                    messageId,
                    messageText,
                    inlineKeyboardBuilder.buildGenreKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
//</editor-fold>
}
