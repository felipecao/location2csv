package de.goeuro.acceptance;

import de.goeuro.connection.GoEuroConnectionImpl;
import de.goeuro.connection.GoEuroGatewayImpl;
import de.goeuro.connection.HttpConnectionImpl;
import de.goeuro.presenter.CsvPresenterImpl;
import de.goeuro.ui.CommandLineOutputMessages;
import de.goeuro.ui.InputHandlerImpl;
import de.goeuro.useCase.RetrieveSuggestionsForCityImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TheSolutionShould {

    private static final String BERLIN = "Berlin";
    private static final String INPUT_THAT_GIVES_NO_RESULTS = "Jabuticaba";

    @Mock
    PrintStream printStreamMock;

    @InjectMocks
    InputHandlerImpl inputHandler;

    @Before
    public void setup() {
        deleteOutputFileFromPreviousRun();
        setupDependenciesForSolutionEntryPoint();
    }

    private void deleteOutputFileFromPreviousRun() {
        File outputFileFromPreviousRun = new File(InputHandlerImpl.OUTPUT_FILE_NAME);

        if(outputFileFromPreviousRun.exists()){
            outputFileFromPreviousRun.delete();
        }
    }

    private void setupDependenciesForSolutionEntryPoint() {
        inputHandler = new InputHandlerImpl(
                new CsvPresenterImpl(
                        new RetrieveSuggestionsForCityImpl(
                                new GoEuroGatewayImpl(
                                        new GoEuroConnectionImpl(
                                                new HttpConnectionImpl()
                                        )
                                )
                        ),
                        InputHandlerImpl.OUTPUT_FILE_NAME)
                , printStreamMock
        );
    }

    @Test
    public void create_a_csv_file_with_a_header_and_8_rows_for_Berlin_and_display_success_message() throws IOException {
        inputHandler.fireCsvCreation(new String[]{BERLIN});
        File outputFile = new File(InputHandlerImpl.OUTPUT_FILE_NAME);
        List<String> outputFileContents = Files.readAllLines(Paths.get(InputHandlerImpl.OUTPUT_FILE_NAME));

        assertTrue(outputFile.exists());
        assertEquals(9, outputFileContents.size());
        assertEquals("_id,name,type,latitude,longitude,", outputFileContents.get(0));
        assertTrue(outputFileContents.contains("376217,Berlin,location,52.52437,13.41053,"));
        assertTrue(outputFileContents.contains("448103,Berlingo,location,45.50298,10.04366,"));
        assertTrue(outputFileContents.contains("425332,Berlingerode,location,51.45775,10.2384,"));
        assertTrue(outputFileContents.contains("425326,Bernau bei Berlin,location,52.67982,13.58708,"));
        assertTrue(outputFileContents.contains("314826,Berlin Tegel,airport,52.5548,13.28903,"));
        assertTrue(outputFileContents.contains("314827,Berlin Schönefeld,airport,52.3887261,13.5180874,"));
        assertTrue(outputFileContents.contains("334196,Berlin Hbf,station,52.525589,13.369548,"));
        assertTrue(outputFileContents.contains("333977,Berlin Ostbahnhof,station,52.510972,13.434567,"));

        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.SEARCH_FOUND_RESULTS);
    }

    @Test
    public void display_a_message_saying_no_results_were_found() throws IOException {
        inputHandler.fireCsvCreation(new String[]{INPUT_THAT_GIVES_NO_RESULTS});
        File outputFile = new File(InputHandlerImpl.OUTPUT_FILE_NAME);

        assertFalse(outputFile.exists());
        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.SEARCH_DID_NOT_FIND_ANY_RESULTS);
    }
}
