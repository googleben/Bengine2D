package com.ben.game;

import com.ben.graphics.Rectangle;

import javafx.scene.paint.Color;

public abstract class GameRectangle extends DrawableGameObject {
	
	protected double width;
	protected double height;
	protected Color color;
	
	public GameRectangle(double x, double y, double width, double height, double rotation, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void setWidth(double width) {
		this.width = width;
		Rectangle r = drawableToRectangle();
		r.setWidth(width);
		this.drawable = r;
	}
	public double getWidth() {return width;}
	
	public void setHeight(double height) {
		this.height = height;
		Rectangle r = drawableToRectangle();
		r.setHeight(height);
		this.drawable = r;
	}
	public double getHeight() {return height;}
	
	protected Rectangle drawableToRectangle() {
		return (Rectangle)drawable;
	}
	
	public Color getColor() { return color; }
	public void setColor(Color c) { this.color = c; drawableToRectangle().setColor(c); }
	
}
