package ru.bluewhale.fgis.mapper;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;

@Component
public class SimpleWriter {

    private String filePath;
    private PrintWriter printWriter;

    public SimpleWriter() {
        filePath = "src/main/resources/fgisJson_" + System.currentTimeMillis()+ ".json";
    }

    public SimpleWriter(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String s) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.exit(-1);
    }
}
