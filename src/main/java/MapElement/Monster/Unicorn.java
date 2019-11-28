package MapElement.Monster;

public class Unicorn extends Monster {

    protected static int UNICORN_HP = 7;

    public Unicorn(){
        super();
        setHP(UNICORN_HP);
    }

    @Override
    public int getMaxHP(){
        return UNICORN_HP + evolvedTimes*2;
    }

}
