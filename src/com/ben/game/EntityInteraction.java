package com.ben.game;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author Ben
 *
 * @param <E> {@link GameObject} or subclass, 
 */
public abstract class EntityInteraction<E extends GameObject> {
    
    /**
     * Consumer class for calling the method for interaction.
     */
    private Consumer<GameObject> cons;
    /**
     * Predicate class for checking if interacting should happen.
     */
    private Predicate<GameObject> pred;
    
    /**
     * Constructor that automatically generates the Predicate as {@code x instanceof instance}.
     * @param cons Consumer object for {@link EntityInteraction#cons cons} field.
     * @param instance Instance of E for automated predicate class.
     */
    public EntityInteraction(Consumer<GameObject> cons, E instance) {
        this.cons = cons;
        this.pred = ((o) -> instance.getClass().isInstance(o));
    }
    
    /**
     * Constructor with full control over both the consumer and predicate.
     * @param cons Consumer object for {@link EntityInteraction#cons cons} field.
     * @param pred Predicate object for= {@link EntityInteraction#pred pred} field.
     */
    public EntityInteraction(Consumer<GameObject> cons, Predicate<GameObject> pred) {
        this.cons = cons;
        this.pred = pred;
    }
    
    /**
     * Constructor that automatically generates the Predicate as {@code true}.
     * @param cons Consumer object for {@link EntityInteraction#cons cons} field.
     */
    public EntityInteraction(Consumer<GameObject> cons) {
        this.cons = cons;
        this.pred = ((o) -> true);
    }
    
    /**
     * Tests to find out if this should interact with a {@link GameObject}.
     * @param e {@link GameObject} to test.
     * @return Returns true if this should interact with e.
     */
    public boolean should(GameObject e) {
        return pred.test(e);
    }
    
    /**
     * Tests if it should interact, then interacts if true.
     * @param e {@link GameObject} to interact with.
     */
    public void interact(GameObject e) {
        if (should(e)) cons.accept(e);
    }
    
}
