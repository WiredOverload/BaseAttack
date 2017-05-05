/*
 * Honor Code: I pledge that this program represents my own
 *     program code. I received help from (enter the names of
 *     others that helped with the assignment, write no one if
 *     you received no help) in designing and debugging my program.
 */
package baseattack;

import java.io.BufferedWriter;
import java.io.File;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Michael Hodges and Christian Jungers
 * 
 * @todo
 *      This is really in no particular order, feel free to add
 *      Add Health bar
 *      Add functionality to pause menu
 *          write current game state to file
 *      Add sound effects
 *          explosions
 *          pew pew laser sounds
 *      Add VFX
 *          hit indicator (smallExplosion)
 *          minion death explosions
 *          minion death pieces that fly out
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
        
        //music logic
        Media music;
        if((int)(Math.random() * 2) == 0)
            music = new Media(new File("src/Assets/BigBang.mp3").toURI().toString());
        else
            music = new Media(new File("src/Assets/FallingWithStyle.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(music);
        mediaPlayer.play();

        //Added all buttons we needed put in VBox for layout
        Button easy = new Button();
        easy.setText("Easy Game");
        easy.setFont(Font.font("Impact", 40));
        easy.setStyle("-fx-text-fill: black; -fx-background-color: red;");
        easy.setTranslateY(-100);
        
        Button medium = new Button();
        medium.setText("Medium Game");
        medium.setFont(Font.font("Impact", 40));
        medium.setStyle("-fx-text-fill: black; -fx-background-color: red;");
        medium.setTranslateY(0);
        
        Button hard = new Button();
        hard.setText("Hard Game");
        hard.setFont(Font.font("Impact", 40));
        hard.setStyle("-fx-text-fill: black; -fx-background-color: red;");
        hard.setTranslateY(100);

        Button mbtn = new Button();
        mbtn.setText("Speed");
        mbtn.setFont(Font.font("Impact"));

        Button mbtn2 = new Button();
        mbtn2.setText("Tank");
        mbtn2.setFont(Font.font("Impact"));

        Button mbtn3 = new Button();
        mbtn3.setText("Normal");
        mbtn3.setFont(Font.font("Impact"));

        VBox vBox1 = new VBox(mbtn, mbtn2, mbtn3);
        vBox1.setTranslateX(1);
        vBox1.setTranslateY(55);

        Button mbtn4 = new Button();
        mbtn4.setText("Speed");
        mbtn4.setFont(Font.font("Impact"));

        Button mbtn5 = new Button();
        mbtn5.setText("Tank");
        mbtn5.setFont(Font.font("Impact"));

        Button mbtn6 = new Button();
        mbtn6.setText("Normal");
        mbtn6.setFont(Font.font("Impact"));

        VBox vBox2 = new VBox(mbtn4, mbtn5, mbtn6);
        vBox2.setTranslateX(1);
        vBox2.setTranslateY(185);

        Button mbtn7 = new Button();
        mbtn7.setText("Speed");
        mbtn7.setFont(Font.font("Impact"));

        Button mbtn8 = new Button();
        mbtn8.setText("Tank");
        mbtn8.setFont(Font.font("Impact"));

        Button mbtn9 = new Button();
        mbtn9.setText("Normal");
        mbtn9.setFont(Font.font("Impact"));

        VBox vBox3 = new VBox(mbtn7, mbtn8, mbtn9);
        vBox3.setTranslateX(1);
        vBox3.setTranslateY(315);

        Button mbtn10 = new Button();
        mbtn10.setText("Speed");
        mbtn10.setFont(Font.font("Impact"));

        Button mbtn11 = new Button();
        mbtn11.setText("Tank");
        mbtn11.setFont(Font.font("Impact"));

        Button mbtn12 = new Button();
        mbtn12.setText("Normal");
        mbtn12.setFont(Font.font("Impact"));

        VBox vBox4 = new VBox(mbtn10, mbtn11, mbtn12);
        vBox4.setTranslateX(1);
        vBox4.setTranslateY(445);

        Button mbtn13 = new Button();
        mbtn13.setText("Speed");
        mbtn13.setFont(Font.font("Impact"));

        Button mbtn14 = new Button();
        mbtn14.setText("Tank");
        mbtn14.setFont(Font.font("Impact"));

        Button mbtn15 = new Button();
        mbtn15.setText("Normal");
        mbtn15.setFont(Font.font("Impact"));

        VBox vBox5 = new VBox(mbtn13, mbtn14, mbtn15);
        vBox5.setTranslateX(1);
        vBox5.setTranslateY(575);

        Button ubtn = new Button();
        ubtn.setText("Upgrade Bases");
        ubtn.setFont(Font.font("Impact"));
        ubtn.setTranslateX(-540);
        ubtn.setTranslateY(-330);

        Button pbtn = new Button();//pause button
        pbtn.setText("Pause Game");
        pbtn.setFont(Font.font("Impact"));
        pbtn.setTranslateX(-400);
        pbtn.setTranslateY(330);
        
        Button pbtn2 = new Button();//back to game button
        pbtn2.setText("Back To Game");
        pbtn2.setFont(Font.font("Impact"));
        pbtn2.setTranslateX(0);
        pbtn2.setTranslateY(330);
        
        Text audio = new Text();
        audio.setText("Audio");
        audio.setFont(Font.font("Impact", 60));
        audio.setStroke(Color.RED);
        audio.setFill(Color.BLACK);
        audio.setTranslateX(10);
        audio.setTranslateY(-75);
        
        Button mute = new Button();//back to game button
        mute.setText("Mute");
        mute.setFont(Font.font("Impact"));
        mute.setTranslateX(0);
        mute.setTranslateY(0);

        //Added title to main menu and added style
        Text title = new Text();
        title.setText("Base Attack!");
        title.setFont(Font.font("Impact", 80));
        title.setStroke(Color.RED);
        title.setFill(Color.BLACK);
        title.setTranslateY(-250);

        //money bar for gameplay
        Text money = new Text();
        money.setText("0");
        money.setFont(Font.font("Impact", 80));
        money.setStroke(Color.RED);
        money.setFill(Color.BLACK);
        money.setTranslateY(-320);
        
        //Victory or Defeat text
        Text gameEnd = new Text();
        gameEnd.setText("");
        gameEnd.setFont(Font.font("Impact", 80));
        gameEnd.setStroke(Color.RED);
        gameEnd.setFill(Color.BLACK);

        //turns out each scene needs its own root
        StackPane root1 = new StackPane();//for title screen
        StackPane root2 = new StackPane();//for gameplay
        StackPane root3 = new StackPane();//for pause menu

        root1.getChildren().add(easy);
        root1.getChildren().add(medium);
        root1.getChildren().add(hard);
        root1.getChildren().add(title);

        root2.getChildren().add(vBox1);
        root2.getChildren().add(vBox2);
        root2.getChildren().add(vBox3);
        root2.getChildren().add(vBox4);
        root2.getChildren().add(vBox5);

        root2.getChildren().add(pbtn);
        root2.getChildren().add(ubtn);
        root2.getChildren().add(money);
       
        
        root3.getChildren().add(mute);
        root3.getChildren().add(audio);
        root3.getChildren().add(pbtn2);

        root2.getChildren().add(gameEnd);

      
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
        canvas3.toBack();
        GraphicsContext gc = canvas1.getGraphicsContext2D();
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();
        GraphicsContext gc3 = canvas3.getGraphicsContext2D();

        //easy difficulty 
        easy.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene2);
                try {
                    FileWriter gameLog = new FileWriter("game_log.txt", true);
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm:ss");
                    String myDate = dateFormat.format(date);
                    try (BufferedWriter out = new BufferedWriter(gameLog)) {
                        out.write("Easy game started: " + myDate);
                        out.newLine();
                        out.close();
                    }

                } catch (IOException ex) {
                    Logger.getLogger(BaseAttack.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        //Medium difficulty increases enemy spawn speed and base health 
        medium.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene2);
                try {
                    FileWriter gameLog = new FileWriter("game_log.txt", true);
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm:ss");
                    String myDate = dateFormat.format(date);
                    try (BufferedWriter out = new BufferedWriter(gameLog)) {
                        out.write("Medium game started: " + myDate);
                        out.newLine();
                        out.close();
                    }

                } catch (IOException ex) {
                    Logger.getLogger(BaseAttack.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 p2.setIncome(p2.getIncome() + (p2.getIncome() * 1));
                    for(int i = 0; i < p2.getBases().size(); i++) {
                        p2.getBases().get(i).setHealth(p2.getBases().get(i).getHealth() + (p2.getBases().get(i).getHealth() / 1));
                    }

            }
        });
        
        //Hard difficulty greater increase to enemy spawn speed and base health 
         hard.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene2);
                try {
                    FileWriter gameLog = new FileWriter("game_log.txt", true);
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm:ss");
                    String myDate = dateFormat.format(date);
                    try (BufferedWriter out = new BufferedWriter(gameLog)) {
                        out.write("Hard game started: " + myDate);
                        out.newLine();
                        out.close();
                    }

                } catch (IOException ex) {
                    Logger.getLogger(BaseAttack.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                 p2.setIncome(p2.getIncome() + (p2.getIncome() * 3));
                    for(int i = 0; i < p2.getBases().size(); i++) {
                        p2.getBases().get(i).setHealth(p2.getBases().get(i).getHealth() + (p2.getBases().get(i).getHealth()* 2));
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
        
        //back to game button
        pbtn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene2);
            }
        });
        
        //mute button
        mute.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (mediaPlayer.isMute()) {
                        mediaPlayer.setMute(false);
                    } else {
                        mediaPlayer.setMute(true);
                    }
            }
        });
        
        //upgrade button
        ubtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (p1.getMoney() > 5000) {
                    p1.setIncome(p1.getIncome() + (p1.getIncome()/3));
                    for(int i = 0; i < p1.getBases().size(); i++) {
                        p1.getBases().get(i).setHealth(p1.getBases().get(i).getHealth() + (p1.getBases().get(i).getHealth()/3));
                    }
                    p1.setMoney(p1.getMoney() - 5000);

                }
            }
        });

        //All buttons to spawn ships 
        mbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (p1.getMoney() > 5000) {
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
                if (p1.getMoney() > 5000) {
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
                if (p1.getMoney() > 5000) {
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
                if (p1.getMoney() > 5000) {
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
                if (p1.getMoney() > 5000) {
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
                    if(p2.getBases().size() == 0) {
                        gameEnd.setText("Victory!");
                        FileWriter gameLog;
                        try {
                            gameLog = new FileWriter("game_log.txt", true);
                            BufferedWriter out = new BufferedWriter(gameLog);
                            out.write("Game ended in victory!");
                            out.newLine();
                            out.close();
                        } catch (IOException ex) {
                            Logger.getLogger(BaseAttack.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        stop();
                    } else if(p1.getBases().size() == 0) {
                        gameEnd.setText("Defeat!");
                        FileWriter gameLog;
                        try {
                            gameLog = new FileWriter("game_log.txt", true);
                            BufferedWriter out = new BufferedWriter(gameLog);
                            out.write("Game ended in failure!");
                            out.newLine();
                            out.close();
                        } catch (IOException ex) {
                            Logger.getLogger(BaseAttack.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        stop();
                    }
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
        if (player.getMoney() >= choice) {
            switch (choice) {
                case 1000:
                    player.getBases().get((int) (Math.random() * player.getBases().size())).getMinions().add(new Minion(0, player.getDirection()));
                    player.setMoney(player.getMoney() - 1000);
                    break;
                case 3000:
                    player.getBases().get((int) (Math.random() * player.getBases().size())).getMinions().add(new Minion(1, player.getDirection()));
                    player.setMoney(player.getMoney() - 3000);
                    break;
                case 5000:
                    player.getBases().get((int) (Math.random() * player.getBases().size())).getMinions().add(new Minion(2, player.getDirection()));
                    player.setMoney(player.getMoney() - 5000);
                    break;
            }
            choice = (int) (Math.random() * 3);
            switch (choice) {
                case 0:
                    return (1000);
                case 1:
                    return (3000);
                case 2:
                    return (5000);
            }
        } else if (choice == 0) {
            choice = (int) (Math.random() * 3);
            switch (choice) {
                case 0:
                    return (1000);
                case 1:
                    return (3000);
                case 2:
                    return (5000);
            }
        }
        return choice;
    }
}
