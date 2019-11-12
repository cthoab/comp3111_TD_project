package Arena;


import MapElement.Monster.Fox;
import MapElement.Monster.Monster;
import MapElement.Monster.Penguin;
import MapElement.Monster.Unicorn;
import MapElement.Tower.*;

import java.util.ArrayList;
import java.util.Random;

public class Arena {

    public static final int DefaultResources = 5;
    public int Resources = DefaultResources;
    public ArrayList<Monster> monsters = new ArrayList<>();
    public ArrayList<Tower> towers = new ArrayList<>();


    public Arena(){

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
        for(Monster m : monsters) {
            for (int i = 0; i < m.getSpeed(); i++)
                m.move();
            m.addSurvivedtime();
            m.evolve();
            if(m.getClass() == Penguin.class)
                ((Penguin)m).replenish();
        }
    }

    public boolean checkGameOver(){
        for(Monster m : monsters)
            if(m.getX_position() > 440 && m.getHP()>0)
                return true;
        return false;
    }

    public void removeDeadMonsters(){
        for(Monster m : monsters){
            if(m.getHP() <= 0)
                monsters.remove(m);
        }
    }

    public boolean BuildTower(char T, int row, int col){
        switch (T){
            case 'B' : if (Resources>BasicTower.BuildCost){
                towers.add(new BasicTower(row*40,col*40));
                return true;
            }
            case 'C' : if (Resources>Catapult.BuildCost){
                towers.add(new Catapult(row*40,col*40));
                return true;
            }
            case 'I' : if (Resources> IceTower.BuildCost){
                towers.add(new IceTower(row*40,col*40));
                return true;
            }
            case 'L' : if (Resources> LaserTower.BuildCost){
                towers.add(new LaserTower(row*40,col*40));
                return true;
            }
            default: return false;
        }
    }




}
