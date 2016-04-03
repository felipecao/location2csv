package de.goeuro.connection;

import java.util.List;
import java.util.Map;

/**
 * Represents an HTTP connection, isolating the solution from the responsibility of establishing HTTP connection to
 * any HTTP resource or URI.
 */
public interface HttpConnection {

    /**
     * Executes an HTTP GET call to the {@param url} passed as parameter and returns the query results as a collection of maps.
     * Each map contains information about each suggestion returned by the API, just like a JSON object.
     * For instance, a call to http://api.goeuro.com/api/v2/position/suggest/en/Berlin would return the following JSON collection:
     * <code>
     *  [
     *      {
     *          "_id": 376217,
     *          "key": null,
     *          "name": "Berlin",
     *          "fullName": "Berlin, Germany",
     *          "iata_airport_code": null,
     *          "type": "location",
     *          "country": "Germany",
     *          "geo_position": {
     *              "latitude": 52.52437,
     *              "longitude": 13.41053
     *          },
     *          "locationId": 8384,
     *          "inEurope": true,
     *          "countryCode": "DE",
     *          "coreCountry": true,
     *          "distance": null
     *      },
     *      {
     *          "_id": 448103,
     *          "key": null,
     *          "name": "Berlingo",
     *          "fullName": "Berlingo, Italy",
     *          "iata_airport_code": null,
     *          "type": "location",
     *          "country": "Italy",
     *          "geo_position": {
     *              "latitude": 45.50298,
     *              "longitude": 10.04366
     *          },
     *          "locationId": 147721,
     *          "inEurope": true,
     *          "countryCode": "IT",
     *          "coreCountry": true,
     *          "distance": null
     *      }
     *  ]
     * </code>
     *
     * This, in turn, will be translated to a <code>List<Map></code>, which could be reproduced by the following code:
     * <code>
     *     List<Map> results = new ArrayList<>;
     *     Map<String, Object> firstResult = new HashMap<>;
     *     Map<String, Object> secondResult = new HashMap<>;
     *
     *     firstResult.put("_id", 376217);
     *     firstResult.put("key", null);
     *     firstResult.put("name", "Berlin");
     *     firstResult.put("fullName", "Berlin, Germany");
     *     firstResult.put("iata_airport_code", null);
     *     firstResult.put("type", "location");
     *     firstResult.put("country", "Germany");
     *     firstResult.put("geo_position", new HashMap<String, Object>{{
     *         put("latitude", 52.52437);
     *         put("longitude", 13.41053);
     *     }});
     *     firstResult.put("locationId", 8384);
     *     firstResult.put("inEurope", true);
     *     firstResult.put("countryCode", "DE");
     *     firstResult.put("coreCountry", true);
     *     firstResult.put("distance", null);
     *
     *     firstResult.put("_id", 448103);
     *     firstResult.put("key", null);
     *     firstResult.put("name", "Berlingo");
     *     firstResult.put("fullName", "Berlingo, Italy");
     *     firstResult.put("iata_airport_code", null);
     *     firstResult.put("type", "location");
     *     firstResult.put("country", "Italy");
     *     firstResult.put("geo_position", new HashMap<String, Object>{{
     *         put("latitude", 45.50298);
     *         put("longitude", 10.04366);
     *     }});
     *     firstResult.put("locationId", 147721);
     *     firstResult.put("inEurope", true);
     *     firstResult.put("countryCode", "IT");
     *     firstResult.put("coreCountry", true);
     *     firstResult.put("distance", null);
     *
     *     results.add(firstResult);
     *     results.add(secondResult);
     *
     * </code>
     *
     * @param url The URL to which a GET call will be performed
     * @return A collection of maps containing the GET results.
     */
    List<Map> executeGet(String url);
}
