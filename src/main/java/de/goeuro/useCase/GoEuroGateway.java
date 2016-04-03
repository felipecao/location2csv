package de.goeuro.useCase;

import de.goeuro.entity.Suggestion;

import java.util.List;

/**
 * Establishes the separation between the solution context and GoEuro API context. It's responsible for communicating
 * to GoEuro API and converting the search results from GoEuro's data structure into {@link Suggestion} objects, hence
 * abstracting GoEuro's internal representation and focusing solely on what's important for the solution.
 *
 * This is an implementation for the "gateway" object proposed by Martin Fowler in
 * http://martinfowler.com/articles/refactoring-external-service.html
 */
public interface GoEuroGateway {

    /**
     * Invokes GoEuro API to fetch suggestions for the provided city and converts data from GoEuro data structure into a
     * collection of {@link Suggestion} from GoEuro API based on the city provided as input.
     *
     * @param city The city for which we want to search suggestions.
     * @return The collection of {@link Suggestion}'s fetched from GoEuro API.
     */
    List<Suggestion> retrieveSuggestionsForCity(String city);
}
