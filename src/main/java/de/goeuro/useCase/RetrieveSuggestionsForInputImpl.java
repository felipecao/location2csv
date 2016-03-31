package de.goeuro.useCase;

import de.goeuro.RetrieveSuggestionsForInput;
import de.goeuro.domain.Suggestion;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

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
        if(isBlank(input)) {
            return new ArrayList<>();
        }
        return gateway.retrieveSuggestionsForCity(input);
    }
}
