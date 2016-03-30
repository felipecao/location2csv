package de.goeuro.useCase;

import de.goeuro.connection.GoEuroGateway;
import de.goeuro.domain.Suggestion;

import java.util.List;

/**
 * Created by felipe on 3/30/16.
 */
class RetrieveSuggestionsForInputImpl implements RetrieveSuggestionsForInput {

    GoEuroGateway gateway;

    public RetrieveSuggestionsForInputImpl(GoEuroGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<Suggestion> invoke(String input) {
        return gateway.retrieveSuggestionsForCity(input);
    }
}
