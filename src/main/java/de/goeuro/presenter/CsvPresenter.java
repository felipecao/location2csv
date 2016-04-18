package de.goeuro.presenter;

import de.goeuro.entity.Suggestion;

import java.util.List;

/**
 * Presents a collection of suggestions from GoEuro API as a CSV file.
 *
 * This is an implementation of the "presenters" layer defined by Robert C. Martin
 * in https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
 */
public interface CsvPresenter {

    /**
     * Creates a CSV file containing _id, name, type, longitude and latitude information
     * extracted from {@suggestions}.
     *
     * @param suggestions The suggestions returned from GoEuro API.
     */
    void exportSuggestions(List<Suggestion> suggestions);

}
