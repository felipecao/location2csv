package de.goeuro.connection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoEuroConnectionImplShould {

    List<Map> resultsFromHttpService;

    @Mock
    HttpConnection httpConnectionMock;

    @InjectMocks
    GoEuroConnectionImpl goEuroConnection;

    @Before
    public void setup() {
        Integer totalEntriesToMock = new Random().nextInt(50);
        resultsFromHttpService = new ArrayList<>();

        for(int i = 0; i < totalEntriesToMock; i++) {
            resultsFromHttpService.add(setupEntryWithRandomData());
        }
    }

    @Test
    public void invoke_http_service_and_return_its_results() {
        String url = "http://api.goeuro.com/api/v2/position/suggest/en/city";
        when(httpConnectionMock.executeGet(url)).thenReturn(resultsFromHttpService);

        List<Map> searchResults = goEuroConnection.retrieveSuggestionsForCity("city");

        assertEquals(resultsFromHttpService, searchResults);
    }

    @Test
    public void return_an_empty_result_if_city_is_null() {
        when(httpConnectionMock.executeGet(anyString())).thenReturn(resultsFromHttpService);

        List<Map> searchResults = goEuroConnection.retrieveSuggestionsForCity(null);

        assertEquals(new ArrayList<Map>(), searchResults);
    }

    @Test
    public void return_an_empty_result_if_city_is_blank() {
        when(httpConnectionMock.executeGet(anyString())).thenReturn(resultsFromHttpService);

        List<Map> searchResults = goEuroConnection.retrieveSuggestionsForCity("     ");

        assertEquals(new ArrayList<Map>(), searchResults);
    }

    private Map<String, Object> setupEntryWithRandomData() {
        Map<String, Object> entry = new HashMap<>();

        entry.put("_id", new Random().nextInt());
        entry.put("key", null);
        entry.put("name", UUID.randomUUID().toString());
        entry.put("iata_airport_code", null);
        entry.put("type", UUID.randomUUID().toString());
        entry.put("country", UUID.randomUUID().toString());
        entry.put("geo_position", new HashMap<String, Double>() {{
            put("latitude", new Random().nextDouble());
            put("longitude", new Random().nextDouble());
        }});
        entry.put("locationId", new Random().nextInt());
        entry.put("inEurope", new Random().nextBoolean());
        entry.put("countryCode", UUID.randomUUID().toString());
        entry.put("coreCountry", new Random().nextBoolean());
        entry.put("distance", null);

        return entry;
    }

}