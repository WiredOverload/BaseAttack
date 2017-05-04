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
 *      Add price text to buttons
 *      Add Health bar
 *      Add Minion logic
 *          ranged
 *      Add functionality to pause menu
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
        mbtn.setText("Speed Ship");
        mbtn.setFont(Font.font("Impact"));
        mbtn.setTranslateX(-200);
        mbtn.setTranslateY(-330);

        Button mbtn2 = new Button();
        mbtn2.setText("Tank Ship");
        mbtn2.setFont(Font.font("Impact"));
        mbtn2.setTranslateX(-300);
        mbtn2.setTranslateY(-330);

        Button mbtn3 = new Button();
        mbtn3.setText("Normal Ship");
        mbtn3.setFont(Font.font("Impact"));
        mbtn3.setTranslateX(-400);
        mbtn3.setTranslateY(-330);
        
        Button mbtn4 = new Button();
        mbtn4.setText("Speed Ship");
        mbtn4.setFont(Font.font("Impact"));
        mbtn4.setTranslateX(-200);
        mbtn4.setTranslateY(-215);
        
        Button mbtn5 = new Button();
        mbtn5.setText("Tank Ship");
        mbtn5.setFont(Font.font("Impact"));
        mbtn5.setTranslateX(-300);
        mbtn5.setTranslateY(-215);
        
        Button mbtn6 = new Button();
        mbtn6.setText("Normal Ship");
        mbtn6.setFont(Font.font("Impact"));
        mbtn6.setTranslateX(-400);
        mbtn6.setTranslateY(-215);
        
        Button mbtn7 = new Button();
        mbtn7.setText("Speed Ship");
        mbtn7.setFont(Font.font("Impact"));
        mbtn7.setTranslateX(-200);
        mbtn7.setTranslateY(-85);
        
        Button mbtn8 = new Button();
        mbtn8.setText("Tank Ship");
        mbtn8.setFont(Font.font("Impact"));
        mbtn8.setTranslateX(-300);
        mbtn8.setTranslateY(-85);
        
        Button mbtn9 = new Button();
        mbtn9.setText("Normal Shipd");
        mbtn9.setFont(Font.font("Impact"));
        mbtn9.setTranslateX(-400);
        mbtn9.setTranslateY(-85);
        
        Button mbtn10 = new Button();
        mbtn10.setText("Speed Ship");
        mbtn10.setFont(Font.font("Impact"));
        mbtn10.setTranslateX(-200);
        mbtn10.setTranslateY(45);
        
        Button mbtn11 = new Button();
        mbtn11.setText("Tank Ship");
        mbtn11.setFont(Font.font("Impact"));
        mbtn11.setTranslateX(-300);
        mbtn11.setTranslateY(45); 
        
        Button mbtn12 = new Button();
        mbtn12.setText("Normal Shipd");
        mbtn12.setFont(Font.font("Impact"));
        mbtn12.setTranslateX(-400);
        mbtn12.setTranslateY(45);
      
        Button mbtn13 = new Button();
        mbtn13.setText("Speed Ship");
        mbtn13.setFont(Font.font("Impact"));
        mbtn13.setTranslateX(-200);
        mbtn13.setTranslateY(175);

        Button mbtn14 = new Button();
        mbtn14.setText("Tank Ship");
        mbtn14.setFont(Font.font("Impact"));
        mbtn14.setTranslateX(-300);
        mbtn14.setTranslateY(175);

        Button mbtn15 = new Button();
        mbtn15.setText("Normal Ship");
        mbtn15.setFont(Font.font("Impact"));
        mbtn15.setTranslateX(-400);
        mbtn15.setTranslateY(175);



        Button ubtn = new Button();
        ubtn.setText("Upgrade Base");
        ubtn.setFont(Font.font("Impact"));
        ubtn.setTranslateX(-540);
        ubtn.setTranslateY(-330);

        Button pbtn = new Button();
        pbtn.setText("Pause Game");
        pbtn.setFont(Font.font("Impact"));
        pbtn.setTranslateX(-400);
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
        root2.getChildren().add(mbtn4);
        root2.getChildren().add(mbtn5);
        root2.getChildren().add(mbtn6);
        root2.getChildren().add(mbtn7);
        root2.getChildren().add(mbtn8);
        root2.getChildren().add(mbtn9);
        root2.getChildren().add(mbtn10);
        root2.getChildren().add(mbtn11);
        root2.getChildren().add(mbtn12);
        root2.getChildren().add(mbtn13);
        root2.getChildren().add(mbtn14);
        root2.getChildren().add(mbtn15);
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
        
        //All buttons to spawn ships 
        mbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(p1.getMoney() > 5000) {
                   p1.getBases().get(0).getMinions().add(new Minion(2, false));
                   p1.setMoney(p1.getMoney() - 5000);
                    
                }
            }
        });
      
        mbtn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (p1.getMoney() > 3000) {
                    p1.getBases().get(0).getMinions().add(new Minion(1, false));
                    p1.setMoney(p1.getMoney() - 3000);

                }
            }
        });
        
        mbtn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (p1.getMoney() > 1000) {
                    p1.getBases().get(0).getMinions().add(new Minion(0, false));
                    p1.setMoney(p1.getMoney() - 1000);

                }
            }
        });
        
         mbtn4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(p1.getMoney() > 5000) {
                   p1.getBases().get(1).getMinions().add(new Minion(2, false));
                   p1.setMoney(p1.getMoney() - 5000);
                    
                }
            }
        });
      
        mbtn5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (p1.getMoney() > 3000) {
                    p1.getBases().get(1).getMinions().add(new Minion(1, false));
                    p1.setMoney(p1.getMoney() - 3000);

                }
            }
        });
        
        mbtn6.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (p1.getMoney() > 1000) {
                    p1.getBases().get(1).getMinions().add(new Minion(0, false));
                    p1.setMoney(p1.getMoney() - 1000);

                }
            }
        });
        
         mbtn7.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(p1.getMoney() > 5000) {
                   p1.getBases().get(2).getMinions().add(new Minion(2, false));
                   p1.setMoney(p1.getMoney() - 5000);
                    
                }
            }
        });
      
        mbtn8.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (p1.getMoney() > 3000) {
                    p1.getBases().get(2).getMinions().add(new Minion(1, false));
                    p1.setMoney(p1.getMoney() - 3000);

                }
            }
        });
        
        mbtn9.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (p1.getMoney() > 1000) {
                    p1.getBases().get(2).getMinions().add(new Minion(0, false));
                    p1.setMoney(p1.getMoney() - 1000);

                }
            }
        });

         mbtn10.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(p1.getMoney() > 5000) {
                   p1.getBases().get(3).getMinions().add(new Minion(2, false));
                   p1.setMoney(p1.getMoney() - 5000);
                    
                }
            }
        });
      
        mbtn11.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (p1.getMoney() > 3000) {
                    p1.getBases().get(3).getMinions().add(new Minion(1, false));
                    p1.setMoney(p1.getMoney() - 3000);

                }
            }
        });
        
        mbtn12.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (p1.getMoney() > 1000) {
                    p1.getBases().get(3).getMinions().add(new Minion(0, false));
                    p1.setMoney(p1.getMoney() - 1000);

                }
            }
        });
        
         mbtn13.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(p1.getMoney() > 5000) {
                   p1.getBases().get(4).getMinions().add(new Minion(2, false));
                   p1.setMoney(p1.getMoney() - 5000);
                    
                }
            }
        });
      
        mbtn14.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (p1.getMoney() > 3000) {
                    p1.getBases().get(4).getMinions().add(new Minion(1, false));
                    p1.setMoney(p1.getMoney() - 3000);

                }
            }
        });
        
        mbtn15.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (p1.getMoney() > 1000) {
                    p1.getBases().get(4).getMinions().add(new Minion(0, false));
                    p1.setMoney(p1.getMoney() - 1000);

                }
            }
        });




        new AnimationTimer() {
            int tick = 0;
            int cloudTimer = 0;
            int choice = 0;
         

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
                case 1000:
                    player.getBases().get((int)(Math.random() * player.getBases().size())).getMinions().add(new Minion(0, player.getDirection()));
                    player.setMoney(player.getMoney() - 1000);
                    break;
                case 3000:
                    player.getBases().get((int)(Math.random() * player.getBases().size())).getMinions().add(new Minion(1, player.getDirection()));
                    player.setMoney(player.getMoney() - 3000);
                    break;
                case 5000:
                    player.getBases().get((int)(Math.random() * player.getBases().size())).getMinions().add(new Minion(2, player.getDirection()));
                    player.setMoney(player.getMoney() - 5000);
                    break;
            }
            choice = (int)(Math.random() * 3);
            switch(choice) {
                case 0:
                    return(1000);
                case 1:
                    return(3000);
                case 2:
                    return(5000);
            }
        }
        else if(choice == 0){
            choice = (int)(Math.random() * 3);
            switch(choice) {
                case 0:
                    return(1000);
                case 1:
                    return(3000);
                case 2:
                    return(5000);
            }
        }
        return choice;
    }
}
