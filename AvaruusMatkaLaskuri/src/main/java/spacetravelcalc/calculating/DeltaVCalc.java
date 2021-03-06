/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetravelcalc.calculating;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Luokka laskee tarvittavan DV:n kaikkiin siirtymiin paikasta a paikkaan b.
 * @author hyarhyar
 */
public class DeltaVCalc {

    /**
     * Metodi laskee tarvittavan DV:n mihin tahansa siirtymään ja palauttaa sen
     * 
     * @param a     Lähtö paikka
     * @param b     Kohde paikka
     * @return      Siirtymän vaatima DV
     */
    public static double fromAToB(Place a, Place b) {
        
        GravitationalSystem commonParent = lowestCommonParent(a, b);
        
        ArrayList<GravitationalSystem> path1 = pathToParent(a.getParent(), commonParent);
        ArrayList<GravitationalSystem> path2 = pathToParent(b.getParent(), commonParent);
        
        double dv1;
        double dv2;
        
        double r1;
        double r2;
        
        if (path1.isEmpty()) {
            r1 = a.getRadius();
        } else {
            r1 = path1.get(0).getPathRadius();
        }
        
        if (path2.isEmpty()) {
            r2 = b.getRadius();
        } else {
            r2 = path2.get(0).getPathRadius();
        }
        
        if (a.equals(b)) {
            return 0;
        } else if (a.getParent().equals(b.getParent())) {
            return sameParent(a, b);
        } else if (b.getParent().equals(commonParent)) {            
            dv1 = PhysicsEquations.hohmann1(r1, r2, commonParent.getMass());
            dv1 = pathEscape(path1, a, dv1);
            dv2 = PhysicsEquations.hohmann2(r1, r2, commonParent.getMass());
            return dv1 + dv2;
        } else if (a.getParent().equals(commonParent)) {
            dv2 = PhysicsEquations.hohmann1(r2, r1, commonParent.getMass());
            dv2 = pathEscape(path2, b, dv2);
            dv1 = PhysicsEquations.hohmann2(r2, r1, commonParent.getMass());
            return dv1 + dv2;
        } else {
            dv1 = PhysicsEquations.hohmann1(r1, r2, commonParent.getMass());
            dv2 = PhysicsEquations.hohmann2(r2, r1, commonParent.getMass());
            dv1 = pathEscape(path1, a, dv1);
            dv2 = pathEscape(path1, b, dv2);
            return dv1 + dv2;
        }
    }
    
    private static double pathEscape(ArrayList<GravitationalSystem> path, Place p, double v0) {
        double dv = v0;
        for (int i = 1; i < path.size(); i++) {
            dv = PhysicsEquations.escape(path.get(i).getPathRadius(), path.get(i - 1).getMass(), dv);
        }
        dv = PhysicsEquations.escape(p.getRadius(), path.get(path.size() - 1).getMass(), dv);
        return dv;
    }
    
    private static double sameParent(Place a, Place b) {
        double dv1 = PhysicsEquations.hohmann1(a.getRadius(), b.getRadius(), a.getParent().getMass());        
        double dv2 = PhysicsEquations.hohmann2(a.getRadius(), b.getRadius(), a.getParent().getMass());
        return dv1 + dv2;
    }
    
    private static ArrayList<GravitationalSystem> pathToParent(GravitationalSystem a, GravitationalSystem b) {
        ArrayList<GravitationalSystem> systems = new ArrayList<>();
        GravitationalSystem sys = a;
        while (sys != b) {
            systems.add(sys);
            sys = sys.getParent();
        }
        Collections.reverse(systems);
        return systems;
    }
    
    public static GravitationalSystem lowestCommonParent(Place a, Place b) {
        GravitationalSystem parent1 = a.getParent();
        GravitationalSystem parent2 = b.getParent();
        while (!parent1.getTopLevel()) {
            while (!parent2.getTopLevel()) {
                if (parent1.equals(parent2)) {
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
