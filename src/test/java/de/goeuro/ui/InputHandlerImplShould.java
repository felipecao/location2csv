package de.goeuro.ui;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import de.goeuro.presenter.CsvPresenter;
import de.goeuro.presenter.NoSuggestionsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

@RunWith(DataProviderRunner.class)
public class InputHandlerImplShould {

    private static final String CITY = "city";

    @Mock
    CsvPresenter csvPresenterMock;

    @Mock
    PrintStream printStreamMock;

    @InjectMocks
    InputHandlerImpl inputHandler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void present_an_error_message_if_args_is_empty() {
        inputHandler.fireCsvCreation(new String[]{});

        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
    }

    @Test
    @DataProvider({
            "null",
            "     ",
    })
    public void present_an_error_message_if_city_is_not_provided(String city) {
        inputHandler.fireCsvCreation(new String[]{city});

        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
    }

    @Test
    public void present_an_error_message_if_no_search_results_were_found() {
        doThrow(NoSuggestionsException.class).when(csvPresenterMock).createCSVFileWithSuggestionsFromAPI(CITY);
        inputHandler.fireCsvCreation(new String[]{CITY});

        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.SEARCH_DID_NOT_FIND_ANY_RESULTS);
    }

    @Test
    public void present_a_success_message_if_search_was_successful() {
        inputHandler.fireCsvCreation(new String[]{CITY});

        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.SEARCH_FOUND_RESULTS);
    }
}