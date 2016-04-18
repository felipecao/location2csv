package de.goeuro.useCase;

/**
 * Collects input from user via command line and presents proper success/error messages.
 *
 * This is an implementation of the "UI" layer defined by Robert C. Martin
 * in https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
 */
public interface InputHandler {

    /**
     * Verifies if the user has provided a String as input.
     * @return {@link Boolean#FALSE} if user input was null or empty.
     */
    Boolean isInputValid();

    /**
     * Presents a message to the user saying no input was provided.
     */
    void presentInputNotProvidedMessage();

    /**
     * Presents a message to the user saying the CSV file was successfully generated and pointing to its location.
     */
    void presentSuccessMessage();

    /**
     * Presents a message to the user saying her search produced no results.
     */
    void presentNoResultsMessage();

    /**
     * Fetches the city for which the user wants suggestions.
     * @return The city the user is interested in.
     */
    String extractCity();
}
