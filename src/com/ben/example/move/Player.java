package com.ben.example.move;

import com.ben.game.*;
import com.ben.graphics.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends Entity {
    
    public static double speed = 3;
    public static int rotSpeed = (int)speed;
    
    public KeyCode up;
    public KeyCode down;
    public KeyCode left;
    public KeyCode right;
    
    private Window w;
    private Game g;
    
    public Player(Game g, double x, double y, KeyCode up, KeyCode down, KeyCode left, KeyCode right) {
        this.x = x; this.y = y;
        
        Rectangle r = new Rectangle(x,y,10,10,Color.BLACK);
        this.drawable = r;
        
        KeyHandler moveHandler = (k) -> {
            if (k.isPressed(up)) move(speed*Math.sin(Math.toRadians(this.drawable.rotation)),-speed*Math.cos(Math.toRadians(this.drawable.rotation)));
            if (k.isPressed(down)) move(-speed*Math.sin(Math.toRadians(this.drawable.rotation)),speed*Math.cos(Math.toRadians(this.drawable.rotation)));
            if (k.isPressed(right)) rotate(rotSpeed);
            if (k.isPressed(left)) rotate(-rotSpeed);
        };
        
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        
        this.w = g.window;
        w.addOnKeypress(moveHandler);
        this.g = g;
    }
    
    public Player(Game g, double x, double y) {
        this(g, x, y, KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D);
    }
    
    public Player(Game g) {
        this(g,0,0);
    }
    
    public void tick() {
    	g.add(new Trail(
    			x,
    			y,
    			this.drawable.rotation, 
    			g));
    }
    
    public void move(double x, double y) {
        this.x+=x;
        this.y+=y;
        if (this.x<0 || this.x>g.window.mainCanvas.w-((Rectangle)this.drawable).width) this.x -= x; 
        if (this.y<0 || this.y>g.window.mainCanvas.h-((Rectangle)this.drawable).height*2) this.y -= y;
        ((Rectangle)this.drawable).x = this.x;
        ((Rectangle)this.drawable).y = this.y;
    }
    
    public void rotate(int deg) {
        this.drawable.rotation+=deg;
        if (this.drawable.rotation<0) this.drawable.rotation = 360+this.drawable.rotation;
        if (this.drawable.rotation>360) this.drawable.rotation = 360-this.drawable.rotation;
    }
    
}
