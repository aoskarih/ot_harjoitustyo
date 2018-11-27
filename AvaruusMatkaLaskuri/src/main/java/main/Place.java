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
    private double radius;
    
    public Place(PSystem parent, double altitude) {
        this.parent = parent;
        this.radius = parent.getRadius()+altitude;
    }
    
    public PSystem getParent() {
        return this.parent;
    }
    
    public double getRadius() {
        return this.radius;
    }
    
}
