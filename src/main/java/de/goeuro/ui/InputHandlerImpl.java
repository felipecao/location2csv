package de.goeuro.ui;

import de.goeuro.InputHandler;
import de.goeuro.presenter.CsvPresenter;
import de.goeuro.presenter.NoSuggestionsException;

import java.io.PrintStream;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class InputHandlerImpl implements InputHandler {

    public static final String OUTPUT_FILE_NAME = "suggestions.csv";

    private CsvPresenter csvPresenter;
    private PrintStream out;
    private String[] args;

    @Deprecated
    public InputHandlerImpl(CsvPresenter csvPresenter, PrintStream out) {
        this.csvPresenter = csvPresenter;
        this.out = out;
    }

    public InputHandlerImpl(String[] args, PrintStream out) {
        this.args = args;
        this.out = out;
    }

    @Override
    public void fireCsvCreation(String[] args) {

        if (args.length == 0) {
            out.println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
            return;
        }

        String city = args[0];

        if (isBlank(city)) {
            out.println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
            return;
        }

        if(!searchHasResults(city)) {
            out.println(CommandLineOutputMessages.SEARCH_DID_NOT_FIND_ANY_RESULTS);
            return;
        }

        out.println(CommandLineOutputMessages.SEARCH_FOUND_RESULTS);
    }

    @Override
    public Boolean isInputValid() {
        return (args.length > 0) && (isNotBlank(args[0]));
    }

    @Override
    public void presentErrorMessage() {
        out.println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
    }

    @Override
    public void presentSuccessMessage() {
        out.println(CommandLineOutputMessages.SEARCH_FOUND_RESULTS);
    }

    @Override
    public void presentNoResultsMessage() {
        out.println(CommandLineOutputMessages.SEARCH_DID_NOT_FIND_ANY_RESULTS);
    }

    @Override
    public String extractCity() {
        if (!isInputValid()) { {
            return null;
        }}

        return args[0];
    }

    private Boolean searchHasResults(String city) {
        try {
            csvPresenter.createCSVFileWithSuggestionsFromAPI(city);
            return Boolean.TRUE;
        }
        catch (NoSuggestionsException e) {
            return Boolean.FALSE;
        }
    }
}
