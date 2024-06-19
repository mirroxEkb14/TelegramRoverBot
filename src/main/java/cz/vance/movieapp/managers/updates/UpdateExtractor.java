package cz.vance.movieapp.managers.updates;

//<editor-fold defaultstate="collapsed" desc="Imports">
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;
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
    public String getMessageText(@NotNull Update update) { return update.getMessage().getText(); }

    @Override
    public String getStickerFileId(@NotNull Update update) { return update.getMessage().getSticker().getFileId(); }

    @Override
    public long getCallbackChatId(@NotNull Update update) { return update.getCallbackQuery().getMessage().getChatId(); }

    @Override
    public long getCallbackMessageId(@NotNull Update update) { return update.getCallbackQuery().getMessage().getMessageId(); }

    @Override
    public boolean isCallbackQuery(@NotNull Update update) { return update.hasCallbackQuery(); }

    @Override
    public String getCallbackQuery(@NotNull Update update) { return update.getCallbackQuery().getData(); }
}
