package Arena;


import MapElement.MapElement;
import MapElement.Monster.*;
import MapElement.Tower.Tower;
import java.util.Random;

import java.util.ArrayList;

public class Arena {

    private Resources resources;
    public ArrayList<Monster> monsters = new ArrayList<Monster>();
    public ArrayList<Tower> towers = new ArrayList<Tower>();


    public Arena(){
        resources = new Resources(5000);
    }
    public Resources getResources(){
        return resources;
    }

    public void spawnMonster(){
        Random rand = new Random();
        int n = rand.nextInt(3);
        switch(n){
            case 0:
                monsters.add(new Fox());
                break;
            case 1:
                monsters.add(new Penguin());
                break;
            case 2:
                monsters.add(new Unicorn());
        }

    }

    public void monsterMove(){
        for(Monster m : monsters)
            for(int i = 0; i < m.getSpeed(); i++)
                m.move();
    }

}
