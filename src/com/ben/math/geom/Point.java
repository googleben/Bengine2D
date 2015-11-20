package com.ben.math.geom;


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
    
    public static double dist(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.getX()-p1.getX(), 2)+Math.pow(p2.getX()-p1.getX(), 2));
    }
    
    public boolean equals(Point p) {
        return p.getX()==x && p.getY()==y;
    }
    
    public String toString() {
        return "("+x+","+y+")";
    }
    
}
