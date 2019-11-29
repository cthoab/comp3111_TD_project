package MapElement.Tower;

import MapElement.MapElement;

public class LaserTower extends Tower {
    public static int BuildCost = 8;
    public static final int DefaultPower = 10;
    public static final int DefaultAttackCost = 1;

    public int attackCost;


    public LaserTower(int x, int y) {
        super(DefaultPower,2000, BuildCost);
        attackCost = DefaultAttackCost;
        setX_position(x);
        setY_position(y);
    }

    @Override
    public void upgrade() {
        super.upgrade();
        attackCost++;
    }

    @Override
    public String TowerToString(){
        return "Laser Tower\n" +
                super.TowerToString() +
                "Infinity";
    }

    @Override
    public boolean checkInRange(MapElement m) {
        return true;
    }

}
