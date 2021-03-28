package org.example;
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    List<ShapeObj> objects;//am creat o lista de obiecte, astfel incat sa pot sa sterg elementele dupa o anumita ordine , precum layerele

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        objects = new ArrayList<>();
        createOffscreenImage(); init();
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }
    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY()); repaint();
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }
    private void drawShape(int x, int y) {
        Random rand = new Random();
        Color color;
        int radius = rand.nextInt(300);//... TODO; //generate a random number
        int sides;

       //in cazul in care a fost apasat butonul reset sau load , lista de obiecte trebuie sa fie 0 iar urmatorul obiect
        //sa fie null , astfel incat creez un poligon cu 0 laturi care nu va exista, incat sa dau reset la primul click
        if (frame.controlPanel.reset == 0)
            sides = (int) frame.configPanel.sidesField.getValue();//...TODO; //get the value from UI (in ConfigPanel)
        else {
            sides = 0;
        }

       //voi salva in color culoarea aleasa in combobox ul din susul aplicatiei
        if (frame.configPanel.colorCombo.getSelectedIndex() == 0)
            color = Color.getHSBColor(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));//...TODO; //create a transparent random Color
        else
            color = Color.BLACK;

        ShapeObj s;

        graphics.setColor(color);

        //se va alege forma folosita prin intermediul butoanelor din dreapta
        //default ul este polygon, astfel incat daca se apasa pe butonul elypse -> aux=1
        if(frame.shapePanel.aux==0) {
            s=new RegularPolygon(x,y,radius,sides,color);
        }
        else
            s=new NodeShape(x,y,radius,color);

        objects.add(s);
        graphics.fill((Shape) s);
        System.out.println(objects.size());

        //cand reset=1 , lista de obiecte isi va sterge toate elementele iar reset va reveni la valoarea 0
        if(frame.controlPanel.reset==1) {
            frame.controlPanel.reset = 0;
            objects.clear();
        }
    }
    @Override
    public void update(Graphics g) { } //Why did I do that?

    public void drawObject(ShapeObj shape){
        graphics.setColor(shape.getColor());
        graphics.fill((Shape)shape);
    }
//functia refresh se foloseste in cazul in care s-a dat refresh la un anumit obiect
    //index este numarul elementului , astfel incat vom sti pe care sa il eliminam
    //fiecare obiect care este in fata lui va fi cu ul loc in spate(o mutare la stanga)
    //functia sterge toate elementele iar pe urma le va desena din nou in noua ordine
    public void refresh(int index)
    {
        for(int i=index-1;i<objects.size()-1;i++)
            objects.set(i,objects.get(i+1));
        objects.remove(objects.size()-1);

        frame.canvas.graphics.setColor(Color.WHITE); //fill the image with white
        frame.canvas.graphics.fillRect(0, 0, 800, 600);

        for(ShapeObj Ishape:objects)
            drawObject(Ishape);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}