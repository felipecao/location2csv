package de.goeuro.connection;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class HttpConnectionImpl implements HttpConnection {

    private static final String EMPTY_JSON_ARRAY = "[]";

    @Override
    public List<Map> executeGet(String url) {
        if(isBlank(url)) {
            return new ArrayList<>();
        }
        HttpResponse response = executeRequest(new HttpGet(url));
        return responseAsJSON(response);
    }

    private HttpResponse executeRequest(HttpUriRequest request) {
        HttpClient client = HttpClientBuilder.create().build();

        try {
            return client.execute(request);
        }
        catch (IOException e) {
            return null;
        }
    }

    private List<Map> responseAsJSON(HttpResponse response) {
        if(null == response || response.getStatusLine().getStatusCode() >= 400) {
            return new ArrayList<>();
        }

        String jsonAsString = extractJSONFromResponse(response);
        return new Gson().fromJson(jsonAsString, List.class);
    }

    private String extractJSONFromResponse(HttpResponse response) {
        try {
            return EntityUtils.toString(response.getEntity());
        }
        catch (IOException e) {
            return EMPTY_JSON_ARRAY;
        }
    }
}
