package MapElement.Monster;

public class Penguin extends Monster{

    private static int REPLENISH_HP = 1;

    public Penguin(){
        super();
    }

    public void replenish(){
        setHP(getHP() + REPLENISH_HP);
    }
}
