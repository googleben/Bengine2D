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
    protected double x;
    /**
     * Y position of this object.
     */
    protected double y;
    
    /**
     * Method to "tick" or perform any actions independently.
     */
    public void tick() {};
    
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    
    public double getY() { return x; }
    public void setY(double y) { this.y = y; }
    
}
