/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetravelcalc.calculating;

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
    private String name;
    private boolean topLevel;
    private String systemName = "";
    
    //Normal system
    public PSystem(String name, double mass, double radius, double altitude, PSystem parent) {
        this.centerMass = mass;
        this.radius = radius;
        this.name = name;
        
        parent.addChildren(this);
        this.parent = parent;
        this.pathRadius = parent.getRadius()+altitude;
        
        this.topLevel = false;
    }
    
    //Top level system
    public PSystem(String name, double mass, double radius, String systemName) {
        this.centerMass = mass;
        this.radius = radius;
        this.name = name;
        this.parent = null;
        this.pathRadius = 0;
        this.topLevel = true;
        this.systemName = systemName;
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
    
    public ArrayList<PSystem> getChildren() {
        return this.children;
    }
    
    public ArrayList<PSystem> getAllChildren() {
        ArrayList<PSystem> systems = new ArrayList<>();
        if(this.children.isEmpty()) {
            return systems;
        }            
        for(PSystem p : this.children) {
            systems.addAll(p.getAllChildren());
            systems.add(p);
        }
        return systems;
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
    
    public boolean getTopLevel() {
        return this.topLevel;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getSystemName() {
        if(topLevel) {
            return systemName;
        } else {
            return parent.getSystemName();
        }
    }
    
    public ArrayList<PSystem> getSystems() {
        if(topLevel) {
            ArrayList<PSystem> systems = getAllChildren();
            systems.add(this);
            return systems;
        } else {
            return parent.getAllChildren();
        }
    }
}
