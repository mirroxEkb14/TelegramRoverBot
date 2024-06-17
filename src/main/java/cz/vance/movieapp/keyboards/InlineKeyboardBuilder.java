package cz.vance.movieapp.keyboards;

//<editor-fold default-state="collapsed" desc="Imports">
import cz.vance.movieapp.utils.Catalogue;
import cz.vance.movieapp.utils.Genre;
import cz.vance.movieapp.utils.UserMood;
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
                UserMood.DEPRESSION_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(UserMood.DEPRESSION_BUTTON.getContent())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                UserMood.CHEERFUL_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(UserMood.CHEERFUL_BUTTON.getContent())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                UserMood.FIGHTING_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(UserMood.FIGHTING_BUTTON.getContent())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                UserMood.FAMILY_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(UserMood.FAMILY_BUTTON.getContent())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                UserMood.FRIENDS_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(UserMood.FRIENDS_BUTTON.getContent())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                UserMood.LOVE_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(UserMood.LOVE_BUTTON.getContent())));

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
                Catalogue.MOVIE_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Catalogue.MOVIE_BUTTON.getContent())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                Catalogue.SERIES_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Catalogue.SERIES_BUTTON.getContent())));

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
                Genre.COMEDY_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.COMEDY_BUTTON.getContent())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                Genre.DRAMA_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.DRAMA_BUTTON.getContent())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                Genre.MELODRAMA_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.MELODRAMA_BUTTON.getContent())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                Genre.THRILLER_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.THRILLER_BUTTON.getContent())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                Genre.ACTION_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.ACTION_BUTTON.getContent())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                Genre.FICTION_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.FICTION_BUTTON.getContent())));
        thirdInlineRow.add(inlineButtonBuilder.apply(
                Genre.DETECTIVE_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.DETECTIVE_BUTTON.getContent())));
        thirdInlineRow.add(inlineButtonBuilder.apply(
                Genre.FAMILY_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.FAMILY_BUTTON.getContent())));
        thirdInlineRow.add(inlineButtonBuilder.apply(
                Genre.SPORT_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.SPORT_BUTTON.getContent())));
        fourthInlineRow.add(inlineButtonBuilder.apply(
                Genre.FANTASY_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.FANTASY_BUTTON.getContent())));
        fourthInlineRow.add(inlineButtonBuilder.apply(
                Genre.ANIMATION_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.ANIMATION_BUTTON.getContent())));
        fourthInlineRow.add(inlineButtonBuilder.apply(
                Genre.ADVENTURE_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.ADVENTURE_BUTTON.getContent())));
        fifthInlineRow.add(inlineButtonBuilder.apply(
                Genre.BIOGRAPHY_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.BIOGRAPHY_BUTTON.getContent())));
        fifthInlineRow.add(inlineButtonBuilder.apply(
                Genre.CRIMINAL_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.CRIMINAL_BUTTON.getContent())));
        fifthInlineRow.add(inlineButtonBuilder.apply(
                Genre.DOCUMENTARY_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.DOCUMENTARY_BUTTON.getContent())));
        sixthInlineRow.add(inlineButtonBuilder.apply(
                Genre.WAR_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.WAR_BUTTON.getContent())));
        sixthInlineRow.add(inlineButtonBuilder.apply(
                Genre.MUSIC_BUTTON.getContent(),
                IInlineKeyboardBuilder.getCreatedCallback(Genre.MUSIC_BUTTON.getContent())));

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
