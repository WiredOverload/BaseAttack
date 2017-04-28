/*
 * Honor Code: I pledge that this program represents my own
 *     program code. I received help from (enter the names of
 *     others that helped with the assignment, write no one if
 *     you received no help) in designing and debugging my program.
 */
package baseattack;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Michael Hodges
 */
public class BaseAttack extends Application {

    @Override
    public void start(Stage primaryStage) {
        Player p1 = new Player(100, 0, 128); //user player
        Player p2 = new Player(100, 0, 1024 - 128);//AI player
        
        
        Button btn = new Button();
        btn.setText("Start Game");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                new AnimationTimer() {
                    int tick = 0;
                    public void handle(long currentNanoTime) {
                        //actual gameloop code goes here
                        tick++;
                        p1.update(tick);
                    }
                }.start();
            }
        });

        //neccessary javafx stuff
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Base Attack!");
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(1024, 512);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
