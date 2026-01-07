package org.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {

    private Circle circleState;

    private Circle showCircleHandler(VBox root){
        Circle c = new Circle(30);
        root.getChildren().add(c);

        return c;
    }

    @Override
    public void start(Stage stage) {
        Button addCButton = new Button("Add circle");

        addCButton.setMinSize(200, 50);
        addCButton.setMaxSize(200, 50);
        addCButton.setStyle("""
            -fx-background-color: #3498db;
            -fx-text-fill: white;
            -fx-font-size: 14px;
        """);

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(addCButton);

        addCButton.setOnAction(e -> {
            if(this.circleState == null){
                this.circleState = showCircleHandler(root);
                addCButton.setText("Remove circle");
            }else{
                root.getChildren().remove(circleState);
                this.circleState = null;
                addCButton.setText("Add circle");
            }
        });

        Scene scene = new Scene(root, 800, 800);

        stage.setTitle("Hello JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*

I want to add keys moving to the circle.
So i can move it

 */