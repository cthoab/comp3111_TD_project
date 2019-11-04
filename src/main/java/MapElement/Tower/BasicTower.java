package MapElement.Tower;

import Arena.Resources;
import MapElement.Monster.Monster;

public class BasicTower extends Tower {
    public static int BuildCost;        // TODO need to be assigned value
    public static BasicTower BuildBasicTower(int damage, int range, Resources resources){
        if (resources.getResources() >= BuildCost)
            return new BasicTower(damage,range,BuildCost);
        // We use BuildCost as the first UpgradeCost
        return null;
    }


    private BasicTower(int damage, int range, int UpgradeCost) {
        super(damage, range, UpgradeCost);
    }

    @Override
    public boolean upgrade(Resources resources) {
        boolean CanUpgrade = super.upgrade(resources);
        if (CanUpgrade) {
            setDamage(getDamage() + 2);
            setUpgradeCost(getUpgradeCost() + 5);
        }
        return CanUpgrade;
    }

    @Override
    protected boolean attack(Monster m, Resources resources) {
        //TODO
        return false;
    }
}
