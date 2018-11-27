/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import main.DeltaVCalc;
import main.PSystem;
import main.Place;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hyarhyar
 */
public class BasicTransfersTest {
    
    PSystem earth;
    Place lowOrbit;
    Place geoOrbit;
    
    @Before
    public void setUp() {
        earth = new PSystem(5.97e24, 6.371e6, 0, null);
        lowOrbit = new Place(earth, 250e3);
        geoOrbit = new Place(earth, 35786e3);
    }

    @Test
    public void hahmannToHigherOrbit() {
        double dv = DeltaVCalc.fromAToB(lowOrbit, geoOrbit);
        assertEquals((int) dv, 3914);
    }
    
    @Test
    public void hahmannToLowerOrbit() {
        double dv = DeltaVCalc.fromAToB(geoOrbit, lowOrbit);
        assertEquals((int) dv, 3914);
    }
    
    @Test
    public void hahmannToSameOrbit() {
        double dv = DeltaVCalc.fromAToB(geoOrbit, geoOrbit);
        assertEquals((int) dv, 0);
    }
}
