package MapElement.Monster;

import java.lang.Math;
import MapElement.MapElement;

public abstract class Monster extends MapElement {
    private int hp;
    private int speed;
    private int survivedtime;
    protected static int DEFAULT_HP = 5;
    protected static int DEFAULT_SPEED = 40;

    public int getHP(){ return hp; }
    public void setHP(int hp){ this.hp = hp; }
    public int getSpeed(){ return speed; }
    public void setSpeed(int speed){ this.speed = speed; }

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
    };
    public void evolve(){
        if(survivedtime % 5 == 0) {
            hp += 2;
            speed += 10;
        }
    };

    public void addSurvivedtime(){
        survivedtime++;
    }

    protected Monster(){
        this.hp = DEFAULT_HP;
        this.speed = DEFAULT_SPEED;
        survivedtime = 0;
        setX_position(20);
        setY_position(20);
    }
}
