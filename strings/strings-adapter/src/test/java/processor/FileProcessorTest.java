package processor;

import domain.ParagraphJustifier;
import domain.ParagraphValidator;
import domain.ParagraphWrapper;
import org.junit.jupiter.api.Test;
import usecase.TextFormatter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {

    /**
     * Verifies that the generated output file is comparable to the output file of example considering the input file.
     * @throws IOException
     */
    @Test
    void execute() throws IOException {
        ParagraphValidator validator = new ParagraphValidator(40);
        TextFormatter textFormatter = new TextFormatter(new ParagraphWrapper(validator), new ParagraphJustifier(validator));

        String inputFilePath = "src/test/resources/inputExample.txt";
        String outputFilePath = "src/test/resources/outputFile.txt";
        FileProcessor fileProcessor = new FileProcessor(textFormatter);
        fileProcessor.execute(inputFilePath, outputFilePath);

        assertTrue(compareFiles());
    }

    private boolean compareFiles() throws IOException {
        BufferedReader actual = new BufferedReader(new FileReader("src/test/resources/outputFile.txt"));
        BufferedReader expected = new BufferedReader(new FileReader("src/test/resources/outputExpected.txt"));

        String actualLine = actual.readLine();
        String expectedLine = expected.readLine();

        boolean areEqual = true;
        int lineNum = 1;

        while (actualLine != null || expectedLine != null) {
            System.out.println(actualLine);
            System.out.println(expectedLine);

            if(actualLine == null || expectedLine == null) {
                areEqual = false;
                break;
            } else if(!actualLine.equalsIgnoreCase(expectedLine)) {
                areEqual = false;
                break;
            }
            actualLine = actual.readLine();
            expectedLine = expected.readLine();
            lineNum++;
        }
        actual.close();
        expected.close();
        return areEqual;
    }

}