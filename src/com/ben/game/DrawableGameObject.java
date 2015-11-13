package com.ben.game;

import com.ben.graphics.Drawable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Base class for {@link GameObject GameObjects} that can be represented on a {@link com.ben.graphics.Canvas Canvas} by a {@link Drawable}.
 * @author Ben
 *
 */
public abstract class DrawableGameObject extends GameObject {
    
    /**
     * If the Drawable should be visible.
     */
    protected boolean visible = true;
    /**
     * {@link Drawable} object to be drawn.
     */
    protected Drawable drawable;
    
    protected int rotation;
    
    protected Color color;
    
    /**
     * Sets the EventHandler for the click MouseEvent.
     * @param e EventHandler for the click MouseEvent.
     */
    public void setOnClick(EventHandler<? super MouseEvent> e) { drawable.setOnClick(e); }
    /**
     * Sets the EventHandler for the drag MouseEvent.
     * @param e EventHandler for the drag MouseEvent.
     */
    public void setOnDrag(EventHandler<? super MouseEvent> e) { drawable.setOnClick(e); }
    
    public double getRotation() { return rotation; }
    public void setRotation(int rotation) { this.rotation = rotation; }
    
    public Drawable getDrawable() { return drawable; }
    
    public boolean isVisible() { return visible; }
    public void setVisible(boolean rotation) { visible = rotation; }
    
    public Color getColor() { return color; }
    public void setColor(Color c) { this.color = c; }
    
}
