/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spacetravelcalc.calculating.PSystem;

/**
 *
 * @author hyarhyar
 */
public class PSystemTest {
    
    PSystem test1;
    PSystem test2;
    
    public PSystemTest() {
        test1 = new PSystem("test1", 1, 1, "Test System");
        test2 = new PSystem("test2", 1, 1, 1, test1);
    }

    @Before
    public void setUp() {
    }

    
    @Test
    public void GetSystemNameTop() {
        String n = test1.getSystemName();
        assertEquals("Test System", n);
    }
    
    @Test
    public void GetSystemNameBot() {
        String n = test2.getSystemName();
        assertEquals("Test System", n);
    }
    
    @Test
    public void GetSystemsTop() {
        ArrayList<PSystem> arr = test1.getSystems();
        ArrayList<PSystem> correct = new ArrayList<>();
        correct.add(test1);
        correct.add(test2);
        assertEquals(new HashSet<>(correct), new HashSet<>(arr));
    }
    
    @Test
    public void GetSystemsBot() {
        ArrayList<PSystem> arr = test2.getSystems();
        ArrayList<PSystem> correct = new ArrayList<>();
        correct.add(test1);
        correct.add(test2);
        assertEquals(new HashSet<>(correct), new HashSet<>(arr));
    }

    @Test
    public void GetSystemMap() {
        HashMap<String, PSystem> map = test1.getSystemMap();
        HashMap<String, PSystem> correct = new HashMap<>();
        correct.put("test1", test1);
        correct.put("test2", test2);
        assertEquals(correct, map);
    }
    
    @Test
    public void GetChildren() {
        ArrayList<PSystem> children = test1.getChildren();
        ArrayList<PSystem> correct = new ArrayList<>();
        correct.add(test2);
        assertEquals(correct, children);
    }
}
