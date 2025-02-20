package cz.vance.movieapp.models;

import org.jetbrains.annotations.Nullable;

/**
 * Represents a photo that a user sends to the bot.
 */
public record UserPhoto(String fileId,
                        int fileWidth,
                        int fileHeight,
                        String inputCaption,
                        String outputCaption) {

    private static final String OUTPUT_CAPTION_SAMPLING =
            "<b>File id</b>: %s;\n<b>Width</b>: %d;\n<b>Height</b>: %d;\n<b>Caption</b>: %s;";

    public static String getOutputCaption(String fileId,
                                          int fileWidth,
                                          int fileHeight,
                                          @Nullable String inputCaption) {
        return String.format(OUTPUT_CAPTION_SAMPLING,
                fileId, fileWidth, fileHeight, inputCaption == null ? "N/A" : inputCaption);
    }
}
