/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetravelcalc.operating;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import spacetravelcalc.calculating.PSystem;

/**
 *
 * @author hyarhyar
 */
public class SystemFileReader {

    
    public static PSystem getDefaultSystem() {
        PSystem maa = new PSystem("Maa", 5.97e24, 6.37e6, "Maa-kuu");
        PSystem kuu = new PSystem("Kuu", 7.34e22, 1.73e6, 3.84e8, maa);
        return maa;
    }
    
    public static PSystem getNewSystem(File file) throws IOException {
        PSystem topLevel = new PSystem("", 0, 0, "");
        String[] lines = fileData(file).toArray(new String[0]);
        boolean valid = false;
        for (String l : lines) {
            String[] data = l.split(";");
            if (data[0].equals("t")) {
                topLevel = new PSystem(data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]), data[4]);
            } else if (data[0].equals("c")) {
                PSystem par = topLevel.getSystemMap().get(data[5]);
                PSystem chi = new PSystem(data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]), Double.parseDouble(data[4]), par);
            }
        }
        return topLevel;
    }
    
    private static ArrayList<String> fileData(File file) throws FileNotFoundException, IOException {
        ArrayList<String> data = new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            data.add(line);
        }
        fileReader.close();
        return data;
    }
    
}
