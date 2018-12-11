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
import spacetravelcalc.calculating.GravitationalSystem;

/**
 * Luokka hoitaa systeemien oletus systeemien ja tiedostosta haettavien systeemien määrittämisen.
 * @author hyarhyar
 */
public class SystemFileReader {

    /**
     * Metodi määrittää oletus systeemin
     * @return oletus systeemi
     */
    public static GravitationalSystem getDefaultSystem() {
        GravitationalSystem maa = new GravitationalSystem("Maa", 5.97e24, 6.37e6, "Maa-kuu");
        GravitationalSystem kuu = new GravitationalSystem("Kuu", 7.34e22, 1.73e6, 3.84e8, maa);        
        return maa;
    }
    
    /**
     * Metodi ottaa tiedoston ja määrittää sen pohjalta uuden systeemin, jonka se palauttaa.
     * @param file          systeemi tiedosto
     * @return              uusi systeemi
     * @throws IOException
     */
    public static GravitationalSystem getNewSystem(File file) throws IOException {
        ArrayList<String> data = fileData(file);
        return decode(data);
    }
    
    public static GravitationalSystem decode(ArrayList<String> data) {
        GravitationalSystem topLevel = new GravitationalSystem("", 0, 0, "");
        String[] lines = data.toArray(new String[0]);
        boolean valid = false;
        for (String l : lines) {
            String[] dataArray = l.split(";");
            if (dataArray[0].equals("t")) {
                topLevel = new GravitationalSystem(dataArray[1], Double.parseDouble(dataArray[2]), Double.parseDouble(dataArray[3]), dataArray[4]);
            } else if (dataArray[0].equals("c")) {
                GravitationalSystem par = topLevel.getSystemMap().get(dataArray[5]);
                GravitationalSystem chi = new GravitationalSystem(dataArray[1], Double.parseDouble(dataArray[2]), Double.parseDouble(dataArray[3]), Double.parseDouble(dataArray[4]), par);
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
