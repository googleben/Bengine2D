package com.ben.example.move;

import com.ben.game.*;
import com.ben.graphics.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends Entity {
    
    public static double speed = 1;
    public static int rotSpeed = (int)speed;
    
    public KeyCode up;
    public KeyCode down;
    public KeyCode left;
    public KeyCode right;
    
    private Canvas c;
    private Game g;
    
    private int tick = 1;
    
    public Player(Game g, double x, double y, KeyCode up, KeyCode down, KeyCode left, KeyCode right) {
        this.x = x; this.y = y;
        
        Rectangle r = new Rectangle(x,y,10,10,Color.BLACK);
        this.drawable = r;
        
        KeyHandler moveHandler = (k) -> {
            if (k.isPressed(up)) move(speed*Math.sin(Math.toRadians(this.drawable.getRotation())),-speed*Math.cos(Math.toRadians(this.drawable.getRotation())));
            if (k.isPressed(down)) move(-speed*Math.sin(Math.toRadians(this.drawable.getRotation())),speed*Math.cos(Math.toRadians(this.drawable.getRotation())));
            if (k.isPressed(right)) rotate(rotSpeed);
            if (k.isPressed(left)) rotate(-rotSpeed);
        };
        
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        
        this.c = g.canvas;
        g.addOnKeypress(moveHandler);
        this.g = g;
    }
    
    public Player(Game g, double x, double y) {
        this(g, x, y, KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D);
    }
    
    public Player(Game g) {
        this(g,0,0);
    }
    
    public void tick() {
    	if (tick++%3==0) { g.add(new Trail(x,y,this.drawable.getRotation(),g)); tick=0; }
    }
    
    public void move(double x, double y) {
        this.x+=x;
        this.y+=y;
        if (this.x<0 || this.x>g.canvas.getWidth()-((Rectangle)this.drawable).width*1.5) this.x -= x; 
        if (this.y<0 || this.y>g.canvas.getHeight()-((Rectangle)this.drawable).height*4) this.y -= y;
        ((Rectangle)this.drawable).setX(this.x);
        ((Rectangle)this.drawable).setY(this.y);
    }
    
    public void rotate(int deg) {
        this.drawable.setRotation(this.drawable.getRotation()+deg);
        if (this.drawable.getRotation()<0) this.drawable.setRotation(360+this.drawable.getRotation());
        if (this.drawable.getRotation()>360) this.drawable.setRotation(360-this.drawable.getRotation());
    }
    
}
