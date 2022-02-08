package by.receipt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    public static List<String> readFromCsv(String path) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String insert;
            while ((insert = reader.readLine()) != null) {
                list.add(insert);
            }

        } catch (IOException e) {
            e.getStackTrace();
        }
        return list;
    }


}
