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
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
        Image spaceBase720 = new Image("Assets/spaceBase720.png");//background stars
        Image spaceClouds720 = new Image("Assets/spaceClouds720.png");
        Image spaceClouds720v2 = new Image("Assets/spaceClouds720v2.png");

        //Added some style to button 
        Button btn = new Button();
        btn.setText("Start Game");
        btn.setFont(Font.font("Impact", 50));
        btn.setStyle("-fx-text-fill: black; -fx-background-color: red;");
        btn.setTranslateY(40);

        //Added title to main menu and added style
        Text title = new Text();
        title.setText("Base Attack!");
        title.setFont(Font.font("Impact", 80));
        title.setStroke(Color.RED);
        title.setFill(Color.BLACK);
        title.setTranslateY(-80);

        //neccessary javafx stuff
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(title);
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("Base Attack");
        primaryStage.setScene(scene);
        Canvas canvas = new Canvas(1280, 720);
        root.getChildren().add(canvas);
        canvas.toBack();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                new AnimationTimer() {
                    int tick = 0;
                    int cloudTimer = 0;

                    public void handle(long currentNanoTime) {
                        //actual gameloop code goes here
                        tick++; //tick isn't really necessary anymore as we have switched from
                        //time based movement to coordinate movement
                        p1.update();
                        gc.drawImage(spaceBase720, 0, 0);
                        gc.drawImage(spaceClouds720, cloudTimer % 2560, 0);
                        gc.drawImage(spaceClouds720, (cloudTimer % 2560) - 2560, 0);
                        gc.drawImage(spaceClouds720v2, cloudTimer / 2, 0);
                        gc.drawImage(spaceClouds720v2, (cloudTimer / 2) - 2560, 0);
                        if (cloudTimer == 2560 * 2) {
                            cloudTimer = 0;
                        } else {
                            cloudTimer++;
                        }
                    }
                }.start();
            }
        });

        //for some reason this wasn't running in the button action handler, so it's temporarily here to see if it works
        new AnimationTimer() {
            int tick = 0;
            int cloudTimer = 0;

            public void handle(long currentNanoTime) {
                //actual gameloop code goes here
                tick++; //tick isn't really necessary anymore as we have switched from
                //time based movement to coordinate movement
                p1.update();
                gc.drawImage(spaceBase720, 0, 0);
                gc.drawImage(spaceClouds720, cloudTimer % 2560, 0);
                gc.drawImage(spaceClouds720, (cloudTimer % 2560) - 2560, 0);
                gc.drawImage(spaceClouds720v2, cloudTimer / 2, 0);
                gc.drawImage(spaceClouds720v2, (cloudTimer / 2) - 2560, 0);
                if (cloudTimer == 2560 * 2) {
                    cloudTimer = 0;
                } else {
                    cloudTimer++;
                }
            }
        }.start();

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
