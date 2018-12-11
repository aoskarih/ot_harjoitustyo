/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import spacetravelcalc.calculating.GravitationalSystem;

/**
 *
 * @author hyarhyar
 */
public class GSystemTest {
    
    GravitationalSystem test1;
    GravitationalSystem test2;
    GravitationalSystem test3;

    
    public GSystemTest() {
        test1 = new GravitationalSystem("test1", 1, 1, "Test System");
        test2 = new GravitationalSystem("test2", 1, 1, 1, test1);
        test3 = new GravitationalSystem("test3", 1, 1, 2, test1);
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
        ArrayList<GravitationalSystem> arr = test1.getSystems();
        ArrayList<GravitationalSystem> correct = new ArrayList<>();
        correct.add(test1);
        correct.add(test2);
        correct.add(test3);
        assertEquals(new HashSet<>(correct), new HashSet<>(arr));
    }
    
    @Test
    public void GetSystemsBot() {
        ArrayList<GravitationalSystem> arr = test2.getSystems();
        ArrayList<GravitationalSystem> correct = new ArrayList<>();
        correct.add(test1);
        correct.add(test2);
        correct.add(test3);
        assertEquals(new HashSet<>(correct), new HashSet<>(arr));
    }

    @Test
    public void GetSystemMap() {
        HashMap<String, GravitationalSystem> map = test1.getSystemMap();
        HashMap<String, GravitationalSystem> correct = new HashMap<>();
        correct.put("test1", test1);
        correct.put("test2", test2);
        correct.put("test3", test3);
        assertEquals(correct, map);
    }
    
    @Test
    public void GetChildren() {
        ArrayList<GravitationalSystem> children = test1.getChildren();
        ArrayList<GravitationalSystem> correct = new ArrayList<>();
        correct.add(test2);
        correct.add(test3);
        assertEquals(correct, children);
    }
    
    @Test
    public void compareTo() {
        ArrayList<GravitationalSystem> sorting1 = new ArrayList<>();
        sorting1.add(test3);
        sorting1.add(test2);
        ArrayList<GravitationalSystem> sorting2 = new ArrayList<>();
        sorting2.add(test2);
        sorting2.add(test3);
        Collections.sort(sorting1);
        Collections.sort(sorting2);
        assertEquals(sorting1, sorting2);
    }
    
    @Test
    public void layersTop() {
        int l = test1.layersDeep();
        assertEquals(0, l);
    }
    
    @Test
    public void layersBot() {
        int l = test2.layersDeep();
        assertEquals(1, l);
    }
    
}
