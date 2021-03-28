package org.example;
import java.awt.*;

public class RegularPolygon extends Polygon implements ShapeObj {
    double x,y;
    double radius;
    int sides;
    Color color;
    public RegularPolygon(int x0, int y0, int radius, int sides, Color color) {
        double alpha = 2 * Math.PI / sides;
        for (int i = 0; i < sides; i++) {
            double x = x0 + radius * Math.cos(alpha * i);
            double y = y0 + radius * Math.sin(alpha * i);
            this.addPoint((int) x, (int) y);
        }
        this.x=x0;
        this.y=y0;
        this.radius=radius;
        this.sides=sides;
        this.color=color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
