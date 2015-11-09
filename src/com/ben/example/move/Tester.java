package com.ben.example.move;

import com.ben.game.Game;

public class Tester {
    
    public static void main(String[] args) {
        Game g = new Game();
        g.add(new Player(g.window));
        g.setSize(500, 500);
    }
    
}
