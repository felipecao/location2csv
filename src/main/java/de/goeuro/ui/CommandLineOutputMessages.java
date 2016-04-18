package de.goeuro.ui;

public abstract class CommandLineOutputMessages {
    public static final String CITY_NOT_PROVIDED_BY_USER = "ERROR: You haven't provided a city, terminating execution";
    public static final String SEARCH_DID_NOT_FIND_ANY_RESULTS = "There are no suggestions for this city, skipping CSV creation";
    public static final String SEARCH_FOUND_RESULTS_PATTERN = "Your search was successful! A CSV file called %s was created in this folder containing your search results.";
}
