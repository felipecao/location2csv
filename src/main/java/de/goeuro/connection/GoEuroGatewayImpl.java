package de.goeuro.connection;

import de.goeuro.entity.Suggestion;
import de.goeuro.useCase.GoEuroGateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class GoEuroGatewayImpl implements GoEuroGateway {

    private GoEuroConnection connection;

    public GoEuroGatewayImpl(GoEuroConnection connection) {
        this.connection = connection;
    }

    @Override
    public List<Suggestion> retrieveSuggestionsForCity(String city) {
        if(isBlank(city)) {
            return new ArrayList<>();
        }

        List<Map> apiResults = connection.retrieveSuggestionsForCity(city);
        Function<Map, Suggestion> mapToSuggestion = (map -> SuggestionImpl.fromMap(map));

        List<Suggestion> suggestions = apiResults.stream()
                .map(mapToSuggestion)
                .filter(s -> null != s)
                .collect(Collectors.toList());

        return suggestions;
    }
}
