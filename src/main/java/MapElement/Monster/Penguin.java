package MapElement.Monster;

/**
 * Penguin class
 */
public class Penguin extends Monster {

    private static int REPLENISH_HP = 1;
    private static int ORIGINAL_HP;

    /**
     * Constructor
     */
    public Penguin() {
        super();
        ORIGINAL_HP = getHP();
    }

    /**
     * Replenish when it dies the first time
     */
    public void replenish() {
        if (getHP() + REPLENISH_HP <= ORIGINAL_HP)
            setHP(getHP() + REPLENISH_HP);
        else
            setHP(ORIGINAL_HP);
    }
}
