package de.goeuro.ui;

import de.goeuro.useCase.UserNotifier;

import java.io.PrintStream;

public class ConsoleUserNotifier implements UserNotifier {

    private PrintStream out;
    private String outputFileName;

    public ConsoleUserNotifier(PrintStream out, String outputFileName) {
        this.out = out;
        this.outputFileName = outputFileName;
    }

    @Override
    public void notifyInputNotProvided() {
        out.println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
    }

    @Override
    public void notifySuccess() {
        out.println(String.format(CommandLineOutputMessages.SEARCH_FOUND_RESULTS_PATTERN, outputFileName));
    }

    @Override
    public void notifyNoResults() {
        out.println(CommandLineOutputMessages.SEARCH_DID_NOT_FIND_ANY_RESULTS);
    }
}
