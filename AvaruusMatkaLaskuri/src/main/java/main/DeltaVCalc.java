/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author hyarhyar
 */
public class DeltaVCalc {

    private PSystem sys;
    
    public DeltaVCalc(PSystem sys) {
        this.sys = sys;
    }

    public static double fromAToB(Place a, Place b) {
        if (a.getParent().equals(b.getParent())) {
            return sameParent(a, b);
        }
        return 0;
    }
    
    private static double sameParent(Place a, Place b) {
        double dv = Transfers.hohmann(a.getAltitude(), b.getAltitude(), a.getParent().getMass());        
        return dv;
    }
    
}
