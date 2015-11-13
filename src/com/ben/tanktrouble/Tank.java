package com.ben.tanktrouble;

import com.ben.game.Entity;
import com.ben.math.geom.Shape;
import javafx.scene.paint.Color;

public class Tank extends Entity implements ICollidable {
    
    public com.ben.math.geom.Rectangle bounds;
    
    public double width;
    public double height;
    
    public Tank(double x, double y, int rotation, Color c) {
        this.x = x;
        this.y = y;
        this.color = c;
        this.rotation = rotation;
        this.width = 10;
        this.height = 50;
    }
    
    public Tank(double x, double y, int rotation) {
        this(x,y,rotation,Color.BLACK);
    }
    
    public Tank(double x, double y, Color c) {
        this(x,y,0,c);
    }
    
    public Tank(double x, double y) {
        this(x,y,0,Color.BLACK);
    }
    
    public boolean colliding(Shape s) {
        regenBounds();
        return bounds.intersects(s);
    }
    
    public void regenBounds() {
        this.bounds = new com.ben.math.geom.Rectangle(this.x, this.y, this.width, this.height);
    }
    
}
