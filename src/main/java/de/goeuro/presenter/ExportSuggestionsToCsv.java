package de.goeuro.presenter;

import de.goeuro.entity.Suggestion;

import java.util.List;

/**
 * Represents the case where the solution is used to retrieve suggestions from GoEuro API, regardless of
 * the media representation to be used to present those results to the user.
 *
 * This is an implementation of the "use cases" layer defined by Robert C. Martin
 * in https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
 */
public interface ExportSuggestionsToCsv {

    /**
     * Executes the retrieval of suggestions for the {@param city} provided as input.
     *
     * @param city The city for which we want to fetch suggestions.
     * @return A collection of suggestions provided by GoEuro API;
     */
    List<Suggestion> fetch(String city);
}
