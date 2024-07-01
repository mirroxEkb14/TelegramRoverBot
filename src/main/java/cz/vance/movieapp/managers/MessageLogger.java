package cz.vance.movieapp.managers;

//<editor-fold default-state="collapsed" desc="Imports">
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cz.vance.movieapp.managers.updates.IUpdateExtractor;
import cz.vance.movieapp.managers.updates.UpdateExtractor;
import cz.vance.movieapp.models.LogEntry;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
//</editor-fold>

/**
 * Logs incoming messages from the user to the spicified <b>.log file</b>.
 */
public final class MessageLogger {

    private static final IUpdateExtractor updateExtractor = new UpdateExtractor();

    private static final String LOG_FILE = "src/main/resources/message-logs.log";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Configures the ObjectMapper to pretty-print the JSON output.
    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static void log(@NotNull Update botUpdate) {
        log(updateExtractor.getUserFirstName(botUpdate),
                updateExtractor.getUserLastName(botUpdate),
                String.valueOf(updateExtractor.getUserId(botUpdate)),
                updateExtractor.getMessageText(botUpdate));
    }

    private static void log(String firstName,
                           String lastName,
                           String userId,
                           String messageText) {
        final LogEntry logEntry = new LogEntry(firstName, lastName, userId, messageText);
        try {
            final String logMessage = objectMapper.writeValueAsString(logEntry);

            final Path logFilePath = Paths.get(LOG_FILE);
            Files.createDirectories(logFilePath.getParent());

            if (!Files.exists(logFilePath))
                Files.createFile(logFilePath);

            Files.write(
                    Paths.get(LOG_FILE),
                    (logMessage + System.lineSeparator()).getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
