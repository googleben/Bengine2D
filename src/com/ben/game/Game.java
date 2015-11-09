package com.ben.game;

import java.util.ArrayList;
import com.ben.graphics.GameApplication;
import com.ben.graphics.Window;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Game class is the main class for a game created in this engine. It handles all {@link GameObject GameObjects} and the JavaFX 
 * instance and drawing in form of a {@link com.ben.graphics.Window Window} object.
 * @author Ben
 *
 */
public class Game {
    
    /**
     * {@link com.ben.graphics.Window Window} for drawing on.
     */
    public Window window;
    /**
     * GameApplication as instance of JavaFX to pass to Window.
     */
    public static GameApplication application = null;
    /**
     * ArrayList containing all {@link GameObject GameObjects} to be handled.
     */
    public ArrayList<GameObject> objects;
    
    public Stage stage;
    
    /**
     * Constructor for the Game object.
     */
    public Game() {
        objects = new ArrayList<GameObject>() {
            private static final long serialVersionUID = 3636913014306906907L;
        };
        Runnable launchFX = () -> GameApplication.launch(GameApplication.class);
        new Thread(launchFX).start();
        //GameApplication.launch(GameApplication.class);
        while (application == null) {
            System.out.println("Waiting for application to be created...");
            try {
                Thread.sleep(100);
            } catch(Exception e) {e.printStackTrace();}
        }
        window = application.w;
        stage = application.w.mainCanvas.stage;
        window.mainCanvas.addTask(() -> {for (GameObject o : (ArrayList<GameObject>)objects.clone()) o.tick();});
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
        window.add(o.drawable);
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
        window.remove(o.drawable);
    }
    
    public void newWindow() {
        window = new Window(stage);
        window.mainCanvas.s = new Scene(new Pane());
    }
    
    public void setMenu(GridPane p) {
        stage.setScene(new Scene(p));
        
    }
    
    public void setSize(double w, double h) {
        window.mainCanvas.setSize(w, h);
    }
    public void autosize() {
        window.mainCanvas.autosize();
    }
    
    public void setFPS(int FPS) {
        window.mainCanvas.refreshRate = 1000/FPS;
    }
    public void setRefresh(long refresh) {
        window.mainCanvas.refreshRate = refresh;
    }
    
}
