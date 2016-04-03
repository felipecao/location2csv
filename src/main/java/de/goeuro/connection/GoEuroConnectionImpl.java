package de.goeuro.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by felipe on 4/2/16.
 */
public class GoEuroConnectionImpl implements GoEuroConnection {

    private static final String BASE_URL = "http://api.goeuro.com/api/v2/position/suggest/en/%s";

    private HttpConnection httpConnection;

    public GoEuroConnectionImpl(HttpConnection httpConnection) {
        this.httpConnection = httpConnection;
    }

    @Override
    public List<Map> retrieveSuggestionsForCity(String city) {
        if(isBlank(city)) {
            return new ArrayList<>();
        }

        String url = String.format(BASE_URL, city);
        return httpConnection.executeGet(url);
    }
}
