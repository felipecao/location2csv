package de.goeuro.useCase;

/**
 * Collects input from user via command line and presents proper notifySuccess/error messages.
 *
 * This is an implementation of the "UI" layer defined by Robert C. Martin
 * in https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
 */
public interface UserInput {

    /**
     * Verifies if the user has provided a String as input.
     * @return {@link Boolean#FALSE} if user input was null or empty.
     */
    Boolean isPresent();

    /**
     * Fetches the city for which the user wants suggestions.
     * @return The city the user is interested in.
     */
    String get();
}
