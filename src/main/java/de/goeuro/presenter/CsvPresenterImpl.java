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

    private String outputFileName;
    private StringBuilder contents;

    public CsvPresenterImpl(String outputFileName) {
        this.outputFileName = outputFileName;
        this.contents = new StringBuilder();
    }

    @Override
    public void exportSuggestions(List<Suggestion> suggestions) {
        if(null == suggestions || suggestions.isEmpty()) {
            return;
        }
        createCSVFileFromSuggestions(suggestions);
    }

    private void createCSVFileFromSuggestions(List<Suggestion> suggestions) {
        addHeaders();
        addSuggestions(suggestions);
        writeContentsToOutputFile();
    }

    private void addHeaders(){
        CSV_HEADERS.stream().forEach( h ->
            contents.append(h).append(SEPARATOR)
        );
        contents.append(NEW_LINE);
    }

    private void addSuggestions(List<Suggestion> suggestions) {
        suggestions.stream().forEach( s ->
            contents.append(s.getId()).append(SEPARATOR)
                    .append(s.getName()).append(SEPARATOR)
                    .append(s.getType()).append(SEPARATOR)
                    .append(s.getLatitude()).append(SEPARATOR)
                    .append(s.getLongitude()).append(SEPARATOR)
                    .append(NEW_LINE)
        );
    }

    private void writeContentsToOutputFile() {
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
