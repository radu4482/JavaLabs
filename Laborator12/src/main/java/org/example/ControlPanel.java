package org.example;

import java.awt.event.MouseAdapter;
import java.beans.Introspector;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;


public class ControlPanel extends JPanel {
    final MainFrame frame;
    String files = "text.xml";
    JComboBox chooseableElements = new JComboBox();
    JTextField choosenElementName = new JTextField(10);
    JButton submitBtn = new JButton("Submit");
    JButton undoBtn = new JButton("Remove Last");
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public void init() {
        //Umplem ComboBoxul
        chooseableElements.addItem("Text");
        chooseableElements.addItem("Button");
        chooseableElements.addItem("CheckBox");
        chooseableElements.addItem("ComboBox");
        chooseableElements.addItem("List");
        chooseableElements.addItem("RadioButton");
        chooseableElements.addItem("Slider");
        chooseableElements.addItem("Spinner");
        chooseableElements.addItem("TextField");
        chooseableElements.addItem("PasswordField");
        chooseableElements.setSelectedIndex(0);

        //adaugam sectiunea de decizie a tipului de componenta
        add(new Label("Type of the swing"));
        add(chooseableElements);

        //adaugam sectiunea in care alegem denumirea componentei
        add(new Label("Name of the swing"));
        add(choosenElementName);

        //adaugam butoanele
        add(submitBtn);
        add(undoBtn);
        add(saveBtn);
        add(loadBtn);

        //Atribuim actiuni butoanelor
        submitBtn.addActionListener(this::submit);
        undoBtn.addActionListener(this::undo);
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
    }

    //Submit din button
    public void submit(ActionEvent e) {
        if (frame.designPanel.components.size() < 50) {
            String choose;
            String name;
            if (choosenElementName.getText().isEmpty()) name = "Default";
            else name = choosenElementName.getText();
            choose = chooseableElements.getSelectedItem().toString();

            frame.designPanel.newAdd(choose, name);

            //repaint everything
            refresh();
        } else
            System.out.println("Max 50 items");
    }

    //Submit cu mouse
    public void submit(int x, int y) {
        if (frame.designPanel.components.size() < 50) {
            String choose;
            String name;
            if (choosenElementName.getText().isEmpty()) name = "Default";
            else name = choosenElementName.getText();
            choose = chooseableElements.getSelectedItem().toString();

            frame.positioningPanel.setx(x);
            frame.positioningPanel.sety(y);

            frame.designPanel.newAdd(choose, name);

            //repaint everything
            refresh();
        } else
            System.out.println("Max 50 items");
    }


    public void save(ActionEvent e) {
        try {
            ImageIO.write(frame.designPanel.image, "XML", new File(files));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void load(ActionEvent e) {
        try {
            BufferedImage load = ImageIO.read(new File(files));
            frame.designPanel.image = load;
            refresh();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    //Sterge ultimul element adaugat
    public void undo(ActionEvent e) {
        frame.designPanel.undo();
        refresh();
    }

    public void refresh() {
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

}
