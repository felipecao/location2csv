package de.goeuro;

import de.goeuro.ui.CommandLineOutputMessages;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TheSolutionShould {

    private static final String BERLIN = "Berlin";
    private static final String INPUT_THAT_GIVES_NO_RESULTS = "Jabuticaba";

    private PrintStream printStreamMock;

    private Main main;

    @Before
    public void setup() {
        deleteOutputFileFromPreviousRun();

        main = new Main();
        printStreamMock = mock(PrintStream.class);
    }

    private void deleteOutputFileFromPreviousRun() {
        File outputFileFromPreviousRun = new File(Main.OUTPUT_FILE_NAME);

        if(outputFileFromPreviousRun.exists()){
            outputFileFromPreviousRun.delete();
        }
    }

    @Test
    public void create_a_csv_file_with_a_header_and_8_rows_for_Berlin_and_display_success_message() throws IOException {

        main.executeSolution(new String[]{BERLIN}, printStreamMock);

        Path outputFile = Paths.get(Main.OUTPUT_FILE_NAME);
        List<String> outputFileContents = Files.readAllLines(outputFile);

        assertTrue(outputFile.toFile().exists());
        assertEquals(9, outputFileContents.size());
        assertEquals("_id,name,type,latitude,longitude,", outputFileContents.get(0));
        assertTrue(outputFileContents.contains("376217,Berlin,location,52.52437,13.41053,"));
        assertTrue(outputFileContents.contains("448103,Berlingo,location,45.50298,10.04366,"));
        assertTrue(outputFileContents.contains("425332,Berlingerode,location,51.45775,10.2384,"));
        assertTrue(outputFileContents.contains("425326,Bernau bei Berlin,location,52.67982,13.58708,"));
        assertTrue(outputFileContents.contains("314826,Berlin Tegel,airport,52.5548,13.28903,"));
        assertTrue(outputFileContents.contains("314827,Berlin Schönefeld,airport,52.3887261,13.5180874,"));
        assertTrue(outputFileContents.contains("334196,Berlin Hbf,station,52.525589,13.369548,"));
        assertTrue(outputFileContents.contains("334098,Berlin Spandau,station,52.53447,13.19753,"));

        verify(printStreamMock, times(1)).println(
                String.format(CommandLineOutputMessages.SEARCH_FOUND_RESULTS_PATTERN, Main.OUTPUT_FILE_NAME)
        );
    }

    @Test
    public void display_a_message_saying_no_results_were_found() throws IOException {

        main.executeSolution(new String[]{INPUT_THAT_GIVES_NO_RESULTS}, printStreamMock);

        Path outputFile = Paths.get(Main.OUTPUT_FILE_NAME);

        assertFalse(outputFile.toFile().exists());
        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.SEARCH_DID_NOT_FIND_ANY_RESULTS);
    }

    @Test
    public void display_a_message_saying_no_input_was_provided() throws IOException {

        main.executeSolution(new String[]{}, printStreamMock);

        Path outputFile = Paths.get(Main.OUTPUT_FILE_NAME);

        assertFalse(outputFile.toFile().exists());
        verify(printStreamMock, times(1)).println(CommandLineOutputMessages.CITY_NOT_PROVIDED_BY_USER);
    }
}
