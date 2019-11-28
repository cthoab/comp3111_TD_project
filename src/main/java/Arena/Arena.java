package Arena;


import MapElement.Monster.Fox;
import MapElement.Monster.Monster;
import MapElement.Monster.Penguin;
import MapElement.Monster.Unicorn;
import MapElement.Tower.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Arena {

    public static final int DefaultResources = 500;
    public SimpleIntegerProperty Resources = new SimpleIntegerProperty(DefaultResources);
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
            for (int i = 0; i < m.getSpeed(); i++) {
                if(m.getHP()>0){
                    m.move();
//                    TowerAttack();
                }
            }
            m.addSurvivedtime();
            m.evolve();
            if(m.getClass() == Penguin.class)
                ((Penguin)m).replenish();
        }
    }

    public boolean checkGameOver(){
        for(Monster m : monsters)
            if(m.getX_position() >= 440 && m.getHP()>0)
                return true;
        return false;
    }

    public void removeDeadMonsters(){
        Iterator<Monster> m = monsters.iterator();
        while(m.hasNext()){
            Monster monster = m.next();
            if(monster.getHP()<=0)
                m.remove();
        }
    }

    public boolean BuildTower(char T, int position_x, int position_y){
        if(TowerAt(position_x,position_y)!=null)            //A tower exist in the given position
            return false;
        switch (T){
            case 'B' : if (Resources.get()>BasicTower.BuildCost){
                towers.add(new BasicTower(position_x,position_y));
                Resources.set(Resources.get() - BasicTower.BuildCost);
                return true;
            }
            case 'C' : if (Resources.get()>Catapult.BuildCost){
                towers.add(new Catapult(position_x,position_y));
                Resources.set(Resources.get() - Catapult.BuildCost);
                return true;
            }
            case 'I' : if (Resources.get()> IceTower.BuildCost){
                towers.add(new IceTower(position_x,position_y));
                Resources.set(Resources.get() - IceTower.BuildCost);
                return true;
            }
            case 'L' : if (Resources.get()> LaserTower.BuildCost){
                towers.add(new LaserTower(position_x,position_y));
                Resources.set(Resources.get() - LaserTower.BuildCost);
                return true;
            }
            default: return false;
        }
    }

    public Tower TowerAt (int position_x, int position_y){
        for (Tower tower : towers){
            if (tower.getX_position() == position_x)
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
//    public SimpleStringProperty TowerInfo (int position_x, int position_y){
//        SimpleStringProperty tower_info = null;
//        for (Tower tower : towers){
//            if (tower.getX_position() == position_x)
//                if (tower.getY_position() == position_y)
//                    tower_info = new SimpleStringProperty(tower.TowerToString());
//        }
//        return tower_info;
//    }

    public boolean RemoveTower (int position_x, int position_y){
        if (TowerAt(position_x,position_y)!=null){
            towers.remove(TowerAt(position_x,position_y));
            return true;
        }
        return false;
    }

    public boolean UpgradeTower (int position_x, int position_y){
        Tower tower = TowerAt(position_x, position_y);
        if (tower.getUpgradeCost()<Resources.get()){
            Resources.set(Resources.get() - tower.getUpgradeCost());
            tower.upgrade();
            return true;
        }
        return false;
    }




//    private void TowerAttack(){
//        for(Tower t : towers){
//            for(Monster m : monsters){
//                ArrayList<Monster> inRangeMonsters = new ArrayList<>();
//                int maxSteps = 0;
//                Monster closestMonster = null;
//                if(t.checkInRange(m) && !t.getAttacked() && m.getHP()>0)
//                    inRangeMonsters.add(m);
//                for(Monster inRangeM:inRangeMonsters)
//                    if (inRangeM.getSteps() > maxSteps)
//                        closestMonster = inRangeM;
//                if(closestMonster != null){
//                    t.setAttacked(true);
//                    closestMonster.setHP(closestMonster.getHP() - t.getDamage());
//                    System.out.println(t.getClass() + " Attacked " + m.getClass());
//                }
//            }
//        }
//    }

    public void resetTowers(){
        for(Tower t:towers)
            t.setAttacked(false);
    }

}
