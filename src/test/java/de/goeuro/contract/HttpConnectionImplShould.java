package de.goeuro.contract;

import de.goeuro.connection.HttpConnectionImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class HttpConnectionImplShould {

    HttpConnectionImpl httpService;

    @Before
    public void setup() {
        httpService = new HttpConnectionImpl();
    }

    @Test
    public void return_at_least_8_results_on_a_get_call_to_go_euro_api_with_berlin_as_param() {
        String url = "http://api.goeuro.com/api/v2/position/suggest/en/Berlin";
        List<Map> suggestions = httpService.executeGet(url);
        List<String> names = suggestions.stream().map( s -> s.get("name").toString()).collect(Collectors.toList());

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertTrue(8 >= suggestions.size());
        assertTrue(names.contains("Berlin"));
        assertTrue(names.contains("Berlingo"));
        assertTrue(names.contains("Berlingerode"));
        assertTrue(names.contains("Bernau bei Berlin"));
        assertTrue(names.contains("Berlin Tegel"));
        assertTrue(names.contains("Berlin Schönefeld"));
        assertTrue(names.contains("Berlin Hbf"));
        assertTrue(names.contains("Berlin Ostbahnhof"));
    }

    @Test
    public void return_no_results_for_a_get_call_to_a_null_url() {
        List<Map> suggestions = httpService.executeGet(null);

        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty());
    }

    @Test
    public void return_no_results_for_a_get_call_to_a_blank_url() {
        List<Map> suggestions = httpService.executeGet("    ");

        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty());
    }

    @Test
    public void return_no_results_for_a_get_call_to_an_invalid_url() {
        List<Map> suggestions = httpService.executeGet("iucneiwuncxoiemdoixmqomqdoimqoi");

        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty());
    }

    @Test
    public void return_no_results_for_a_get_call_to_a_url_that_produces_a_400_status_code() {
        List<Map> suggestions = httpService.executeGet("http://api.goeuro.com/api/v2/position/suggest/Berlin");

        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty());
    }

    @Test
    public void return_no_results_for_a_get_call_to_a_url_that_produces_no_suggestions() {
        List<Map> suggestions = httpService.executeGet("http://api.goeuro.com/api/v2/position/suggest/Jabuticaba");

        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty());
    }
}