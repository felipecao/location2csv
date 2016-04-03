package de.goeuro.entity;

/**
 * Represents a suggestion provided by GoEuro API for a given city used as input on a search.
 *
 * This is an implementation of the "entities" layer defined by Robert C. Martin
 * in https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
 */
public interface Suggestion {

    Long getId();
    String getName();
    String getType();
    Double getLatitude();
    Double getLongitude();
}
