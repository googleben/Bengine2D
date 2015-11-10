package com.ben.example.move;

import com.ben.game.*;
import com.ben.graphics.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends Entity {
    
    public static double speed = 3;
    public static int rotSpeed = (int)speed;
    
    public Player(Window w, double x, double y) {
        this.x = x; this.y = y;
        
        Rectangle r = new Rectangle(x,y,10,10,Color.BLACK);
        this.drawable = r;
        
        KeyHandler moveHandler = (code) -> {
            System.out.println("A");
            if (code.equals(KeyCode.W)) move(speed*Math.sin(Math.toRadians(this.drawable.rotation)),-speed*Math.cos(Math.toRadians(this.drawable.rotation)));
            if (code.equals(KeyCode.S)) move(-speed*Math.sin(Math.toRadians(this.drawable.rotation)),speed*Math.cos(Math.toRadians(this.drawable.rotation)));
            if (code.equals(KeyCode.D)) rotate(rotSpeed);
            if (code.equals(KeyCode.A)) rotate(-rotSpeed);
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
    
    public void rotate(int deg) {
        this.drawable.rotation+=deg;
        if (this.drawable.rotation<0) this.drawable.rotation = 360+this.drawable.rotation;
        if (this.drawable.rotation>360) this.drawable.rotation = 360-this.drawable.rotation;
    }
    
}
