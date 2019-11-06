package MapElement.Tower;

import Arena.Resources;
import MapElement.Monster.Monster;

public class Catapult extends Tower {
    private int reloadTimeLeft;
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

    public void coolDown(){
        if (reloadTimeLeft >0)
            reloadTimeLeft --;
    }

    private Catapult(int damage, int range, int UpgradeCost) {
        super(damage, range, UpgradeCost); reloadTimeLeft = 0;
    }

    @Override
    protected boolean attack(Monster m, Resources resources) {

        //TODO
        return false;
    }
}
