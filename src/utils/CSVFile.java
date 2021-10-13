package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVFile {
    public static List<String> readFile(String filePath) throws IOException {
        List<String> records = new ArrayList<>();
        try( BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line);
            }
        }
        return records;
    }

    public static void writeFile(String filePath, List<String> records) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        FileWriter writer = new FileWriter(filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        for (int i = 0; i < records.size(); i++) {
            if (i != 0) {
                bufferedWriter.write("\n");
            }
            bufferedWriter.write(records.get(i));
        }
        bufferedWriter.close();
    }
}
