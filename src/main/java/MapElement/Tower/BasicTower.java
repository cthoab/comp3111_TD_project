package MapElement.Tower;


import MapElement.MapElement;

/**
 * BasicTower class
 */
public class BasicTower extends Tower {
    /**
     * Building Cost of the BasicTower
     */
    public static final int BuildCost = 2;
    /**
     * Default Power of the BasicTower
     */
    public static final int DefaultPower = 4;
    /**
     *  Default Range of the BasicTower
     */
    public static final int DefaultRange = 65;

    /**
     * Constructor
     * @param x x-coordinate of the tower
     * @param y y-coordinate of the tower
     */
    public BasicTower(int x, int y) {
        super(DefaultPower, DefaultRange, BuildCost);
        setX_position(x);
        setY_position(y);
    }

    /**
     * Return the info of the tower
     * @return String that contains the info of the tower
     */
    @Override
    public String TowerToString() {
        return "Basic Tower\n" +
                super.TowerToString() +
                getRange();
    }

    /**
     * Check whether the monster is in the range of the tower
     * @param m the target monster
     * @return True if in range, False otherwise
     */
    @Override
    public boolean checkInRange(MapElement m) {
        if (getDistance(this, m) < DefaultRange)
            return true;
        return false;
    }

}
