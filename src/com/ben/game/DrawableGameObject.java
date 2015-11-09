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
    public boolean visible = true;
    /**
     * {@link Drawable} object to be drawn.
     */
    public Drawable drawable;
    
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
    
}
