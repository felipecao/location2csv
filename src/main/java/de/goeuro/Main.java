package de.goeuro;

import de.goeuro.connection.GoEuroConnectionImpl;
import de.goeuro.connection.GoEuroGatewayImpl;
import de.goeuro.connection.HttpConnectionImpl;
import de.goeuro.presenter.CsvPresenterImpl;
import de.goeuro.ui.InputHandlerImpl;
import de.goeuro.useCase.ExportSuggestionsToCsvImpl;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();

        main.executeSolution(args);
        System.exit(0);
    }

    private void executeSolution(String[] args) {
        InputHandler inputHandler = setupInputHandler();

        inputHandler.fireCsvCreation(args);
    }

    private InputHandler setupInputHandler() {
        return new InputHandlerImpl(
                new CsvPresenterImpl(
                        new ExportSuggestionsToCsvImpl(
                                new GoEuroGatewayImpl(
                                        new GoEuroConnectionImpl(
                                                new HttpConnectionImpl()
                                        )
                                )
                        ),
                        InputHandlerImpl.OUTPUT_FILE_NAME)
                , System.out
        );
    }
}
