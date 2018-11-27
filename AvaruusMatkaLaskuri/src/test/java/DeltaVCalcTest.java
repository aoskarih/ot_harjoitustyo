/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import spacetravelcalc.calculating.DeltaVCalc;
import spacetravelcalc.calculating.PSystem;
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
    
    PSystem sun;
    PSystem earth;
    PSystem moon;
    PSystem jupiter;
    PSystem io;
    PSystem europa;
    
    @Before
    public void setUp() {
        sun = new PSystem(1.986e30, 696e6, 0, null);
        earth = new PSystem(5.97e24, 6.378e6, 149e9, sun);
        moon = new PSystem(7.35e22, 1.737e6, 384e6, earth);
        jupiter = new PSystem(1.9e27, 71e6, 778e9, sun);
        io = new PSystem(8.94e22, 1.815e6, 421e6, jupiter);
        europa = new PSystem(4.8e22, 1.57e6, 670e6, jupiter);
    }
    
    @Test
    public void commomParentSame() {
         PSystem parent = DeltaVCalc.lowestCommonParent(new Place(earth, 0), new Place(earth, 0));
         assertEquals(parent, earth);
    }

    @Test
    public void commomParentTopLevel() {
         PSystem parent = DeltaVCalc.lowestCommonParent(new Place(earth, 0), new Place(jupiter, 0));
         assertEquals(parent, sun);
    }
    
    @Test
    public void commomParentDeeper() {
         PSystem parent = DeltaVCalc.lowestCommonParent(new Place(moon, 0), new Place(io, 0));
         assertEquals(parent, sun);
    }
    
    @Test
    public void commomParentDifferentLayer() {
         PSystem parent = DeltaVCalc.lowestCommonParent(new Place(earth, 0), new Place(io, 0));
         assertEquals(parent, sun);
    }
    
    @Test
    public void commomParentSameNotTopLeve() {
         PSystem parent = DeltaVCalc.lowestCommonParent(new Place(io, 0), new Place(europa, 0));
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
}
