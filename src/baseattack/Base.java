/*
 * Honor Code: I pledge that this program represents my own
 *     program code. I received help from (enter the names of
 *     others that helped with the assignment, write no one if
 *     you received no help) in designing and debugging my program.
 */
package baseattack;

import java.util.ArrayList;

/**
 * Assignment: Author: Cumulative completion time:
 *
 *
 *
 * @author Michael Hodges
 */
public class Base {

    private int health;
    private ArrayList minions = new ArrayList();

    public Base(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public ArrayList getMinions() {
        return minions;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMinions(ArrayList minions) {
        this.minions = minions;
    }

    
}
