package de.goeuro.ui;

import de.goeuro.useCase.InputHandler;

import java.io.PrintStream;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class InputHandlerImpl implements InputHandler {

    private PrintStream out;
    private String[] args;
    private String outputFileName;

    public InputHandlerImpl(String[] args, PrintStream out, String outputFileName) {
        this.args = args;
        this.out = out;
        this.outputFileName = outputFileName;
    }

    @Override
    public Boolean isInputValid() {
        return (args.length > 0) && (isNotBlank(args[0]));
    }

    @Override
    public void presentInputNotProvidedMessage() {
        out.println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
    }

    @Override
    public void presentSuccessMessage() {
        out.println(String.format(CommandLineOutputMessages.SEARCH_FOUND_RESULTS_PATTERN, outputFileName));
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
}
