package com.ben.math.geom;

import java.util.Arrays;
import java.util.HashSet;

public class Rectangle extends Shape {
    
    private LineSegment[] edges;
    
    private Point[] vertices;
    private Point center;
    
    private double width;
    private double height;
    
    public Rectangle(LineSegment l1, LineSegment l2, LineSegment l3, LineSegment l4) {
        edges = new LineSegment[] {l1,l2,l3,l4};
        HashSet<Point> points = new HashSet<Point>();
        HashSet<Double> lengths = new HashSet<Double>();
        for (LineSegment l : edges) { points.add(l.getP1()); points.add(l.getP2()); lengths.add(l.distance()); }
        vertices = points.toArray(new Point[0]);
        Double[] lengthsArr = lengths.toArray(new Double[0]);
        width = lengthsArr[0];
        height = lengthsArr.length==1 ? width : lengthsArr[1];
    }
    
    public Rectangle(double x, double y, double width, double height, double rotation) {
        getLineSegments(x,y,width,height,rotation);
    }
    
    public Rectangle(double x, double y, double width, double height) {
        this(x,y,width,height,0);
    }
    
    private void getLineSegments(double x, double y, double width, double height, double rotation) {
        
        center = new Point(x+width/2, y+height/2);
        
        Point c1 = new Point(x,y);
        Point c2 = new Point(x+width,y);
        Point c3 = new Point(x+width,y+height);
        Point c4 = new Point(x,y+height);
        c1.rotate(rotation, center);
        c2.rotate(rotation, center);
        c3.rotate(rotation, center);
        c4.rotate(rotation, center);
        
        vertices = new Point[] {c1,c2,c3,c4};
        edges = new LineSegment[] {
                new LineSegment(c1,c2), //top
                new LineSegment(c2,c3), //right
                new LineSegment(c3,c4), //bottom
                new LineSegment(c4,c1)  //left
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

    public boolean intersects(LineSegment l) {
        return l.intersects(edges[0]) || l.intersects(edges[1]) || l.intersects(edges[2]) || l.intersects(edges[3]);
    }
    
    @Override
    public String toString() {
        return Arrays.toString(vertices);
    }
    
}

class Triangle extends Shape {
    
    public Point[] vertices;
    public LineSegment[] edges;
    
    public Triangle(Point p1, Point p2, Point p3) {
        vertices = new Point[] {p1,p2,p3};
        edges = new LineSegment[] {
                new LineSegment(p1,p2),
                new LineSegment(p2,p3),
                new LineSegment(p3,p1)
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

    public boolean intersects(LineSegment l) {
        // TODO Auto-generated method stub
        return false;
    }
    
    

    public double getArea() {
        LineSegment base = edges[0];
        LineSegment height = new LineSegment(base.midpoint(), vertices[2]);
        return .5*base.distance()*height.distance();
    }

}

