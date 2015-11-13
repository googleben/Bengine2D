package com.ben.tanktrouble;

import com.ben.game.Game;

public class TankTrouble {
    
    public static void main(String[] args) {
        Game g = new Game();
        g.add(new Tank(g,250,250));
        g.setSize(500, 500);
        g.canvas.setStyle("-fx-background-color:#ccc");
    }
    
}
