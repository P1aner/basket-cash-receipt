package by.receipt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
public class CheckView {
    private static final Logger log = LoggerFactory.getLogger(CheckView.class);

    public void consolePrint(String string) {
        System.out.println(string);
    }

    public void filePrint(String string) {
        try (FileWriter fileWriter = new FileWriter("check.txt", false)) {
            fileWriter.write(string);
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }
    }

}
