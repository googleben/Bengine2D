package com.ben.game;


/**
 * Base class for all objects handled by the {@link Game} class.
 * @author Ben
 *
 */
public abstract class GameObject {
    
    /**
     * X position of this object.
     */
    public double x;
    /**
     * Y position of this object.
     */
    public double y;
    
    /**
     * Method to "tick" or perform any actions independently.
     */
    public void tick() {};
    
}
