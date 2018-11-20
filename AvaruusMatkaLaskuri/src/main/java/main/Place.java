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
public class Place {

    private PSystem parent;
    private double altittude;
    
    public Place(PSystem parent, double radius) {
        this.parent = parent;
        this.altittude = radius;
    }
    
    public PSystem getParent() {
        return this.parent;
    }
    
    public double getAltitude() {
        return this.altittude;
    }
    
}
