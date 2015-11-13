package com.ben.tanktrouble;

import com.ben.game.Entity;
import com.ben.game.Game;
import com.ben.graphics.Canvas;
import com.ben.graphics.KeyHandler;
import com.ben.graphics.Rectangle;
import com.ben.math.geom.Shape;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Tank extends Entity implements ICollidable {
    
    public com.ben.math.geom.Rectangle bounds;
    
    private double width;
    private double height;
    
    private KeyCode up = KeyCode.W;
    private KeyCode down = KeyCode.S;
    private KeyCode left = KeyCode.A;
    private KeyCode right = KeyCode.D;
    
    private double speed = 1;
    private int rotSpeed = 1;
    
    private Game g;
    private Canvas c;
    
    public Tank(Game g, double x, double y, int rotation, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.rotation = rotation;
        this.width = 30;
        this.height = 50;
        Rectangle r = new Rectangle(x,y,width,height,Color.BLACK);
        this.drawable = r;
        
        KeyHandler moveHandler = (k) -> {
            if (k.isPressed(up)) move(speed*Math.sin(Math.toRadians(this.drawable.getRotation())),-speed*Math.cos(Math.toRadians(this.drawable.getRotation())));
            if (k.isPressed(down)) move(-speed*Math.sin(Math.toRadians(this.drawable.getRotation())),speed*Math.cos(Math.toRadians(this.drawable.getRotation())));
            if (k.isPressed(right)) rotate(rotSpeed);
            if (k.isPressed(left)) rotate(-rotSpeed);
        };
        
        this.c = g.canvas;
        g.addOnKeypress(moveHandler);
        this.g = g;
    }
    
    public Tank(Game g, double x, double y, int rotation) {
        this(g,x,y,rotation,Color.BLACK);
    }
    
    public Tank(Game g, double x, double y, Color c) {
        this(g,x,y,0,c);
    }
    
    public Tank(Game g, double x, double y) {
        this(g,x,y,0,Color.BLACK);
    }
    
    public boolean colliding(Shape s) {
        regenBounds();
        return bounds.intersects(s);
    }
    
    public void regenBounds() {
        this.bounds = new com.ben.math.geom.Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public void move(double x, double y) {
        this.x+=x;
        this.y+=y;
        if (this.x<0 || this.x>g.canvas.getWidth()-((Rectangle)this.drawable).width) this.x -= x; 
        if (this.y<0 || this.y>g.canvas.getHeight()-((Rectangle)this.drawable).height*2) this.y -= y;
        ((Rectangle)this.drawable).setX(this.x);
        ((Rectangle)this.drawable).setY(this.y);
    }
    
    public void rotate(int deg) {
        this.drawable.setRotation(this.drawable.getRotation()+deg);
        if (this.drawable.getRotation()<0) this.drawable.setRotation(360+this.drawable.getRotation());
        if (this.drawable.getRotation()>360) this.drawable.setRotation(360-this.drawable.getRotation());
    }
    
}
