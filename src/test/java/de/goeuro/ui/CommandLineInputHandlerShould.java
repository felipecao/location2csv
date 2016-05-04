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
public class CommandLineInputHandlerShould {

    private CsvPresenter csvPresenterMock;
    private PrintStream printStreamMock;
    private CommandLineUserInput inputHandler;
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
        inputHandler = new CommandLineUserInput(args);
        assertFalse(inputHandler.isPresent());
    }

    @Test
    @DataProvider({
            "null",
            "     ",
    })
    public void warn_if_user_provided_blank_city(final String input) {
        args = new String[]{input};
        inputHandler = new CommandLineUserInput(args);
        assertFalse(inputHandler.isPresent());
    }

    @Test
    @DataProvider({
            "not null",
            "Berlin",
            "Jaboticaba",
    })
    public void define_input_as_valid_if_a_string_is_provided(final String input) {
        args = new String[]{input};
        inputHandler = new CommandLineUserInput(args);
        assertTrue(inputHandler.isPresent());
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
        inputHandler = new CommandLineUserInput(args);
        String city = inputHandler.get();

        if(isNotBlank(input)) {
            assertEquals(city, input);
        }
        else {
            assertNull(city);
        }
    }

}