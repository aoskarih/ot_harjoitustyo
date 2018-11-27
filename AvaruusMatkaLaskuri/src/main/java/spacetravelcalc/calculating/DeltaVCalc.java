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
public class DeltaVCalc {


    public static double fromAToB(Place a, Place b) {
        if (a.equals(b)) {
            return 0;
        }
        else if (a.getParent().equals(b.getParent())) {
            return sameParent(a, b);
        }
        else if (a.getParent().getParent().equals(b.getParent())) {
            return toParent(a, b);
        }
        else if (b.getParent().getParent().equals(a.getParent())) {
            return toChild(a, b);
        }
        return 0;
    }
    
    private static double sameParent(Place a, Place b) {
        double dv1 = Transfers.hohmann1(a.getRadius(), b.getRadius(), a.getParent().getMass());        
        double dv2 = Transfers.hohmann2(a.getRadius(), b.getRadius(), a.getParent().getMass());
        return dv1+dv2;
    }
    
    private static double toParent(Place a, Place b) {
        double dv_h = Transfers.hohmann1(a.getParent().getPathRadius(), b.getRadius(), b.getParent().getMass());
        double dv1 = Transfers.escape(a.getRadius(), a.getParent().getMass(), dv_h);
        double dv2 = Transfers.hohmann2(a.getParent().getPathRadius(), b.getRadius(), b.getParent().getMass());
        return dv1 + dv2;
    }
    
    private static double toChild(Place a, Place b) {
        double dv1 = Transfers.hohmann1(a.getRadius(), b.getParent().getPathRadius(), a.getParent().getMass());
        double dv_h = Transfers.hohmann2(a.getRadius(), b.getParent().getPathRadius(), a.getParent().getMass());
        double dv2 = Transfers.capture(b.getRadius(), b.getParent().getMass(), dv_h);
        return dv1 + dv2;
    }
    
    public static PSystem lowestCommonParent(Place a, Place b) {
        PSystem parent1 = a.getParent();
        PSystem parent2 = b.getParent();
        while (parent1.getPathRadius() != 0) {
            while (parent2.getPathRadius() != 0) {
                if(parent1.equals(parent2)) {
                    return parent1;
                }
                parent2 = parent2.getParent();
            }
            parent1 = parent1.getParent();
            parent2 = b.getParent();
        }
        return parent1;
    }
    
    
    
}
