/*
 * Honor Code: I pledge that this program represents my own
 *     program code. I received help from (enter the names of
 *     others that helped with the assignment, write no one if
 *     you received no help) in designing and debugging my program.
 */
package baseattack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Michael Hodges
 * 
 * @todo
 *      This is really in no particular order, feel free to add
 *      Add more buttons, 4 to each base
 *      Add main button functionality
 *      Add price text to buttons
 *      Add Health bar
 *      Add Minion logic
 *          ranged
 *      Add functionality to pause menu
 *          ability to access it
 *          write current game state to file
 *          audio settings
 *          buttons for all the above
 *      Add sound effects
 *          explosions
 *          pew pew laser sounds
 *          space melee sound?
 *          music
 *      Add VFX
 *          hit indicator (smallExplosion)
 *          minion death explosions
 *          minion death pieces that fly out
 *      Add win/lose condition
 *      Add functionality to spawning buttons
 *      Add very light third cloud effect over everything?
 * @bugs
 *      indexOutOfBounds exception any time a base dies
 *          Player object line 80
 *          game doesn't actually crash though
 */
public class BaseAttack extends Application {

    @Override
    public void start(Stage primaryStage) {
        Player p1 = new Player(false, 300, 0); //user player
        Player p2 = new Player(true, 300, 0);//AI player
        
        Image spaceBase720 = new Image("Assets/spaceBase720.png");//background stars
        Image spaceClouds720v1 = new Image("Assets/spaceClouds720.png");//clouds right side up
        Image spaceClouds720v2 = new Image("Assets/spaceClouds720v2.png");//clouds upside down

        //Added all buttons we needed
        Button btn = new Button();
        btn.setText("Start Game");
        btn.setFont(Font.font("Impact", 50));
        btn.setStyle("-fx-text-fill: black; -fx-background-color: red;");
        btn.setTranslateY(40);

        Button mbtn = new Button();
        mbtn.setText("Ranged Ship");
        mbtn.setFont(Font.font("Impact"));
        mbtn.setTranslateX(200);
        mbtn.setTranslateY(-330);

        Button mbtn2 = new Button();
        mbtn2.setText("Tank Ship");
        mbtn2.setFont(Font.font("Impact"));
        mbtn2.setTranslateX(300);
        mbtn2.setTranslateY(-330);

        Button mbtn3 = new Button();
        mbtn3.setText("Normal Ship");
        mbtn3.setFont(Font.font("Impact"));
        mbtn3.setTranslateX(400);
        mbtn3.setTranslateY(-330);

        Button ubtn = new Button();
        ubtn.setText("Upgrade Base");
        ubtn.setFont(Font.font("Impact"));
        ubtn.setTranslateX(540);
        ubtn.setTranslateY(-330);

        Button pbtn = new Button();
        pbtn.setText("Pause Game");
        pbtn.setFont(Font.font("Impact"));
        pbtn.setTranslateX(400);
        pbtn.setTranslateY(330);

        //Added title to main menu and added style
        Text title = new Text();
        title.setText("Base Attack!");
        title.setFont(Font.font("Impact", 80));
        title.setStroke(Color.RED);
        title.setFill(Color.BLACK);
        title.setTranslateY(-80);
        
        //money bar for gameplay
        Text money = new Text();
        money.setText("0");
        money.setFont(Font.font("Impact", 80));
        money.setStroke(Color.RED);
        money.setFill(Color.BLACK);
        money.setTranslateY(-320);

        //turns out each scene needs its own root
        StackPane root1 = new StackPane();//for title screen
        StackPane root2 = new StackPane();//for gameplay
        StackPane root3 = new StackPane();//for pause menu

        root1.getChildren().add(btn);
        root1.getChildren().add(title);

        root2.getChildren().add(mbtn);
        root2.getChildren().add(mbtn2);
        root2.getChildren().add(mbtn3);
        root2.getChildren().add(pbtn);
        root2.getChildren().add(ubtn);
        root2.getChildren().add(money);

        Scene scene1 = new Scene(root1, 1280, 720);//title screen
        Scene scene2 = new Scene(root2, 1280, 720);//gameplay
        Scene scene3 = new Scene(root3, 1280, 720);//pause menu

        primaryStage.setTitle("Base Attack");
        primaryStage.setScene(scene1);

        //children can only have 1 parent, so multiple canvasses for multiple clouds
        //there's probablly a much better way to do this without making 1 of each thing for every scene
        Canvas canvas1 = new Canvas(1280, 720);
        root1.getChildren().add(canvas1);
        Canvas canvas2 = new Canvas(1280, 720);
        root2.getChildren().add(canvas2);
        Canvas canvas3 = new Canvas(1280, 720);
        root3.getChildren().add(canvas3);

        canvas1.toBack();
        canvas2.toBack();
        GraphicsContext gc = canvas1.getGraphicsContext2D();
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();
        GraphicsContext gc3 = canvas3.getGraphicsContext2D();

        //start button
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene2);
                try {
                    FileWriter gameLog = new FileWriter("game_log.txt");
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm:ss");
                    String myDate = dateFormat.format(date);
                    try (BufferedWriter out = new BufferedWriter(gameLog)) {
                        out.write("Game started: " + myDate);
                    } 

                } catch (IOException ex) {
                    Logger.getLogger(BaseAttack.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        //pause button
        pbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene3);
            }
        });

        new AnimationTimer() {
            int tick = 0;
            int cloudTimer = 0;
            int choice = 0;
            int choice2 = 0;//just for testing

            public void handle(long currentNanoTime) {
                //actual gameloop code goes here
                tick++;
                //money update
                money.setText(Integer.toString(p1.getMoney()));
                //not strictly necessary to check scene, but should be more efficient than drawing 2x more than needed
                if (primaryStage.getScene() == scene1) {
                    gc.drawImage(spaceBase720, 0, 0);
                    gc.drawImage(spaceClouds720v1, cloudTimer % 2560, 0);
                    gc.drawImage(spaceClouds720v1, (cloudTimer % 2560) - 2560, 0);
                    gc.drawImage(spaceClouds720v2, cloudTimer / 2, 0);
                    gc.drawImage(spaceClouds720v2, (cloudTimer / 2) - 2560, 0);
                } else if (primaryStage.getScene() == scene2) {
                    choice2 = aiUpdate(p1, choice2);//just for testing
                    p1.update(p2);
                    choice = aiUpdate(p2, choice);
                    p2.update(p1);
                    
                    gc2.drawImage(spaceBase720, 0, 0);
                    gc2.drawImage(spaceClouds720v1, cloudTimer % 2560, 0);
                    gc2.drawImage(spaceClouds720v1, (cloudTimer % 2560) - 2560, 0);
                    gc2.drawImage(spaceClouds720v2, cloudTimer / 2, 0);
                    gc2.drawImage(spaceClouds720v2, (cloudTimer / 2) - 2560, 0);

                    p1.render(gc2);
                    p2.render(gc2);
                } else {
                    gc3.drawImage(spaceBase720, 0, 0);
                    gc3.drawImage(spaceClouds720v1, cloudTimer % 2560, 0);
                    gc3.drawImage(spaceClouds720v1, (cloudTimer % 2560) - 2560, 0);
                    gc3.drawImage(spaceClouds720v2, cloudTimer / 2, 0);
                    gc3.drawImage(spaceClouds720v2, (cloudTimer / 2) - 2560, 0);
                }
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

    public static int aiUpdate(Player player, int choice) {
        if(player.getMoney() >= choice) {
            switch(choice) {
                case 100:
                    player.getBases().get((int)(Math.random() * player.getBases().size())).getMinions().add(new Minion(0, player.getDirection()));
                    player.setMoney(player.getMoney() - 100);
                    break;
                case 300:
                    player.getBases().get((int)(Math.random() * player.getBases().size())).getMinions().add(new Minion(1, player.getDirection()));
                    player.setMoney(player.getMoney() - 300);
                    break;
                case 1000:
                    player.getBases().get((int)(Math.random() * player.getBases().size())).getMinions().add(new Minion(2, player.getDirection()));
                    player.setMoney(player.getMoney() - 1000);
                    break;
            }
            choice = (int)(Math.random() * 3);
            switch(choice) {
                case 0:
                    return(100);
                case 1:
                    return(300);
                case 2:
                    return(1000);
            }
        }
        else if(choice == 0){
            choice = (int)(Math.random() * 3);
            switch(choice) {
                case 0:
                    return(100);
                case 1:
                    return(300);
                case 2:
                    return(1000);
            }
        }
        return choice;
    }
}
