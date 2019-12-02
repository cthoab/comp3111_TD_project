package MapElement.Tower;

import MapElement.MapElement;

/**
 * An abstract class that defines what is a Tower
 */
public abstract class Tower extends MapElement {
    private int UpgradeCost;
    private int damage;
    private int range;
    private int level;
    private boolean attacked;

    protected Tower(int damage, int range, int UpgradeCost) {
        this.level = 1;
        this.UpgradeCost = UpgradeCost;
        this.damage = damage;
        this.range = range;
        attacked = false;
    }

    /**
     * Getter of the tower's power(damage)
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    protected void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Getter of the towers's level
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Getter of the tower's range
     * @return range
     */
    public int getRange() {
        return range;
    }

    /**
     * Getter of the tower's Upgrade Cost
     * @return UpgradeCost
     */
    public int getUpgradeCost() {
        return UpgradeCost;
    }

    protected void setUpgradeCost(int upgradeCost) {
        UpgradeCost = upgradeCost;
    }

    /**
     * Getter of whether the tower has attacked
     * @return attacked
     */
    public boolean getAttacked() {
        return attacked;
    }

    /**
     * Setter of the attacked boolean
     * @param attacked the value to be set as
     */
    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    /**
     * The function that strength the tower's damage and raise the cost of further upgrade
     */
    public void upgrade() {
        damage = (int) (damage * 1.5);
        UpgradeCost = (int) (UpgradeCost * 1.5);
        level++;
    }

    /**
     * Return the info of the Tower ie. Level, Power, Range
     * @return String that contains the tower's info
     */
    public String TowerToString() {
        return "Level: " +
                level +
                '\n' +
                "Power: " +
                damage +
                '\n' +
                "Range: ";
    }

    /**
     * Check whether the monster is in the range of the tower
     * @param m the target monster
     * @return True if in range, False otherwise
     */
    public abstract boolean checkInRange(MapElement m);

    /**
     * Return the simple info of the tower ie. Type of the tower, Position of the tower
     * @return String that contains the simple info of the tower
     */
    public String simpleInfo() {
        String output;
        if (this instanceof BasicTower)
            output = "Basic Tower ";
        else if (this instanceof IceTower)
            output = "Ice Tower ";
        else if (this instanceof LaserTower)
            output = "Laser Tower ";
        else if (this instanceof Catapult)
            output = "Catapult ";
        else
            throw new IllegalStateException("Tower of wrong type.");
        return output + "at (" + this.getX_position() + "," + this.getY_position() + ") ";
    }
}
