package de.goeuro.connection;

import de.goeuro.domain.Suggestion;
import de.goeuro.useCase.GoEuroGateway;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipe on 3/30/16.
 */
class GoEuroGatewayImpl implements GoEuroGateway {

    @Override
    public List<Suggestion> retrieveSuggestionsForCity(String city) {
        return new ArrayList<>();
    }
}
