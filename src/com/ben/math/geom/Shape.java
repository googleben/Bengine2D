package com.ben.math.geom;


public abstract class Shape {
    
    private double x;
    private double y;
    
    public abstract boolean contains(Point p);
    public abstract boolean intersects(LineSegment l);
    public abstract boolean intersects(Shape s);
    
    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    
    public abstract double getArea();
    
}
