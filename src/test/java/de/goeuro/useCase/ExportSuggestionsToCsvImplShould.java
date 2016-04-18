package de.goeuro.useCase;

import de.goeuro.entity.Suggestion;
import de.goeuro.presenter.CsvPresenter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExportSuggestionsToCsvImplShould {

    private static final String BERLIN = "Berlin";

    @Mock
    private GoEuroGateway gateway;

    @Mock
    private CsvPresenter csvPresenter;

    @Mock
    private InputHandler inputHandler;

    @InjectMocks
    private ExportSuggestionsToCsvImpl useCase;

    @Test
    public void retrieve_one_suggestion_from_gateway_and_invoke_csv_generation_with_one_entry() {
        List<Suggestion> oneSuggestion = oneSuggestion();

        when(inputHandler.extractCity()).thenReturn(BERLIN);
        when(inputHandler.isInputValid()).thenReturn(Boolean.TRUE);
        when(gateway.retrieveSuggestionsForCity(BERLIN)).thenReturn(oneSuggestion);

        useCase.execute();

        verify(csvPresenter, times(1)).exportSuggestions(oneSuggestion);
        verify(inputHandler, times(1)).extractCity();
        verify(inputHandler, times(1)).presentSuccessMessage();
        verify(inputHandler, never()).presentNoResultsMessage();
        verify(inputHandler, never()).presentInputNotProvidedMessage();
    }

    @Test
    public void retrieve_four_suggestions_from_gateway_and_invoke_csv_generation_with_four_entries() {
        List<Suggestion> fourSuggestions = fourSuggestions();

        when(inputHandler.extractCity()).thenReturn(BERLIN);
        when(inputHandler.isInputValid()).thenReturn(Boolean.TRUE);
        when(gateway.retrieveSuggestionsForCity(BERLIN)).thenReturn(fourSuggestions);

        useCase.execute();

        verify(csvPresenter, times(1)).exportSuggestions(fourSuggestions);
        verify(inputHandler, times(1)).extractCity();
        verify(inputHandler, times(1)).presentSuccessMessage();
        verify(inputHandler, never()).presentNoResultsMessage();
        verify(inputHandler, never()).presentInputNotProvidedMessage();
    }

    @Test
    public void present_a_message_saying_there_were_no_results() {
        List<Suggestion> noResults = new ArrayList<>();

        when(inputHandler.extractCity()).thenReturn(BERLIN);
        when(inputHandler.isInputValid()).thenReturn(Boolean.TRUE);
        when(gateway.retrieveSuggestionsForCity(BERLIN)).thenReturn(noResults);

        useCase.execute();

        verify(csvPresenter, never()).exportSuggestions(anyList());
        verify(inputHandler, times(1)).extractCity();
        verify(inputHandler, times(1)).presentNoResultsMessage();
        verify(inputHandler, never()).presentSuccessMessage();
        verify(inputHandler, never()).presentInputNotProvidedMessage();
    }

    @Test
    public void present_an_error_message_if_input_is_invalid() {
        List<Suggestion> noResults = new ArrayList<>();

        when(inputHandler.isInputValid()).thenReturn(Boolean.FALSE);
        when(gateway.retrieveSuggestionsForCity(BERLIN)).thenReturn(noResults);

        useCase.execute();

        verify(csvPresenter, never()).exportSuggestions(anyList());
        verify(inputHandler, never()).extractCity();
        verify(inputHandler, never()).presentNoResultsMessage();
        verify(inputHandler, never()).presentSuccessMessage();
        verify(inputHandler, times(1)).presentInputNotProvidedMessage();
    }

    private List<Suggestion> oneSuggestion() {
        List<Suggestion> suggestions = new ArrayList<>();

        suggestions.add(suggestionWithRandomName());

        return suggestions;
    }

    private Suggestion suggestionWithRandomName() {
        String randomName = UUID.randomUUID().toString();
        Suggestion suggestion = mock(Suggestion.class);

        when(suggestion.getName()).thenReturn(randomName);

        return suggestion;
    }

    private List<Suggestion> fourSuggestions() {
        List<Suggestion> suggestions = new ArrayList<>();

        suggestions.add(suggestionWithRandomName());
        suggestions.add(suggestionWithRandomName());
        suggestions.add(suggestionWithRandomName());
        suggestions.add(suggestionWithRandomName());

        return suggestions;
    }

}