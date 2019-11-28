package MapElement.Tower;

import MapElement.MapElement;

public class IceTower extends Tower {
    public static final int BuildCost = 4;
    public static final int DefaultRange = 120;
    public static final int DefaultPower = 15;




    //Damage in IceTower means the speed to slow down, it will not reduce HP.

    public IceTower(int x, int y) {
        super(DefaultPower, DefaultRange, BuildCost);
        setX_position(x);
        setY_position(y);
    }

    @Override
    public String TowerToString(){
        return "Ice Tower\n" +
                super.TowerToString() +
                getRange();
    }

    @Override
    public boolean checkInRange(MapElement m) {
        if(getDistance(this, m) < DefaultRange)
            return true;
        return false;
    }


}
