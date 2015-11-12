package com.ben.game;

import java.util.ArrayList;

import com.ben.graphics.Canvas;
import com.ben.graphics.GameApplication;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Game class is the main class for a game created in this engine. It handles all {@link GameObject GameObjects} and the JavaFX 
 * instance and drawing in form of a {@link com.ben.graphics.canvas canvas} object.
 * @author Ben
 *
 */
public class Game {
    
    /**
     * {@link com.ben.graphics.canvas canvas} for drawing on.
     */
    public Canvas canvas;
    /**
     * GameApplication as instance of JavaFX to pass to canvas.
     */
    public static GameApplication application = null;
    /**
     * ArrayList containing all {@link GameObject GameObjects} to be handled.
     */
    public ArrayList<GameObject> objects;
    
    public Stage stage;
    
    private Thread JFXThread;
    
    /**
     * Constructor for the Game object.
     */
    @SuppressWarnings("unchecked")
    public Game() {
        objects = new ArrayList<GameObject>();
        Runnable launchFX = () -> GameApplication.launch(GameApplication.class);
        this.JFXThread = new Thread(launchFX);
        JFXThread.start();
        while (application == null) {
            System.out.println("Waiting for application to be created...");
            try {
                Thread.sleep(100);
            } catch(Exception e) {e.printStackTrace();}
        }
        canvas = application.canvas;
        stage = application.canvas.getStage();
        canvas.addTask(() -> {for (GameObject o : (ArrayList<GameObject>)objects.clone()) o.tick();});
    }
    
    /**
     * Adds a {@link GameObject} to {@link Game#objects objects}.
     * @param o {@link GameObject} to add to {@link Game#objects objects}.
     */
    public void add(GameObject o) {
        objects.add(o);
    }
    
    /**
     * Adds a {@link DrawableGameObject} to {@link Game#objects objects}.
     * @param o {@link DrawableGameObject} to add to {@link Game#objects objects}.
     */
    public void add(DrawableGameObject o) {
        objects.add(o);
        canvas.add(o.drawable);
    }
    
    /**
     * Removes a {@link GameObject} from {@link Game#objects objects}.
     * @param o {@link GameObject} to remove from {@link Game#objects objects}.
     */
    public void remove(GameObject o) {
        objects.remove(o);
    }
    /**
     * Removes a {@link DrawableGameObject} from {@link Game#objects objects}.
     * @param o {@link DrawableGameObject} to remove from {@link Game#objects objects}.
     */
    public void remove(DrawableGameObject o) {
        objects.remove(o);
        canvas.remove(o.drawable);
    }
    
    public void newcanvas() {
        canvas = new Canvas(stage);
        canvas.setScene(new Scene(new Pane()));
    }
    
    public GridPane createMenu() {
    	return canvas.createMenu();
    }
    
    public void setSize(double w, double h) {
        canvas.setSize(w, h);
    }
    public void autosize() {
        canvas.autosize();
    }
    
    public void setFPS(int FPS) {
        canvas.setRefreshRate(1000/FPS);
    }
    public void setRefresh(long refresh) {
        canvas.setRefreshRate(refresh);
    }
    
}
