package de.goeuro.ui;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import de.goeuro.presenter.CsvPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.PrintStream;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(DataProviderRunner.class)
public class InputHandlerImplShould {

    private CsvPresenter csvPresenterMock;
    private PrintStream printStreamMock;
    private InputHandlerImpl inputHandler;
    private String[] args;
    private String outputFileName = "output.csv";

    @Before
    public void setup() {
        csvPresenterMock = mock(CsvPresenter.class);
        printStreamMock = mock(PrintStream.class);
        args = new String[]{};
    }

    @Test
    public void warn_if_user_provided_no_input() {
        inputHandler = new InputHandlerImpl(args, printStreamMock, outputFileName);
        assertFalse(inputHandler.isInputValid());
    }

    @Test
    @DataProvider({
            "null",
            "     ",
    })
    public void warn_if_user_provided_blank_city(final String input) {
        args = new String[]{input};
        inputHandler = new InputHandlerImpl(args, printStreamMock, outputFileName);
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
        inputHandler = new InputHandlerImpl(args, printStreamMock, outputFileName);
        assertTrue(inputHandler.isInputValid());
    }

    @Test
    public void output_the_expected_error_message() {
        inputHandler = new InputHandlerImpl(args, printStreamMock, outputFileName);
        inputHandler.presentInputNotProvidedMessage();
        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
    }

    @Test
    public void output_the_expected_success_message() {
        inputHandler = new InputHandlerImpl(args, printStreamMock, outputFileName);
        inputHandler.presentSuccessMessage();
        verify(printStreamMock, times(1)).println(String.format(CommandLineOutputMessages.SEARCH_FOUND_RESULTS_PATTERN, outputFileName));
    }

    @Test
    public void output_the_expected_no_results_message() {
        inputHandler = new InputHandlerImpl(args, printStreamMock, outputFileName);
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
        inputHandler = new InputHandlerImpl(args, printStreamMock, outputFileName);
        String city = inputHandler.extractCity();

        if(isNotBlank(input)) {
            assertEquals(city, input);
        }
        else {
            assertNull(city);
        }
    }

}