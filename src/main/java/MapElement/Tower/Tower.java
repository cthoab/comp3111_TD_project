package MapElement.Tower;

import Arena.Resources;
import MapElement.MapElement;
import MapElement.Monster.Monster;


public abstract class Tower extends MapElement {
    private int UpgradeCost;
    private int damage;
    private int range;
    private int level;

    public int getDamage() {
        return damage;
    }

    protected void setDamage(int damage) {
        this.damage = damage;
    }

    public int getLevel() {
        return level;
    }

    public int getRange() {
        return range;
    }

    public int getUpgradeCost() {
        return UpgradeCost;
    }

    protected void setUpgradeCost(int upgradeCost) {
        UpgradeCost = upgradeCost;
    }


    //To be overridden
    protected boolean upgrade(Resources resources){
        if (resources.getResources()<UpgradeCost)
            return false;
        level++;
        return true;
    }

    protected Tower(int damage, int range, int level, int UpgradeCost){
        this.level = level;
        this.UpgradeCost = UpgradeCost;
        this.damage = damage;
        this.range = range;
    }

    protected Tower(int damage, int range, int UpgradeCost){
        this(damage,range,0,UpgradeCost);
    }


}
