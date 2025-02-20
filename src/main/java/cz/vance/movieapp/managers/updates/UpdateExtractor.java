package cz.vance.movieapp.managers.updates;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.exceptions.MovieRatingFormatException;
import cz.vance.movieapp.managers.records.IMovieRatingRecord;
import cz.vance.movieapp.managers.records.IUserRecord;
import cz.vance.movieapp.models.MovieRating;
import cz.vance.movieapp.models.User;
import cz.vance.movieapp.models.UserPhoto;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
//</editor-fold>

/**
 * Implements a set of basic operations for extracting information from an incoming bot update, i.e. messages and callbacks.
 */
public final class UpdateExtractor implements IUpdateExtractor {

    @Override
    public long getMessageChatId(@NotNull Update update) {
        return messagePresenceChecker.apply(update.getMessage()) ?
                update.getMessage().getChatId() :
                messagePresenceChecker.apply(update.getCallbackQuery().getMessage()) ?
                update.getCallbackQuery().getMessage().getChatId() : DEFAULT_ID;
    }

    @Override
    public int getMessageId(@NotNull Update update) {
        return messagePresenceChecker.apply(update.getMessage()) ?
                update.getMessage().getMessageId() : DEFAULT_ID;
    }

    @Override
    public long getUserId(@NotNull Update update) {
        return messagePresenceChecker.apply(update.getMessage()) ?
                update.getMessage().getFrom().getId() :
                update.getCallbackQuery().getFrom().getId();
    }

    @Override
    public @NotNull User getDBUserFromUpdate(@NotNull Update update) {
        final org.telegram.telegrambots.meta.api.objects.User tgUser = messagePresenceChecker.apply(update.getMessage()) ?
                update.getMessage().getFrom() :
                update.getCallbackQuery().getFrom();
        final long tgId = tgUser.getId();
        final String username = getUserName(tgUser);
        final String joinDate = LocalDateTime.now().format(IUserRecord.USER_TABLE_DATE_FORMAT);

        return new User((int)tgId, username, joinDate);
    }

    @Override
    public String getUserFirstName(@NotNull Update update) {
        return messagePresenceChecker.apply(update.getMessage()) ?
                update.getMessage().getChat().getFirstName() :
                update.getCallbackQuery().getMessage().getChat().getFirstName();
    }

    @Override
    public String getUserLastName(@NotNull Update update) {
        return messagePresenceChecker.apply(update.getMessage()) ?
                update.getMessage().getChat().getLastName() :
                update.getCallbackQuery().getMessage().getChat().getLastName();
    }

    @Override
    public String getMessageText(@NotNull Update update) { return update.getMessage().getText(); }

    @Override
    public String getCallbackMessageText(@NotNull Update update) { return update.getCallbackQuery().getMessage().getText(); }

    @Override
    public @NotNull String getSendFeedbackCallbackMessageText(@NotNull Update update) {
        final String originalText = update.getCallbackQuery().getMessage().getText();
        int firstNewlineIndex = originalText.indexOf(LINE_SEPARATOR);

        if (firstNewlineIndex != -1)
            return originalText.substring(firstNewlineIndex + 1).trim();
        return originalText.trim();
    }

    @Override
    public @NotNull MovieRating getMovieRatingCallbackObject(@NotNull Update update) throws MovieRatingFormatException {
        final String originalText = update.getCallbackQuery().getMessage().getText();
        final int tgId = (int)getUserId(update);
        final String ratingDate = LocalDateTime.now().format(IMovieRatingRecord.MOVIE_RATINGS_TABLE_DATE_FORMAT);

        final String[] splitTitleFromFormat = originalText.split(LINE_FORMAT_SEPARATOR);
        final String[] splitText = splitTitleFromFormat[1].split(LINE_SEPARATOR);

        if (splitText.length < 4)
            throw new MovieRatingFormatException();

        return new MovieRating(
                tgId, ratingDate, splitText[0], splitText[1], splitText[2], splitText[3]);
    }

    @Override
    public String getStickerFileId(@NotNull Update update) { return update.getMessage().getSticker().getFileId(); }

    @Override
    public @NotNull UserPhoto getUserPhoto(@NotNull Update update) {
        final List<PhotoSize> photos = update.getMessage().getPhoto();

        final String fileId = photos.stream()
                .max(Comparator.comparing(PhotoSize::getFileSize))
                .map(PhotoSize::getFileId)
                .orElse(null);
        final int fileWidth = photos.stream()
                .max(Comparator.comparing(PhotoSize::getFileSize))
                .map(PhotoSize::getWidth)
                .orElse(0);
        final int fileHeight = photos.stream()
                .max(Comparator.comparing(PhotoSize::getFileSize))
                .map(PhotoSize::getHeight)
                .orElse(0);
        final String inputCaption = update.getMessage().getCaption();
        final String outputCaption = UserPhoto.getOutputCaption(fileId, fileWidth, fileHeight, inputCaption);

        return new UserPhoto(fileId, fileWidth, fileHeight, inputCaption, outputCaption);
    }

    @Override
    public long getCallbackChatId(@NotNull Update update) { return update.getCallbackQuery().getMessage().getChatId(); }

    @Override
    public long getCallbackMessageId(@NotNull Update update) { return update.getCallbackQuery().getMessage().getMessageId(); }

    @Override
    public boolean isCallbackQuery(@NotNull Update update) { return update.hasCallbackQuery(); }

    @Override
    public String getCallbackQuery(@NotNull Update update) { return update.getCallbackQuery().getData(); }

    /**
     * Extracts and returns the <b>username</b> of the user who sent the message.
     * <br>
     * If the user has no Telegram username, the {@link IUpdateExtractor#DEFAULT_USERNAME} is returned.
     *
     * @param tgUser The Telegram <b>user</b> object.
     *
     * @return The user's Telegram <b>username</b>.
     */
    private String getUserName(@NotNull org.telegram.telegrambots.meta.api.objects.User tgUser) {
        return tgUser.getUserName() == null ? DEFAULT_USERNAME : tgUser.getUserName();
    }
}
