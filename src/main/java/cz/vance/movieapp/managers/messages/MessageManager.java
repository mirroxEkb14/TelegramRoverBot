package cz.vance.movieapp.managers.messages;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.keyboards.IInlineKeyboardBuilder;
import cz.vance.movieapp.keyboards.IReplyKeyboardBuilder;
import cz.vance.movieapp.keyboards.InlineKeyboardBuilder;
import cz.vance.movieapp.keyboards.ReplyKeyboardBuilder;
import cz.vance.movieapp.managers.ProgramSleeper;
import cz.vance.movieapp.managers.builders.IMessageBuilder;
import cz.vance.movieapp.managers.builders.MessageBuilder;
import cz.vance.movieapp.managers.formatters.IMessageFormatter;
import cz.vance.movieapp.managers.formatters.MessageFormatter;
import cz.vance.movieapp.managers.records.*;
import cz.vance.movieapp.managers.selections.IUserSelectionManager;
import cz.vance.movieapp.managers.selections.UserSelectionManager;
import cz.vance.movieapp.managers.updates.IUpdateExtractor;
import cz.vance.movieapp.managers.updates.UpdateExtractor;
import cz.vance.movieapp.models.*;
import cz.vance.movieapp.managers.randomizers.IMessageRandomizer;
import cz.vance.movieapp.managers.randomizers.MessageRandomizer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import cz.vance.movieapp.bot.TelegramRoverBot;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
//</editor-fold>

/**
 * Implements a set of basic operations for handling messages.
 * <br>
 * Contains a {@link IMessageRandomizer} instance providing all the possible bot messages.
 * <p>
 * All the bot messages are stored within the {@link BotMessage} class.
 * <br>
 * The {@link BotMessageManager} class provides getters for the {@link BotMessage}.
 * <br>
 * The {@link MessageRandomizer} class provides random selection for the {@link BotMessageManager}.
 */
public final class MessageManager implements IMessageManager {

    //<editor-fold default-state="collapsed" desc="Fields">
    /**
     * Instance of the {@link TelegramRoverBot} class needed to send messages.
     */
    private final TelegramLongPollingBot bot;

    private final ModeTracker modeTracker;

    private final IUserSelectionManager userSelectionManager;
    private final IMovieRecord movieRecord;
    private final IFeedbackRecord feedbackRecord;
    private final IMessageFormatter messageFormatter;
    private final IUserRecord userRecord;

    private final IReplyKeyboardBuilder replyKeyboardBuilder;
    private final IInlineKeyboardBuilder inlineKeyboardBuilder;

    /**
     * Instance of the {@link MessageBuilder} class that returns the <b>SendMessage</b> objects with the provided
     * message parameter, such as <b>chat id</b>, <b>message text</b>, and <b>keyboard</b>.
     */
    private final IMessageBuilder messageBuilder;
    /**
     * Instance of the {@link UpdateExtractor} class that extracts information from an incoming Telegram update
     * (including <b>chat id</b>, <b>message text</b>, <b>message id</b> etc.).
     */
    private final IUpdateExtractor updateExtractor;
    /**
     * Instance of the {@link MessageRandomizer} class that returns phrase strings during the <b>smart search</b>.
     */
    private final IMessageRandomizer messageRandomizer;
    /**
     * Instance of the {@link BotMessageManager} class enables changing the language of the bot messages.
     */
    private final IBotMessageManager botMessageManager;

    /**
     * Stores the latest message id for each chat id.
     * <br>
     * Is used to determine if the user interacts with inline keyboards that were sent to him earlier and should be
     * removed.
     */
    private final HashMap<Long, Integer> latestMessageIds = new HashMap<>();

    /**
     * Stores the <b>chat ids</b> of all the users who have launched the bot.
     * <br>
     * Is used to determine the <b>chat id</b> to remove the reply keyboard when the bot is terminated.
     */
    private final Set<Long> chatIds = new HashSet<>();

