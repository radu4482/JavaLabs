package org.example;
import java.awt.*;
import javax.swing.*;
public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    ShapePanel shapePanel;
    ObjectsPanel objectsPanel;
//vom avea cele 5 parti ale paginii
    //centru: zona grafica
    //sus: zona in care alegem culoarea sau nr de laturi ale unui poligon
    //stanga: forma dorita
    //dreapta: zona de delete al unei forme specifice
    //jos: zona referitoare la fisier

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //create the components
        canvas = new DrawingPanel(this);
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);
        shapePanel = new ShapePanel(this);
        objectsPanel = new ObjectsPanel(this);
 //...TODO
        //arrange the components in the container (frame)
        //JFrame uses a BorderLayout by default
        setLayout (new BorderLayout());
        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER); //this is BorderLayout.CENTER
        add(controlPanel, BorderLayout.SOUTH);
        add(shapePanel, BorderLayout.WEST);
        add(objectsPanel,BorderLayout.EAST);
 //...TODO
        setVisible(true);
        pack();
    }

}

