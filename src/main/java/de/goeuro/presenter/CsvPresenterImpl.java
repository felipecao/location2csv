package de.goeuro.presenter;

import de.goeuro.entity.Suggestion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CsvPresenterImpl implements CsvPresenter {

    private static final String SEPARATOR = ",";
    private static final String NEW_LINE = "\n";
    private static final List<String> CSV_HEADERS = Arrays.asList("_id", "name", "type", "latitude", "longitude");

    private ExportSuggestionsToCsv suggestionsForCity;
    private String outputFileName;

    public CsvPresenterImpl(ExportSuggestionsToCsv suggestionsForCity, String outputFileName) {
        this.suggestionsForCity = suggestionsForCity;
        this.outputFileName = outputFileName;
    }

    @Override
    public void createCSVFileWithSuggestionsFromAPI(String city) {
        List<Suggestion> suggestionsFromAPI = retrieveSuggestionsFromAPI(city);
        createCSVFileFromSuggestions(suggestionsFromAPI);
    }

    private List<Suggestion> retrieveSuggestionsFromAPI(String city) {
        return suggestionsForCity.fetch(city);
    }

    private void createCSVFileFromSuggestions(List<Suggestion> suggestions) {
        if (null == suggestions || suggestions.isEmpty()) {
            throw new NoSuggestionsException();
        }

        StringBuilder contents = new StringBuilder();

        addHeadersToContents(contents);
        addSuggestionsToContents(suggestions, contents);
        writeContentsToOutputFile(contents);
    }

    private void addHeadersToContents(StringBuilder contents){
        CSV_HEADERS.stream().forEach( h ->
            contents.append(h).append(SEPARATOR)
        );
        contents.append(NEW_LINE);
    }

    private void addSuggestionsToContents(List<Suggestion> suggestions, StringBuilder contents) {
        suggestions.stream().forEach( s ->
            contents.append(s.getId()).append(SEPARATOR)
                    .append(s.getName()).append(SEPARATOR)
                    .append(s.getType()).append(SEPARATOR)
                    .append(s.getLatitude()).append(SEPARATOR)
                    .append(s.getLongitude()).append(SEPARATOR)
                    .append(NEW_LINE)
        );
    }

    private void writeContentsToOutputFile(StringBuilder contents) {
        PrintWriter writer = createPrintWriter();
        writer.write(contents.toString());
        writer.close();
    }

    private PrintWriter createPrintWriter() {
        try {
            return new PrintWriter(new File(outputFileName));
        }
        catch (FileNotFoundException e) {
            throw new CouldNotCreateOutputFileException(e);
        }
    }
}
