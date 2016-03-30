package de.goeuro.connection;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class GoEuroGatewayImplShould {

    @Mock
    GoEuroConnection connectionMock;

    @InjectMocks
    GoEuroGatewayImpl gateway;

    public void return_an_empty_list_of_suggestions_if_connection_provides_no_results() {
//        when(connectionMock.retrieveSuggestionsForCity(anyString())).thenReturn();
    }
}