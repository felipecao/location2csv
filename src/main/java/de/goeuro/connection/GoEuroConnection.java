package de.goeuro.connection;

import java.util.List;
import java.util.Map;

/**
 * Isolates and establishes a HTTP interface between the solution and GoEuro API.
 */
public interface GoEuroConnection {

    /**
     * Executes a GET call to GoEuro API and returns a collection of suggestions for the {@param city}.
     * @param city The city for which we want to retrieve suggestions
     * @return A list of maps containing information about suggestions provided for the specified {@param city}
     * @see HttpConnection
     */
    List<Map> retrieveSuggestionsForCity(String city);
}
