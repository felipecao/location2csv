package de.goeuro;

/**
 * Implements the case where the solution is used to retrieve suggestions from GoEuro API and
 * present those results to the user in CSV format.
 *
 * This is an implementation of the "use cases" layer defined by Robert C. Martin
 * in https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
 */
public interface ExportSuggestionsToCsv {

    /**
     * Executes the use case supported by this interface.
     */
    void execute();
}
