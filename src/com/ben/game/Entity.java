package com.ben.game;

import java.util.ArrayList;

/**
 * Base class for Entities. Entities can interact with each other and perform their own actions every tick. Although technically any {@link GameObject}
 * should be able to "tick", only Entities should in a normal situation.
 * @author Ben
 *
 */
public abstract class Entity extends DrawableGameObject {
    
    /**
     * List of rules for interactions 
     */
    public ArrayList<EntityInteraction<? extends GameObject>> interactions;
    
    /**
     * Constructor for Entity. Should <i>not</i> be used as an object in a game.
     */
    public Entity() {
        interactions = new ArrayList<EntityInteraction<? extends GameObject>>();
    }
    
    /**
     * Detects if this should interact with the object, then interacts if true.
     * @param o {@link GameObject} to interact with.
     */
    public void interact(GameObject o) {
        for (EntityInteraction<? extends GameObject> i : interactions) {
            i.interact(o);
        }
    }
    
}
