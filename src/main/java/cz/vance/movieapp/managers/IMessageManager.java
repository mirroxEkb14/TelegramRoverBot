package cz.vance.movieapp.managers;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * This <b>IMessageManager</b> interface declares a set of basic operations for handling messages based on incoming
 * updates from the Telegram Bot API
 */
public interface IMessageManager {

    /**
     * Sends an echo message based on the provided bot update
     * <p>
     * An <b>update</b> bears information about various events that occur in a chat, such as <b>new messages</b>,
     * <b>edited messages</b>, <b>channel posts</b>, and more
     *
     * @param botUpdate The received update
     *
     * @see Update
     */
    void sendEcho(Update botUpdate);
}
