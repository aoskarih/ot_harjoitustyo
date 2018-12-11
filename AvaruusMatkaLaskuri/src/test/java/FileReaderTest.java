/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spacetravelcalc.calculating.GravitationalSystem;
import spacetravelcalc.operating.SystemFileReader;

/**
 *
 * @author hyarhyar
 */
public class FileReaderTest {
    
    ArrayList<String> data;
    GravitationalSystem aurinko;
    GravitationalSystem maa;
    GravitationalSystem kuu;
    
    public FileReaderTest() {
    }
    
    @Before
    public void setup() {
        data = new ArrayList<>();
        data.add("t;Aurinko;1.986e30;696e6;Aurinkokunta");
        data.add("c;Maa;5.97e24;6.378e6;149e9;Aurinko");
        data.add("c;Kuu;7.35e22;1.737e6;384e6;Maa");
        
        aurinko = new GravitationalSystem("Aurinko", 1.986e30, 696e6, "Aurinkokunta");
        maa = new GravitationalSystem("Maa", 5.97e24, 6.378e6, 149e9, aurinko);
        kuu = new GravitationalSystem("Kuu", 7.35e22, 1.737e6, 384e6, maa);
    }
    
    @Test
    public void testDecodeTop() {
        assertEquals(aurinko.toString(), SystemFileReader.decode(data).toString());
    }
    
    @Test
    public void testDecodeChildren() {
        GravitationalSystem a = SystemFileReader.decode(data);
        ArrayList<GravitationalSystem> childList = aurinko.getChildren();
        assertEquals(childList.toString(), a.getChildren().toString());
    }
    
    @Test
    public void testDecodeAllChildren() {
        GravitationalSystem a = SystemFileReader.decode(data);
        ArrayList<GravitationalSystem> childList = aurinko.getAllChildren();
        assertEquals(childList.toString(), a.getAllChildren().toString());
    }

}
