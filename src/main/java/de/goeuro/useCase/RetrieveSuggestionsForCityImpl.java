package de.goeuro.useCase;

import de.goeuro.entity.Suggestion;
import de.goeuro.presenter.RetrieveSuggestionsForCity;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class RetrieveSuggestionsForCityImpl implements RetrieveSuggestionsForCity {

    GoEuroGateway gateway;

    public RetrieveSuggestionsForCityImpl(GoEuroGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<Suggestion> fetch(String city) {
        if(isBlank(city)) {
            return new ArrayList<>();
        }
        return gateway.retrieveSuggestionsForCity(city);
    }
}
