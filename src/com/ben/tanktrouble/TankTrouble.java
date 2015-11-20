package com.ben.tanktrouble;

import com.ben.game.Game;
import javafx.scene.paint.Color;

public class TankTrouble {
    
    public static void main(String[] args) {
        Game g = new Game();
        g.add(new Tank(g,250,250));
        g.setSize(500, 500);
        g.canvas.setStyle("-fx-background-color:#ccc");
        g.add(new Boundary(0,0,1,500,0,Color.BLACK));
    }
    
}
