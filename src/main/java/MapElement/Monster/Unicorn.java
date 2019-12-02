package MapElement.Monster;

/**
 * Unicorn class
 */
public class Unicorn extends Monster {

    protected static int UNICORN_HP = 7;

    /**
     * Constructor
     */
    public Unicorn() {
        super();
        setHP(UNICORN_HP);
    }

    /**
     * Getter of the MAX HP of the unicorn since it has a higher HP than the others
     * @return MAX HP of that unicorn
     */
    @Override
    public int getMaxHP() {
        return UNICORN_HP + evolvedTimes * 2;
    }

}
