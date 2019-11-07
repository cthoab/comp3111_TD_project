package MapElement.Tower;

import Arena.Resources;
import MapElement.Monster.Monster;

public class IceTower extends Tower {
    public static int BuildCost;

    protected IceTower(int damage, int range, int level, int UpgradeCost) {
        super(damage, range, level, UpgradeCost);
    }

    @Override
    protected boolean attack(Monster m, Resources resources) {
        return false;
    }
}
