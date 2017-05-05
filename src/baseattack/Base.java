/*
 * Honor Code: I pledge that this program represents my own
 *     program code. I received help from (enter the names of
 *     others that helped with the assignment, write no one if
 *     you received no help) in designing and debugging my program.
 */
package baseattack;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 * Assignment: Final
 *
 * Cumulative completion time: about a week
 *
 * @author REDACTED
 */
public class Base {

    private Boolean direction;
    private int health;
    private int x;
    private int y;//this y coordinate has the y axis at the top of the screen
    private ArrayList<Minion> minions = new ArrayList<Minion>();
    private Image image;//placeholder image
    AudioClip boom = new AudioClip(new File("src/Assets/boom.wav").toURI().toString());
    private Image explode1 = new Image("Assets/smallHit.png");
    private Image explode2 = new Image("Assets/mediumExplosion2.png");
    private Image explode3 = new Image("Assets/mediumExplosion1.png");

    public Base(Boolean direction, int health, int x, int y) {
        this.direction = direction;
        this.health = health;
        this.x = x;
        this.y = y;
        if(direction == false)
            image = new Image("Assets/blue_base.png");
        else
            image = new Image("Assets/red_base.png");
    }

    public int getHealth() {
        return health;
    }

    public ArrayList<Minion> getMinions() {
        return minions;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Boolean getDirection() {
        return direction;
    }

    
    public void setHealth(int health) {
        this.health = health;
    }

    public void setMinions(ArrayList minions) {
        this.minions = minions;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * updates the base and its minions
     * @param enemy the enemy base on the same level
     */
    public void update(Base enemy) {
        for(int i = 0; i < minions.size(); i++) {
            if(minions.get(i).getHealth() < 1) {
                if(minions.get(i).getExplode() > 6)
                    minions.remove(i);
                else if(minions.get(i).getExplode() == 0){
                    minions.get(i).setExplode(minions.get(i).getExplode() + 1);
                    boom.play();
                }
                else {
                    minions.get(i).setExplode(minions.get(i).getExplode() + 1);
                }
            }
            else
                minions.get(i).update(enemy);
        }
    }
    
    /**
     * Renders the base and its minions
     * @param gc the graphics context to render to
     */
    public void render(GraphicsContext gc) {
        for(int i = 0; i < minions.size(); i++){
            if(minions.get(i).getExplode() > 0 && minions.get(i).getExplode() < 3) {
                if(direction)
                    gc.drawImage(explode1, minions.get(i).getX() + 12 - 32, y + 12 + 16);
                else
                    gc.drawImage(explode1, minions.get(i).getX() + 12, y + 12 + 16);
            }
            else if(minions.get(i).getExplode() > 2 && minions.get(i).getExplode() < 5) {
                if(direction)
                    gc.drawImage(explode2, minions.get(i).getX() + 8 - 32, y + 8 + 16);
                else
                    gc.drawImage(explode2, minions.get(i).getX() + 8, y + 8 + 16);
            }
            else if(minions.get(i).getExplode() > 4) {
                if(direction)
                    gc.drawImage(explode3, minions.get(i).getX() - 32, y + 16);
                else
                    gc.drawImage(explode3, minions.get(i).getX(), y + 16);
            }
            else
                minions.get(i).render(gc, y, direction);
        }
        if(direction == false)//neccesary to check as images render starting at the top left corner of the image
            gc.drawImage(image, x, y);
        else
            gc.drawImage(image, x - 64, y);
    }
}
