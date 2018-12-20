/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetravelcalc.calculating;

/**
 *
 * @author hyarhyar
 */
public class TravelTimeCalc {
    
    public static double timeFromAToB(Place a, Place b) {
        
        GravitationalSystem commonParent = DeltaVCalc.lowestCommonParent(a, b);
        
        double r1 = altitudeToParent(commonParent, a);
        double r2 = altitudeToParent(commonParent, b);
        double t = PhysicsEquations.kepler3rd(r1, r2, commonParent.getMass()) / 2;
        System.out.println(t);
        return t;
    }
    
    public static double altitudeToParent(GravitationalSystem parent, Place p) {
        if (p.getParent().toString().equals(parent.toString())) {
            return p.getRadius();
        } else {
            GravitationalSystem current = p.getParent();
            while (!current.getParent().toString().equals(parent.toString())) {
                current = current.getParent();
            }
            return current.getPathRadius(); 
        }
    }
    
}
