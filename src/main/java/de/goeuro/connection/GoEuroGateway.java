package de.goeuro.connection;

import de.goeuro.domain.Suggestion;

import java.util.List;

/**
 * Created by felipe on 3/30/16.
 */
public interface GoEuroGateway {
    List<Suggestion> retrieveSuggestionsForCity(String city);
}
