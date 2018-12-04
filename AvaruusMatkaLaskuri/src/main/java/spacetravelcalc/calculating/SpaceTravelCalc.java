/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetravelcalc.calculating;

import spacetravelcalc.ui.MainWindow;
import javafx.application.Application;
/**
 *
 * @author hyarhyar
 */
public class SpaceTravelCalc {
    
    public static void main(String[] args) {
        
        PSystem sun = new PSystem("Aurinko", 1.986e30, 696e6, "Aurinkokunta");
        PSystem earth = new PSystem("Maa", 5.97e24, 6.378e6, 149e9, sun);
        PSystem moon = new PSystem("Kuu", 7.35e22, 1.737e6, 384e6, earth);
        PSystem jupiter = new PSystem("Jupiter", 1.9e27, 70e6, 778e9, sun);
        PSystem io = new PSystem("Io", 8.94e22, 1.815e6, 421e6, jupiter);
        PSystem europa = new PSystem("Europa", 4.8e22, 1.57e6, 670e6, jupiter);
        PSystem mars = new PSystem("Mars", 6.4e23, 3.4e6, 227e9, sun);
        PSystem deimos = new PSystem("Deimos", 1.5e15, 6.2e3, 23.4e6, mars);
        
        double maageo = DeltaVCalc.fromAToB(new Place(earth, 250e3), new Place(earth, 35786e3));
        double maakuu = DeltaVCalc.fromAToB(new Place(earth, 250e3), new Place(moon, 100e3));
        double kuumaa = DeltaVCalc.fromAToB(new Place(moon, 100e3), new Place(earth, 250e3));
        double iomoon = DeltaVCalc.fromAToB(new Place(deimos, 1e3), new Place(moon, 100e3));
        
        Application.launch(MainWindow.class);
        
        System.out.println((int) maageo);
        System.out.println((int) maakuu);
        System.out.println((int) kuumaa);
        System.out.println((int) iomoon);
    }    
}
