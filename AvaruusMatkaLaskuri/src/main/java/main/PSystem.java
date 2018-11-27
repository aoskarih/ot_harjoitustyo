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
    private double pathRadius;
    private ArrayList<PSystem> children = new ArrayList<>();
    private PSystem parent;
    
    //Default system
    public PSystem(double mass, double radius, double altitude, PSystem parent) {
        this.centerMass = mass;
        this.radius = radius;
        
        if (parent != null) {
            parent.addChildren(this);
            this.parent = parent;
            this.pathRadius = parent.getRadius()+altitude;
        }
        else {
            this.parent = new PSystem();
            this.pathRadius = 0;
        }
    }

    //blank system to be top level systems parent
    public PSystem() {
    }
    
    // TODO
    // System confuguration with file
    public PSystem(String file) {
    }
    
    public void addChildren(PSystem child) {
        children.add(child);
    }
    
    public void addChildren(ArrayList<PSystem> child) {
        for(PSystem c : child) {
            this.children.add(c);
        }
    }
    
    public PSystem getParent() {
        return this.parent;
    }
    
    public ArrayList getChildren() {
        return this.children;
    }
    
    public double getMass() {
        return this.centerMass;
    }
    
    public double getRadius() {
        return this.radius;
    }
    
    public double getPathRadius() {
        return this.pathRadius;
    }
}
