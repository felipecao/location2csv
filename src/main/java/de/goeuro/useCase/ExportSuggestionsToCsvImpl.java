package de.goeuro.useCase;

import de.goeuro.entity.Suggestion;
import de.goeuro.presenter.ExportSuggestionsToCsv;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class ExportSuggestionsToCsvImpl implements ExportSuggestionsToCsv {

    GoEuroGateway gateway;

    public ExportSuggestionsToCsvImpl(GoEuroGateway gateway) {
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
