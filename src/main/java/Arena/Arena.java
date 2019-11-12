package Arena;


import MapElement.Monster.Fox;
import MapElement.Monster.Monster;
import MapElement.Monster.Penguin;
import MapElement.Monster.Unicorn;
import MapElement.Tower.*;

import java.util.ArrayList;
import java.util.Random;

public class Arena {

    public static final int DefaultResources = 500;
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
        for(Monster m : monsters)
            for(int i=0; i<m.getSpeed(); i++)
                m.move();
    }

    public boolean BuildTower(char T, int position_x, int position_y){
        if(TowerAt(position_x,position_y)!=null)            //A tower exist in the given position
            return false;
        switch (T){
            case 'B' : if (Resources>BasicTower.BuildCost){
                towers.add(new BasicTower(position_x,position_y));
                Resources -= BasicTower.BuildCost;
                return true;
            }
            case 'C' : if (Resources>Catapult.BuildCost){
                towers.add(new Catapult(position_x,position_y));
                Resources -= Catapult.BuildCost;
                return true;
            }
            case 'I' : if (Resources> IceTower.BuildCost){
                towers.add(new IceTower(position_x,position_y));
                Resources -= IceTower.BuildCost;
                return true;
            }
            case 'L' : if (Resources> LaserTower.BuildCost){
                towers.add(new LaserTower(position_x,position_y));
                Resources -= LaserTower.BuildCost;
                return true;
            }
            default: return false;
        }
    }

    public Tower TowerAt (int postion_x, int position_y){
        for (Tower tower : towers){
            if (tower.getX_position() == postion_x)
                if (tower.getY_position() == position_y)
                    return tower;
        }
        return null;
    }

    public String TowerInfo (int position_x, int position_y){
        for (Tower tower : towers){
            if (tower.getX_position() == position_x)
                if (tower.getY_position() == position_y)
                    return tower.TowerToString();
        }
        return null;
    }

    public boolean RemoveTower (int position_x, int position_y){
        if (TowerAt(position_x,position_y)!=null){
            towers.remove(TowerAt(position_x,position_y));
            return true;
        }
        return true;
    }



}
