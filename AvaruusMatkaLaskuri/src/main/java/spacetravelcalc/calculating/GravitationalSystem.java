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
 * Luokka määrittelee systeemin keskuskappaleen, joka voi
 * kiertää toista kappaletta ja jolla voi olla kiertolaisia.
 * 
 * @author hyarhyar
 */
public class GravitationalSystem implements Comparable<GravitationalSystem> {
    
    private double centerMass;
    private double radius;
    private double pathRadius;
    private ArrayList<GravitationalSystem> children = new ArrayList<>();
    private GravitationalSystem parent;
    private String name;
    private boolean topLevel;
    private String systemName = "";

    /**
     * Määrittelee kappaleen/systeemin, joka kiertää toista kappaletta
     * 
     * @param name          kappaleen nimi
     * @param mass          kappaleen massa
     * @param radius        kappaleen säde
     * @param altitude      kappaleen kiertoradan säde
     * @param parent        systeemi johon kappale kuuluu
     */
    public GravitationalSystem(String name, double mass, double radius, double altitude, GravitationalSystem parent) {
        this.centerMass = mass;
        this.radius = radius;
        this.name = name;
        
        parent.addChildren(this);
        this.parent = parent;
        this.pathRadius = parent.getRadius() + altitude;
        
        this.topLevel = false;
    }

    /**
     * Määrittelee ylimmällä tasolla olevan kappaleen/systeemin, jolla ei ole vanhempia
     * 
     * @param name          tämän kappaleen nimi
     * @param mass          kappaleen massa
     * @param radius        kappaleen säde
     * @param systemName    yleisen systeemin nimi
     */
    public GravitationalSystem(String name, double mass, double radius, String systemName) {
        this.centerMass = mass;
        this.radius = radius;
        this.name = name;
        this.parent = null;
        this.pathRadius = 0;
        this.topLevel = true;
        this.systemName = systemName;
    }
    
    /**
     * Metodi lisää annetun systeemin tähän systeemiin.
     * @param child
     */
    public void addChildren(GravitationalSystem child) {
        children.add(child);
    }
    
    public GravitationalSystem getParent() {
        return this.parent;
    }
    
    /**
     * Metodi palauttaa tämän systeemin lapset.
     * 
     * @return tämän systeemin lapset
     */
    public ArrayList<GravitationalSystem> getChildren() {
        return this.children;
    }
        
    /**
     * Metodi palauttaa kaikki systeemin alla olevat systeemit, eli
     * kaikki lapset, lapsien lapset, jne.
     * 
     * @return kaikki alisysteemit
     */
    public ArrayList<GravitationalSystem> getAllChildren() {
        ArrayList<GravitationalSystem> systems = new ArrayList<>();
        ArrayList<GravitationalSystem> children = this.children;
        if (children.isEmpty()) {
            return systems;
        }      
        Collections.sort(children);
        for (GravitationalSystem p : children) {
            systems.add(p);
            ArrayList<GravitationalSystem> childrenOfChildren = p.getAllChildren();
            Collections.sort(childrenOfChildren);
            systems.addAll(childrenOfChildren);
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
    
    /**
     * Metodi palauttaa yleisen systeemin nimen, johon tämä systeemi ja kaikki sen vanhemmat kuuluu.
     * @return yleisen systeemin nimi
     */
    public String getSystemName() {
        if (topLevel) {
            return systemName;
        } else {
            return parent.getSystemName();
        }
    }
    
    /**
     * Metodi palauttaa lukuna monennessako "kerroksessa" systeemi on.
     * Jos aurinko on päällimmäinen kerros, niin maa palauttaa 1 ja kuu 2.
     * 
     * @return systeemin kerros
     */
    public int layersDeep() {
        GravitationalSystem s = this;
        int layer = 0;
        while (!s.topLevel) {
            layer++;
            s = s.getParent();
        }
        return layer;
    }
    
    public HashMap<String, GravitationalSystem> getSystemMap() {
        HashMap<String, GravitationalSystem> sys = new HashMap<>();
        for (GravitationalSystem p : getSystems()) {
            sys.put(p.getName(), p);
        }
        return sys;
    }
    
    /**
     * Metodi palauttaa kaikki yleiseen systeemiin kuuluvat systeemit, eli tämän systeemin kaikki lapset ja vanhemmat sekä kaikkien vanhempien lapset.
     * @return kaikki tunnetut systeemit
     */
    public ArrayList<GravitationalSystem> getSystems() {
        if (topLevel) {
            ArrayList<GravitationalSystem> systems = new ArrayList<>();
            systems.add(this);
            systems.addAll(getAllChildren());
            return systems;
        } else {
            return parent.getSystems();
        }
    }

    @Override
    public String toString() {
        return name + " " + Double.toString(centerMass) + " " + Double.toString(radius) + " " + getSystemName();
    }
    
    @Override
    public int compareTo(GravitationalSystem o) {
        return Double.compare(this.getPathRadius(), o.getPathRadius());
    }
}
