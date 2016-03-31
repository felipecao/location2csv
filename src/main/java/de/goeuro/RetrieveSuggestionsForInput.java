package de.goeuro;

import de.goeuro.domain.Suggestion;

import java.util.List;

public interface RetrieveSuggestionsForInput {

    List<Suggestion> invoke(String input);
}
