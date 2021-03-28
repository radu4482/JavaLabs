package org.example;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ShapePanel extends JPanel{
    final MainFrame frame;
    JButton polygon = new JButton("Polygon");
    JButton elipse = new JButton("Elipse");
    int aux=0;
    public ShapePanel(MainFrame frame)
    {
        this.frame=frame;
        init();
    }

    public void init()
    {
        setLayout(new GridLayout(10, 1));
        add(polygon);
        add(elipse);
        polygon.addActionListener(this::poly);
        elipse.addActionListener(this::eli);

    }

    private void poly(ActionEvent e)
    {
        frame.configPanel.add();
        aux=0;
    }

    private void eli(ActionEvent e)
    {
        frame.configPanel.remove();
        aux=1;
    }
}
