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

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RetrieveSuggestionsForInputShould {

    @Mock
    GoEuroGateway gatewayMock;

    @InjectMocks
    RetrieveSuggestionsForInputImpl useCase;

    @Test
    public void return_no_suggestions_if_goeuro_gateway_returns_nothing() {
        when(gatewayMock.retrieveSuggestionsForCity(anyString())).thenReturn(new ArrayList<Suggestion>());

        List<Suggestion> suggestions = useCase.invoke("city");

        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty());
    }

    @Test
    public void return_one_suggestion_if_goeuro_gateway_returns_one_item() {
        Integer totalExpectedSuggestions = 1;
        List<Suggestion> expectedSuggestions = mockSuggestions(totalExpectedSuggestions, "name");
        when(gatewayMock.retrieveSuggestionsForCity(anyString())).thenReturn(expectedSuggestions);

        List<Suggestion> returnedSuggestions = useCase.invoke("city");

        assertNotNull(returnedSuggestions);
        assertFalse(returnedSuggestions.isEmpty());
        assertEquals(totalExpectedSuggestions.intValue(), returnedSuggestions.size());
        assertEquals(expectedSuggestions, returnedSuggestions);
    }

    @Test
    public void return_two_suggestions_if_goeuro_gateway_returns_two_items() {
        Integer totalExpectedSuggestions = 2;
        List<Suggestion> expectedSuggestions = mockSuggestions(totalExpectedSuggestions, "name");
        when(gatewayMock.retrieveSuggestionsForCity(anyString())).thenReturn(expectedSuggestions);

        List<Suggestion> returnedSuggestions = useCase.invoke("city");

        assertNotNull(returnedSuggestions);
        assertFalse(returnedSuggestions.isEmpty());
        assertEquals(totalExpectedSuggestions.intValue(), returnedSuggestions.size());
        assertEquals(expectedSuggestions, returnedSuggestions);
    }

    @Test
    public void return_the_same_suggestions_provided_by_goeuro_gateway() {
        Integer totalExpectedSuggestions = new Random().nextInt(10);
        List<Suggestion> expectedSuggestions = mockSuggestions(totalExpectedSuggestions, "name");
        when(gatewayMock.retrieveSuggestionsForCity(anyString())).thenReturn(expectedSuggestions);

        List<Suggestion> returnedSuggestions = useCase.invoke("city");

        assertNotNull(returnedSuggestions);
        assertFalse(returnedSuggestions.isEmpty());
        assertEquals(totalExpectedSuggestions.intValue(), returnedSuggestions.size());
        assertEquals(expectedSuggestions, returnedSuggestions);
    }

    @Test
    public void return_an_empty_list_of_suggestions_if_input_is_null() {
        Integer totalExpectedSuggestions = new Random().nextInt(10);
        List<Suggestion> expectedSuggestions = mockSuggestions(totalExpectedSuggestions, "name");
        when(gatewayMock.retrieveSuggestionsForCity(anyString())).thenReturn(expectedSuggestions);

        List<Suggestion> returnedSuggestions = useCase.invoke(null);

        assertNotNull(returnedSuggestions);
        assertTrue(returnedSuggestions.isEmpty());
    }

    @Test
    public void return_an_empty_list_of_suggestions_if_input_is_blank() {
        Integer totalExpectedSuggestions = new Random().nextInt(10);
        List<Suggestion> expectedSuggestions = mockSuggestions(totalExpectedSuggestions, "name");
        when(gatewayMock.retrieveSuggestionsForCity(anyString())).thenReturn(expectedSuggestions);

        List<Suggestion> returnedSuggestions = useCase.invoke("    ");

        assertNotNull(returnedSuggestions);
        assertTrue(returnedSuggestions.isEmpty());
    }

    private List<Suggestion> mockSuggestions(Integer times, String name) {
        List<Suggestion> suggestions = new ArrayList<>();

        for(int i=0; i < times; i++) {
            suggestions.add(new Suggestion(name));
        }

        return suggestions;
    }
}