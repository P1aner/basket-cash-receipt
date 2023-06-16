package by.receipt.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CheckView {
    private static final Logger log = LoggerFactory.getLogger(CheckView.class);
    @Value("${app.filePath}")
    private String path;


    public void consolePrint(String string) {
        System.out.print(string);
    }

    public void filePrint(String string) {
        try (FileWriter fileWriter = new FileWriter(path, false)) {
            fileWriter.write(string);
        } catch (IOException e) {
            log.error(String.valueOf(e));
        }
    }

}
