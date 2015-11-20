package com.ben.example.move;

import com.ben.game.*;
import com.ben.graphics.Rectangle;
import javafx.scene.paint.Color;

public class Trail extends GameRectangle {
	
	public int rotation;
	public int life;
	public Game g;
	
	private int tick = 1;
	
	public Trail(double x, double y, int rot, Game g) {
		this(x,y,rot,g,Color.BLACK);
	}
	
	public Trail(double x, double y, int rot, Game g, Color c) {
		super(x,y,10,10,rot,c);
		this.color = c;
		this.life = 200;
		this.g = g;
		this.drawable = new Rectangle(x,y,10,10,color);
		drawable.setRotation(rot);
	}
	
	public void tick() {
	    if (tick++%3==0) {
    		if (color!=null) { setColor(this.color.brighter());
    		setColor(this.color.desaturate()); } else System.out.println("null color");
    		if (life--==0) g.remove(this);
    		tick = 1;
	    }
	}
	
}