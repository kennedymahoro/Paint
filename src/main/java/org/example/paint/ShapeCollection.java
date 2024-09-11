package org.example.paint;
import java.util.ArrayList;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class ShapeCollection{
    private ArrayList<Line> lines;
    private ArrayList<Rectangle> rectangles;
    private ArrayList<Ellipse> ellipses;

    public ShapeCollection(){
        lines = new ArrayList<Line>();
        rectangles = new ArrayList<Rectangle>();
        ellipses = new ArrayList<Ellipse>();
    }
    // add shape
    public void addShape(Shape shape){
        if(shape instanceof Line){
            lines.add((Line)shape);
        }
        else if(shape instanceof Rectangle){
            rectangles.add((Rectangle)shape);
        }
        else if(shape instanceof Ellipse){
            ellipses.add((Ellipse)shape);
        }
    }
    // get lines
    public ArrayList<Line> getLines(){
        return lines;
    }
    // get rectangles
    public ArrayList<Rectangle> getRectangles(){
        return rectangles;
    }
    // get ellipses
    public ArrayList<Ellipse> getEllipses(){
        return ellipses;
    }
    // get all shapes
    public ArrayList<Shape> getAllShapes(){
        ArrayList<Shape> allShapes = new ArrayList<Shape>();
        allShapes.addAll(lines);
        allShapes.addAll(rectangles);
        allShapes.addAll(ellipses);
        return allShapes;
    }

}
