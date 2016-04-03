package de.goeuro.presenter;

/**
 * Retrieves a collection of suggestions from GoEuro API to the {@param city} provided as input
 * and presents it as a CSV file.
 *
 * This is an implementation of the "presenters" layer defined by Robert C. Martin
 * in https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
 */
public interface CsvPresenter {

    /**
     * Creates a CSV file containing _id, name, type, longitude and latitude information regarding
     * GoEuro suggestions for a {@param city}.
     *
     * @param city The city we want to retrieve results.
     * @return The name of the CSV file created as output.
     */
    void createCSVFileWithSuggestionsFromAPI(String city);

}
