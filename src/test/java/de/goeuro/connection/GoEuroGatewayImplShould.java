package de.goeuro.connection;

import de.goeuro.entity.Suggestion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.goeuro.TestConstants.CITY;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoEuroGatewayImplShould {

    @Mock
    GoEuroConnection connectionMock;

    @InjectMocks
    GoEuroGatewayImpl gateway;

    @Test
    public void return_an_empty_list_of_suggestions_if_connection_provides_no_results() {
        when(connectionMock.retrieveSuggestionsForCity(anyString())).thenReturn(new ArrayList<Map>());

        List<Suggestion> suggestions = gateway.retrieveSuggestionsForCity(CITY);

        assertNotNull(suggestions);
        assertEquals(new ArrayList<SuggestionImpl>(), suggestions);
    }

    @Test
    public void return_a_list_with_one_suggestion_if_connection_provides_one_result() {
        String city = "Berlin";
        when(connectionMock.retrieveSuggestionsForCity(city)).thenReturn(suggestionsFromAPI(1));

        List<Suggestion> suggestions = gateway.retrieveSuggestionsForCity(city);

        assertNotNull(suggestions);
        assertEquals(suggestions(1), suggestions);
    }

    @Test
    public void return_a_list_with_two_suggestions_if_connection_provides_two_results() {
        String city = "Berlin";
        when(connectionMock.retrieveSuggestionsForCity(city)).thenReturn(suggestionsFromAPI(2));

        List<Suggestion> suggestions = gateway.retrieveSuggestionsForCity(city);

        assertNotNull(suggestions);
        assertEquals(suggestions(2), suggestions);
    }

    @Test
    public void return_a_list_with_three_suggestions_if_connection_provides_three_valid_results_and_one_null_result() {
        String city = "Berlin";
        when(connectionMock.retrieveSuggestionsForCity(city)).thenReturn(threeAPISuggestionsWithOneNullElement());

        List<Suggestion> suggestions = gateway.retrieveSuggestionsForCity(city);

        assertNotNull(suggestions);
        assertEquals(suggestions(3), suggestions);
    }

    @Test
    public void return_an_empty_list_if_input_is_null() {
        String city = "Berlin";
        when(connectionMock.retrieveSuggestionsForCity(city)).thenReturn(suggestionsFromAPI(2));

        List<Suggestion> suggestions = gateway.retrieveSuggestionsForCity(null);

        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty());
    }

    @Test
    public void return_an_empty_list_if_input_is_empty() {
        String city = "Berlin";
        when(connectionMock.retrieveSuggestionsForCity(city)).thenReturn(suggestionsFromAPI(2));

        List<Suggestion> suggestions = gateway.retrieveSuggestionsForCity("   ");

        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty());
    }

    private List<Map> suggestionsFromAPI(Integer totalSuggestions) {
        List<Map> suggestions = new ArrayList<>();
        Map<String, Object> entry = null;

        for(int i = 0; i < totalSuggestions; i++){

            entry = new HashMap<>();

            entry.put("_id", 376217D);
            entry.put("key", null);
            entry.put("name", "Berlin " + i);
            entry.put("iata_airport_code", null);
            entry.put("type", "location");
            entry.put("country", "Germany");
            entry.put("geo_position", new HashMap<String, Double>() {{
                put("latitude", 52.52437);
                put("longitude", 13.41053);
            }});
            entry.put("locationId", 8384);
            entry.put("inEurope", true);
            entry.put("countryCode", "DE");
            entry.put("coreCountry", true);
            entry.put("distance", null);

            suggestions.add(entry);
        }

        return suggestions;
    }

    private List<Map> threeAPISuggestionsWithOneNullElement() {
        List<Map> suggestions = suggestionsFromAPI(3);

        suggestions.add(null);

        return suggestions;
    }

    private List<SuggestionImpl> suggestions(Integer totalSuggestions) {
        List<SuggestionImpl> suggestions = new ArrayList<>();

        for(int i = 0; i < totalSuggestions; i++) {
            suggestions.add(
                    SuggestionImpl.fromMap(
                            suggestionWith(376217D, "Berlin " + i, "location", 52.52437, 13.41053)
                    )
            );
        }

        return suggestions;
    }

    private Map<String, Object> suggestionWith(Double id, String name, String type, Double latitude, Double longitude) {
        Map<String, Object> suggestionInfo = new HashMap<>();

        suggestionInfo.put("_id", id);
        suggestionInfo.put("name", name);
        suggestionInfo.put("type", type);
        suggestionInfo.put("geo_position", new HashMap<String, Double>(){{
            put("latitude", latitude);
            put("longitude", longitude);
        }});

        return suggestionInfo;
    }
}