/*
 * Honor Code: I pledge that this program represents my own
 *     program code. I received help from (enter the names of
 *     others that helped with the assignment, write no one if
 *     you received no help) in designing and debugging my program.
 */
package baseattack;

import java.io.File;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

/**
 * Assignment: Author: Cumulative completion time:
 *
 *
 *
 * @author Michael Hodges
 */
public class Minion {

    private int health;
    private int speed;
    private int meleeAttack; //attack value of melee attacks
    private int rangedAttack; //attack value of ranged attacks
    private int cooldown;//needed for bounce between attacks
    private int x; //the x coordinate of the minion
    private Image image; //sprite for the minion
    AudioClip ping = new AudioClip(new File("ping.wav").toURI().toString());

    public Minion(int type, boolean direction) {
        //necesary rotation stuff
        ImageView iv;
        SnapshotParameters params;
        //switch for ship type
        switch (type) {
            case 0://normal ship
                //rotation logic
                iv = new ImageView(new Image("Assets/medium1.png"));
                if (direction) {
                    iv.setRotate(-90);
                } else {
                    iv.setRotate(90);
                }
                params = new SnapshotParameters();
                params.setFill(Color.TRANSPARENT);
                image = iv.snapshot(params, null);
                health = 100;
                speed = 2;
                meleeAttack = 20;
                rangedAttack = 0;
                break;
            case 1://tank ship
                iv = new ImageView(new Image("Assets/medium2.png"));
                if (direction) {
                    iv.setRotate(-90);
                } else {
                    iv.setRotate(90);
                }
                params = new SnapshotParameters();
                params.setFill(Color.TRANSPARENT);
                image = iv.snapshot(params, null);
                health = 480;
                speed = 1;
                meleeAttack = 40;
                rangedAttack = 0;
                break;
            case 2://ranged ship
                iv = new ImageView(new Image("Assets/medium3.png"));
                if (direction) {
                    iv.setRotate(-90);
                } else {
                    iv.setRotate(90);
                }
                params = new SnapshotParameters();
                params.setFill(Color.TRANSPARENT);
                image = iv.snapshot(params, null);
                health = 50;
                speed = 3;
                meleeAttack = 80;
                rangedAttack = 30;
                break;
        }
        cooldown = 0;
        if (direction) {
            x = 1280 - 64;
        } else {
            x = 0 + 64;
        }
    }

    public int getHealth() {
        return health;
    }

    public int getMeleeAttack() {
        return meleeAttack;
    }

    public int getRangedAttack() {
        return rangedAttack;
    }

    public int getX() {
        return x;
    }

    public Image getImage() {
        return image;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMeleeAttack(int meleeAttack) {
        this.meleeAttack = meleeAttack;
    }

    public void setRangedAttack(int rangedAttack) {
        this.rangedAttack = rangedAttack;
    }

    public void setX(int spawnTick) {
        this.x = spawnTick;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void update(Base enemy) {
        for (int i = 0; i < enemy.getMinions().size(); i++) {
            if (enemy.getDirection() == true) {
                if (enemy.getMinions().get(i).getX() - x < 64) {
                    ping.play();
                    cooldown = 10;//change this to change attacking speed
                    enemy.getMinions().get(i).setHealth(enemy.getMinions().get(i).getHealth() - meleeAttack);
                }
            } else if (enemy.getDirection() == false) {
                if (x - enemy.getMinions().get(i).getX() < 64) {
                    ping.play();
                    cooldown = 10;//change this to change attacking speed
                    enemy.getMinions().get(i).setHealth(enemy.getMinions().get(i).getHealth() - meleeAttack);
                }
            }
        }
        if (enemy.getDirection() == true) {
            if (enemy.getX() - x < 64 + 32) {
                ping.play();
                cooldown = 10;//change this to change attacking speed
                enemy.setHealth(enemy.getHealth() - meleeAttack);
            }
        } else if (enemy.getDirection() == false) {
            if (x - enemy.getX() < 64 + 32) {
                ping.play();
                cooldown = 10;//change this to change attacking speed
                enemy.setHealth(enemy.getHealth() - meleeAttack);
            }
        }
        if (enemy.getDirection() == true) {
            if (cooldown > 0) {
                x -= (int)Math.ceil(speed / 2);//change this to change reversing speed
                cooldown--;
            } else {
                x += speed;
            }
        } else if (enemy.getDirection() == false) {
            if (cooldown > 0) {
                x += (int)Math.ceil(speed / 2);//change this to change reversing speed
                cooldown--;
            } else {
                x -= speed;
            }
        }
    }

    public void render(GraphicsContext gc, int y, boolean direction) {
        if (direction == false) {
            gc.drawImage(image, x, y + 16);
        }
        if (direction == true)//change 32 to dynamic size based on sprite size of ship
        {
            gc.drawImage(image, x - 32, y + 16);
        }
    }
}
