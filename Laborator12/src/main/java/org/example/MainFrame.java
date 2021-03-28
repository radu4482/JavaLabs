package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DesignPanel designPanel;
    PositioningPanel positioningPanel;
    SettingsPanel settingsPanel;

    public MainFrame() {
        super("My Application(Laborator12)");
        init();
    }

    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        controlPanel = new ControlPanel(this);
        designPanel = new DesignPanel(this);
        positioningPanel = new PositioningPanel(this);
        settingsPanel = new SettingsPanel(this);
        setLayout(new BorderLayout());

        add(controlPanel, BorderLayout.NORTH);
        add(designPanel, BorderLayout.CENTER);
        add(positioningPanel, BorderLayout.EAST);
        add(settingsPanel, BorderLayout.WEST);

        setVisible(true);
        pack();

    }
}


