package com.ben.graphics;

import javafx.scene.paint.Color;


/**
 * A {@link DrawableShape} object in form of a circle.
 * <b>NOTE:</b> X and Y values are the <i>CENTER</i> of the circle!
 * @author Ben
 *
 */
public class Circle extends DrawableShape {
    
    /**
     * Radius of the circle.
     */
    private double radius;
    
    /**
     * Constructor for the circle.
     * @param x Center of the circle on the X axis.
     * @param y Center of the circle on the Y axis.
     * @param radius Radius of the circle.
     * @param color Color of the circle.
     */
    public Circle(double x, double y, double radius, Color color) {
        this.rotation = 0;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        node = new javafx.scene.shape.Circle(x,y,radius);
        remakeNode();
    }
    
    /* 
     * @see com.ben.graphics.Drawable#remakeNode()
     */
    public void remakeNode() {
        super.remakeNode();
        javafx.scene.shape.Circle circ = (javafx.scene.shape.Circle)node;
        circ.setCenterX(x);
        circ.setCenterY(y);
        circ.setRadius(radius);
        node = circ;
    }
    
    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }
    
}
