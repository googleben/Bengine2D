package com.ben.math.geom;


public class Line {
    
    public final String LINEAR_EQUATION = "y=mx+b";
    
    private Point p1;
    private Point p2;
    
    private double[] boundsX;
    private double[] boundsY;
    
    private String equation;
    private double m;
    private double b;
    
    private boolean vertical;
    
    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.boundsX = new double[] {Math.min(p1.getX(), p2.getX()), Math.max(p1.getX(), p2.getX())};
        this.boundsY = new double[] {Math.min(p1.getY(), p2.getX()), Math.max(p1.getY(), p2.getY())};
        remakeEquation();
    }
    
    public Point getP1() { return p1; }
    public Point getP2() { return p2; }
    public void setP1(Point p1) { this.p1 = p1; }
    public void setP2(Point p2) { this.p2 = p2; }
    
    public double[] getBoundsX() { return boundsX; }
    public double[] getBoundsY() { return boundsY; }
    
    private void findVertical() { this.vertical = p1.getX()==p2.getX(); }
    
    public boolean isVertical() { return vertical; }
    
    private void remakeEquation() { 
        findVertical();
        if (!vertical) { findB(); this.equation = "y="+m+"x"+"+"+b; }
        else { this.m = p1.getX(); this.b = Integer.MAX_VALUE; this.equation = "x="+m; }
    }
    
    private void findM() { this.m = (p1.getX()-p2.getX())/(p1.getY()-p2.getY()); }
    private void findB() { findM(); this.b = p1.getY()-(m*(p1.getX())); }
    
    public double getM() { return m; }
    public double getB() { return b; }
    
    public String getEquation() { return equation; }
    
    public boolean equals(Line l) { return ( (p1==l.getP1()) && (p2==l.getP2()) ); }
    public boolean equationEquals(Line l) { return (m==l.getM()) && (b==l.getB()); }
    
    public boolean contains(Point p) { return (new Line(p1, p)).equationEquals(this); }
    
    public boolean intersects(Line l) { return (this.vertical && l.isVertical()) ? this.m==l.getM() : this.b!=l.getB(); }
    
    public boolean intersects(Shape s) { return s.intersects(this); }
    
    public double distance() { return Math.sqrt(Math.pow(p2.getX()-p1.getX(), 2)+Math.pow(p2.getY()-p1.getY(), 2)); }
    
    public Point midpoint() { return new Point((p2.getX()-p1.getX()), m*(p2.getX()-p1.getX())+b); }
    
}
