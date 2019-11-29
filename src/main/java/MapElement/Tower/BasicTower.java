package MapElement.Tower;


import MapElement.MapElement;

public class BasicTower extends Tower {
    public static final int BuildCost = 2;        // TODO need to be assigned value
    public static final int DefaultPower = 2;
    public static final int DefaultRange = 65;


    public BasicTower(int x, int y){
        super(DefaultPower, DefaultRange, BuildCost);
        setX_position(x);
        setY_position(y);
    }

    @Override
    public String TowerToString(){
        return "Basic Tower\n" +
                super.TowerToString() +
                getRange();
    }

    @Override
    public boolean checkInRange(MapElement m){
        if(getDistance(this, m) < DefaultRange)
            return true;
        return false;
    }

}
