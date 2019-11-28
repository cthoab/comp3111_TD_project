package MapElement.Monster;

import MapElement.MapElement;

public abstract class Monster extends MapElement {
    private int hp;
    private int speed;
    private int survivedtime;
    private int stepsTaken;
    protected int evolvedTimes;
    protected static int DEFAULT_HP = 5;
    protected static int DEFAULT_SPEED = 40;

    public int getHP(){ return hp; }
    public void setHP(int hp){ this.hp = hp; }
    public int getSpeed(){ return speed; }
    public void setSpeed(int speed){ this.speed = speed; }
    public int getSteps(){return stepsTaken;}

    public void move(){
        if(this.getX_position()>=440)
            return;
        //move down
        else if((this.getX_position()-20)%160 == 0 && this.getY_position()>=20 && this.getY_position()<460)
            setY_position(getY_position()+1);
        //move up
        else if ((this.getX_position()-100)%160 == 0 && this.getY_position()>20 && this.getY_position()<=460)
            setY_position(getY_position()-1);
        //move right
        else
            setX_position(getX_position()+1);
        stepsTaken++;
    }

    public void evolve(){
        if(survivedtime % 5 == 0) {
            hp += 2;
            speed += 10;
            evolvedTimes++;
        }
    }

    public void addSurvivedtime(){
        survivedtime++;
    }

    protected Monster(){
        this.hp = DEFAULT_HP;
        this.speed = DEFAULT_SPEED;
        survivedtime = 0;
        stepsTaken = 0;
        evolvedTimes = 0;
        setX_position(20);
        setY_position(20);
    }

    public String simpleInfo(){
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

    public int getMaxHP(){
        return DEFAULT_HP + evolvedTimes*2;
    }
}
