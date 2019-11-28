package MapElement.Tower;

import MapElement.MapElement;


public abstract class Tower extends MapElement {
    private int UpgradeCost;
    private int damage;
    private int range;
    private int level;
    private boolean attacked;

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

    public boolean getAttacked(){ return attacked;}

    public void setAttacked(boolean attacked){this.attacked = attacked;}


    public void upgrade(){
        damage = (int) (damage*1.5);
        UpgradeCost = (int) (UpgradeCost*1.5);
        level++;
    }

    protected Tower(int damage, int range, int UpgradeCost){
        this.level = 1;
        this.UpgradeCost = UpgradeCost;
        this.damage = damage;
        this.range = range;
        attacked = false;
    }

    public String TowerToString(){
        return "Level: " +
                level +
                '\n' +
                "Power: " +
                damage +
                '\n' +
                "Range: ";
    }

    public abstract boolean checkInRange(MapElement m);

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
