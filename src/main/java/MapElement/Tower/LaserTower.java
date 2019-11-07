package MapElement.Tower;

import Arena.Resources;
import MapElement.Monster.Monster;

public class LaserTower extends Tower {
    public static int BuildCost;

    protected LaserTower(int damage, int range, int UpgradeCost) {
        super(damage, range, UpgradeCost);
    }

    @Override
    protected boolean attack(Monster m, Resources resources) {
        return false;
    }
}
