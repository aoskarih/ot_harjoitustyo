/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetravelcalc.ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import spacetravelcalc.operating.SystemFileReader;
import spacetravelcalc.calculating.DeltaVCalc;
import spacetravelcalc.calculating.PSystem;
import spacetravelcalc.calculating.Place;


/**
 *
 * @author hyarhyar
 */
public class MainWindow extends Application {

    private ArrayList<PSystem> topLevelSystems;
    private HashMap<String, PSystem> systemMap;
    private PSystem system1;
    private PSystem system2;
    private Double alt1;
    private Double alt2;
    
    public MainWindow() throws IOException {
        topLevelSystems = new ArrayList<>();
        
        //Load default system
        topLevelSystems.add(SystemFileReader.getDefaultSystem());
    }    
    
    @Override
    public void start(Stage stage) {
        BorderPane group = new BorderPane();
        VBox left = new VBox();
        VBox right = new VBox();
        
        Text info1 = new Text("\n\nValitse ensin systeemi.\n");
        
        Text place1 = new Text("\nLähtö planeetta ja kiertorata");
        Text place2 = new Text("\n\nKohde planeetta ja kiertorata");
        
        NumberOnlyTextField altitude1 = new NumberOnlyTextField();
        NumberOnlyTextField altitude2 = new NumberOnlyTextField();
        
        MenuButton system = new MenuButton("Systeemi");
        MenuButton psystem1 = new MenuButton("Valitse planeetta");
        MenuButton psystem2 = new MenuButton("Valitse planeetta");
        
        updateSystems(system, psystem1, psystem2);
        
        Button addSys = new Button("Lisää uusi systeemi");
        addSys.setOnAction(e -> {
            try {
                topLevelSystems.add(loadNewSystem(stage));
                updateSystems(system, psystem1, psystem2);
            } catch (IOException ex) {
                System.out.println("Can't load new system from file.");
            }
        });
        
        Text answer = new Text();
        Button calculate = new Button("Laske DV");
        
        calculate.setOnAction(event -> {
            
            alt1 = Double.parseDouble(altitude1.getText());
            alt2 = Double.parseDouble(altitude2.getText());
            
            if (system1 != null && system2 != null && alt1 != null && alt2 != null) {
                Place a = new Place(system1, alt1);
                Place b = new Place(system2, alt2);
                double dv = DeltaVCalc.fromAToB(a, b);
                answer.setText("Tarvittava DV: " + Double.toString((int) dv) + " m/s");
            } else {
                System.out.println(system1);
                System.out.println(system2);
                System.out.println(alt1);
                System.out.println(alt2);
                answer.setText("Anna paremmat arvot");
            }
        });
        
        left.getChildren().addAll(info1, system, place1, psystem1, altitude1, place2, psystem2, altitude2, addSys);
        right.getChildren().addAll(calculate, answer);
        
        group.setLeft(left);
        group.setRight(right);
        
        Scene scene = new Scene(group, 640, 480);
        stage.setScene(scene);
        stage.show();
        
    }
    
    private void updateSystems(MenuButton system, MenuButton psystem1, MenuButton psystem2) {
        
        system.getItems().clear();
        
        for (PSystem p : topLevelSystems) {
            MenuItem e = new MenuItem(p.getSystemName());
            e.setOnAction(event -> {
                system.setText(e.getText());
                
                psystem1.setText("Valitse planeetta");
                psystem2.setText("Valitse planeetta");
                psystem1.getItems().clear();
                psystem2.getItems().clear();
                
                systemMap = p.getSystemMap();
                
                for (PSystem q : p.getSystems()) {
                    String label = "";
                    if (q.layersDeep() == 0) {
                        label = q.getName();
                    } else {
                        label = new String(new char[q.layersDeep()-1]).replace("\0", "   ") + "  ┗ " + q.getName();
                    }
                    MenuItem i = new MenuItem(label);
                    i.setOnAction(action -> {
                        psystem1.setText(i.getText());
                        system1 = q;
                    });
                    psystem1.getItems().add(i);
                    MenuItem j = new MenuItem(label);
                    j.setOnAction(action -> {
                        psystem2.setText(j.getText());
                        system2 = q;
                    });
                    psystem2.getItems().add(j);
                }
            });
            system.getItems().add(e);
        }
    
    }
    
    private PSystem loadNewSystem(Stage stage) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File sysFile = fileChooser.showOpenDialog(stage);
        return SystemFileReader.getNewSystem(sysFile);
    } 
}
