package Arena;


import MainProgram.MyController;
import MapElement.Monster.Fox;
import MapElement.Monster.Monster;
import MapElement.Monster.Penguin;
import MapElement.Monster.Unicorn;
import MapElement.Tower.*;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * The class that contains Towers, Monsters and Resources.
 */
public class Arena {

    /**
     * Default amount of Resources that an arena should have
     */
    public static final int DefaultResources = 10;
    /**
     * The resources that the arena has
     */
    public SimpleIntegerProperty Resources = new SimpleIntegerProperty(DefaultResources);
    /**
     * The monsters in the arena
     */
    public ArrayList<Monster> monsters = new ArrayList<>();
    /**
     * The towers in the arena
     */
    public ArrayList<Tower> towers = new ArrayList<>();
    private int evolveCount = 0;


    /**
     * Default constructor
     */
    public Arena() {
    }

    /**
     * Constructor that allows customising initial resources
     * @param Resources the amount of resources that the arena has at the beginnings
     */
    public Arena(int Resources) {
        this.Resources.set(Resources);
    }

    /**
     * A function that spawn one monster per execution, more times it is called, the stronger the monster spawned.
     */
    public void spawnMonster() {
        Random rand = new Random();
        evolveCount++;
        int n = rand.nextInt(3);
        switch (n) {
            case 0:
                monsters.add(new Fox());
                break;
            case 1:
                monsters.add(new Penguin());
                break;
            case 2:
                monsters.add(new Unicorn());
                break;
        }
        System.out.println("EvolveCount = " + evolveCount);
        for (int i = 0; i < evolveCount / 10; i++) {
            monsters.get(monsters.size() - 1).evolve();
        }

    }

    /**
     * The function that allows the monster to move towards ending zone, evolve, get attacked and replenish.
     */
    public void monsterMove() {
        for (Monster m : monsters) {
            for (int i = 0; i < m.getSpeed(); i++) {
                if (m.getHP() > 0) {
                    m.move();
                    TowerAttack();
                }
            }
            m.addSurvivedtime();
            m.evolve();
            if (m.getClass() == Penguin.class)
                ((Penguin) m).replenish();
        }
    }

    /**
     * The function that check whether the player has loss the game
     * @return True if loss, False otherwise
     */
    public boolean checkGameOver() {
        for (Monster m : monsters)
            if (m.getX_position() >= 440 && m.getHP() > 0)
                return true;
        return false;
    }

    /**
     * The dead body of the monsters will be removed after each call
     */
    public void removeDeadMonsters() {
        Iterator<Monster> m = monsters.iterator();
        while (m.hasNext()) {
            Monster monster = m.next();
            if (monster.getHP() <= 0) {
                Resources.set(Resources.get() + monster.getMaxHP() / 15 + 1);
                m.remove();
            }
        }
    }

    /**
     * Check if it is possible to build a tower at a specific position, if yes, build it
     * @param T The letter that determine what kind of tower should be built
     * @param position_x The x-coordinate of the position
     * @param position_y The y-coordinate of the position
     * @return If resources is enough and the position is empty for tower, return True, False otherwise.
     */
    public boolean BuildTower(char T, int position_x, int position_y) {
        if (TowerAt(position_x, position_y) != null)            //A tower exist in the given position
            return false;
        switch (T) {
            case 'B':
                if (Resources.get() >= BasicTower.BuildCost) {
                    towers.add(new BasicTower(position_x, position_y));
                    Resources.set(Resources.get() - BasicTower.BuildCost);
                    return true;
                }
            case 'C':
                if (Resources.get() >= Catapult.BuildCost) {
                    towers.add(new Catapult(position_x, position_y));
                    Resources.set(Resources.get() - Catapult.BuildCost);
                    return true;
                }
            case 'I':
                if (Resources.get() >= IceTower.BuildCost) {
                    towers.add(new IceTower(position_x, position_y));
                    Resources.set(Resources.get() - IceTower.BuildCost);
                    return true;
                }
            case 'L':
                if (Resources.get() >= LaserTower.BuildCost) {
                    towers.add(new LaserTower(position_x, position_y));
                    Resources.set(Resources.get() - LaserTower.BuildCost);
                    return true;
                }
            default:
                return false;
        }
    }

    /**
     * Return the tower at the specific position
     * @param position_x x-coordinate of the position
     * @param position_y y-coordinate of the position
     * @return the tower on the position or null if no tower in the position
     */
    public Tower TowerAt(int position_x, int position_y) {
        for (Tower tower : towers) {
            if (tower.getX_position() == position_x)
                if (tower.getY_position() == position_y)
                    return tower;
        }
        return null;
    }

