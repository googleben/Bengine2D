package com.ben.example.move;

import java.util.ArrayList;
import com.ben.game.*;
import com.ben.graphics.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends Entity {
    
    public static double speed = 1;
    public static int rotSpeed = (int)speed*2;
    
    public boolean it;
    
    public KeyCode up;
    public KeyCode down;
    public KeyCode left;
    public KeyCode right;
    
    private Game g;
    
    private int lastIt = 0;
    
    private int tick = 1;
    
    public Player(Game g, double x, double y, KeyCode up, KeyCode down, KeyCode left, KeyCode right) {
    	this(g,x,y,up,down,left,right,false);
    }
    
    public Player(Game g, double x, double y, KeyCode up, KeyCode down, KeyCode left, KeyCode right, boolean it) {
        this.x = x; this.y = y;
        //this.it = it;
        
        this.color = Color.BLACK;
        
        Rectangle r = new Rectangle(x,y,10,10,color);
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
        
        g.addOnKeypress(moveHandler);
        this.g = g;
        if (it) runIt();
    }
    
    public Player(Game g, double x, double y) {
        this(g, x, y, KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D);
    }
    
    public Player(Game g) {
        this(g,0,0);
    }
    
	@SuppressWarnings("unchecked")
	public void tick() {
    	if (lastIt>0) lastIt--;
    	if (tick++%3==0) { g.add(new Trail(x,y,this.drawable.getRotation(),g,color)); tick=0; }
    	for (GameObject o : (ArrayList<GameObject>)g.objects.clone()) {
    		if (o instanceof Player && !((Player)o).equals(this)) {
    			Player other = (Player)o;
    			if (it && collides(other)) { 
    				System.out.println("Collide!");
    				if (other!=null) { this.runIt(); other.runIt(); } 
    			}
    		}
    	}
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
    
    public boolean collides(Player p) {
    	double centerX = p.drawable.getX()-5;
    	double centerY = p.drawable.getY()-5;
    	double tX = x-5;
    	double tY = y-5;
    	double dist = Math.sqrt( Math.pow(centerX-tX, 2) + Math.pow(centerY-tY, 2) );
    	return dist<=10;
    }
    
    public void runIt() {
    	System.out.println("Runit!");
    	if (lastIt!=0) return;
    	lastIt = 400;
    	this.it = !it;
    	this.color = it ? Color.RED : Color.BLACK;
    	((DrawableShape)drawable).color = color;
    	drawable.remakeNode();
    }
    
}
