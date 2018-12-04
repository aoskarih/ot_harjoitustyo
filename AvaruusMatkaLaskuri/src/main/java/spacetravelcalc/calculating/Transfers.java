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
public class Transfers {
    
    private static double G = 6.67408e-11;
    
    //Hohmann transfer from r1 to r2
    public static double hohmann1(double r1, double r2, double mass) {
        double dv1 = Math.sqrt(G * mass / r1) * (Math.sqrt((2 * r2) / (r1 + r2)) - 1);
        return Math.abs(dv1);
    }
    
    public static double hohmann2(double r1, double r2, double mass) {
        double dv2 = Math.sqrt(G * mass / r2) * (1 - Math.sqrt((2 * r1) / (r1 + r2)));
        return Math.abs(dv2);
    }
    
    //Escape parent with relative speed v to parent
    public static double escape(double r, double mass, double v) {
        return Math.sqrt(Math.pow(v, 2) + 2 * (mass * G / r)) - Math.sqrt(mass * G / r);
    }
    
    //Capture dv when arriving to child
    public static double capture(double r, double mass, double v) {
        return Math.sqrt(Math.pow(v, 2) + 2 * (mass * G / r)) - Math.sqrt(mass * G / r);
    }
    
}
