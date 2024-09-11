package org.example.paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Paint extends Application {
    @Override


    public void start(Stage stage) throws IOException {
        // title
        stage.setTitle("Definitely not Paint");

        // sidebar

        // label for tools
        Label tools = new Label("Tools");
        tools.setStyle("-fx-padding: 10px;");
        // underline
        tools.setUnderline(true);

        // radiobuttons style
        String radioStyle = "-fx-padding: 10px;";

        // radio buttons
        RadioButton Draw = new RadioButton("Draw");
        RadioButton Move = new RadioButton("Move");
        RadioButton Delete = new RadioButton("Delete");
        Draw.setStyle(radioStyle);
        Move.setStyle(radioStyle);
        Delete.setStyle(radioStyle);

        // toggle group
        ToggleGroup Btn = new ToggleGroup();
        Draw.setToggleGroup(Btn);
        Move.setToggleGroup(Btn);
        Delete.setToggleGroup(Btn);
        // default selected
        Draw.setSelected(true);

        // combo box
        ComboBox<String> shapes = new ComboBox<String>();
        shapes.getItems().addAll("Line", "Rectangle", "Ellipse");
        shapes.setValue("Line");
        HBox Shapes = new HBox(shapes);

        // style the shapes
        Shapes.setStyle("-fx-padding: 10px;");

        // vbox
        VBox sidebarTop = new VBox( tools, Draw, Move, Delete, Shapes);

        // label for color
        Label color = new Label("Color");
        color.setUnderline(true);
        color.setStyle("-fx-padding: 10px;");

        // slider for color

        // red
        // name
        Label red = new Label("Red");
        Slider Red = new Slider();
        Red.setMin(0);
        Red.setMax(255);
        Red.setValue(0);
        Red.setShowTickLabels(true);
        // green
        Label green = new Label("Green");
        Slider Green = new Slider();
        Green.setMin(0);
        Green.setMax(255);
        Green.setValue(0);
        Green.setShowTickLabels(true);
        // blue
        Label blue = new Label("Blue");
        Slider Blue = new Slider();
        Blue.setMin(0);
        Blue.setMax(255);
        Blue.setValue(0);
        Blue.setShowTickLabels(true);

        // vbox for sliders
        VBox sliders = new VBox(red,Red,green,Green,blue,Blue);
        sliders.setStyle("-fx-padding: 10px;");

        // label for current color
        Label currentColor = new Label("Current Color");
        currentColor.setStyle("-fx-padding: 10px;");

        // vbox
        VBox sidebarBottom = new VBox(color,sliders,currentColor);

        // vbox for sidebar
        VBox sidebar = new VBox(sidebarTop, sidebarBottom);

        // style of sidebar
        sidebar.setStyle("-fx-border-style: solid;-fx-background-color: white");

        // the canvas
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: white;-fx-border-style: solid;");
        canvas.setPrefSize(800, 600);

        // slider events
        Red.valueProperty().addListener(e -> {
            currentColor.setStyle("-fx-padding: 10px;-fx-background-color: rgb(" + (int) Red.getValue() + "," + (int) Green.getValue() + "," + (int) Blue.getValue() + ");");
        });
        Green.valueProperty().addListener(e -> {
            currentColor.setStyle("-fx-padding: 10px;-fx-background-color: rgb(" + (int) Red.getValue() + "," + (int) Green.getValue() + "," + (int) Blue.getValue() + ");");
        });
        Blue.valueProperty().addListener(e -> {
            currentColor.setStyle("-fx-padding: 10px;-fx-background-color: rgb(" + (int) Red.getValue() + "," + (int) Green.getValue() + "," + (int) Blue.getValue() + ");");
        });

        // canvas events
        AtomicInteger x1 = new AtomicInteger();
        AtomicInteger y1 = new AtomicInteger();
        canvas.setOnMousePressed(e -> {
            x1.set((int)e.getX());
            y1.set((int)e.getY());
        });

        // shapeCollection
        ShapeCollection shapeCollection = new ShapeCollection();

        // draw
        canvas.setOnMouseDragged(e -> {
            if(Draw.isSelected()){
                int x2 = (int)e.getX();
                int y2 = (int)e.getY();
                if(shapes.getValue().equals("Line")) {
                    Line line = new Line(x1.get(), y1.get(), x2, y2);
                    line.setStyle("-fx-stroke: rgb(" + (int) Red.getValue() + "," + (int) Green.getValue() + "," + (int) Blue.getValue() + ");");
                    canvas.getChildren().add(line);
                    shapeCollection.addShape(line);
                    x1.set(x2);
                    y1.set(y2);
                }
                else if(shapes.getValue().equals("Rectangle")){
                    Rectangle rect = new Rectangle(x1.get(), y1.get(), x2 - x1.get(), y2 - y1.get());
                    rect.setStyle("-fx-fill: rgb(" + (int) Red.getValue() + "," + (int) Green.getValue() + "," + (int) Blue.getValue() + ");");
                    canvas.getChildren().add(rect);
                    shapeCollection.addShape(rect);
                }
                else if(shapes.getValue().equals("Ellipse")){
                    Ellipse ellipse = new Ellipse(x1.get(), y1.get(), Math.abs(x2 - x1.get()), Math.abs(y2 - y1.get()));
                    ellipse.setStyle("-fx-fill: rgb(" + (int) Red.getValue() + "," + (int) Green.getValue() + "," + (int) Blue.getValue() + ");");
                    canvas.getChildren().add(ellipse);
                    shapeCollection.addShape(ellipse);
                }
            }
            else if(Move.isSelected()){
                int x2 = (int)e.getX();
                int y2 = (int)e.getY();
                for(Shape shape : shapeCollection.getAllShapes()){
                    if(shape instanceof Line line){
                        line.setStartX(line.getStartX() + x2 - x1.get());
                        line.setStartY(line.getStartY() + y2 - y1.get());
                        line.setEndX(line.getEndX() + x2 - x1.get());
                        line.setEndY(line.getEndY() + y2 - y1.get());
                    }
                    else if(shape instanceof Rectangle rect){
                        rect.setX(rect.getX() + x2 - x1.get());
                        rect.setY(rect.getY() + y2 - y1.get());
                    }
                    else if(shape instanceof Ellipse ellipse){
                        ellipse.setCenterX(ellipse.getCenterX() + x2 - x1.get());
                        ellipse.setCenterY(ellipse.getCenterY() + y2 - y1.get());
                    }
                }
                x1.set(x2);
                y1.set(y2);
            }
            else if(Delete.isSelected()){
                int x2 = (int)e.getX();
                int y2 = (int)e.getY();
                ArrayList<Shape> shapesToRemove = new ArrayList<Shape>();
                for(Shape shape : shapeCollection.getAllShapes()){
                    if(shape instanceof Line line){
                        if(line.contains(x2, y2)){
                            shapesToRemove.add(line);
                        }
                    }
                    else if(shape instanceof Rectangle rect){
                        if(rect.contains(x2, y2)){
                            shapesToRemove.add(rect);
                        }
                    }
                    else if(shape instanceof Ellipse ellipse){
                        if(ellipse.contains(x2, y2)){
                            shapesToRemove.add(ellipse);
                        }
                    }
                }
                for(Shape shape : shapesToRemove){
                    canvas.getChildren().remove(shape);
                    shapeCollection.getAllShapes().remove(shape);
                }
            }
        });



        // hbox for canvas and sidebar
        HBox root = new HBox(sidebar, canvas);

        // scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();








    }

    public static void main(String[] args) {
        launch();
    }
}