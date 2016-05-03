package de.goeuro.useCase;

import de.goeuro.ExportSuggestionsToCsv;
import de.goeuro.entity.Suggestion;
import de.goeuro.presenter.CsvPresenter;

import java.util.List;

public class ExportSuggestionsToCsvImpl implements ExportSuggestionsToCsv {

    private GoEuroGateway gateway;
    private CsvPresenter csvPresenter;
    private InputHandler inputHandler;

    public ExportSuggestionsToCsvImpl(GoEuroGateway gateway, CsvPresenter csvPresenter, InputHandler inputHandler) {
        this.gateway = gateway;
        this.csvPresenter = csvPresenter;
        this.inputHandler = inputHandler;
    }

    @Override
    public void execute() {

        if (!inputHandler.isInputValid()) { // TODO this validation should be done by a separate object
            inputHandler.presentInputNotProvidedMessage();
            return;
        }

        String city = inputHandler.extractCity();
        List<Suggestion> suggestions = gateway.retrieveSuggestionsForCity(city);

        if(suggestions.isEmpty()) {
            inputHandler.presentNoResultsMessage();
            return;
        }

        csvPresenter.exportSuggestions(suggestions);
        inputHandler.presentSuccessMessage();
    }
}
