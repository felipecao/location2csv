package de.goeuro.connection;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class SuggestionImplShould {

    final Double id = 1D;
    final String name = "name";
    final String type = "type";
    final Double latitude = 1D;
    final Double longitude = 1D;

    @Test
    public void instantiate_a_Suggestion_with_all_attributes_if_input_map_contains_all_attributes() {
        SuggestionImpl s = SuggestionImpl.fromMap(mapWithAllAttributes());

        assertNotNull(s);
        assertEquals(id.longValue(), s.getId().longValue());
        assertEquals(name, s.getName());
        assertEquals(type, s.getType());
        assertEquals(latitude, s.getLatitude());
        assertEquals(longitude, s.getLongitude());
    }

    @Test
    public void create_a_null_instance_if_map_matches_no_attributes() {
        SuggestionImpl s = SuggestionImpl.fromMap(fullMapWithoutMatchingAttributes());

        assertNull(s);
    }

    @Test
    public void create_a_null_instance_if_map_is_null() {
        SuggestionImpl s = SuggestionImpl.fromMap(null);

        assertNull(s);
    }

    @Test
    public void create_a_null_instance_if_map_is_empty() {
        SuggestionImpl s = SuggestionImpl.fromMap(new HashMap<>());

        assertNull(s);
    }

    @Test
    @DataProvider({
            "id", "name", "type", "latitude", "longitude"
    })
    public void create_an_instance_with_just_one_attribute_if_map_has_only_one_attribute(String attributeName) {
        SuggestionImpl s = SuggestionImpl.fromMap(mapWithAttribute(attributeName));

        assertNotNull(s);

        assertEquals("id".equals(attributeName) ? id.longValue() : null, s.getId());
        assertEquals("name".equals(attributeName) ? name : null, s.getName());
        assertEquals("type".equals(attributeName) ? type : null, s.getType());
        assertEquals("latitude".equals(attributeName) ? latitude : null, s.getLatitude());
        assertEquals("longitude".equals(attributeName) ? longitude : null, s.getLongitude());
    }

    private Map<String, Object> mapWithAllAttributes() {
        Map<String, Object> allAttributes = new HashMap<>();
        Map<String, Double> geoPosition = new HashMap<String, Double>() {{
            put("latitude", latitude);
            put("longitude", longitude);
        }};

        allAttributes.put("_id", id);
        allAttributes.put("name", name);
        allAttributes.put("type", type);
        allAttributes.put("geo_position", geoPosition);

        return allAttributes;
    }

    private Map<String, Object> fullMapWithoutMatchingAttributes() {
        Map<String, Object> allAttributes = new HashMap<>();
        Map<String, Double> geoPosition = new HashMap<String, Double>() {{
            put("latitude", latitude);
            put("longitude", longitude);
        }};

        allAttributes.put("a_id", id);
        allAttributes.put("a_name", name);
        allAttributes.put("a_type", type);
        allAttributes.put("a_geo_position", geoPosition);

        return allAttributes;
    }

    private Map<String, Object> mapWithAttribute(String attributeName) {

        Map<String, Object> oneAttribute = new HashMap<>();
        Map<String, Double> geoPosition = new HashMap<>();

        switch (attributeName) {
            case "id":
                oneAttribute.put("_id", id);
                break;
            case "name":
                oneAttribute.put("name", name);
                break;
            case "type":
                oneAttribute.put("type", type);
                break;
            case "latitude":
                geoPosition.put("latitude", latitude);
                oneAttribute.put("geo_position", geoPosition);
                break;
            case "longitude":
                geoPosition.put("longitude", longitude);
                oneAttribute.put("geo_position", geoPosition);
                break;
        }

        return oneAttribute;
    }

}