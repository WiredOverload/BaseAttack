/*
 * Honor Code: I pledge that this program represents my own
 *     program code. I received help from (enter the names of
 *     others that helped with the assignment, write no one if
 *     you received no help) in designing and debugging my program.
 */
package baseattack;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Assignment: Author: Cumulative completion time:
 *
 *
 *
 * @author Michael Hodges
 */
public class Base {

    private Boolean direction;
    private int health;
    private int x;
    private int y;//this y coordinate has the y axis at the top of the screen
    private ArrayList<Minion> minions = new ArrayList<Minion>();
    private Image image;//placeholder image

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

    public ArrayList getMinions() {
        return minions;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
    

    public void update() {
        
    }
    
    public void render(GraphicsContext gc) {
        for(int i = 0; i < minions.size(); i++){
            minions.get(i).render(gc, y);
        }
        if(direction == false)//neccesary to check as images render starting at the top left corner of the image
            gc.drawImage(image, x, y);
        else
            gc.drawImage(image, x - 64, y);
    }
}
