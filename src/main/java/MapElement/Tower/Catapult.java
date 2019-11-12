package MapElement.Tower;

public class Catapult extends Tower {
    private int reloadTimeLeft;
    private int reloadTime;
    public static final int BuildCost = 4;
    public static final int DefaultPower = 2;
    public static final int DefaultInnerRange = 50;
    public static final int DefaultOuterRange = 150;
    public static final int DefaultReloadTime = 5;


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
    public void upgrade() {
        super.upgrade();
        reloadTime = (reloadTime>2)? reloadTime-1:reloadTime;
    }

    public Catapult(int x, int y) {
        super(DefaultPower, DefaultOuterRange, BuildCost); reloadTimeLeft = 0; reloadTime = DefaultReloadTime;
        setX_position(x);
        setY_position(y);
    }

    @Override
    public String TowerToString(){
        return "Catapult\n" +
                super.TowerToString() +
                "50 - 150";
    }

}
