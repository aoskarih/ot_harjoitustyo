/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import spacetravelcalc.calculating.DeltaVCalc;
import spacetravelcalc.calculating.GravitationalSystem;
import spacetravelcalc.calculating.Place;
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
public class DeltaVCalcTest {
    
    GravitationalSystem sun;
    GravitationalSystem earth;
    GravitationalSystem moon;
    GravitationalSystem jupiter;
    GravitationalSystem io;
    GravitationalSystem europa;
    
    Place lowOrbit;
    Place geoOrbit;
    
    @Before
    public void setUp() {
        sun = new GravitationalSystem("Aurinko", 1.986e30, 696e6, "Aurinkokunta");
        earth = new GravitationalSystem("Maa", 5.97e24, 6.378e6, 149e9, sun);
        moon = new GravitationalSystem("Kuu", 7.35e22, 1.737e6, 384e6, earth);
        jupiter = new GravitationalSystem("Jupiter", 1.9e27, 71e6, 778e9, sun);
        io = new GravitationalSystem("Io", 8.94e22, 1.815e6, 421e6, jupiter);
        europa = new GravitationalSystem("Europa", 4.8e22, 1.57e6, 670e6, jupiter);
        
        lowOrbit = new Place(earth, 250e3);
        geoOrbit = new Place(earth, 35786e3);
    }
    
    @Test
    public void hahmannToHigherOrbit() {
        double dv = DeltaVCalc.fromAToB(lowOrbit, geoOrbit)/100;
        assertEquals((int) dv*100, 3900);
    }
    
    @Test
    public void hahmannToLowerOrbit() {
        double dv = DeltaVCalc.fromAToB(geoOrbit, lowOrbit)/100;
        assertEquals((int) dv*100, 3900);
    }
    
    @Test
    public void hahmannToSameOrbit() {
        double dv = DeltaVCalc.fromAToB(geoOrbit, geoOrbit);
        assertEquals((int) dv, 0);
    }
    
    @Test
    public void commomParentSame() {
         GravitationalSystem parent = DeltaVCalc.lowestCommonParent(new Place(earth, 0), new Place(earth, 0));
         assertEquals(parent, earth);
    }

    @Test
    public void commomParentTopLevel() {
         GravitationalSystem parent = DeltaVCalc.lowestCommonParent(new Place(earth, 0), new Place(jupiter, 0));
         assertEquals(parent, sun);
    }
    
    @Test
    public void commomParentDeeper() {
         GravitationalSystem parent = DeltaVCalc.lowestCommonParent(new Place(moon, 0), new Place(io, 0));
         assertEquals(parent, sun);
    }
    
    @Test
    public void commomParentDifferentLayer() {
         GravitationalSystem parent = DeltaVCalc.lowestCommonParent(new Place(earth, 0), new Place(io, 0));
         assertEquals(parent, sun);
    }
    
    @Test
    public void commomParentSameNotTopLeve() {
         GravitationalSystem parent = DeltaVCalc.lowestCommonParent(new Place(io, 0), new Place(europa, 0));
         assertEquals(parent, jupiter);
    }
    
    @Test
    public void toParent() {
        double dv = DeltaVCalc.fromAToB(new Place(earth, 250e3), new Place(moon, 100e3))/100;
        assertEquals(3900, (int) dv*100);
    }
    
    @Test
    public void toChild() {
        double dv = DeltaVCalc.fromAToB(new Place(moon, 100e3), new Place(earth, 250e3))/100;
        assertEquals(3900, (int) dv*100);
    }
    
    @Test
    public void multipleLayers() {
        double dv = DeltaVCalc.fromAToB(new Place(moon, 100e3), new Place(io, 100e3))/100;
        assertEquals(13100, (int) dv*100);
    }
}
