package de.goeuro.ui;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleUserNotifierShould {

    private String outputFileName = "output.csv";
    private PrintStream printStreamMock;
    private ConsoleUserNotifier notifier;

    @Before
    public void setup() {
        printStreamMock = mock(PrintStream.class);
        notifier = new ConsoleUserNotifier(printStreamMock, outputFileName);
    }

    @Test
    public void output_the_expected_error_message() {
        notifier.notifyInputNotProvided();
        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
    }

    @Test
    public void output_the_expected_success_message() {
        notifier.notifySuccess();
        verify(printStreamMock, times(1)).println(String.format(CommandLineOutputMessages.SEARCH_FOUND_RESULTS_PATTERN, outputFileName));
    }

    @Test
    public void output_the_expected_no_results_message() {
        notifier.notifyNoResults();
        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.SEARCH_DID_NOT_FIND_ANY_RESULTS);
    }


}