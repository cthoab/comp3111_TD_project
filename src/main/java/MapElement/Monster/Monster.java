package MapElement.Monster;

import java.lang.Math;
import MapElement.MapElement;

public abstract class Monster extends MapElement {
    private int hp;
    private int speed;
    protected static int DEFAULT_HP = 5;
    protected static int DEFAULT_SPEED = 1;

    public int getHP(){ return hp; }
    public void setHP(int hp){ this.hp = hp; }
    public int getSpeed(){ return speed; }
    public void setSpeed(int speed){ this.speed = speed; }

    public void move(){
        //TODO
    };
    public void evolve(){
        hp = (int)Math.round(hp * 1.5);
        speed = (int)Math.round(speed * 1.5);
    };

    protected Monster(){
        this.hp = DEFAULT_HP;
        this.speed = DEFAULT_SPEED;
    }
}
