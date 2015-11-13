package com.ben.math.geom;

import java.util.HashSet;

public class Rectangle extends Shape {
    
    private Line[] edges;
    
    private Point[] vertices;
    
    private double width;
    private double height;
    
    public Rectangle(Line l1, Line l2, Line l3, Line l4) {
        edges = new Line[] {l1,l2,l3,l4};
        HashSet<Point> points = new HashSet<Point>();
        HashSet<Double> lengths = new HashSet<Double>();
        for (Line l : edges) { points.add(l.getP1()); points.add(l.getP2()); lengths.add(l.distance()); }
        vertices = points.toArray(new Point[0]);
        Double[] lengthsArr = lengths.toArray(new Double[0]);
        width = lengthsArr[0];
        height = lengthsArr.length==1 ? width : lengthsArr[1];
    }
    
    public Rectangle(double x, double y, double width, double height, double rotation) {
        getLines(x,y,width,height,rotation);
    }
    
    public Rectangle(double x, double y, double width, double height) {
        this(x,y,width,height,0);
    }
    
    private void getLines(double x, double y, double width, double height, double rotation) {
        Point c1 = new Point(x,y);
        Point c2 = new Point(x+width*Math.cos(Math.toRadians(rotation)),y+height*Math.sin(Math.toRadians(rotation))); //top right
        Point c3 = new Point(x+width*Math.cos(Math.toRadians(rotation)),y+height*Math.cos(Math.toRadians(rotation))); //bottom right
        Point c4 = new Point(x+width*Math.sin(Math.toRadians(rotation)),y+height*Math.cos(Math.toRadians(rotation))); //bottom left
        vertices = new Point[] {c1,c2,c3,c4};
        edges = new Line[] {
                new Line(c1,c2), //top
                new Line(c2,c3), //right
                new Line(c3,c4), //bottom
                new Line(c4,c1)  //left
        };
    }
    
    public double getArea() {
        return width*height;
    }
    
    public Point[] getVertices() { return vertices; }

    public boolean contains(Point p) {
        Triangle[] tris = new Triangle[] {
                new Triangle(vertices[0],p,vertices[3]),
                new Triangle(vertices[3],p,vertices[2]),
                new Triangle(vertices[2],p,vertices[1]),
                new Triangle(p,vertices[1],vertices[0])
        };
        double areaSum = 0;
        for (Triangle t : tris) areaSum+=t.getArea();
        return areaSum<=getArea();
    }

    public boolean intersects(Shape s) {
        if (s instanceof Rectangle) {
            for (Point p : ((Rectangle)s).getVertices()) if (this.contains(p)) return true;
            for (Point p : this.getVertices()) if (s.contains(p)) return true;
            return false;
        }
        return false;
    }

    public boolean intersects(Line l) {
        return l.intersects(edges[0]) || l.intersects(edges[1]) || l.intersects(edges[2]) || l.intersects(edges[3]);
    }
    
}

class Triangle extends Shape {
    
    public Point[] vertices;
    public Line[] edges;
    
    public Triangle(Point p1, Point p2, Point p3) {
        vertices = new Point[] {p1,p2,p3};
        edges = new Line[] {
                new Line(p1,p2),
                new Line(p2,p3),
                new Line(p3,p1)
        };
    }

    public boolean contains(Point p) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean intersects(Shape s) {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean intersects(Line l) {
        // TODO Auto-generated method stub
        return false;
    }

    public double getArea() {
        Line base = edges[0];
        Line height = new Line(base.midpoint(), vertices[2]);
        return .5*base.distance()*height.distance();
    }

}

