package MapElement.Monster;

public class Penguin extends Monster{

    private static int REPLENISH_HP = 1;
    private static int ORIGINAL_HP;

    public Penguin(){
        super();
        ORIGINAL_HP = getHP();
    }

    public void replenish(){
        if(getHP() + REPLENISH_HP <= ORIGINAL_HP)
            setHP(getHP() + REPLENISH_HP);
        else
            setHP(ORIGINAL_HP);
    }
}
