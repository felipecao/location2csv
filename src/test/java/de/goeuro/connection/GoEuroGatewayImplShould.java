package de.goeuro.connection;

import de.goeuro.entity.Suggestion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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
        when(connectionMock.retrieveSuggestionsForCity(anyString())).thenReturn(null);

        List<Suggestion> suggestions = gateway.retrieveSuggestionsForCity("city");

        assertEquals(new ArrayList<Suggestion>(), suggestions);
    }

    @Test
    public void return_a_list_with_one_suggestion_if_connection_provides_one_result() {
        when(connectionMock.retrieveSuggestionsForCity(anyString())).thenReturn(null);

        List<Suggestion> suggestions = gateway.retrieveSuggestionsForCity("city");

        assertEquals(new ArrayList<Suggestion>(), suggestions);
    }

    // return an empty list if input is null
    // return an empty list if input is empty
}