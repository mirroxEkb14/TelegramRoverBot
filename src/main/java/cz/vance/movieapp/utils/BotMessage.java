package cz.vance.movieapp.utils;

/**
 * Provides all the texts for messages the bot can send to the user.
 */
public enum BotMessage {
    WELCOME_MESSAGE("Welcome %s!\nI am <b>%s</b>, created to be your own movie-guide"),
    HELP_MESSAGE("""
            <u>\ud83c\udfacSmart Search</u>
            This is the point of me existing: I try to make a perfect selection for you according not only to the movie genre, but also to your current spiritual state (mood)
            <u>\ud83c\udfafOur choice</u>
            I present to your attention best movies from my point of view
            <u>\ud83d\udc40No idea</u>
            A random sampling of movies if you are not in the mood to choose
            <u>\ud83c\udfadFeedback</u>
            Share your good/bad experience about a watched movie with me! I ensure you all your comments will be read by me. And also... the more feedbacks you leave, the more smarter I'm becoming\ud83d\udc68\u200d\ud83c\udf93"""),
    UNKNOWN_INPUT("I beg your pardon, but you've sent an unknown content to me.");

    private final String content;

    BotMessage(String content) { this.content = content; }

    public String getContent() { return content; }
}
