package MapElement.Tower;

import MapElement.MapElement;


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

}
