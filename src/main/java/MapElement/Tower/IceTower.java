package MapElement.Tower;

import Arena.Resources;

public class IceTower extends Tower {
    public static int BuildCost;

    public static IceTower BuildIceTower(int damage,int range, Resources resources){
        if (resources.getResources() >= BuildCost)
            return new IceTower(damage,range,BuildCost);
        // We use BuildCost as the first UpgradeCost
        return null;
    }

    //Damage in IceTower means the speed to slow down, it will not reduce HP.

    private IceTower(int damage, int range, int UpgradeCost) {
        super(damage, range, UpgradeCost);
    }

}
