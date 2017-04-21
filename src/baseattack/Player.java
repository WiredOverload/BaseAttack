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
    private int health;
    private int money;
    private ArrayList minions = new ArrayList();
    //upgrades
    private int spawnRate;
    private int income;

    public Player(int health, int money) {
        this.money = money;
        this.health = health;
        this.spawnRate = 120;
        this.income = 10;
    }

    public int getHealth() {
        return health;
    }

    public int getMoney() {
        return money;
    }

    public int getSpawnRate() {
        return spawnRate;
    }

    public int getIncome() {
        return income;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    //actual update code
    public void update(int tick) {
        money += income;
        if(tick % spawnRate == 0)//need to add type logic
            minions.add(new Minion(1));
    }

}
