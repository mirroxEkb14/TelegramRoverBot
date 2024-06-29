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
    public @NotNull InlineKeyboardMarkup buildSmartSearchMoodKeyboard() {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> secondInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getDepressionMoodInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getDepressionMoodInlineButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getCheerfulMoodInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getCheerfulMoodInlineButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getFightingMoodInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getFightingMoodInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getFamilyMoodInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getFamilyMoodInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getFriendsMoodInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getFriendsMoodInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getLoveMoodInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getLoveMoodInlineButton())));

        inlineRows.add(firstInlineRow);
        inlineRows.add(secondInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }

    @Override
    public @NotNull InlineKeyboardMarkup buildSmartSearchCatalogueKeyboard() {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> secondInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getMovieInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getMovieInlineButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getSeriesInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getSeriesInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getSmartSearchBackInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getSmartSearchBackInlineButton())));

        inlineRows.add(firstInlineRow);
        inlineRows.add(secondInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }

    @Override
    public @NotNull InlineKeyboardMarkup buildSmartSearchGenreKeyboard() {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> secondInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> thirdInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> fourthInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> fifthInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> sixthInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> seventhInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getComedyInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getComedyInlineButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getDramaInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getDramaInlineButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getMelodramaInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getMelodramaInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getThrillerInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getThrillerInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getActionInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getActionInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getFictionInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getFictionInlineButton())));
        thirdInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getDetectiveInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getDetectiveInlineButton())));
        thirdInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getFamilyInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getFamilyInlineButton())));
        thirdInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getSportInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getSportInlineButton())));
        fourthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getFantasyInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getFantasyInlineButton())));
        fourthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getAnimationInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getAnimationInlineButton())));
        fourthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getAdventureInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getAdventureInlineButton())));
        fifthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getBiographyInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getBiographyInlineButton())));
        fifthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getCriminalInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getCriminalInlineButton())));
        fifthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getDocumentaryInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getDocumentaryInlineButton())));
        sixthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getWarInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getWarInlineButton())));
        sixthInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getMusicInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getMusicInlineButton())));
        seventhInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getSmartSearchBackInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getSmartSearchBackInlineButton())));

        inlineRows.add(firstInlineRow);
        inlineRows.add(secondInlineRow);
        inlineRows.add(thirdInlineRow);
        inlineRows.add(fourthInlineRow);
        inlineRows.add(fifthInlineRow);
        inlineRows.add(sixthInlineRow);
        inlineRows.add(seventhInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }

    @Override
    public @NotNull InlineKeyboardMarkup buildSmartSearchConfirmationKeyboard() {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> secondInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getSmartSearchYesInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getSmartSearchYesInlineButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getSmartSearchNoInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getSmartSearchNoInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getSmartSearchBackInlineButton(),
                IInlineKeyboardBuilder.getCreatedCallback(botMessageManager.getSmartSearchBackInlineButton())));

        inlineRows.add(firstInlineRow);
        inlineRows.add(secondInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }

    @Override
    public @NotNull InlineKeyboardMarkup buildNoIdeaFirstMovieKeyboard() {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> secondInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getNoIdeaNextMovieInlineButton(),
                IInlineKeyboardBuilder.getNoIdeaCreatedCallback(botMessageManager.getNoIdeaNextMovieInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getNoIdeaToMainMenuInlineButton(),
                IInlineKeyboardBuilder.getNoIdeaCreatedCallback(botMessageManager.getNoIdeaToMainMenuInlineButton())));

        inlineRows.add(firstInlineRow);
        inlineRows.add(secondInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }

    @Override
    public @NotNull InlineKeyboardMarkup buildNoIdeaInterimMovieKeyboard() {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> secondInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getNoIdeaPreviousMovieInlineButton(),
                IInlineKeyboardBuilder.getNoIdeaCreatedCallback(botMessageManager.getNoIdeaPreviousMovieInlineButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getNoIdeaNextMovieInlineButton(),
                IInlineKeyboardBuilder.getNoIdeaCreatedCallback(botMessageManager.getNoIdeaNextMovieInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getNoIdeaToMainMenuInlineButton(),
                IInlineKeyboardBuilder.getNoIdeaCreatedCallback(botMessageManager.getNoIdeaToMainMenuInlineButton())));

        inlineRows.add(firstInlineRow);
        inlineRows.add(secondInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }

    @Override
    public @NotNull InlineKeyboardMarkup buildNoIdeaLastMovieKeyboard() {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> secondInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getNoIdeaPreviousMovieInlineButton(),
                IInlineKeyboardBuilder.getNoIdeaCreatedCallback(botMessageManager.getNoIdeaPreviousMovieInlineButton())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getNoIdeaToMainMenuInlineButton(),
                IInlineKeyboardBuilder.getNoIdeaCreatedCallback(botMessageManager.getNoIdeaToMainMenuInlineButton())));

        inlineRows.add(firstInlineRow);
        inlineRows.add(secondInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }

    @Override
    public @NotNull InlineKeyboardMarkup buildWeRecommendInterimMovieKeyboard(String watchUrl) {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();
        final List<InlineKeyboardButton> secondInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getWeRecommendPreviousMovieInlineButton(),
                IInlineKeyboardBuilder.getWeRecommendCreatedCallback(botMessageManager.getWeRecommendPreviousMovieInlineButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getWeRecommendNextMovieInlineButton(),
                IInlineKeyboardBuilder.getWeRecommendCreatedCallback(botMessageManager.getWeRecommendNextMovieInlineButton())));
        secondInlineRow.add(InlineKeyboardButton
                .builder()
                .text(botMessageManager.getWeRecommendWatchInlineButton())
                .callbackData(IInlineKeyboardBuilder.getWeRecommendCreatedCallback(botMessageManager.getWeRecommendWatchInlineButton()))
                .url(watchUrl)
                .build());
        secondInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getNoIdeaToMainMenuInlineButton(),
                IInlineKeyboardBuilder.getWeRecommendCreatedCallback(botMessageManager.getNoIdeaToMainMenuInlineButton())));

        inlineRows.add(firstInlineRow);
        inlineRows.add(secondInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }

    @Override
    public @NotNull InlineKeyboardMarkup buildSendFeedbackConfirmationKeyboard() {
        final InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();

        final List<List<InlineKeyboardButton>> inlineRows = new ArrayList<>();
        final List<InlineKeyboardButton> firstInlineRow = new ArrayList<>();

        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getSendFeedbackYesInlineButton(),
                IInlineKeyboardBuilder.getSendFeedbackCreatedCallback(botMessageManager.getSendFeedbackYesInlineButton())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                botMessageManager.getSendFeedbackNoInlineButton(),
                IInlineKeyboardBuilder.getSendFeedbackCreatedCallback(botMessageManager.getSendFeedbackNoInlineButton())));

        inlineRows.add(firstInlineRow);

        keyboardMarkup.setKeyboard(inlineRows);
        return keyboardMarkup;
    }
}
