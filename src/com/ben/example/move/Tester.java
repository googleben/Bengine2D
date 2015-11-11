package com.ben.example.move;

import com.ben.game.Game;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

public class Tester {
    
    public static void main(String[] args) {
        Game g = new Game();
        
        GridPane menu = g.createMenu();
        
        Platform.runLater(() -> {
        	Button play = new Button("Play!");
            menu.add(play, 0, 0);
            play.setOnMouseClicked((e) -> {
            	makeGame(g);
            });
            
            Button quit = new Button("Quit");
            menu.add(quit, 0, 1);
            quit.setOnMouseClicked((e) -> {
            	System.exit(0);
            });
        });
        
        
        
    }
    
    public static void makeGame(Game g) {
    	g.window.mainCanvas.exitMenu();
    	g.add(new Player(g,250,250));
    	g.add(new Player(g, 300, 250, KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT));
        g.setSize(500, 500);
    }
    
}
