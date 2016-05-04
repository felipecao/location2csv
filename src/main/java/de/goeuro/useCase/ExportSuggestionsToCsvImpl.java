package de.goeuro.useCase;

import de.goeuro.ExportSuggestionsToCsv;
import de.goeuro.entity.Suggestion;
import de.goeuro.presenter.CsvPresenter;

import java.util.List;

public class ExportSuggestionsToCsvImpl implements ExportSuggestionsToCsv {

    private GoEuroGateway gateway;
    private CsvPresenter csvPresenter;
    private InputHandler inputHandler;
    private UserNotifier userNotifier;

    public ExportSuggestionsToCsvImpl(GoEuroGateway gateway, CsvPresenter csvPresenter, InputHandler inputHandler,
                                      UserNotifier userNotifier) {
        this.gateway = gateway;
        this.csvPresenter = csvPresenter;
        this.inputHandler = inputHandler;
        this.userNotifier = userNotifier;
    }

    @Override
    public void execute() {

        if (!inputHandler.hasUserProvidedInput()) {
            userNotifier.notifyInputNotProvided();
            return;
        }

        String city = inputHandler.extractCity();
        List<Suggestion> suggestions = gateway.retrieveSuggestionsForCity(city);

        if(suggestions.isEmpty()) {
            userNotifier.notifyNoResults();
            return;
        }

        csvPresenter.exportSuggestions(suggestions);
        userNotifier.notifySuccess();
    }
}