    /**
     * Is used to determine if the user pressed the <b>send feedback</b> reply keyboard button.
     * <br>
     * Is then used to extract the user's feedback message.
     */
    private static boolean isSendFeedbackPressed;
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Latest Message Handler">
    /**
     * Is always called on the first step of each <b>main menu mods</b>, because each mode means sending a new message
     * and the id of this message should be stored in the map.
     * <br>
     * Previously stored message ids are removed.
     */
    private final Consumer<Message> latestMessageHandler = message -> {
        if (IUpdateExtractor.messagePresenceChecker.apply(message)) {
            final long chatId = message.getChatId();
            final int messageId = message.getMessageId();
            latestMessageIds.clear();
            latestMessageIds.put(chatId, messageId);
        }
    };

    /**
     * Is called when some of the <b>'main menu' reply keyboard buttons</b> were pressed.
     * <br>
     * If the user, for instance, is in the <b>smart search</b> mode (meaning selects a <b>mood</b>, <b>catalogue</b>,
     * <b>genre</b> or <b>confirmation inline button</b>) and then, not finishing the search, presses one of the
     * <b>'main menu' reply keyboard buttons</b>, the bot removes the current <b>inline keyboard</b> and sends a new
     * message.
     */
    private final Consumer<Long> modeProcessingHandler = chatId -> {
        if (!latestMessageIds.isEmpty()){
            handleRepeatedInlineKeyboard(chatId, latestMessageIds.get(chatId));
            latestMessageIds.remove(chatId);
        }
    };
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Constructor">
    public MessageManager(TelegramLongPollingBot bot) {
        modeTracker = ModeTracker.getInstance();

        userSelectionManager = UserSelectionManager.getInstance();
        movieRecord = MovieRecord.getInstance();
        feedbackRecord = FeedbackRecord.getInstance();
        userRecord = UserRecord.getInstance();

        messageFormatter = new MessageFormatter();

        replyKeyboardBuilder = new ReplyKeyboardBuilder();
        inlineKeyboardBuilder = new InlineKeyboardBuilder();

        messageBuilder = new MessageBuilder();
        updateExtractor = new UpdateExtractor();

        messageRandomizer = new MessageRandomizer();

        botMessageManager = BotMessageManager.getInstance();

        this.bot = bot;
    }
    //</editor-fold>

    @Override
    public void sendEcho(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);

        if (IUpdateExtractor.hasText(botUpdate)) {
            final String messageText = updateExtractor.getMessageText(botUpdate);
            sendEchoText(chatId, messageText);
        } else if (IUpdateExtractor.hasSticker(botUpdate)) {
            final String stickerFileId = updateExtractor.getStickerFileId(botUpdate);
            sendEchoSticker(chatId, stickerFileId);
        } else if (IUpdateExtractor.hasPhoto(botUpdate)) {
            final UserPhoto userPhoto = updateExtractor.getUserPhoto(botUpdate);
            sendEchoPhoto(chatId, userPhoto);
        } else if (IUpdateExtractor.hasDocument(botUpdate) || IUpdateExtractor.hasVideo(botUpdate) ||
                IUpdateExtractor.hasAudio(botUpdate) || IUpdateExtractor.hasVoice(botUpdate)) {
            sendUnknownInput(chatId);
        }

