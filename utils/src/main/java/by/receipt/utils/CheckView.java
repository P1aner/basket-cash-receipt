package by.receipt.utils;

import java.io.FileWriter;
import java.io.IOException;

public class CheckView {
    public void consolePrint(String string) {
        System.out.println(string);
    }

    public void filePrint(String string) {
        try (FileWriter fileWriter = new FileWriter("check.txt", false)) {
            fileWriter.write(string);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

}
