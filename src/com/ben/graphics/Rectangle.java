package com.ben.graphics;

import javafx.scene.paint.Color;

/**
 * Rectangle {@link DrawableShape}.
 * @author Ben
 *
 */
public class Rectangle extends DrawableShape {
    
    /**
     * Width of the rectangle.
     */
    public double width;
    /**
     * Height of the rectangle.
     */
    public double height;
    
    /**
     * Constructor for the rectangle.
     * @param x X value of the rectangle.
     * @param y Y value of the rectangle.
     * @param width Width of the rectangle.
     * @param height Height of the rectangle.
     * @param color Fill color of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height, Color color) {
        this.rotation = 0;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
        this.node = new javafx.scene.shape.Rectangle(x,y,width,height);
        remakeNode();
    }
    
    
    /* 
     * @see com.ben.graphics.Drawable#remakeNode()
     */
    public void remakeNode() {
        super.remakeNode();
        javafx.scene.shape.Rectangle rect = (javafx.scene.shape.Rectangle)node;
        rect.setX(x);
        rect.setY(y);
        rect.setWidth(width);
        rect.setHeight(height);
        node = rect;
    }
    
}