        userRecord.insertUserIfNotExists(botUpdate);
    }

    @Override
    public void sendWelcome(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String messageText = getFormattedWelcomeText(botUpdate);
        final String stickerFileId = messageRandomizer.getWelcomeSticker();

        userRecord.insertUserIfNotExists(botUpdate);

        sendSticker(chatId, stickerFileId);
        sendWelcome(chatId, messageText);
    }

    @Override
    public void sendHelp(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String messageText = messageRandomizer.getHelpMessage();

        sendHelp(chatId, messageText);
        userRecord.insertUserIfNotExists(botUpdate);
    }

    @Override
    public void sendLang(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        if (modeTracker.isSmartSearchActive()) {
            sendMessage(chatId, messageRandomizer.getOnSmartSearchDeviationMessage());
            return;
        } else if (modeTracker.isWeRecommendActive()) {
            sendMessage(chatId, messageRandomizer.getOnWeRecommendDeviationMessage());
            return;
        } else if (modeTracker.isNoIdeaActive()) {
            sendMessage(chatId, messageRandomizer.getOnNoIdeaDeviationMessage());
            return;
        } else if (modeTracker.isSendFeedbackActive()) {
            sendMessage(chatId, messageRandomizer.getOnSendFeedbackDeviationMessage());
            return;
        }

        botMessageManager.changeBotMessageLanguage();
        userRecord.insertUserIfNotExists(botUpdate);

        final String messageText = messageRandomizer.getLangMessage();
        sendLang(chatId, messageText);
    }

    @Override
    public void sendSmartSearchMood(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String atLaunchGreetingsText = messageRandomizer.getSmartSearchAtLaunchGreetingsMessage();
        final String messageText = messageRandomizer.getSmartSearchMoodMessage();
        final String stickerFileId = messageRandomizer.getSmartSearchBeginningSticker();

        modeProcessingHandler.accept(chatId);

        modeTracker.activateSmartSearch();
        userSelectionManager.initializeUserSelection(chatId);

        sendMessage(chatId, atLaunchGreetingsText);
        ProgramSleeper.pauseSmartSearchBeforeSendingMood();

        sendSticker(chatId, stickerFileId);

        final Message moodMessage = sendMessage(chatId, messageText, inlineKeyboardBuilder.buildSmartSearchMoodKeyboard());
        latestMessageHandler.accept(moodMessage);

        userRecord.insertUserIfNotExists(botUpdate);
    }

    @Override
    public void sendSmartSearchCatalogue(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        final String messageText = messageRandomizer.getSmartSearchCatalogueMessage();

        final String mood = updateExtractor.getCallbackQuery(botUpdate);
        userSelectionManager.putMood(chatId, mood);

        sendMessage(chatId, messageId, messageText, inlineKeyboardBuilder.buildSmartSearchCatalogueKeyboard());
    }

    @Override
    public void sendSmartSearchGenre(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);
        final String messageText = messageRandomizer.getSmartSearchGenreMessage();

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        final String catalogue = updateExtractor.getCallbackQuery(botUpdate);
        userSelectionManager.putCatalogue(chatId, catalogue);

        sendMessage(chatId, messageId, messageText, inlineKeyboardBuilder.buildSmartSearchGenreKeyboard());
    }

    @Override
    public void sendSmartSearchConfirmation(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        final String genre = updateExtractor.getCallbackQuery(botUpdate);
        userSelectionManager.putGenre(chatId, genre);

        final String messageText = messageRandomizer.getSmartSearchVerifyingMessage(
                userSelectionManager.getUserSelection(chatId));
        sendMessage(chatId, messageId, messageText, inlineKeyboardBuilder.buildSmartSearchConfirmationKeyboard());
    }

    //<editor-fold default-state="collapsed" desc="Overridden 'sendMovie' Method">
    @Override
    public void sendSmartSearchMovie(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);
        final String stickerFileId = messageRandomizer.getSmartSearchEndSticker();

        if (isSmartSearchNoButtonPressed(botUpdate)) {
            handleSmartSearchNoConfirmationButton(chatId, messageId, botUpdate);
            return;
        }

        removeMessageInlineKeyboard(chatId, messageId);
        modeTracker.deactivateSmartSearch();
        processSmartSearch(chatId, stickerFileId);
    }

    /**
     * Checks if the user has pressed the <b>no confirmation</b> button in the <b>smart search</b> inline keyboard.
     */
    private boolean isSmartSearchNoButtonPressed(Update botUpdate) { return inlineKeyboardBuilder.isSmartSearchNoButton(botUpdate); }

    /**
     * In case the user has pressed the <b>no confirmation</b> button in the <b>smart search</b> inline keyboard:
     * <ul>
     *     <li>Removes the inline keyboard;</li>
     *     <li>Sends a message to the user that the <b>smart search</b> will be rebooted;</li>
     *     <li>Removes the user's latest message id, because the process will be started again;</li>
     *     <li>Removes the user's selection;</li>
     *     <li>Pauses the program before sending the mood message;</li>
     *     <li>Sends the mood message;</li>
     * </ul>
     */
    private void handleSmartSearchNoConfirmationButton(long chatId,
                                                       long messageId,
                                                       Update botUpdate) {
        removeMessageInlineKeyboard(chatId, messageId);
        sendMessage(chatId, messageRandomizer.getOnSmartSearchRebooted());

        latestMessageIds.remove(chatId);
        userSelectionManager.removeUserSelection(chatId);

        ProgramSleeper.pauseSmartSearchBeforeReboot();
        sendSmartSearchMood(botUpdate);
    }

    /**
     * Processes the <b>smart search</b> by:
     * <ul>
     *     <li>Getting the <b>user's selection</b> object;</li>
     *     <li>Getting the <b>smart search movies</b>;</li>
     *     <li>Building the <b>movie message</b>;</li>
     *     <li>Sending a <b>sticker</b>;</li>
     *     <li>Pausing the program before sending the <b>movie message</b> to imitate the process of search;</li>
     *     <li>Sending the <b>movie message</b>;</li>
     *     <li>Removing the user's latest message id;</li>
     *     <li>Removing the <b>user's selection</b>;</li>
     *     <li>Pausing the program before sending a new mood message;</li>
     *     <li>Sending the <b>mood message</b>;</li>
     * </ul>
     */
    private void processSmartSearch(long chatId,
                                    String stickerFileId) {
        final UserSelection userSelection = userSelectionManager.getUserSelection(chatId);
        final List<Movie> smartSearchMovies = movieRecord.getSmartSearchMovies(userSelection);

        final String movieMessageText = smartSearchMovies.isEmpty() ?
                messageFormatter.getSmartSearchNoMoviesMessage() :
                messageFormatter.getSmartSearchMovieMessage(smartSearchMovies);

        sendSticker(chatId, stickerFileId);
        ProgramSleeper.pauseSmartSearchBeforeSendingMovie();
        sendMessage(chatId, movieMessageText);

        ProgramSleeper.pauseSmartSearchAfterSendingMovie();
        sendMessage(chatId, messageRandomizer.getSmartSearchOnFinishMessage());

        latestMessageIds.remove(chatId);
        userSelectionManager.removeUserSelection(chatId);
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Overridden 'handleSmartSearchBack' Method">
    @Override
    public void handleSmartSearchBack(Update botUpdate) {
        final long chatId = updateExtractor.getCallbackChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        final UserSelection userSelection = userSelectionManager.getUserSelection(chatId);

        if (userSelection.isOnlyMoodSelected())
            handleSmartSearchBackToMood(chatId, messageId);
        else if (userSelection.isOnlyCatalogueSelected())
            handleSmartSearchBackToCatalogue(chatId, messageId);
        else if (userSelection.isOnlyGenreSelected())
            handleSmartSearchBackToGenre(chatId, messageId);
    }

    /**
     * Handles the <b>smart search</b> back button:
     * <ul>
     *     <li>Nullifies the user's mood selection;</li>
     *     <li>Sends a message (with attached inline keyboard with the <b>mood selection</b>) to the user that he is
     *     going back to the <b>mood selection</b>;</li>
     * </ul>
     */
    private void handleSmartSearchBackToMood(long chatId, long messageId) {
        final String messageText = messageRandomizer.getOnSmartSearchCatalogueBackMessage();

        final UserSelection userSelection = userSelectionManager.getUserSelection(chatId);
        userSelection.nullifyMood();

        sendMessage(chatId, messageId, messageText, inlineKeyboardBuilder.buildSmartSearchMoodKeyboard());
    }

    /**
     * Handles the <b>smart search</b> back button:
     * <ul>
     *     <li>Nullifies the user's catalogue selection;</li>
     *     <li>Sends a message (with attached inline keyboard with the <b>catalogue selection</b>) to the user that he
     *     is going back to the <b>movies/series selection</b>;</li>
     * </ul>
     */
    private void handleSmartSearchBackToCatalogue(long chatId, long messageId) {
        final String messageText = messageRandomizer.getOnSmartSearchCatalogueBackMessage();

        final UserSelection userSelection = userSelectionManager.getUserSelection(chatId);
        userSelection.nullifyCatalogue();

        sendMessage(chatId, messageId, messageText, inlineKeyboardBuilder.buildSmartSearchCatalogueKeyboard());
    }

    /**
     * Handles the <b>smart search</b> back button:
     * <ul>
     *     <li>Nullifies the user's genre selection;</li>
     *     <li>Sends a message (with attached inline keyboard with the <b>genre selection</b>) to the user that he is
     *     going back to the <b>genre selection</b>;</li>
     * </ul>
     */
    private void handleSmartSearchBackToGenre(long chatId, long messageId) {
        final String messageText = messageRandomizer.getOnSmartSearchGenreBackMessage();

        final UserSelection userSelection = userSelectionManager.getUserSelection(chatId);
        userSelection.nullifyGenre();

        sendMessage(chatId, messageId, messageText, inlineKeyboardBuilder.buildSmartSearchGenreKeyboard());
    }
    //</editor-fold>

    @Override
    public void sendNoIdeaFirstMovie(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String atLaunchGreetingsText = messageRandomizer.getNoIdeaAtLaunchGreetingsMessage();
        final String stickerFileId = messageRandomizer.getNoIdeaBeginningSticker();

        modeProcessingHandler.accept(chatId);

        final Movie noIdeaFirstMovie = movieRecord.getNoIdeaCurrentMovie();
        final String noIdeaFirstMovieText = messageFormatter.getNoIdeaMovieMessage(noIdeaFirstMovie);

        sendSticker(chatId, stickerFileId);
        sendMessage(chatId, atLaunchGreetingsText);
        ProgramSleeper.pauseNoIdeaBeforeSendingFirstMovie();

        final Message firstMovieMessage = sendMessage(chatId, noIdeaFirstMovieText, inlineKeyboardBuilder.buildNoIdeaFirstMovieKeyboard());
        latestMessageHandler.accept(firstMovieMessage);

        modeTracker.activateNoIdea();
        userRecord.insertUserIfNotExists(botUpdate);
    }

    //<editor-fold default-state="collapsed" desc="Overridden 'sendNoIdeaPreviousMovie' and 'sendNoIdeaNextMovie' Methods">
    @Override
    public void sendNoIdeaPreviousMovie(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        final InlineKeyboardMarkup inlineKeyboardMarkup = getNoIdeaAppropriateInlineKeyboard(botUpdate);
        movieRecord.moveNoIdeaToPreviousMovie();

        final Movie noIdeaPreviousMovie = movieRecord.getNoIdeaCurrentMovie();
        final String noIdeaPreviousMovieText = messageFormatter.getNoIdeaMovieMessage(noIdeaPreviousMovie);

        sendMessage(chatId, messageId, noIdeaPreviousMovieText, inlineKeyboardMarkup);
    }

    @Override
    public void sendNoIdeaNextMovie(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        final InlineKeyboardMarkup inlineKeyboardMarkup = getNoIdeaAppropriateInlineKeyboard(botUpdate);
        handleNoIdeaNoMoviesLeft(chatId);
        movieRecord.moveNoIdeaToNextMovie();

        final Movie noIdeaNextMovie = movieRecord.getNoIdeaCurrentMovie();
        final String noIdeaNextMovieText = messageFormatter.getNoIdeaMovieMessage(noIdeaNextMovie);

        sendMessage(chatId, messageId, noIdeaNextMovieText, inlineKeyboardMarkup);
    }

    /**
     * Returns a {@link InlineKeyboardMarkup} instance by checking:
     * <ul>
     *     <li>if the next movie to be displayed is the first in the list, then the <b>previous movie inline keyboard
     *     button</b> should not be present in the markup;</li>
     *     <li>if the next movie to be displayed is the last in the list, then the <b>next movie inline keyboard
     *     button</b> should not be present in the markup;</li>
     *     <li>if the next movie to be displayed is neither the first nor the last in the list, then the <b>previous/next
     *     movie inline keyboard buttons</b> should be present in the markup;</li>
     * </ul>
     *
     * @param botUpdate the {@link Update} instance to check what <b>inline keyboard</b> was pressed.
     */
    private InlineKeyboardMarkup getNoIdeaAppropriateInlineKeyboard(Update botUpdate) {
        if (inlineKeyboardBuilder.isNoIdeaPreviousMovieButton(botUpdate) && movieRecord.isNoIdeaPreviousMovieFirst())
            return inlineKeyboardBuilder.buildNoIdeaFirstMovieKeyboard();
        else if (inlineKeyboardBuilder.isNoIdeaNextMovieButton(botUpdate) && movieRecord.isNoIdeaNextMovieLast())
            return inlineKeyboardBuilder.buildNoIdeaLastMovieKeyboard();
        else
            return inlineKeyboardBuilder.buildNoIdeaInterimMovieKeyboard();
    }

    /**
     * In case the user is on the last movie available in the DB, the bot sends a notification message about it.
     */
    private void handleNoIdeaNoMoviesLeft(long chatId) {
        if (movieRecord.isNoIdeaNextMovieLast()) {
            final String messageText = messageRandomizer.getNoIdeaNoMoviesLeftMessage();
            sendMessage(chatId, messageText);
        }
    }
    //</editor-fold>

    @Override
    public void handleNoIdeaToMainMenu(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);
        final String messageText = messageRandomizer.getNoIdeaToMainMenuMessage();

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        removeMessageInlineKeyboard(chatId, messageId);
        sendMessage(chatId, messageText);

        latestMessageIds.remove(chatId);
        modeTracker.deactivateNoIdea();
    }

    @Override
    public void sendWeRecommendFirstMovie(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String atLaunchGreetingsText = messageRandomizer.getWeRecommendAtLaunchGreetingsMessage();

        modeProcessingHandler.accept(chatId);

        final Movie weRecommendFirstMovie = movieRecord.getWeRecommendCurrentMovie();
        final String movieDetailsUrl = weRecommendFirstMovie.detailsLink();
        final String weRecommendFirstMovieText = messageFormatter.getWeRecommendMovieMessage(weRecommendFirstMovie);

        sendMessage(chatId, atLaunchGreetingsText);
        ProgramSleeper.pauseWeRecommendBeforeSendingFirstMovie();

        final Message firstMovieMessage = sendMessage(
                chatId,
                weRecommendFirstMovieText,
                inlineKeyboardBuilder.buildWeRecommendInterimMovieKeyboard(movieDetailsUrl));
        latestMessageHandler.accept(firstMovieMessage);

        modeTracker.activateWeRecommend();
        userRecord.insertUserIfNotExists(botUpdate);
    }

    //<editor-fold default-state="collapsed" desc="Overridden 'sendWeRecommendPreviousMovie' and 'sendWeRecommendNextMovie' Methods">
    @Override
    public void sendWeRecommendPreviousMovie(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        movieRecord.moveWeRecommendToPreviousMovie();
        processWeRecommend(chatId, messageId);
    }

    @Override
    public void sendWeRecommendNextMovie(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }
        movieRecord.moveWeRecommendToNextMovie();
        processWeRecommend(chatId, messageId);
    }

    /**
     * Processes the <b>We Recommend</b> movie by:
     * <ul>
     *     <li>getting the next movie to display (whether the <b>previous/next movie inline keyboard button</b> was pressed);</li>
     *     <li>building the <b>movie details url</b>;</li>
     *     <li>building the <b>movie message text</b>;</li>
     *     <li>sending the <b>movie message</b> to the user.</li>
     * </ul>
     */
    private void processWeRecommend(long chatId, long messageId) {
        final Movie nextMovieToDisplay = movieRecord.getWeRecommendCurrentMovie();
        final String movieDetailsUrl = nextMovieToDisplay.detailsLink();
        final String weRecommendNextMovieText = messageFormatter.getWeRecommendMovieMessage(nextMovieToDisplay);

        sendMessage(
                chatId,
                messageId,
                weRecommendNextMovieText,
                inlineKeyboardBuilder.buildWeRecommendInterimMovieKeyboard(movieDetailsUrl));
    }
    //</editor-fold>

    @Override
    public void sendWeRecommendWatch(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);
        final String messageText = messageRandomizer.getWeRecommendPleasantViewingMessage();

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        removeMessageInlineKeyboard(chatId, messageId);
        sendMessage(chatId, messageText);

        latestMessageIds.remove(chatId);
    }

    @Override
    public void handleWeRecommendToMainMenu(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);
        final String messageText = messageRandomizer.getWeRecommendToMainMenuMessage();

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        removeMessageInlineKeyboard(chatId, messageId);
        sendMessage(chatId, messageText);

        latestMessageIds.remove(chatId);
        modeTracker.deactivateWeRecommend();
    }

    @Override
    public void sendFeedbackAtLaunchGreetings(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String messageText = messageRandomizer.getSendFeedbackAtLaunchGreetingsMessage();

        modeProcessingHandler.accept(chatId);

        sendMessage(chatId, messageText);
        updateIsSendFeedbackPressed();

        modeTracker.activateSendFeedback();
        userRecord.insertUserIfNotExists(botUpdate);
    }

    @Override
    public void sendFeedbackConfirmation(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final String userFeedbackText = updateExtractor.getMessageText(botUpdate);
        final String messageText = messageRandomizer.getSendFeedbackConfirmationMessage(userFeedbackText);

        modeProcessingHandler.accept(chatId);

        final Message feedbackConfirmationMessage = sendMessage(
                chatId,
                messageText,
                inlineKeyboardBuilder.buildSendFeedbackConfirmationKeyboard());
        latestMessageHandler.accept(feedbackConfirmationMessage);
    }

    //<editor-fold default-state="collapsed" desc="Overridden 'sendFeedbackAtEndFarewell' Method">
    @Override
    public void sendFeedbackAtEndFarewell(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        final long messageId = updateExtractor.getCallbackMessageId(botUpdate);

        if (isOldInlineKeyboard(chatId, messageId)) {
            handleOldInlineKeyboard(chatId, messageId);
            return;
        }

        modeTracker.deactivateSendFeedback();

        if (inlineKeyboardBuilder.isSendFeedbackNoButton(botUpdate)) {
            handleSendFeedbackNoButton(chatId, messageId);
            updateIsSendFeedbackPressed();
            return;
        }
        handleSendFeedbackYesButton(chatId, messageId, botUpdate);
        updateIsSendFeedbackPressed();
    }

    /**
     * Handles the <b>No</b> button press for the <b>Send Feedback</b> message:
     * <ul>
     *     <li>removes the <b>inline keyboard</b>;</li>
     *     <li>sends the <b>'send feedback' no confirmation</b> message;</li>
     *     <li>removes the <b>latest message id</b> from the <b>latest message ids</b> map.</li>
     * </ul>
     */
    private void handleSendFeedbackNoButton(long chatId, long messageId) {
        removeMessageInlineKeyboard(chatId, messageId);
        sendMessage(chatId, messageRandomizer.getSendFeedbackNoConfirmationMessage());

        latestMessageIds.remove(chatId);
    }

    /**
     * Handles the <b>Yes</b> button press for the <b>Send Feedback</b> message:
     * <ul>
     *     <li>removes the <b>inline keyboard</b>;</li>
     *     <li>sends the <b>'send feedback' yes confirmation</b> message;</li>
     *     <li>removes the <b>latest message id</b> from the <b>latest message ids</b> map.</li>
     * </ul>
     */
    private void handleSendFeedbackYesButton(long chatId,
                                             long messageId,
                                             Update botUpdate) {
        final Feedback feedback = new Feedback((int)chatId, updateExtractor.getSendFeedbackCallbackMessageText(botUpdate));
        feedbackRecord.insertFeedback(feedback);

        removeMessageInlineKeyboard(chatId, messageId);
        sendMessage(chatId, messageRandomizer.getSendFeedbackYesConfirmationMessage());

        latestMessageIds.remove(chatId);
    }
    //</editor-fold>

    @Override
    public boolean isSendFeedbackPressed() { return isSendFeedbackPressed; }

    @Override
    public void removeReplyKeyboard() { chatIds.forEach(this::removeMenuReplyKeyboard); }

    @Override
    public void addChatId(Update botUpdate) {
        final long chatId = updateExtractor.getMessageChatId(botUpdate);
        chatIds.add(chatId);
    }

    /* ---------------- Private Helper Methods -------------- */

    //<editor-fold default-state="collapsed" desc="Echo Message Sender">
    private void sendEchoText(long chatId, String messageText) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(chatId, messageText));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendEchoSticker(long chatId, String stickerFileId) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramSticker(chatId, stickerFileId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendEchoPhoto(long chatId, UserPhoto userPhoto) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramPhoto(chatId, userPhoto));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Welcome Message Sender">
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
     * Formats and returns the welcome text by adding <b>user's username</b> and <b>the username of the bot</b>.
     *
     * @param botUpdate The incoming update to extract the <b>user's surname</b>.
     *
     * @return The formatted text for a welcome message.
     */
    private String getFormattedWelcomeText(@NotNull Update botUpdate) {
        return String.format(
                messageRandomizer.getWelcomeMessage(),
                botUpdate.getMessage().getFrom().getFirstName(),
                bot.getBotUsername());
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Help Message Sender">
    private void sendHelp(long chatId, String messageText) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(chatId, messageText));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Lang Message Sender">
    private void sendLang(long chatId, String messageText) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(
                            chatId,
                            messageText,
                            replyKeyboardBuilder.buildMainMenuKeyboard()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Message Senders">
    private @Nullable Message sendMessage(long chatId,
                                          String messageText,
                                          InlineKeyboardMarkup inlineMarkup) {
        try {
            return bot.execute(
                    messageBuilder.buildTelegramMessage(
                            chatId,
                            messageText,
                            inlineMarkup));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void sendMessage(long chatId,
                             long messageId,
                             String messageText,
                             InlineKeyboardMarkup inlineMarkup) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(
                            chatId,
                            messageId,
                            messageText,
                            inlineMarkup));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(long chatId, String messageText) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(
                            chatId,
                            messageText));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Sticker Message Sender">
    private void sendSticker(long chatId, String stickerFileId) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramSticker(
                            chatId,
                            stickerFileId));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Unknown Input Message Sender">
    private void sendUnknownInput(long chatId) {
        try {
            bot.execute(
                    messageBuilder.buildTelegramMessage(
                            chatId,
                            messageRandomizer.getUnknownInputMessage()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Message Editors">
    /**
     * Removes the inline keyboard from the specified message.
     *
     * @param chatId The <b>chat id</b> where the message is located.
     * @param messageId The <b>message id</b> of the message to update.
     */
    private void removeMessageInlineKeyboard(long chatId, long messageId) {
        final EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
        editMessageReplyMarkup.setChatId(chatId);
        editMessageReplyMarkup.setMessageId((int) messageId);
        editMessageReplyMarkup.setReplyMarkup(null);

        try {
            bot.execute(editMessageReplyMarkup);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edits the text of the specified message.
     *
     * @param chatId The <b>chat id</b> where the message is located.
     * @param messageId The <b>message id</b> of the message to update.
     * @param messageText The <b>new text</b> to set for the message.
     */
    private void editMessageText(long chatId,
                                 long messageId,
                                 String messageText) {
        final EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId((int) messageId);
        editMessageText.setText(messageText);

        try {
            bot.execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void removeMenuReplyKeyboard(long chatId) {
        final SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(
                messageRandomizer.getOnBotTerminatedMessage());

        final ReplyKeyboardRemove keyboardRemove = new ReplyKeyboardRemove();
        keyboardRemove.setRemoveKeyboard(true);

        message.setReplyMarkup(keyboardRemove);

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>

    //<editor-fold default-state="collapsed" desc="Old Keyboard Handlers">
    private void handleOldInlineKeyboard(long chatId, long messageId) {
        if (isOldInlineKeyboard(chatId, messageId)) {
            removeMessageInlineKeyboard(chatId, messageId);
            editMessageText(
                    chatId,
                    messageId,
                    messageRandomizer.getOnSmartSearchOldKeyboardRemovedMessage());
        }
    }

    private void handleRepeatedInlineKeyboard(long chatId, long messageId) {
        if (isRepeatedInlineKeyboard(chatId, messageId)) {
            removeMessageInlineKeyboard(chatId, messageId);
            editMessageText(
                    chatId,
                    messageId,
                    messageRandomizer.getOnSmartSearchRepeatedKeyboardRemovedMessage());
        }
    }

    /**
     * Checks if the passed message is repeated, meaning the bot sent a message with an inline keyboard, but then
     * the user triggers the bot to send a new message with some inline keyboard. The previously sent keyboard
     * should be removed.
     */
    private boolean isRepeatedInlineKeyboard(long chatId, long messageId) {
        return latestMessageIds.containsKey(chatId) && latestMessageIds.get(chatId) == (int)messageId;
    }

    /**
     * Checks if the passed message is old, meaning the attached inline keyboard is outdated.
     * <br>
     * The old keyboard — is an inline keyboard attached to a message that was sent before the latest message. For
     * instance, the user could interact with the bot and the last message the bot sent was a message with an inline
     * keyboard. Then the bot was rebooted. This last message is now outdated and the inline keyboard should be removed.
     */
    private boolean isOldInlineKeyboard(long chatId, long messageId) {
        return !latestMessageIds.containsKey(chatId) || latestMessageIds.get(chatId) != (int)messageId;
    }
    //</editor-fold>

    private void updateIsSendFeedbackPressed() { isSendFeedbackPressed = !isSendFeedbackPressed; }

    /* ------------------------------------------------------ */
}
