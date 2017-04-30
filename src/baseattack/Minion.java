/*
 * Honor Code: I pledge that this program represents my own
 *     program code. I received help from (enter the names of
 *     others that helped with the assignment, write no one if
 *     you received no help) in designing and debugging my program.
 */

package baseattack;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;



/**
 * Assignment: 
 * Author: 
 * Cumulative completion time: 
 * 
 * 
 * 
 * @author Michael Hodges
 */
public class Minion {
    private int health;
    private int speed;
    private int meleeAttack; //attack value of melee attacks
    private int rangeAttack; //attack value of ranged attacks
    private int cooldown;
    private int x; //the x coordinate of the minion
    private Image image; //sprite for the minion

    public Minion(int type) {
        switch(type){
            case 1:
                image = new Image("Assets/medium1.png");
                break;
            case 2:
                image = new Image("Assets/medium2.png");
                break;
        }
    }

    public int getHealth() {
        return health;
    }

    public int getMeleeAttack() {
        return meleeAttack;
    }

    public int getRangeAttack() {
        return rangeAttack;
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

    public void setRangeAttack(int rangeAttack) {
        this.rangeAttack = rangeAttack;
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
    
    public void update() {
        this.x += speed;
    }
    
    public void render(GraphicsContext gc, int y, boolean direction) {
        if(direction == false)
            gc.drawImage(image, x, y);
        if(direction == true)//change 32 to dynamic size based on sprite size of ship
            gc.drawImage(image, x - 32, y);
    }
}
