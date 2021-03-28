package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class DesignPanel extends JPanel implements Serializable {
    final MainFrame frame;
    final static int W = 800, H = 800;
    BufferedImage image;
    Graphics2D graphics;
    ArrayList<Component> components;
    int matrix[][] = new int[900][900];

    public DesignPanel(MainFrame frame) {
        this.frame = frame;
        this.components = new ArrayList<>();
        createOffscreenImage();
        init();
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                frame.controlPanel.submit(e.getY(), e.getX());
            }
        });

    }

    public void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(new Color(202, 175, 67)); //fill the image with white
        graphics.fillRect(0, 0, W, H);
        graphics.setColor(Color.BLACK);
        for (int i = 50; i <= 750; i += 50) {
            graphics.drawLine(i, 50, i, 750);
            graphics.drawLine(50, i, 750, i);
        }
    }

    private void init() {
        setMatrix(0, 0, W, H, 0);
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        setLayout(null);
    }

    //se verifica ce fel de componenta swing s-a ales si se apleaza functia addContent cu componenta aleasa
    public void newAdd(String choose, String name) {
        if (choose == "Text")
            addContent(new Label(name), name);
        else if (choose == "Button")
            addContent(new Button(name), name);
        else if (choose == "CheckBox")
            addContent(new Checkbox(), name);
        else if (choose == "ComboBox") {
            addContent(new JComboBox(), name);
        } else if (choose == "List") {
            DefaultListModel test = new DefaultListModel();
            for(int i=0;i<7;i++)test.addElement(name);
            addContent(new JList(test), name);
        } else if (choose == "RadioButton")
            addContent(new JRadioButton(name), name);
        else if (choose == "Slider") {
            addContent(new JSlider(JSlider.HORIZONTAL, 0, 10, 5), name);
        } else if (choose == "Spinner") {
            addContent(new JSpinner(), name);
        } else if (choose == "TextField") {
            addContent(new TextField(name.length()), name);
        } else addContent(new JPasswordField(name), name);
    }

    //seteaza locul si dimensiunile componentei dupa specificatiile facute
    public void setDimensions(Component content, int x, int y, int width, int hight) {
        Dimension size = content.getPreferredSize();

        if (frame.positioningPanel.checked()) {
            hight = size.height;
            width = size.width;
        } else {
            width = frame.positioningPanel.getwidth();
            hight = frame.positioningPanel.gethight();
        }
    }

    //Adauga componenta in caz de este locul liber
    public void addContent(Component content, String name) {
        int x = 0, y = 0, hight = 0, width = 0;
        add(content);
        // frame.settingsPanel.addToTable(content);
        Dimension size = content.getPreferredSize();
        x = frame.positioningPanel.getx();
        y = frame.positioningPanel.gety();

        if (!frame.positioningPanel.checked()) {
            hight = size.height;
            width = size.width;
        } else {
            width = frame.positioningPanel.getwidth();
            hight = frame.positioningPanel.gethight();
        }

        if (clear(x, y, hight, width)) {
            setMatrix(x, y, width, hight, 1);
            components.add(content);
            content.setLocation(x, y);
            content.setSize(width, hight);
            content.setName(name);

            frame.settingsPanel.addToTable(content);
            setDimensions(content, x, y, width, hight);
        } else remove(content);
    }

    //Adauga in matrice valoarea 0 daca este liber , 1 daca este ocupat
    public void setMatrix(int x, int y, int hight, int width, int value) {
        int maxX = x + width;
        int maxY = y + hight;
        for (int i = x; i < maxX; i++)
            for (int j = y; j < maxY; j++)
                this.matrix[i][j] = value;
    }

    //Verifica daca locul ales nu este ocupat deja
    public boolean clear(int x, int y, int hight, int width) {
        if (x + hight > 800 || y + width >= 800) return false;

        for (int i = x; i <= x + hight - 1; i++)
            for (int j = y; j <= y + width - 1; j++)
                if (matrix[i][j] == 1)
                    return false;
        return true;
    }

    //Sterge componenta "content" din lista components
    public void removeComponent(Component content) {
        remove(content);

        setMatrix(content.getX(), content.getY(), content.getWidth(), content.getHeight(), 0);
        frame.settingsPanel.removeFromTable(content);
        components.remove(content);
    }

    //Sterge componenta cu indexul "value" din lista components
    public void removeComponent(int value) {
        if (components.size() >= value - 1) {
            remove(components.get(value - 1));
            components.remove(components.get(value - 1));
            frame.settingsPanel.removeFromTable(value - 1);

            setMatrix(components.get(value - 1).getX(), components.get(value - 1).getY(), components.get(value - 1).getWidth(), components.get(value - 1).getHeight(), 0);
        }
    }

    //Sterge ultima componenta introdusa din lista components
    public void undo() {
        removeComponent(components.get(components.size() - 1));
    }

}
