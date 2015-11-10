package com.ben.example.move;

import com.ben.game.*;
import com.ben.graphics.Rectangle;

import javafx.scene.paint.Color;

public class Trail extends DrawableGameObject {
	
	public int rotation;
	public int life;
	public Color color;
	public Game g;
	
	public Trail(double x, double y, int rot, Game g) {
		this.x = x;
		this.y = y;
		this.rotation = rot;
		this.color = Color.BLACK;
		this.life = 200;
		this.g = g;
		this.drawable = new Rectangle(x,y,10,10,color);
		drawable.setRotation(rot);
	}
	
	public void tick() {
		this.color = this.color.brighter();
		((Rectangle)this.drawable).color = this.color;
		if (life==0) g.remove(this);
	}
	
}
