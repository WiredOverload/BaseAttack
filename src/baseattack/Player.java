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
import javafx.scene.media.AudioClip;

/**
 * Assignment: Author: Cumulative completion time:
 *
 *
 *
 * @author Michael Hodges
 */
public class Player {

    //basic stats
    Boolean direction;//false is facing right, true is facing left
    private int money;
    private ArrayList<Base> bases = new ArrayList<Base>();
    AudioClip boom = new AudioClip(new File("src/Assets/boom.wav").toURI().toString());
    //upgrades
    private int income;

    public Player(Boolean direction, int health, int money) {
        this.direction = direction;
        this.money = money;
        this.income = 10;
        for (int i = 0; i < 5; i++) {//this is assuming bases are 64x64 pixels large, change as needed
            if (direction == false) {
                bases.add(new Base(direction, health, 64, (128 * i) + 64));
            } else {
                bases.add(new Base(direction, health, 1280 - 64, (128 * i) + 64));
            }
        }
    }

    public int getMoney() {
        return money;
    }

    public int getIncome() {
        return income;
    }

    public ArrayList<Base> getBases() {
        return bases;
    }

    public Boolean getDirection() {
        return direction;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setBases(ArrayList bases) {
        this.bases = bases;
    }

    public void setDirection(Boolean direction) {
        this.direction = direction;
    }

    //actual update code
    public void update(Player enemy) {
        money += income;
        for (int i = 0; i < bases.size(); i++) {
            if (bases.get(i).getHealth() < 1) {
                bases.remove(i);
                boom.play();
                for (int j = 0; i < bases.size(); j++) {//this is assuming bases are 64x64 pixels large, change as needed
                    bases.get(j).setY((128 * j) + 64);
                }
                break;//only one base can be removed per tick, else indexOutOfBounds
            }
        }
        if (bases.size() < enemy.getBases().size()) {
            for (int i = 0; i < bases.size(); i++) {
                bases.get(i).update(enemy.getBases().get(i));
            }
        } else {
            for (int i = 0; i < enemy.getBases().size(); i++) {
                bases.get(i).update(enemy.getBases().get(i));
            }
        }
    }

    public void render(GraphicsContext gc) {
        for (int i = 0; i < bases.size(); i++) {
            bases.get(i).render(gc);
        }
    }

}
