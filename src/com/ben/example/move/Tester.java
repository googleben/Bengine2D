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
        	
        	int col = 0;
            
        	Button play1 = new Button("Play!");
            menu.add(play1, 0, col++);
            play1.setOnMouseClicked((e) -> makeGame(g,1));
            
            Button play2 = new Button("Play 2-Player!");
            menu.add(play2, 0, col++);
            play2.setOnMouseClicked((e) -> makeGame(g,2));
            
            Button play3 = new Button("Play 3-Player!");
            menu.add(play3, 0, col++);
            play3.setOnMouseClicked((e) -> makeGame(g,3));
            
            Button quit = new Button("Quit");
            menu.add(quit, 0, col++);
            quit.setOnMouseClicked((e) -> System.exit(0));
            
        });
        
    }
    
    public static void makeGame(Game g, int players) {
    	g.canvas.exitMenu();
    	g.add(new Player(g,250,250));
    	if (players>1) g.add(new Player(g, 300, 250, KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT));
    	if (players>2) g.add(new Player(g, 200, 250, KeyCode.I, KeyCode.K, KeyCode.J, KeyCode.L));
        g.setSize(500, 500);
        g.canvas.setStyle("-fx-background-color:#ccc");
    }
    
}
