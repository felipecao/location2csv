package de.goeuro.useCase;

import de.goeuro.entity.Suggestion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static de.goeuro.TestConstants.CITY;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveSuggestionsForCityImplShould {

    private static final String NAME = "name";

    @Mock
    GoEuroGateway gatewayMock;

    @InjectMocks
    RetrieveSuggestionsForCityImpl useCase;

    @Test
    public void return_an_empty_list_of_suggestions_if_input_is_null() {
        Integer totalExpectedSuggestions = new Random().nextInt(10);
        List<Suggestion> expectedSuggestions = mockSuggestionsWithName(totalExpectedSuggestions, NAME);
        when(gatewayMock.retrieveSuggestionsForCity(anyString())).thenReturn(expectedSuggestions);

        List<Suggestion> returnedSuggestions = useCase.fetch(null);

        assertNotNull(returnedSuggestions);
        assertTrue(returnedSuggestions.isEmpty());
    }

    @Test
    public void return_an_empty_list_of_suggestions_if_input_is_blank() {
        Integer totalExpectedSuggestions = new Random().nextInt(10);
        List<Suggestion> expectedSuggestions = mockSuggestionsWithName(totalExpectedSuggestions, NAME);
        when(gatewayMock.retrieveSuggestionsForCity(anyString())).thenReturn(expectedSuggestions);

        List<Suggestion> returnedSuggestions = useCase.fetch("    ");

        assertNotNull(returnedSuggestions);
        assertTrue(returnedSuggestions.isEmpty());
    }

    @Test
    public void return_no_suggestions_if_goeuro_gateway_returns_nothing() {
        when(gatewayMock.retrieveSuggestionsForCity(anyString())).thenReturn(new ArrayList<>());

        List<Suggestion> suggestions = useCase.fetch(CITY);

        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty());
    }

    @Test
    public void return_one_suggestion_if_goeuro_gateway_returns_one_item() {
        Integer totalExpectedSuggestions = 1;
        List<Suggestion> expectedSuggestions = mockSuggestionsWithName(totalExpectedSuggestions, NAME);
        when(gatewayMock.retrieveSuggestionsForCity(anyString())).thenReturn(expectedSuggestions);

        List<Suggestion> returnedSuggestions = useCase.fetch(CITY);

        assertNotNull(returnedSuggestions);
        assertFalse(returnedSuggestions.isEmpty());
        assertEquals(totalExpectedSuggestions.intValue(), returnedSuggestions.size());
        assertEquals(expectedSuggestions, returnedSuggestions);
    }

    @Test
    public void return_two_suggestions_if_goeuro_gateway_returns_two_items() {
        Integer totalExpectedSuggestions = 2;
        List<Suggestion> expectedSuggestions = mockSuggestionsWithName(totalExpectedSuggestions, NAME);
        when(gatewayMock.retrieveSuggestionsForCity(anyString())).thenReturn(expectedSuggestions);

        List<Suggestion> returnedSuggestions = useCase.fetch(CITY);

        assertNotNull(returnedSuggestions);
        assertFalse(returnedSuggestions.isEmpty());
        assertEquals(totalExpectedSuggestions.intValue(), returnedSuggestions.size());
        assertEquals(expectedSuggestions, returnedSuggestions);
    }

    @Test
    public void return_the_same_suggestions_provided_by_goeuro_gateway() {
        Integer totalExpectedSuggestions = new Random().nextInt(10) + 1; // make sure we don't have 0 suggestions
                                                                         // -- this scenario is covered by other tests
        List<Suggestion> expectedSuggestions = mockSuggestionsWithName(totalExpectedSuggestions, NAME);
        when(gatewayMock.retrieveSuggestionsForCity(anyString())).thenReturn(expectedSuggestions);

        List<Suggestion> returnedSuggestions = useCase.fetch(CITY);

        assertNotNull(returnedSuggestions);
        assertFalse(returnedSuggestions.isEmpty());
        assertEquals(totalExpectedSuggestions.intValue(), returnedSuggestions.size());
        assertEquals(expectedSuggestions, returnedSuggestions);
    }

    private List<Suggestion> mockSuggestionsWithName(Integer times, String name) {
        List<Suggestion> suggestions = new ArrayList<>();
        Suggestion suggestionMock;

        for(int i=0; i < times; i++) {
            suggestionMock = mock(Suggestion.class);
            when(suggestionMock.getName()).thenReturn(name);
            suggestions.add(suggestionMock);
        }

        return suggestions;
    }
}