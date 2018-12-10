/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetravelcalc.calculating;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author hyarhyar
 */
public class PSystem implements Comparable<PSystem> {
    
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
        this.pathRadius = parent.getRadius() + altitude;
        
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
        for (PSystem c : child) {
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
        ArrayList<PSystem> chi = this.children;
        if (chi.isEmpty()) {
            return systems;
        }      
        Collections.sort(chi);
        for (PSystem p : chi) {
            systems.add(p);
            ArrayList<PSystem> chichi = p.getAllChildren();
            Collections.sort(chichi);
            systems.addAll(chichi);
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
        if (topLevel) {
            return systemName;
        } else {
            return parent.getSystemName();
        }
    }
    
    public int layersDeep() {
        PSystem s = this;
        int layer = 0;
        while (!s.topLevel) {
            layer++;
            s = s.getParent();
        }
        return layer;
    }
    
    public HashMap<String, PSystem> getSystemMap() {
        HashMap<String, PSystem> sys = new HashMap<>();
        for (PSystem p : getSystems()) {
            sys.put(p.getName(), p);
        }
        return sys;
    }
    
    public ArrayList<PSystem> getSystems() {
        if (topLevel) {
            ArrayList<PSystem> systems = new ArrayList<>();
            systems.add(this);
            systems.addAll(getAllChildren());
            return systems;
        } else {
            return parent.getSystems();
        }
    }

    @Override
    public int compareTo(PSystem o) {
        return Double.compare(this.getPathRadius(), o.getPathRadius());
    }
}
