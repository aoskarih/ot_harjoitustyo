/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author hyarhyar
 */
public class SpaceTravelCalc {
    
    public static void main(String[] args) {
        
        PSystem earth = new PSystem(5.97e24, 6.371e6, 0, new ArrayList(), null);
        double dv = DeltaVCalc.fromAToB(new Place(earth, earth.getRadius()+250e3), new Place(earth, earth.getRadius()+35786e3));
        System.out.println((int) dv);
    }    
}
