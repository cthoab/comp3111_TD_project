package MapElement.Tower;

import MapElement.MapElement;

/**
 * IceTower class
 */
public class IceTower extends Tower {

    /**
     * Building Cost of an IceTower
     */
    public static final int BuildCost = 4;
    /**
     * Default range of an IceTower
     */
    public static final int DefaultRange = 120;
    /**
     * Default power of an IceTower
     * power in IceTower means the speed to slow down, it will not reduce HP.
     */
    public static final int DefaultPower = 15;


    /**
     * Constructor
     * @param x x-coordinate of the IceTower
     * @param y y-coordinate of the IceTower
     */
    public IceTower(int x, int y) {
        super(DefaultPower, DefaultRange, BuildCost);
        setX_position(x);
        setY_position(y);
    }

    /**
     * Return info of the IceTower ie. Tower Type, Power, Range
     * @return String that contains the info
     */
    @Override
    public String TowerToString() {
        return "Ice Tower\n" +
                super.TowerToString() +
                getRange();
    }

    /**
     * Check whether the monster is in the range of the IceTower
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
