package com.ben.example.move;

import com.ben.game.*;
import com.ben.graphics.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Player extends Entity {
    
    public Player(Window w, double x, double y) {
        this.x = x; this.y = y;
        
        Rectangle r = new Rectangle(x,y,10,10,Color.BLACK);
        this.drawable = r;
        
        KeyHandler moveHandler = (code) -> {
            System.out.println("A");
            if (code.equals(KeyCode.W)) move(0,-1);
            if (code.equals(KeyCode.A)) move(-1,0);
            if (code.equals(KeyCode.S)) move(0,1);
            if (code.equals(KeyCode.D)) move(1,0);
        };
        
        w.addOnKeypress(moveHandler);
    }
    
    public Player(Window w) {
        this(w,0,0);
    }
    
    public void move(double x, double y) {
        this.x+=x;
        this.y+=y;
        ((Rectangle)this.drawable).x = this.x;
        ((Rectangle)this.drawable).y = this.y;
    }
    
}
