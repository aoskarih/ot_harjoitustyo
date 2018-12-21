/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetravelcalc.calculating;

/**
 * Luokka matka-ajan laskemiseen
 * @author hyarhyar
 */
public class TravelTimeCalc {
    
    /**
     * Metodi laskee matka-ajan ja palauttaa sen.
     * @param a
     * @param b
     * @return matka-aika
     */
    public static double timeFromAToB(Place a, Place b) {
        
        GravitationalSystem commonParent = DeltaVCalc.lowestCommonParent(a, b);
        
        double r1 = altitudeToParent(commonParent, a);
        double r2 = altitudeToParent(commonParent, b);
        double t = PhysicsEquations.kepler3rd(r1, r2, commonParent.getMass()) / 2;
        return t;
    }
    
    /**
     * Metodi palauttaa paikan p etäisyyden joko sen vanhempaan tai mihin tahansa vielä ylemmän tason vanhempaan.
     * @param parent
     * @param p
     * @return etäisyys
     */
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
