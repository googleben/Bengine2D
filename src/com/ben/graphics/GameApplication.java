package com.ben.graphics;

import com.ben.game.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


/**
 * Wrapper for JavaFX Application
 * @author Ben
 *
 */
public class GameApplication extends Application {
    
    public Window w;
    
    public void start(Stage s) {
        w = new Window(s);
        Game.application = this;
        Platform.setImplicitExit(false);
    }
    
}
