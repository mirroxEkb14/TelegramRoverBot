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
 * This <b>InlineKeyboardBuilder</b> class implements a set of basic operations for creating custom inline
 * keyboards
 *
 * @see IInlineKeyboardBuilder
 */
public final class InlineKeyboardBuilder implements IInlineKeyboardBuilder {

    /**
     * This <b>functional interface</b> builds an inline button:
     * <ol>
     * <li> sets the text;
     * <li> sets the callback data;
     * </ol>
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
                UserMood.DEPRESSION_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(UserMood.DEPRESSION_BUTTON.content())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                UserMood.CHEERFUL_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(UserMood.CHEERFUL_BUTTON.content())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                UserMood.FIGHTING_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(UserMood.FIGHTING_BUTTON.content())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                UserMood.FAMILY_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(UserMood.FAMILY_BUTTON.content())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                UserMood.FRIENDS_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(UserMood.FRIENDS_BUTTON.content())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                UserMood.LOVE_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(UserMood.LOVE_BUTTON.content())));

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
                Catalogue.MOVIE_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Catalogue.MOVIE_BUTTON.content())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                Catalogue.SERIES_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Catalogue.SERIES_BUTTON.content())));

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
                Genre.COMEDY_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.COMEDY_BUTTON.content())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                Genre.DRAMA_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.DRAMA_BUTTON.content())));
        firstInlineRow.add(inlineButtonBuilder.apply(
                Genre.MELODRAMA_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.MELODRAMA_BUTTON.content())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                Genre.THRILLER_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.THRILLER_BUTTON.content())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                Genre.ACTION_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.ACTION_BUTTON.content())));
        secondInlineRow.add(inlineButtonBuilder.apply(
                Genre.FICTION_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.FICTION_BUTTON.content())));
        thirdInlineRow.add(inlineButtonBuilder.apply(
                Genre.DETECTIVE_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.DETECTIVE_BUTTON.content())));
        thirdInlineRow.add(inlineButtonBuilder.apply(
                Genre.FAMILY_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.FAMILY_BUTTON.content())));
        thirdInlineRow.add(inlineButtonBuilder.apply(
                Genre.SPORT_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.SPORT_BUTTON.content())));
        fourthInlineRow.add(inlineButtonBuilder.apply(
                Genre.FANTASY_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.FANTASY_BUTTON.content())));
        fourthInlineRow.add(inlineButtonBuilder.apply(
                Genre.ANIMATION_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.ANIMATION_BUTTON.content())));
        fourthInlineRow.add(inlineButtonBuilder.apply(
                Genre.ADVENTURE_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.ADVENTURE_BUTTON.content())));
        fifthInlineRow.add(inlineButtonBuilder.apply(
                Genre.BIOGRAPHY_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.BIOGRAPHY_BUTTON.content())));
        fifthInlineRow.add(inlineButtonBuilder.apply(
                Genre.CRIMINAL_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.CRIMINAL_BUTTON.content())));
        fifthInlineRow.add(inlineButtonBuilder.apply(
                Genre.DOCUMENTARY_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.DOCUMENTARY_BUTTON.content())));
        sixthInlineRow.add(inlineButtonBuilder.apply(
                Genre.WAR_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.WAR_BUTTON.content())));
        sixthInlineRow.add(inlineButtonBuilder.apply(
                Genre.MUSIC_BUTTON.content(),
                IInlineKeyboardBuilder.createCallback(Genre.MUSIC_BUTTON.content())));

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
