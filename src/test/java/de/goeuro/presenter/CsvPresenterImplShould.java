package de.goeuro.presenter;

import de.goeuro.entity.Suggestion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CsvPresenterImplShould {

    private static final String EXPECTED_OUTPUT_FILE_NAME = "presenter_suggestions.csv";
    private static final String CITY = "city";

    @Mock
    RetrieveSuggestionsForCity retrieveSuggestionsForCity;

    CsvPresenterImpl csvPresenter;

    @Before
    public void setup() {
        deleteOutputFileFromPreviousRun();
        csvPresenter = new CsvPresenterImpl(retrieveSuggestionsForCity, EXPECTED_OUTPUT_FILE_NAME);
    }

    private void deleteOutputFileFromPreviousRun() {
        File outputFileFromPreviousRun = new File(EXPECTED_OUTPUT_FILE_NAME);

        if(outputFileFromPreviousRun.exists()){
            outputFileFromPreviousRun.delete();
        }
    }

    @Test
    public void create_a_csv_with_4_content_lines_from_a_response_with_4_entries() throws IOException {
        List<Suggestion> suggestions = fourSuggestions();
        when(retrieveSuggestionsForCity.fetch(CITY)).thenReturn(suggestions);

        csvPresenter.createCSVFileWithSuggestionsFromAPI(CITY);
        Path outputFile = Paths.get(EXPECTED_OUTPUT_FILE_NAME);
        List<String> outputFileContents = Files.readAllLines(outputFile);

        assertNotNull(outputFile);
        assertNotNull(outputFile.toFile());
        assertNotNull(outputFileContents);
        assertFalse(outputFileContents.isEmpty());
        assertEquals(5, outputFileContents.size());
    }

    @Test(expected = NoSuggestionsException.class)
    public void throw_an_exception_if_no_suggestions_are_returned() throws IOException {
        when(retrieveSuggestionsForCity.fetch(CITY)).thenThrow(NoSuggestionsException.class);

        csvPresenter.createCSVFileWithSuggestionsFromAPI(CITY);
    }

    private List<Suggestion> fourSuggestions() {
        List<Suggestion> suggestions = new LinkedList<>();

        suggestions.add(buildSuggestion(1, "one", "type1", 1, 1));
        suggestions.add(buildSuggestion(2, "two", "type2", 2, 2));
        suggestions.add(buildSuggestion(3, "three", "type3", 3, 3));
        suggestions.add(buildSuggestion(4, "four", "type4", 4, 4));

        return suggestions;
    }

    private Suggestion buildSuggestion(Integer id, String name, String type, Integer lat, Integer lng ) {
        Suggestion suggestion = mock(Suggestion.class);

        when(suggestion.getId()).thenReturn(id.longValue());
        when(suggestion.getName()).thenReturn(name);
        when(suggestion.getType()).thenReturn(type);
        when(suggestion.getLatitude()).thenReturn(lat.doubleValue());
        when(suggestion.getLongitude()).thenReturn(lng.doubleValue());

        return suggestion;
    }
}