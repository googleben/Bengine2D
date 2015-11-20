package com.ben.tanktrouble;

import java.util.ArrayList;
import com.ben.game.Entity;
import com.ben.game.Game;
import com.ben.game.GameObject;
import com.ben.graphics.KeyHandler;
import com.ben.graphics.Rectangle;
import com.ben.math.geom.LineSegment;
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
    
    private Color color;
    
    public Tank(Game g, double x, double y, int rotation, Color color) {
        this.x = x; this.y = y;
        //this.it = it;
        
        color = Color.BLACK;
        
        Rectangle r = new Rectangle(x,y,25,60,color);
        this.drawable = r;
        setColor(Color.BLACK);
        
        KeyHandler moveHandler = (k) -> {
            if (k.isPressed(up)) move(speed*Math.sin(Math.toRadians(this.drawable.getRotation())),-speed*Math.cos(Math.toRadians(this.drawable.getRotation())));
            if (k.isPressed(down)) move(-speed*Math.sin(Math.toRadians(this.drawable.getRotation())),speed*Math.cos(Math.toRadians(this.drawable.getRotation())));
            if (k.isPressed(right)) rotate(rotSpeed);
            if (k.isPressed(left)) rotate(-rotSpeed);
        };
        this.g = g;
        g.addOnKeypress(moveHandler);
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
    public Shape getBounds() {
        regenBounds();
        return bounds;
    }
    
    public LineSegment top = new LineSegment(0,0,500,0);
    public com.ben.math.geom.Rectangle rTop = new com.ben.math.geom.Rectangle(0,0,500,0,0);
    
    @SuppressWarnings("unchecked")
    public void move(double x, double y) {
        com.ben.math.geom.Rectangle rec = new com.ben.math.geom.Rectangle(this.x+x, this.y+y, this.width, this.height);
        if (rec.intersects(top)) return;
        for (GameObject o : (ArrayList<GameObject>)g.objects.clone()) if (!o.equals(this)) if (o instanceof ICollidable) { if (((ICollidable)o).getBounds().intersects(rec)) { System.out.println("hit"); return; } }
        setX(this.x+x);
        setY(this.y+y);
        
    }
    
    public void rotate(int deg) {
        this.drawable.setRotation(this.drawable.getRotation()+deg);
        if (this.drawable.getRotation()<0) this.drawable.setRotation(360+this.drawable.getRotation());
        if (this.drawable.getRotation()>360) this.drawable.setRotation(360-this.drawable.getRotation());
    }
    
    public void setColor(Color c) {
    	this.color = c;
    	((Rectangle)this.drawable).setColor(c);
    }
    public Color getColor() { return color; }
    
}
