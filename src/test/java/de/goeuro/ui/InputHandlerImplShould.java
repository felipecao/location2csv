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

import static de.goeuro.TestConstants.CITY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(DataProviderRunner.class)
public class InputHandlerImplShould {

    CsvPresenter csvPresenterMock;
    PrintStream printStreamMock;
    InputHandlerImpl inputHandler;

    String[] args;

    @Before
    public void setup() {
        csvPresenterMock = mock(CsvPresenter.class);
        printStreamMock = mock(PrintStream.class);
        args = new String[]{};
    }

    @Test
    public void warn_if_user_provided_no_input() {
        inputHandler = new InputHandlerImpl(args, printStreamMock);
        assertFalse(inputHandler.isInputValid());
    }

    @Test
    @DataProvider({
            "null",
            "     ",
    })
    public void warn_if_user_provided_blank_city(final String input) {
        args = new String[]{input};
        inputHandler = new InputHandlerImpl(args, printStreamMock);
        assertFalse(inputHandler.isInputValid());
    }

    @Test
    @DataProvider({
            "not null",
            "Berlin",
            "Jaboticaba",
    })
    public void define_input_as_valid_if_a_string_is_provided(final String input) {
        args = new String[]{input};
        inputHandler = new InputHandlerImpl(args, printStreamMock);
        assertTrue(inputHandler.isInputValid());
    }

    @Test
    public void output_the_expected_error_message() {
        inputHandler = new InputHandlerImpl(args, printStreamMock);
        inputHandler.presentErrorMessage();
        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
    }

    @Test
    public void output_the_expected_success_message() {
        inputHandler = new InputHandlerImpl(args, printStreamMock);
        inputHandler.presentSuccessMessage();
        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.SEARCH_FOUND_RESULTS);
    }

    @Test
    public void output_the_expected_no_results_message() {
        inputHandler = new InputHandlerImpl(args, printStreamMock);
        inputHandler.presentNoResultsMessage();
        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.SEARCH_DID_NOT_FIND_ANY_RESULTS);
    }

    @Test
    @DataProvider({
            "null",
            "     ",
            "not null",
            "Berlin",
            "Jaboticaba",
    })
    public void extract_the_city_from_input(final String input) {
        args = new String[]{input};
        inputHandler = new InputHandlerImpl(args, printStreamMock);
        String city = inputHandler.extractCity();

        if(isNotBlank(input)) {
            assertEquals(city, input);
        }
        else {
            assertNull(city);
        }
    }

    @Test
    public void present_an_error_message_if_args_is_empty() {
        inputHandler = new InputHandlerImpl(csvPresenterMock, printStreamMock);
        inputHandler.fireCsvCreation(new String[]{});

        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
    }

    @Test
    @DataProvider({
            "null",
            "     ",
    })
    public void present_an_error_message_if_city_is_not_provided(String city) {
        inputHandler = new InputHandlerImpl(csvPresenterMock, printStreamMock);
        inputHandler.fireCsvCreation(new String[]{city});

        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
    }

    @Test
    public void present_an_error_message_if_no_search_results_were_found() {
        doThrow(NoSuggestionsException.class).when(csvPresenterMock).createCSVFileWithSuggestionsFromAPI(CITY);

        inputHandler = new InputHandlerImpl(csvPresenterMock, printStreamMock);
        inputHandler.fireCsvCreation(new String[]{CITY});

        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.SEARCH_DID_NOT_FIND_ANY_RESULTS);
    }

    @Test
    public void present_a_success_message_if_search_was_successful() {
        inputHandler = new InputHandlerImpl(csvPresenterMock, printStreamMock);
        inputHandler.fireCsvCreation(new String[]{CITY});

        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.SEARCH_FOUND_RESULTS);
    }
}