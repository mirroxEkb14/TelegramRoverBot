package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.managers.messages.BotMessageManager;
import cz.vance.movieapp.managers.messages.IBotMessageManager;
import org.jetbrains.annotations.NotNull;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
//</editor-fold>

/**
 * Implements a set of basic operations for creating custom inline keyboards.
 */
public final class InlineKeyboardBuilder implements IInlineKeyboardBuilder {

    private final IBotMessageManager botMessageManager = BotMessageManager.getInstance();

    /**
     * Functional interface builds an inline button.
     * <p>
     * Sets the text.
     * <br>
     * Sets the callback data.
     */
    private static final BiFunction<String, String, InlineKeyboardButton> inlineButtonBuilder = (text, callback) -> {
        final InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callback);
        return button;
    };

    @Override
    public @NotNull InlineKeyboardMarkup buildMoodKeyboard() {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> secondInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getDepressionInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getDepressionInlineButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getCheerfulInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getCheerfulInlineButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getFightingInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getFightingInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getFamilyInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getFamilyInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getFriendsInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getFriendsInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getLoveInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getLoveInlineButton())));

        inlineRows.add(firstInlineRow);
        inlineRows.add(secondInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }

    @Override
    public @NotNull InlineKeyboardMarkup buildCatalogueKeyboard() {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getMovieButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getMovieButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getSeriesButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getSeriesButton())));

        inlineRows.add(firstInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }

    @Override
    public @NotNull InlineKeyboardMarkup buildGenreKeyboard() {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> secondInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> thirdInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> fourthInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> fifthInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> sixthInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getComedyButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getComedyButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getDramaButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getDramaButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getMelodramaButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getMelodramaButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getThrillerButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getThrillerButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getActionButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getActionButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getFictionButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getFictionButton())));
        thirdInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getDetectiveButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getDetectiveButton())));
        thirdInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getFamilyInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getFamilyInlineButton())));
        thirdInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getSportButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getSportButton())));
        fourthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getFantasyButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getFantasyButton())));
        fourthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getAnimationButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getAnimationButton())));
        fourthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getAdventureButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getAdventureButton())));
        fifthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getBiographyButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getBiographyButton())));
        fifthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getCriminalButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getCriminalButton())));
        fifthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getDocumentaryButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getDocumentaryButton())));
        sixthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getWarButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getWarButton())));
        sixthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getMusicButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getMusicButton())));

        inlineRows.add(firstInlineRow);
        inlineRows.add(secondInlineRow);
        inlineRows.add(thirdInlineRow);
        inlineRows.add(fourthInlineRow);
        inlineRows.add(fifthInlineRow);
        inlineRows.add(sixthInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }
}
