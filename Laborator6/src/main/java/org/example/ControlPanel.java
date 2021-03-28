package org.example;
import java.awt.*;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;


public class ControlPanel extends JPanel {
    final MainFrame frame;
    String files="F:/test2.png";
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");
    JButton chooserBtn = new JButton("File Choose");
    JButton altBtn = new JButton("Alt buton");
    int reset=0;

    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(2, 3));
        //setLayout(new DefaultMenuLayout());
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(chooserBtn);
        add(altBtn);
        add(exitBtn);

        //add all buttons ...TODO

        //configure listeners for all buttons
        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(this::load);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener(this::exit);
        chooserBtn.addActionListener(this::chooser);
        //...TODO
    }
    private void save(ActionEvent e){
        try {
            ImageIO.write(frame.canvas.image, "PNG", new File(files));
        } catch (IOException ex) { System.err.println(ex); }
    }

//avem variabila Path files care este initiata cu un anumit path
    //in secventa load se va lua fiecare pixel si se va copia in imaginea afisata ramanand ca un background
    //reset=1 il folosesc pentru a reseta lista de obiecte si pentru a incarca noua imagine
    //...TODO
     private void load(ActionEvent e) {
     try {
         int color;
         BufferedImage load = ImageIO.read(new File(files));
         for(int i=0;i<800;i++)
             for(int j=0;j<600;j++) {
                 color = load.getRGB(i, j);
                 frame.canvas.image.setRGB(i, j, color);
             }

         reset=1;

                 } catch (IOException ex) { System.err.println(ex); }
 }
//la reset, se va pune un dreptunghi alb pe intreaga suprafata a ecranului de afisare

    private void reset(ActionEvent e) {
        frame.canvas.graphics.setColor(Color.WHITE); //fill the image with white
        frame.canvas.graphics.fillRect(0, 0, 800, 600);
        reset=1;
    }

    private void exit(ActionEvent e) { System.exit(0); }

//chooser incarca textul scris de client intr-un string care il va copia in files sub forma dorita
    private void chooser(ActionEvent e){
        String string_file;
        string_file=JOptionPane.showInputDialog("File Path");
        System.out.println(string_file);
        this.files = string_file;
    }
    }