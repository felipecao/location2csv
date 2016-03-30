package de.goeuro.domain;

import java.math.BigDecimal;

public class Suggestion {

    private String id;
    private String name;
    private String type;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Suggestion() {
    }

    public Suggestion(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }
}
