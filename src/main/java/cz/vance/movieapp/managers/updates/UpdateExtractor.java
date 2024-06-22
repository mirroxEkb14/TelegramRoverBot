package cz.vance.movieapp.managers.updates;

//<editor-fold defaultstate="collapsed" desc="Imports">
import cz.vance.movieapp.models.UserPhoto;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

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
}
