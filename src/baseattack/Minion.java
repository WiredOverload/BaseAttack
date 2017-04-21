/*
 * Honor Code: I pledge that this program represents my own
 *     program code. I received help from (enter the names of
 *     others that helped with the assignment, write no one if
 *     you received no help) in designing and debugging my program.
 */

package baseattack;

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
    private int meleeAttack; //attack value of melee attacks
    private int rangeAttack; //attack value of ranged attacks
    private int spawnTick; //the tick in which this minion was spawned
    private Image image; //sprite for the minion

    public Minion(int type) {
        switch(type){
            case 1:
                image = new Image("");
                break;
            case 2:
                image = new Image("");
                break;
        }
    }
    
    
}
