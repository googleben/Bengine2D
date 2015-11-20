package com.ben.example.move;

import com.ben.game.*;
import javafx.scene.paint.Color;

public class Trail extends GameRectangle {
	
	public int life;
	public Game g;
	
	private int tick = 1;
	
	public Trail(double x, double y, int rot, Game g) {
		this(x,y,rot,g,Color.BLACK);
	}
	
	public Trail(double x, double y, int rot, Game g, Color c) {
		super(x,y,10,10,rot,c);
		this.life = 200;
		this.g = g;
	}
	
	public void tick() {
	    if (tick++%3==0) {
    		if (color!=null) { setColor(this.color.brighter());
    		setColor(this.color.desaturate()); }
    		if (life--==0) g.remove(this);
    		tick = 1;
	    }
	}
	
}