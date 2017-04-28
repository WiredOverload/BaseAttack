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
public class Player {

    //basic stats
    private int money;
    private ArrayList bases = new ArrayList();
    //upgrades
    private int income;

    public Player(int health, int money, int x) {
        this.money = money;
        for(int i = 0; i < 5; i++) //this is assuming bases are 64x64 pixels large, change as needed
            bases.add(new Base(health, x, (64*i) + 32));
        this.income = 10;
    }

    public int getMoney() {
        return money;
    }

    public int getIncome() {
        return income;
    }

    public ArrayList getBases() {
        return bases;
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

    //actual update code
    public void update() {
        money += income;
        
    }

}
