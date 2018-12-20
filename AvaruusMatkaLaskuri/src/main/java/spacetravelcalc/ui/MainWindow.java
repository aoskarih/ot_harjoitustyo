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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import spacetravelcalc.operating.SystemFileReader;
import spacetravelcalc.calculating.DeltaVCalc;
import spacetravelcalc.calculating.GravitationalSystem;
import spacetravelcalc.calculating.Place;
import spacetravelcalc.calculating.TravelTimeCalc;


/**
 * Luokka tarjoaa käyttöliittymän pohjan sovellukselle
 * 
 * @author hyarhyar
 */
public class MainWindow extends Application {

    private ArrayList<GravitationalSystem> topLevelSystems;
    private HashMap<String, GravitationalSystem> systemMap;
    private GravitationalSystem system1;
    private GravitationalSystem system2;
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
        Text place1 = new Text("\n\nLähtö planeetta ja kiertoradan korkeus metreissä\n");
        Text place2 = new Text("\n\nKohde planeetta ja kiertoradan korkeus metreissä\n");
        
        NumberOnlyTextField altitude1 = new NumberOnlyTextField();
        NumberOnlyTextField altitude2 = new NumberOnlyTextField();
        
        HBox altitudeBox1 = new HBox(altitude1, new Text("m"));
        HBox altitudeBox2 = new HBox(altitude2, new Text("m"));
        
        MenuButton system = new MenuButton("Systeemi");
        MenuButton psystem1 = new MenuButton("Valitse planeetta");
        MenuButton psystem2 = new MenuButton("Valitse planeetta");
        
        
        updateSystems(system, psystem1, psystem2);
        
        Button addSys = new Button("Lisää uusi systeemi");
        try {
            addSys.setOnAction(e -> {
                topLevelSystems.add(loadNewSystem(stage));
                updateSystems(system, psystem1, psystem2);
            });
        } catch (NullPointerException e) {
            System.out.println("didn't get a file");
        }
        
        Text answerDV = new Text();
        Text answerTime = new Text();
        Button calculateDV = new Button("Laske DV");
        Button calculateTime = new Button("Laske matka-aika");
        
        
        calculateDV.setOnAction(event -> {
            if (system1 != null && system2 != null && !altitude1.getText().isEmpty() && !altitude2.getText().isEmpty()) {
                alt1 = Double.parseDouble(altitude1.getText());
                alt2 = Double.parseDouble(altitude2.getText());
                Place a = new Place(system1, alt1);
                Place b = new Place(system2, alt2);
                double dv = DeltaVCalc.fromAToB(a, b);
                answerDV.setText("\nTarvittava DV:  \n" + Double.toString((int) dv) + " m/s");
            } else {
                answerDV.setText("Anna paremmat arvot");
            }
        });
        
        calculateTime.setOnAction(event -> {
            if (system1 != null && system2 != null && !altitude1.getText().isEmpty() && !altitude2.getText().isEmpty()) {
                alt1 = Double.parseDouble(altitude1.getText());
                alt2 = Double.parseDouble(altitude2.getText());
                Place a = new Place(system1, alt1);
                Place b = new Place(system2, alt2);
                double time = TravelTimeCalc.timeFromAToB(a, b);
                System.out.println(time);
                answerTime.setText("\nMatkaan kuluva aika on noin:  \n" + Double.toString((int) (time/1000) * 1000) + " sekuntia  tai \n" + Double.toString((int) (time / 86400)) + " päivää");
            } else {
                answerTime.setText("Anna paremmat arvot");
            }
        });
        
        left.getChildren().addAll(info1, system, place1, psystem1, altitudeBox1, place2, psystem2, altitudeBox2, new Text("\n\n"), addSys);
        right.getChildren().addAll(new Text("\n\n"), calculateDV, answerDV, new Text("\n\n"), calculateTime, answerTime);
        
        group.setLeft(left);
        group.setRight(right);
        
        Scene scene = new Scene(group, 640, 480);
        stage.setScene(scene);
        stage.show();
        
    }
    
    private void updateSystems(MenuButton system, MenuButton psystem1, MenuButton psystem2) {
        
        system.getItems().clear();
        
        for (GravitationalSystem p : topLevelSystems) {
            MenuItem e = new MenuItem(p.getSystemName());
            e.setOnAction(event -> {
                system.setText(e.getText());
                
                psystem1.setText("Valitse planeetta");
                psystem2.setText("Valitse planeetta");
                psystem1.getItems().clear();
                psystem2.getItems().clear();
                
                system1 = null;
                system2 = null;
                
                systemMap = p.getSystemMap();
                
                for (GravitationalSystem q : p.getSystems()) {
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
    
    private GravitationalSystem loadNewSystem(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        try {
            File sysFile = fileChooser.showOpenDialog(stage);
            return SystemFileReader.getNewSystem(sysFile); 
        } catch (IOException e) {
            System.out.println("Could not load file.");
        }
        return null;
    } 
}
