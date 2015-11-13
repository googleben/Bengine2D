package com.ben.game;

/**
 * FunctionalInterface extending a {@link com.ben.graphics.CanvasTask CanvasTask}.
 * @author Ben
 *
 */
@FunctionalInterface
public interface GameTask extends com.ben.graphics.CanvasTask {
    
    /*
     * @see com.ben.graphics.CanvasTask#doTask()
     */
    public abstract void doTask();
    
}
