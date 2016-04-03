package de.goeuro.connection;

import de.goeuro.entity.Suggestion;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;

class SuggestionImpl implements Suggestion {

    private Long id;
    private String name;
    private String type;
    private Double latitude;
    private Double longitude;

    SuggestionImpl(Double id, String name, String type, Double latitude, Double longitude) {
        this.id = (null != id) ? id.longValue() : null;
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    static SuggestionImpl fromMap(Map<String, Object> input) {

        if (null == input || input.isEmpty()) {
            return null;
        }

        Double id = (Double) input.get("_id");
        String name = (String) input.get("name");
        String type = (String) input.get("type");
        Double latitude = (null != input.get("geo_position")) ? (Double) ((Map)input.get("geo_position")).get("latitude") : null;
        Double longitude = (null != input.get("geo_position")) ? (Double) ((Map)input.get("geo_position")).get("longitude") : null;

        if(null == id
                && null == name
                && null == type
                && null == latitude
                && null == longitude) {
            return null;
        }

        return new SuggestionImpl(id, name, type, latitude, longitude);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        SuggestionImpl that = (SuggestionImpl) other;

        return new EqualsBuilder()
                .append(this.id, that.id)
                .append(this.name, that.name)
                .append(this.type, that.type)
                .append(this.latitude, that.latitude)
                .append(this.longitude, that.longitude)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
                .append(this.name)
                .append(this.type)
                .append(this.latitude)
                .append(this.longitude)
                .toHashCode();
    }

}
