/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetravelcalc.calculating;

/**
 * Luokka määrittää paikan systeemissö.
 * @author hyarhyar
 */
public class Place {

    private GravitationalSystem parent;
    private double radius;
    
    public Place(GravitationalSystem parent, double altitude) {
        this.parent = parent;
        this.radius = parent.getRadius() + altitude;
    }
    
    public GravitationalSystem getParent() {
        return this.parent;
    }
    
    public double getRadius() {
        return this.radius;
    }
    
}
