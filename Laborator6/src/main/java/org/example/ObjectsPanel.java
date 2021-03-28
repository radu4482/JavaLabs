package org.example;
import java.awt.*;
import javax.swing.*;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.util.Random;

public class ObjectsPanel extends JPanel{
    final MainFrame frame;
    JLabel Label;
    JSpinner Field;
    //folosesc aceasta clasa pentru a putea manipula layerele
    //pot doar sa sterg
    public ObjectsPanel(MainFrame frame)
    {
        this.frame=frame;
        init();
    }


    private void init(){
        Label = new JLabel("Number of object:");
        Field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        Field.setValue(10); //se selecteaza numarul obiectului pe care se doreste de a fi eliminat
        JButton delete = new JButton("Delete");
        add(Label);
        add(Field);
        add(delete);
        delete.addActionListener(this::Delete);
    }

    //in cazul in care butonul este apasat, se va apela functia refresh si se va elimina obiectul din lista si se va
    //recolora intreaga mapa cu aceeasi ordine, dar fara elementul sters
    private void Delete(ActionEvent e)
    {
    frame.canvas.refresh((int)Field.getValue());
    }
}
