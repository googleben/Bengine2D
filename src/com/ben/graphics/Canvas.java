package com.ben.graphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * The Canvas object is for use as a container for all {@link Drawable} objects. It contains the primary stage, the scene, and the pane in use for a {@link com.ben.game.Game Game}.
 * In addition to containing the JavaFX components, it draws the {@link Drawable} objects using their {@link Drawable#draw(Pane) draw} method.
 * Finally, the class provides a timer to run extra {@link CanvasTask CanvasTasks}.
 * @author Ben
 *
 */
public class Canvas {
    
    /**
     * ArrayList containing all {@link Drawable} objects to be drawn on this canvas.
     */
    public ArrayList<Drawable> objects;
    /**
     * JavaFX primary stage
     */
    public Stage stage;
    /**
     * ArrayList containing all {@link CanvasTask CanvasTasks} to be performed every refresh.
     */
    public ArrayList<CanvasTask> tasks;
    /**
     * Refresh rate for Timer in milliseconds.
     */
    public long refreshRate;
    /**
     * Timer to refresh the draw and run all CanvasTasks.
     */
    public Timer timer;
    /**
     * Pane for containing objects.
     */
    public Pane pane;
    /**
     * String with CSS for the pane.
     */
    public String style;
    /**
     * Scene for use in stage
     */
    public Scene s;
    
    /**
     * ArrayList containing EventHandlers for handling KeyEvents.
     */
    public ArrayList<KeyHandler> keyHandlers;
    
    /**
     * Whether or not to autosize.
     */
    private boolean autosize = true;
    
    private double w;
    private double h;
    
    public ArrayList<KeyCode> pressed;
    
    public boolean doMainHandler = true;
    
    @SuppressWarnings("unchecked")
    private EventHandler<? super KeyEvent> mainHandler = (e) -> {
        if (e.getEventType().equals(KeyEvent.KEY_PRESSED)) { pressed.add(e.getCode()); System.out.println("pressed"); }
        if (e.getEventType().equals(KeyEvent.KEY_RELEASED)) pressed.remove(e.getCode());
    };
    
    /**
     * Generates a Canvas object with default refresh rate 60Hz.
     * @param stage JavaFX primaryStage
     */
    public Canvas(Stage stage) {
        this.stage = stage;
        this.objects = new ArrayList<Drawable>();
        this.tasks = new ArrayList<CanvasTask>();
        this.pressed  = new ArrayList<KeyCode>();
        this.keyHandlers = new ArrayList<KeyHandler>();
        this.timer = new Timer();
        style = "";
        this.refreshRate = 1000/60;
    }
    
    /**
     * Generates a Canvas object with refresh rate declared in milliseconds.
     * @param stage JavaFX primaryStage.
     * @param refreshRate Refresh rate in milliseconds.
     */
    public Canvas(Stage stage, long refreshRate) {
        this.stage = stage;
        this.objects = new ArrayList<Drawable>();
        this.tasks = new ArrayList<CanvasTask>();
        this.pressed  = new ArrayList<KeyCode>();
        this.keyHandlers = new ArrayList<KeyHandler>();
        this.timer = new Timer();
        this.refreshRate = refreshRate;
        style = "";
    }
    
    /**
     * Generates a canvas object with refresh rate declared in Frames Per Second, or Hertz.
     * @param stage JavaFX primaryStage.
     * @param FPS Refresh rate in Frames Per Second, or Hertz.
     */
    public Canvas(Stage stage, int FPS) {
        this(stage, (long)1000/FPS);
    }
    
    /**
     * Draws the initial state of the canvas.
     */
    public void draw() {
        pane = new Pane();
        pane.setStyle(style);
        s = new Scene(pane);
        stage.setScene(s);
        stage.show();
        stage.sizeToScene();
        stage.setResizable(false);
    }
    
    /**
     * Adds a Drawable object to the canvas.
     * @param d Drawable object to be drawn.
     */
    public void add(Drawable d) {
        objects.add(d);
    }
    
    /**
     * Adds a CanvasTask to the list of timer tasks.
     * @param task task to add to timer tasks.
     */
    public void addTask(CanvasTask task) {
        tasks.add(task);
    }
    
    /**
     * Sets action to be performed upon a keypress.
     * @param e EventHandler for KeyPress event containing method body for event handling.
     */
    public void setOnKeypress(EventHandler<? super KeyEvent> e) { s.setOnKeyPressed(e); doMainHandler = false; }
    
    /**
     * Redraws the canvas, including all objects.
     */
    public void redraw() {
        ArrayList<Drawable> l = (ArrayList<Drawable>) objects.clone();
        for (Iterator<Drawable> it = l.iterator(); it.hasNext(); ) it.next().draw(pane);
        pane.setStyle(style);
        if (autosize) stage.sizeToScene(); else { stage.setWidth(this.w); stage.setHeight(this.h); };
    }
    
    /**
     * Draws initial canvas state and creates timer to handle refresh and tasks.
     */
    public void run() {
        draw();
        
        CanvasTask runKeyHandlers = new CanvasTask() {

            @Override
            public void doTask() {
                System.out.println("task");
                if (doMainHandler) for (KeyCode c : (ArrayList<KeyCode>)pressed.clone()) for (KeyHandler h : (ArrayList<KeyHandler>)keyHandlers.clone()) h.handle(c);
            }
        };
        
        tasks.add(runKeyHandlers);
        
        TimerTask refreshTask = new TimerTask() {
            @Override
            public void run() {
                ArrayList<CanvasTask> l = (ArrayList<CanvasTask>) tasks.clone();
                for (CanvasTask t : l) t.doTask();
                Platform.runLater(new Runnable() {
                    public void run() {
                        redraw();
                    }
                });
            }
        };
        timer.schedule(refreshTask, 5, refreshRate);
    }

    /**
     * Removes a {@link Drawable}.
     * @param d {@link Drawable} to remove
     */
    public void remove(Drawable d) {
        objects.remove(d);
        d.erase(pane);
    }

    /**
     * Removes all {@link Drawable Drawables} from the canvas.
     */
    public void removeAll() {
        objects = new ArrayList<Drawable>();
    }
    
    /**
     * Adds an EventHandler to the list of EventHandlers for KeyEvents.
     * @param e EventHandler to handle KeyEvents
     */
    public void addKeyPressHandler(KeyHandler e) {
        doMainHandler = true;
        setOnKeypress(mainHandler);
        keyHandlers.add(e);
    }
    
    /**
     * Sets size of canvas, turns off autosize.
     * @param w Width
     * @param h Height
     */
    public void setSize(double w, double h) {
        autosize = false;
        this.w = w;
        this.h = h;
    }
    /**
     * Sets the canvas to autosize based on contained elements.
     */
    public void autosize() {
        autosize = true;
    }
    
}
