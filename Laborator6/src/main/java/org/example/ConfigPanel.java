package org.example;
import java.awt.*;
import javax.swing.*;
import javax.swing.JLabel;
import java.util.Random;


public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel sidesLabel; // we’re drawing regular polygons
    JSpinner sidesField; // number of sides
    JComboBox colorCombo; // the color of the shape

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //create the label and the spinner
        Label label = new Label("Some text");
        label.setFont(new Font("Dialog", Font.PLAIN, 12));
        sidesLabel = new JLabel("Number of sides:");
        sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        colorCombo=new JComboBox();
        sidesField.setValue(10); //default number of sides
        //create the colorCombo, containing the values: Random and Black
        Random rand=new Random();
       // Color Randomi = Color.getHSBColor(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
       // Color negru = Color.black;
        colorCombo.addItem("Random");
        colorCombo.addItem("Negru");
        colorCombo.setSelectedIndex(0);
 //...TODO
        add(colorCombo);
        add(sidesLabel); //JPanel uses FlowLayout by default
        add(sidesField);
    }
//am incercat sa folosesc doua functii care sa imi creeze casutele din zona de sus
    //in cazul in care apasam pe buttonul de Elipse , se renunta la Label si la Field ramanand doar colorCombo ul
    //in caz de se apasa butonul de Polygon este apasat, se asigura ca nu au fost puse de doua ori
    public void add(){
        remove(sidesLabel);
        remove(sidesField);
        add(sidesLabel); //JPanel uses FlowLayout by default
        add(sidesField);
    }

    public void remove(){
        remove(sidesLabel);
        remove(sidesField);
    }

}