package com.ben.game;

import com.ben.graphics.Rectangle;
import com.ben.math.geom.Shape;
import javafx.scene.paint.Color;

public abstract class GameRectangle extends DrawableGameObject {
	
	protected double width;
	protected double height;
	protected Color color;
	
	public GameRectangle(double x, double y, double width, double height, int rotation, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.rotation = rotation;
		this.drawable = new Rectangle(x,y,width,height,color);
		drawable.setRotation((int)rotation);
	}
	
	public void setWidth(double width) {
		this.width = width;
		drawableToRectangle().setWidth(width);
	}
	public double getWidth() {return width;}
	
	public void setHeight(double height) {
		this.height = height;
		drawableToRectangle().setHeight(height);
	}
	public double getHeight() {return height;}
	
	protected Rectangle drawableToRectangle() {
		return (Rectangle)drawable;
	}
	
	public Color getColor() { return color; }
	public void setColor(Color c) {
	    this.color = c;
	    drawableToRectangle().setColor(c);
	}
	
	public com.ben.math.geom.Rectangle getBounds() {
	    return new com.ben.math.geom.Rectangle(x, y, width, height);
	}
	public boolean colliding(Shape s) {
	    return getBounds().intersects(s);
	}
	
}
