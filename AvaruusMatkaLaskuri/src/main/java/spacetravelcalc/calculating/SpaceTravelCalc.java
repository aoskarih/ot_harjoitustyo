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
        
        PSystem earth = new PSystem("Maa", 5.97e24, 6.371e6, "Maa-kuu");
        PSystem moon = new PSystem("Kuu", 7.34e22, 1.738e6, 3.84e8, earth);
        
        //double maageo = DeltaVCalc.fromAToB(new Place(earth, 250e3), new Place(earth, 35786e3));
        //double maakuu = DeltaVCalc.fromAToB(new Place(earth, 250e3), new Place(moon, 100e3));
        //double kuumaa = DeltaVCalc.fromAToB(new Place(moon, 100e3), new Place(earth, 250e3));
        
        
        Application.launch(MainWindow.class);
        
        //System.out.println((int) maageo);
        //System.out.println((int) maakuu);
        //System.out.println((int) kuumaa);
        
    }    
}
