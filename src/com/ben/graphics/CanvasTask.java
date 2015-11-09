package com.ben.graphics;


/**
 * Functional interface for performing tasks via the {@link Canvas Canvas's} {@link Canvas#timer timer} object.
 * @author Ben
 *
 */
@FunctionalInterface
public interface CanvasTask {
    
    /**
     * Empty method to be overridden and performed by a {@link Canvas}.
     */
    public abstract void doTask();
    
}
