package org.example;
import java.awt.geom.*;
import java.awt.Color;
public class NodeShape extends Ellipse2D.Double implements ShapeObj{
    double x,y;
    double radius;
    Color color;
    public NodeShape(double x0, double y0, double radius, Color color) {
        super(x0 - radius / 2, y0 - radius / 2, radius, radius);
        this.x=x0;
        this.y=y0;
        this.radius=radius;
        this.color=color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}