    /**
     * Return the tower's info ie. Tower type, Range, Power(Damage)
     * @param position_x x-coordinate of the tower's position
     * @param position_y y-coordinate of the tower's position
     * @return the info of the tower, null if there is no tower in the position
     */
    public String TowerInfo(int position_x, int position_y) {
        for (Tower tower : towers) {
            if (tower.getX_position() == position_x)
                if (tower.getY_position() == position_y)
                    return tower.TowerToString();
        }
        return null;
    }

    /**
     * Remove the tower at specific position
     * @param position_x x-coordinate of the tower
     * @param position_y y-coordinate of the tower
     * @return True if there is tower in the position and is now removed, False if there is no tower in the position
     */
    public boolean RemoveTower(int position_x, int position_y) {
        if (TowerAt(position_x, position_y) != null) {
            towers.remove(TowerAt(position_x, position_y));
            return true;
        }
        return false;
    }

    /**
     * Upgrade the tower at specific position
     * @param position_x x-coordinate of the tower
     * @param position_y y-coordinate of the tower
     * @return True if there is tower and resources is enough, False otherwise
     */
    public boolean UpgradeTower(int position_x, int position_y) {
        Tower tower = TowerAt(position_x, position_y);
        if (tower.getUpgradeCost() < Resources.get()) {
            Resources.set(Resources.get() - tower.getUpgradeCost());
            tower.upgrade();
            return true;
        }
        return false;
    }


    private void TowerAttack() {
        for (Tower t : towers) {
            Monster closestMonster = null;
            int maxSteps = 0;
            for (Monster m : monsters) {
                ArrayList<Monster> inRangeMonsters = new ArrayList<>();
                if (t.checkInRange(m) && !t.getAttacked() && m.getHP() > 0)
                    inRangeMonsters.add(m);
                for (Monster inRangeM : inRangeMonsters) {
                    if (inRangeM.getSteps() > maxSteps) {
                        closestMonster = inRangeM;
                        maxSteps = inRangeM.getSteps();
                    }
                }
            }
            if (closestMonster != null) {
                t.setAttacked(true);
                int OriginalHP = closestMonster.getHP();
                if (t instanceof IceTower) {
                    MyController.DrawIceAttack(closestMonster.getX_position(), closestMonster.getY_position(), t.getDamage());
                    closestMonster.setSpeed(closestMonster.getSpeed() - t.getDamage());
                    System.out.println(t.simpleInfo() + " freeze " + closestMonster.simpleInfo());
                    ;
                    System.out.println(closestMonster.simpleInfo() + " is " + t.getDamage() + " Slower.");
                } else if (t instanceof LaserTower && Resources.get() >= ((LaserTower) t).attackCost) {
                    int diffx = t.getX_position() - closestMonster.getX_position();
                    int diffy = t.getY_position() - closestMonster.getY_position();
                    MyController.DrawLaser(t.getX_position(), t.getY_position(), t.getX_position() - diffx * 1000, t.getY_position() - diffy * 1000, t.getDamage());
                    System.out.println(t.simpleInfo() + " attacked " + closestMonster.simpleInfo());
                    MyController.aoeDamage(this, true);
                    //Resources.set(Resources.get() - ((LaserTower) t).attackCost);
                } else if (t instanceof BasicTower) {
                    MyController.DrawAttack(closestMonster.getX_position(), closestMonster.getY_position(), t.getDamage());
                    closestMonster.setHP(closestMonster.getHP() - t.getDamage());
                    System.out.println(t.simpleInfo() + " attacked " + closestMonster.simpleInfo());
                    System.out.println("HP of " + closestMonster.simpleInfo() + " dropped from " + OriginalHP + " to " + closestMonster.getHP());
                } else if (t instanceof Catapult) {
                    if (((Catapult) t).ReloadTimeLeft() == 0) {
                        MyController.throwStone(closestMonster.getX_position(), closestMonster.getY_position(), t.getDamage());
                        ((Catapult) t).Reload();
                        System.out.println(t.simpleInfo() + " attacked " + closestMonster.simpleInfo());
                        System.out.println("HP of " + closestMonster.simpleInfo() + " dropped from " + OriginalHP + " to " + closestMonster.getHP());
                        MyController.aoeDamage(this, false);
                    }
                    ((Catapult) t).coolDown();
                }
            }
        }
    }

    /**
     * All attack is finish, reset the tower as not yet attack for the next frame
     */
    public void resetTowers() {
        for (Tower t : towers)
            t.setAttacked(false);
    }

}
