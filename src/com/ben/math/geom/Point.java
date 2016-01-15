package com.ben.math.geom;

import java.awt.geom.AffineTransform;


public class Point {
    
    private double x;
    private double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
    
    public String toString() { return "("+x+", "+y+")"; }
    
    public void rotate(double deg, Point origin) {
        double[] pt = {x, y};
        AffineTransform.getRotateInstance(Math.toRadians(deg), origin.x, origin.y).transform(pt, 0, pt, 0, 1);
        this.x = pt[0]; this.y = pt[1];
    }
    
    public static void main(String... args) {
        Rectangle r = new Rectangle(-1,-1,2,2,45);
        System.out.println(r);
    }
    
}
