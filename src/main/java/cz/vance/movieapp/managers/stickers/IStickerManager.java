package cz.vance.movieapp.managers.stickers;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.models.Sticker;
import cz.vance.movieapp.utils.StickerCategory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
//</editor-fold>

/**
 * Declares a set of methods for managing the stickers the bot can send, loaded from the .json file.
 */
public interface IStickerManager {

    String stickerFilePath = "/stickers.json";

    /**
     * Returns a list of sticker objects by the specified category.
     *
     * @param category The {@link StickerCategory} object.
     *
     * @return A <b>list</b> of sorted stickers.
     */
    @NotNull List<Sticker> getStickersByCategory(StickerCategory category);
}
