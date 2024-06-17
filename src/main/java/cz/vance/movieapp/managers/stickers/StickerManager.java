package cz.vance.movieapp.managers.stickers;

//<editor-fold default-state="collapsed" desc="Imports">
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.vance.movieapp.models.Sticker;
import cz.vance.movieapp.utils.StickerCategory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//</editor-fold>

/**
 * Implements the logic for the sticker management.
 */
public final class StickerManager implements IStickerManager {

    private final List<Sticker> stickers;

    //<editor-fold default-state="collapsed" desc="Singleton">
    private static StickerManager instance;

    private StickerManager() {
        stickers = new ArrayList<>();

        loadStickers();
    }

    public static StickerManager getInstance() {
        if (instance == null)
            return new StickerManager();
        return instance;
    }
    //</editor-fold>

    private void loadStickers() {
        stickers.clear();
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            stickers.addAll(objectMapper.readValue(
                    StickerManager.class.getResourceAsStream(IStickerManager.stickerFilePath),
                    new TypeReference<>() {}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Alternative using the lambda expressions:
     * <pre>{@code
     *      stickers.stream()
     *          .filter(sticker -> sticker.category().equals(category.getContent()))
     *          .toList();
     *      }
     * </pre>
     */
    @Override
    public @NotNull List<Sticker> getStickersByCategory(StickerCategory category) {
        final List<Sticker> sortedStickers = new ArrayList<>();
        for (Sticker sticker : stickers) {
            if (sticker.category().equals(category.getContent()))
                sortedStickers.add(sticker);
        }
        return sortedStickers;
    }
}
