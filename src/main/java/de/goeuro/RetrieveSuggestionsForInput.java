package de.goeuro;

import de.goeuro.entity.Suggestion;

import java.util.List;

public interface RetrieveSuggestionsForInput {

    List<Suggestion> invoke(String input);
}
