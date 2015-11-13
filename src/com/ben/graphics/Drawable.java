package com.ben.graphics;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


/**
 * Base class for all objects to be drawn on a {@link Canvas}.
 * @author Ben
 *
 */
public abstract class Drawable {
    
    public double x;
    public double y;
    public int rotation;
    public boolean visible;
    public Node node;
    /**
     * Boolean value containing drawn state.<br>
     * true=currently drawn<br>
     * false=not drawn
     */
    public boolean isDrawn;
    
    /**
     * Sets rotation of the object in degrees.
     * @param rotation Rotation in degrees.
     */
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
    
    /**
     * Draws the object onto Pane p.
     * @param p JavaFX pane to draw on.
     */
    public void draw(Pane p) {
        if (isDrawn) p.getChildren().remove(node);
        //remakeNode();
        while (node==null) {
            System.out.println("null!");
        }
        remakeNode();
        p.getChildren().add(node);
        isDrawn = true;
    }
    
    /**
     * Removes the object from Pane p.
     * @param p JavaFX pane to remove from.
     */
    public void erase(Pane p) {
        if (isDrawn) p.getChildren().remove(node);
        isDrawn = false;
    }
    
    /**
     * Method that recreates the node completely to account for any changes to its parameters before redrawing.
     */
    public void remakeNode() {
        try {
            node.setRotate(rotation);
        }
        catch(NullPointerException e) {
            System.out.println(node.toString());
        }
    }
    
    /**
     * Sets the EventHandler for clicks.
     * @param e EventHandler for handling a click MouseEvent on the Pane.
     */
    public void setOnClick(EventHandler<? super MouseEvent> e) { node.setOnMouseClicked(e); }
    /**
     * Sets the EventHandler for dragging.
     * @param e EventHandler for handling a drag MouseEvent on the Pane.
     */
    public void setOnDrag(EventHandler<? super MouseEvent> e) { node.setOnMouseDragged(e); }
    
}
