/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetravelcalc.ui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import spacetravelcalc.calculating.PSystem;


/**
 *
 * @author hyarhyar
 */
public class MainWindow extends Application{

    private ArrayList<PSystem> topLevelSystems;
    private PSystem system1;
    private PSystem system2;
    private double alt1;
    private double alt2;
    
    public MainWindow() {
        topLevelSystems = new ArrayList<>();
        
        //Load default systems
        PSystem earth = new PSystem("Maa", 5.97e24, 6.371e6, "Maa-kuu");
        PSystem moon = new PSystem("Kuu", 7.34e22, 1.738e6, 3.84e8, earth);
        
        PSystem sun = new PSystem("Aurinko", 1.986e30, 696e6, "Aurinkokunta");
        PSystem earth1 = new PSystem("Maa", 5.97e24, 6.378e6, 149e9, sun);
        PSystem moon1 = new PSystem("Kuu", 7.35e22, 1.737e6, 384e6, earth1);
        PSystem jupiter = new PSystem("Jupiter", 1.9e27, 71e6, 778e9, sun);
        PSystem io = new PSystem("Io", 8.94e22, 1.815e6, 421e6, jupiter);
        PSystem europa = new PSystem("Europa", 4.8e22, 1.57e6, 670e6, jupiter);
        
        
        topLevelSystems.add(earth);
        topLevelSystems.add(sun);
    }    
    
    @Override
    public void start(Stage stage){
        BorderPane group = new BorderPane();
        VBox left = new VBox();
        VBox right = new VBox();
        
        Text place1 = new Text("\nLähtö planeetta ja kiertorata");
        Text place2 = new Text("\n\nKohde planeetta ja kiertorata");
        
        NumberOnlyTextField altitude1 = new NumberOnlyTextField();
        NumberOnlyTextField altitude2 = new NumberOnlyTextField();
        
        altitude1.setOnAction(event -> {
            alt1 = Double.parseDouble(altitude1.getText());
        });
        
        altitude2.setOnAction(event -> {
            alt2 = Double.parseDouble(altitude2.getText());
        });
        
        MenuButton system = new MenuButton("Systeemi");
        MenuButton psystem1 = new MenuButton("Valitse planeetta");
        MenuButton psystem2 = new MenuButton("Valitse planeetta");
        
        for(PSystem p : topLevelSystems) {
            MenuItem e = new MenuItem(p.getSystemName());
            e.setOnAction(event -> {
                system.setText(e.getText());
                psystem1.setText("Valitse planeetta");
                psystem2.setText("Valitse planeetta");
                psystem1.getItems().clear();
                psystem2.getItems().clear();
                for(PSystem q : p.getSystems()) {
                    MenuItem i = new MenuItem(q.getName());
                    i.setOnAction(action -> {
                        psystem1.setText(i.getText());
                        system1 = q;
                    });
                    psystem1.getItems().add(i);
                    MenuItem j = new MenuItem(q.getName());
                    j.setOnAction(action -> {
                        psystem2.setText(j.getText());
                        system2 = q;
                    });
                    psystem2.getItems().add(j);
                }

            });
            system.getItems().add(e);
        }
        
        left.getChildren().addAll(system, place1, psystem1, altitude1, place2, psystem2, altitude2);
        right.getChildren().addAll();
        
        
        group.setLeft(left);
        group.setRight(right);
        
        Scene scene = new Scene(group, 640, 480);
        stage.setScene(scene);
        stage.show();
        
    }
    
    
    
}
