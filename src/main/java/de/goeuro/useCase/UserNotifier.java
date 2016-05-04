package de.goeuro.useCase;

public interface UserNotifier {

    /**
     * Presents a message to the user saying no input was provided.
     */
    void notifyInputNotProvided();

    /**
     * Presents a message to the user saying the CSV file was successfully generated and pointing to its location.
     */
    void notifySuccess();

    /**
     * Presents a message to the user saying her search produced no results.
     */
    void notifyNoResults();
}
