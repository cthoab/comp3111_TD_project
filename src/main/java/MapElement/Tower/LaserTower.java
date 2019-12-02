package MapElement.Tower;

import MapElement.MapElement;

/**
 * LaserTower class
 */
public class LaserTower extends Tower {
    /**
     * Default power of an LaserTower
     */
    public static final int DefaultPower = 5;
    /**
     * Default cost of attack of an LaserTower
     */
    public static final int DefaultAttackCost = 1;
    /**
     * Building Cost of an LaserTower
     */
    public static int BuildCost = 8;
    /**
     * The attack cost of the LaserTower
     */
    public int attackCost;

    /**
     * Constructor
     * @param x x-coordinate of the LaserTower
     * @param y y-coordinate of the LaserTower
     */
    public LaserTower(int x, int y) {
        super(DefaultPower, 2000, BuildCost);
        attackCost = DefaultAttackCost;
        setX_position(x);
        setY_position(y);
    }

    /**
     * Strength the Power, raise the cost of attack and further upgrade
     */
    @Override
    public void upgrade() {
        super.upgrade();
        attackCost++;
    }

    /**
     * Return info of the tower ie. Tower Type, Power, Range
     * @return String that contains the info
     */
    @Override
    public String TowerToString() {
        return "Laser Tower\n" +
                super.TowerToString() +
                "Infinity";
    }

    /**
     * Check whether the monster is in the range of the LaserTower
     * @param m the target monster
     * @return Always True as LaserTower has infinite range
     */
    @Override
    public boolean checkInRange(MapElement m) {
        return true;
    }

}
