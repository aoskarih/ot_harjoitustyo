/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;
import spacetravelcalc.calculating.GravitationalSystem;
import spacetravelcalc.calculating.Place;
import spacetravelcalc.calculating.TravelTimeCalc;

/**
 *
 * @author hyarhyar
 */
public class TravelTimeTest {
    
    GravitationalSystem earth;
    GravitationalSystem moon;
    GravitationalSystem moonOfMoon;
    Place a;
    Place b;
    
    public TravelTimeTest() {
        earth = new GravitationalSystem("Maa", 5.97e24, 6.378e6, "Maa-Kuu");
        moon = new GravitationalSystem("Kuu", 7.35e22, 1.737e6, 384e6, earth);
        moonOfMoon = new GravitationalSystem("KuunKuu", 7.35e15, 1.737e4, 3e6, moon);
        a = new Place(earth, 250000);
        b = new Place(moonOfMoon, 10000);
    }

    
    @Test
    public void altitudeTestA() {
        double r = TravelTimeCalc.altitudeToParent(earth, a);
        assertEquals(6628000, (int) r);
    }
    
    @Test
    public void altitudeTestB() {
        double r = TravelTimeCalc.altitudeToParent(earth, b);
        assertEquals(390378000, (int) r);
    }
    
    @Test
    public void timeTest() {
        double t = TravelTimeCalc.timeFromAToB(a, b);
        assertEquals(440167, (int) t);
    }
    
    
}
