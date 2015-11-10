package com.ben.example.move;

import com.ben.game.Game;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Tester {
    
    public static void main(String[] args) {
        Game g = new Game();
        
        Button b = new Button("Play!");
        GridPane menu = g.createMenu();
        menu.add(b, 0, 0);
        b.setOnMouseClicked((e) -> {
        	makeGame(g);
        });
        
        
    }
    
    public static void makeGame(Game g) {
    	g.window.mainCanvas.exitMenu();
    	g.add(new Player(g,250,250));
        g.setSize(500, 500);
    }
    
}
