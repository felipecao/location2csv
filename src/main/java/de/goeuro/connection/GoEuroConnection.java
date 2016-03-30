package de.goeuro.connection;

import java.util.List;

/**
 * Created by felipe on 3/30/16.
 */
public interface GoEuroConnection {
    List retrieveSuggestionsForCity(String city);
}
