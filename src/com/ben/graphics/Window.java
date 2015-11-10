package com.ben.graphics;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


/**
 * The Window object contains the {@link Canvas}. It acts as an interface for outside objects to interact with the Canvas and handles
 * basic creation of the Stage object.
 * @author Ben
 *
 */
public class Window {
    
    /**
     * Main {@link Canvas} for drawing.
     */
    public Canvas mainCanvas;
    
    /**
     * Constructor for the Window object.
     * @param stage JavaFX main stage.
     */
    public Window(Stage stage) {
        start(stage);
    }
    
    /**
     * Creates the canvas and runs it.
     * @param stage JavaFX main stage.
     */
    private void start(Stage stage) {
        stage.setOnCloseRequest((WindowEvent e) -> System.exit(0));
        this.mainCanvas = new Canvas(stage, 60);
        mainCanvas.run();
    }
    
    /**
     * Sets the style for the pane.
     * @param style String containing CSS.
     */
    public void setStyle(String style) {
        mainCanvas.style = style;
    }
    
    /**
     * Adds a drawable object to the canvas.
     * @param d {@link Drawable} object to be drawn.
     */
    public void add(Drawable d) {
        mainCanvas.add(d);
    }
    
    public void remove(Drawable d) {
        mainCanvas.remove(d);
    }
    
    /**
     * Sets the EventHandler for handling a keypress KeyEvent.
     * @param e EventHandler for the keypress KeyEvent.
     */
    public void setOnKeypress(EventHandler<? super KeyEvent> e) { mainCanvas.setOnKeypress(e); }
    public void addOnKeypress(KeyHandler e) { mainCanvas.addKeyPressHandler(e); }
    
    public void setSize(double w, double h) {
        mainCanvas.setSize(w, h);
    }
    public void autosize() {
        mainCanvas.autosize();
    }
    
}
