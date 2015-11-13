package com.ben.graphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


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
    private ArrayList<Drawable> objects;
    /**
     * JavaFX primary stage
     */
    private Stage stage;
    /**
     * ArrayList containing all {@link CanvasTask CanvasTasks} to be performed every refresh.
     */
    private ArrayList<CanvasTask> tasks;
    /**
     * Refresh rate for Timer in milliseconds.
     */
    private long refreshRate;
    /**
     * Timer to refresh the draw and run all CanvasTasks.
     */
    private Timer timer;
    /**
     * Pane for containing objects.
     */
    private Pane pane;
    /**
     * String with CSS for the pane.
     */
    private String style;
    /**
     * Scene for use in stage
     */
    private Scene s;
    
    /**
     * ArrayList containing EventHandlers for handling KeyEvents.
     */
    private ArrayList<KeyHandler> keyHandlers;
    
    /**
     * Whether or not to autosize.
     */
    private boolean autosize = true;
    
    /**
     * Width of the screen (if set to manual size)
     */
    private double w;
    /**
     * Height of the screen (if set to manual size)
     */
    private double h;
    
    /**
     * Keyboard object
     */
    public static Keyboard keyboard = new Keyboard();
    
    /**
     * Main event handler for key events
     */
    private EventHandler<? super KeyEvent> mainHandler = keyboard.mainHandler;
    
    /**
     * Generates a Canvas object with default refresh rate 60Hz.
     * @param stage JavaFX primaryStage
     */
    public Canvas(Stage stage) {
        this(stage, 60);
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
        this.keyHandlers = new ArrayList<KeyHandler>();
        this.timer = new Timer();
        this.refreshRate = refreshRate;
        stage.setOnCloseRequest((WindowEvent e) -> System.exit(0));
        run();
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
        s.setOnKeyPressed(mainHandler);
        s.setOnKeyReleased(mainHandler);
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
    //public void setOnKeypress(EventHandler<? super KeyEvent> e) { s.setOnKeyPressed(e); s.setOnKeyReleased(e); }
    
    /**
     * Redraws the canvas, including all objects.
     */
    
    public void redraw() {
        @SuppressWarnings("unchecked")
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
        
        TimerTask refreshTask = new TimerTask() {
            @SuppressWarnings("unchecked")
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
        Platform.runLater(() -> d.erase(pane));
    }

    /**
     * Removes all {@link Drawable Drawables} from the canvas.
     */
    public void removeAll() {
        objects = new ArrayList<Drawable>();
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

	/**
	 * @return Empty GridPane for menu creation
	 */
	public GridPane createMenu() {
		GridPane p = new GridPane();
		Platform.runLater(new Runnable() {
			public void run() {
				pane = p;
				s = new Scene(p);
				stage.setScene(s);
			}
		});
		p.setAlignment(Pos.CENTER);
		p.setHgap(10);
		p.setVgap(10);
		p.setPadding(new Insets(25,25,25,25));
		return p;
	}
	/**
	 * Exits GridPane-based drawing by calling the (@link Canvas#draw() draw()} method.
	 */
	public void exitMenu() {
		draw();
	}

	public ArrayList<Drawable> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<Drawable> objects) {
		this.objects = objects;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public ArrayList<CanvasTask> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<CanvasTask> tasks) {
		this.tasks = tasks;
	}

	public long getRefreshRate() {
		return refreshRate;
	}

	public void setRefreshRate(long refreshRate) {
		this.refreshRate = refreshRate;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Pane getPane() {
		return pane;
	}

	public void setPane(Pane pane) {
		this.pane = pane;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Scene getScene() {
		return s;
	}

	public void setScene(Scene s) {
		this.s = s;
	}

	public ArrayList<KeyHandler> getKeyHandlers() {
		return keyHandlers;
	}

	public void setKeyHandlers(ArrayList<KeyHandler> keyHandlers) {
		this.keyHandlers = keyHandlers;
	}

	public boolean isAutosize() {
		return autosize;
	}

	public void setAutosize(boolean autosize) {
		this.autosize = autosize;
	}

	public double getWidth() {
		return w;
	}

	public void setWidth(double w) {
		this.w = w;
	}

	public double getHeight() {
		return h;
	}

	public void setHeight(double h) {
		this.h = h;
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

	public static void setKeyboard(Keyboard keyboard) {
		Canvas.keyboard = keyboard;
	}

	public EventHandler<? super KeyEvent> getMainHandler() {
		return mainHandler;
	}

	public void setMainHandler(EventHandler<? super KeyEvent> mainHandler) {
		this.mainHandler = mainHandler;
	}
    
}
