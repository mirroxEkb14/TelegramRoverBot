package cz.vance.movieapp.models;

/**
 * Represents a sticker that the bot can send to the user.
 */
public record Sticker(String category,
                      String setName,
                      String fileId) {}
