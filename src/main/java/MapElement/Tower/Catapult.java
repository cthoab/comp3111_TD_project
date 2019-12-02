package MapElement.Tower;

import MapElement.MapElement;

/**
 * Catapult class
 */
public class Catapult extends Tower {
    /**
     * Building Cost of a Catapult
     */
    public static final int BuildCost = 4;
    /**
     * Default Power of a Catapult
     */
    public static final int DefaultPower = 6;
    /**
     * Default Inner range of a Catapult
     */
    public static final int DefaultInnerRange = 50;
    /**
     * Default Outer range of a Catapult
     */
    public static final int DefaultOuterRange = 150;
    /**
     * Default Reload time of a Catapult
     */
    public static final int DefaultReloadTime = 5;
    private int reloadTimeLeft;
    private int reloadTime;

    /**
     * Constructor
     * @param x x-coordinate of the catapult
     * @param y y-coordinate of the catapult
     */
    public Catapult(int x, int y) {
        super(DefaultPower, DefaultOuterRange, BuildCost);
        reloadTimeLeft = 0;
        reloadTime = DefaultReloadTime;
        setX_position(x);
        setY_position(y);
    }

    /**
     * Getter of the reload time left of the catapult
     * @return reloadTimeLeft
     */
    public int ReloadTimeLeft() {
        return reloadTimeLeft;
    }

    /**
     * Reload the catapult
     */
    public void Reload() {
        reloadTimeLeft = reloadTime;
    }

    /**
     * Cool down the tower by 1
     */
    public void coolDown() {
        if (reloadTimeLeft > 0)
            reloadTimeLeft--;
    }

    /**
     * Strength the tower on Power and shorten the reload time, raise the cost of further upgrade
     */
    @Override
    public void upgrade() {
        super.upgrade();
        reloadTime = (reloadTime > 2) ? reloadTime - 1 : reloadTime;
    }

    /**
     * Return the info of the catapult ie. Tower Type, Power, Range, Cool Down Time Left
     * @return String that contains the info
     */
    @Override
    public String TowerToString() {
        return "Catapult\n" +
                super.TowerToString() +
                "50 - 150\n" +
                "Cool Down Time Left: " +
                reloadTimeLeft;
    }

    /**
     * Check whether the monster is in range of the catapult
     * @param m the target monster
     * @return True if in range, false otherwise
     */
    @Override
    public boolean checkInRange(MapElement m) {
        return (getDistance(this, m) >= DefaultInnerRange && getDistance(this, m) <= DefaultOuterRange);
    }

}
