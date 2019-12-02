package MapElement.Monster;

import MapElement.MapElement;

/**
 * An abstract class that defines what is a Monster
 */
public abstract class Monster extends MapElement {
    protected static int DEFAULT_HP = 5;
    protected static int DEFAULT_SPEED = 40;
    protected int evolvedTimes;
    private int hp;
    private int speed;
    private int survivedtime;
    private int stepsTaken;

    protected Monster() {
        this.hp = DEFAULT_HP;
        this.speed = DEFAULT_SPEED;
        survivedtime = 0;
        stepsTaken = 0;
        evolvedTimes = 0;
        setX_position(20);
        setY_position(20);
    }

    /**
     * Getter of monster's HP
     * @return monster's HP
     */
    public int getHP() {
        return hp;
    }

    /**
     * Setter of monster's HP
     * @param hp HP value that is going to be set
     */
    public void setHP(int hp) {
        this.hp = hp;
    }

    /**
     * Getter of the monster's speed
     * @return monster's speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Setter of the monster's speed
     * @param speed Speed value that is going to be set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Getter of how many move the monster has taken
     * @return Steps
     */
    public int getSteps() {
        return stepsTaken;
    }

    /**
     * The function that control where the monster will move towards
     */
    public void move() {
        if (this.getX_position() >= 440)
            return;
            //move down
        else if ((this.getX_position() - 20) % 160 == 0 && this.getY_position() >= 20 && this.getY_position() < 460)
            setY_position(getY_position() + 1);
            //move up
        else if ((this.getX_position() - 100) % 160 == 0 && this.getY_position() > 20 && this.getY_position() <= 460)
            setY_position(getY_position() - 1);
            //move right
        else
            setX_position(getX_position() + 1);
        stepsTaken++;
    }

    /**
     * The function that makes monster stronger
     */
    public void evolve() {
        if (survivedtime % 5 == 0) {
            hp += 2;
            speed += 10;
            evolvedTimes++;
        }
    }

    /**
     * The function that calculate the monster's time of survival since spawn
     */
    public void addSurvivedtime() {
        survivedtime++;
    }

    /**
     * Output the type and the position of the monster in String
     * @return String (info of the monster)
     */
    public String simpleInfo() {
        String output;
        if (this instanceof Fox)
            output = "Fox ";
        else if (this instanceof Penguin)
            output = "Penguin ";
        else if (this instanceof Unicorn)
            output = "Unicorn ";
        else
            throw new IllegalStateException("Monster of wrong type.");
        return output + "at (" + this.getX_position() + "," + this.getY_position() + ")";
    }

    /**
     * Getter of the MAX HP of the monster
     * @return The monster's maximum HP
     */
    public int getMaxHP() {
        return DEFAULT_HP + evolvedTimes * 2;
    }
}
