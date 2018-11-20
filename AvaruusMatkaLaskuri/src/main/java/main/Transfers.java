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
public class Transfers {
    
    private static double G = 6.67408e-11;
    
    public static double hohmann(double r1, double r2, double mass) {
        double dv1 = Math.sqrt(G*mass/r1) * (Math.sqrt((2 * r2) / (r1 + r2)) - 1);
        double dv2 = Math.sqrt(G*mass/r2) * (1 - Math.sqrt((2 * r1) / (r1 + r2)));
        System.out.println("Acceleration 1: "+Double.toString(dv1));
        System.out.println("Acceleration 2: "+Double.toString(dv2));
        return Math.abs(dv1 + dv2);
    }
    
    
    
}
