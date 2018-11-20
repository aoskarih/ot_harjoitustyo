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
public class PSystem {
    
    private double centerMass;
    private double radius;
    private double altitude;
    private ArrayList<PSystem> children;
    private PSystem parent;
    
    //Default system
    public PSystem(double mass, double radius, double altitude, ArrayList children, PSystem parent) {
        this.centerMass = mass;
        this.children = children;
        this.parent = parent;
        this.radius = radius;
        this.altitude = altitude;
    }
    
    // TODO
    // System confuguration with file
    public PSystem(String file) {
    }
    
    
    public double getMass() {
        return this.centerMass;
    }
    
    public double getRadius() {
        return this.radius;
    }
    
    public double getAltitude() {
        return this.altitude;
    }
}
