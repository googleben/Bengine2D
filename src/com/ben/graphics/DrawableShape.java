package com.ben.graphics;

import javafx.scene.paint.*;
import javafx.scene.shape.Shape;

/**
 * Base class for Drawables in form of shapes (rectangles, circles, etc.).
 * @author Ben
 *
 */
public abstract class DrawableShape extends Drawable {
    
    /**
     * Fill color of the shape.
     */
    public Color color;
    
    /* (non-Javadoc)
     * @see com.ben.graphics.Drawable#remakeNode()
     */
    public void remakeNode() {
        super.remakeNode();
        Shape shape = (Shape)node;
        shape.setFill(color);
    }
    
    
    
}
