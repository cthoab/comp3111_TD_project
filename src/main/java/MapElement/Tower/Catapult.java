package MapElement.Tower;

import Arena.Resources;

public class Catapult extends Tower {
    private int reloadTimeLeft;
    private int reloadTime;
    public static int BuildCost;
    public static Catapult BuildCatapult(int damage, int range, Resources resources){
        if (resources.getResources() >= BuildCost)
            return new Catapult(damage,range,BuildCost);
        // We use BuildCost as the first UpgradeCost
        return null;
    }

    public int ReloadTimeLeft() {
        return reloadTimeLeft;
    }

    public void Reload(){
        reloadTimeLeft = reloadTime;
    }

    public void coolDown(){
        if (reloadTimeLeft >0)
            reloadTimeLeft --;
    }

    @Override
    public boolean upgrade(Resources resources) {
        boolean CanUpgrade = super.upgrade(resources);
        if (CanUpgrade) {
            setDamage(getDamage() + 2);
            reloadTime = (reloadTime>=2)? reloadTime-1 : reloadTime;
            setUpgradeCost(getUpgradeCost() + 7);
        }
        return CanUpgrade;
    }

    private Catapult(int damage, int range, int UpgradeCost) {
        super(damage, range, UpgradeCost); reloadTimeLeft = 0; reloadTime = 5;
    }

}
