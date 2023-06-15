package by.receipt.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckViewTest {
    private static final Logger log = LoggerFactory.getLogger(CheckViewTest.class);

    @Test
    void consolePrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        CheckView checkView = new CheckView();
        checkView.consolePrint("Hello");
        String expectedOutput = "Hello";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testFileWriterException() {
        String path = "check.txt";
        CheckView checkView = new CheckView(path);
        checkView.filePrint("Hello\nworld");
        StringBuffer stringBuffer = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String insert;
            while ((insert = reader.readLine()) != null) {
                stringBuffer.append(insert + '\n');
            }
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }
        assertEquals(stringBuffer.toString(), "Hello\nworld\n");

    }
}