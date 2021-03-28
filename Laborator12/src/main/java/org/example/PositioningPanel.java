package org.example;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.lang.Object.*;
import javax.swing.*;
import java.awt.*;

public class PositioningPanel extends JPanel {
    MainFrame frame;
    JSpinner xValue, yValue, width, hight;
    JCheckBox specificSize;

    public PositioningPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public void init() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        xValue = new JSpinner(new SpinnerNumberModel(10, 0, 500, 20));
        yValue = new JSpinner(new SpinnerNumberModel(10, 0, 500, 20));
        width = new JSpinner(new SpinnerNumberModel(10, 0, 500, 20));
        hight = new JSpinner(new SpinnerNumberModel(10, 0, 500, 20));
        specificSize = new JCheckBox("Specified Size");
        specificSize.setSelected(false);

        add(new Label("X value"));
        add(xValue);
        add(new Label("Y value"));
        add(yValue);
        add(specificSize);
        add(new Label("Hight"));
        add(hight);
        add(new Label("Width"));
        add(width);
    }

    public void setx(int value) {
        xValue.setValue(value);
    }

    public void sety(int value) {
        yValue.setValue(value);
    }

    public int gety() {
        return (Integer) xValue.getValue();
    }

    public int getx() {
        return (Integer) yValue.getValue();
    }

    public int getwidth() {
        return (Integer) width.getValue();
    }

    public int gethight() {
        return (Integer) hight.getValue();
    }

    //Verifica daca utilizatorul doreste sa specifice valorile dimensiunii
    public boolean checked() {
        if (specificSize.isSelected()) return true;
        return false;
    }

}
