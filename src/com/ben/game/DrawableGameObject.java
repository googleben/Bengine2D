package com.ben.game;

import com.ben.graphics.Drawable;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

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
    public void setRotation(int rotation) { this.rotation = rotation; this.drawable.setRotation(rotation); }
    
    public Drawable getDrawable() { return drawable; }
    
    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; this.drawable.setVisible(visible); }
    
    public void setX(double x) { this.x = x; this.drawable.setX(x); }
    public void setY(double y) { this.y = y; this.drawable.setY(y); }
    
}
