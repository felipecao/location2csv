package de.goeuro.useCase;

import de.goeuro.ExportSuggestionsToCsv;
import de.goeuro.entity.Suggestion;
import de.goeuro.presenter.CsvPresenter;

import java.util.List;

public class ExportSuggestionsToCsvImpl implements ExportSuggestionsToCsv {

    private GoEuroGateway gateway;
    private CsvPresenter csvPresenter;
    private UserInput userInput;
    private UserNotifier userNotifier;

    public ExportSuggestionsToCsvImpl(GoEuroGateway gateway, CsvPresenter csvPresenter, UserInput userInput,
                                      UserNotifier userNotifier) {
        this.gateway = gateway;
        this.csvPresenter = csvPresenter;
        this.userInput = userInput;
        this.userNotifier = userNotifier;
    }

    @Override
    public void execute() {

        if (!userInput.isPresent()) {
            userNotifier.notifyInputNotProvided();
            return;
        }

        String city = userInput.get();
        List<Suggestion> suggestions = gateway.retrieveSuggestionsForCity(city);

        if(suggestions.isEmpty()) {
            userNotifier.notifyNoResults();
            return;
        }

        csvPresenter.exportSuggestions(suggestions);
        userNotifier.notifySuccess();
    }
}
