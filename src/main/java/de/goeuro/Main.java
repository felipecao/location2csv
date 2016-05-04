package de.goeuro;

import de.goeuro.connection.GoEuroConnectionImpl;
import de.goeuro.connection.GoEuroGatewayImpl;
import de.goeuro.connection.HttpConnectionImpl;
import de.goeuro.presenter.CsvPresenter;
import de.goeuro.presenter.CsvPresenterImpl;
import de.goeuro.ui.CommandLineUserInput;
import de.goeuro.ui.ConsoleUserNotifier;
import de.goeuro.useCase.ExportSuggestionsToCsvImpl;
import de.goeuro.useCase.GoEuroGateway;
import de.goeuro.useCase.UserInput;
import de.goeuro.useCase.UserNotifier;

import java.io.PrintStream;

public class Main {

    public static final String OUTPUT_FILE_NAME = "suggestions.csv";

    private ExportSuggestionsToCsv useCase;
    private UserInput userInput;
    private CsvPresenter csvPresenter;
    private UserNotifier userNotifier;
    private GoEuroGateway gateway;

    public static void main(String[] args) {
        Main main = new Main();

        main.executeSolution(args, System.out);
        System.exit(0);
    }

    void executeSolution(String[] args, PrintStream out) {
        setupDependencies(args, out);
        executeUseCase();
    }

    private void setupDependencies(String[] args, PrintStream out) {
        setupInputHandlerDependencies(args);
        setupGateway();
        setupCsvPresenter();
        setupNotifier(out);
        setupUseCase();
    }

    private void setupInputHandlerDependencies(String[] args) {
        userInput = new CommandLineUserInput(args);
    }

    private void setupGateway() {
        gateway = new GoEuroGatewayImpl(
                new GoEuroConnectionImpl(
                        new HttpConnectionImpl()
                )
        );
    }

    private void setupCsvPresenter() {
        csvPresenter = new CsvPresenterImpl(OUTPUT_FILE_NAME);
    }

    private void setupNotifier(PrintStream out) {
        userNotifier = new ConsoleUserNotifier(out, OUTPUT_FILE_NAME);
    }

    private void setupUseCase() {
        useCase = new ExportSuggestionsToCsvImpl(gateway, csvPresenter, userInput, userNotifier);
    }

    private void executeUseCase() {
        useCase.execute();
    }

}
