package org.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {

    private Circle circleState;
    private final float circleMovingStep = 3;
    private Pane gamePane;

    private void circleKeyHandler(KeyEvent keyEvent) {
        double originX = circleState.getCenterX();
        double originY = circleState.getCenterY();

        double newX = 0;
        double newY = 0;

        double paneW = gamePane.getWidth();
        double paneH = gamePane.getHeight();

        double circleR = circleState.getRadius();

        switch (keyEvent.getCode()) {
            case W -> {
                newY = originY - circleMovingStep;
            }

            case S -> {
                newY = originY + circleMovingStep;
            }

            case D -> {
                newX = originX + circleMovingStep;
            }

            case A -> {
                newX = originX - circleMovingStep;
            }
            default -> {
            }
        }

        // System.out.println();
        // System.out.println("Pane W" + paneW);
        // System.out.println("Pane H" + paneH);

        // System.out.println("Circle R" + circleR);

        // System.out.println("Circle X" + newX);
        // System.out.println("Circle Y" + newY);


        if(newY - circleR >= 0 && newY + circleR <= paneH){
            circleState.setCenterY(newY);
        }

        if(newX - circleR >= 0 && newX + circleR <= paneW){
            circleState.setCenterX(newX);
        }

    }

    EventHandler<KeyEvent> sceneKeyHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            if (circleState != null) {
                circleKeyHandler(keyEvent);
            }
        }
    };

    private Circle showCircleHandler(Pane pane) {
        Circle c = new Circle(30);

        double paneW = pane.getScene().getWidth();
        double paneH = pane.getScene().getHeight();

        c.setCenterX(paneW / 2);
        c.setCenterY(paneH / 2);

        pane.getChildren().add(c);

        return c;
    }

    private Button addCircleButton(Pane pane) {
        Button b = new Button("Add circle");

        b.setMinSize(200, 50);
        b.setMaxSize(200, 50);
        b.setStyle("""
                    -fx-background-color: #3498db;
                    -fx-text-fill: white;
                    -fx-font-size: 14px;
                """);

        b.setOnAction(e -> {
            if (this.circleState == null) {
                this.circleState = showCircleHandler(pane);
                b.setText("Remove circle");
            } else {
                pane.getChildren().remove(circleState);
                this.circleState = null;
                b.setText("Add circle");
            }
        });

        return b;
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        this.gamePane = new Pane();
        VBox ui = new VBox(20);

        Button addCircleButton = this.addCircleButton(this.gamePane);

        ui.setAlignment(Pos.CENTER);
        ui.getChildren().add(addCircleButton);

        root.setTop(ui);
        root.setCenter(this.gamePane);

        Scene scene = new Scene(root, 800, 800);
        scene.setOnKeyPressed(this.sceneKeyHandler);

        stage.setTitle("Hello JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
