package MapElement;
import MainProgram.*;
import MapElement.Tower.*;
import MapElement.Monster.*;
import MapElement.*;
import Arena.*;

import org.junit.Assert;
import org.junit.Test;
public class Test1 {

    @Test
    public void testBasicTower() {
        BasicTower t = new BasicTower(0,0);
    Assert.assertEquals(t.getX_position(),0);
    Assert.assertEquals(t.getY_position(),0);
    Assert.assertEquals(t.getLevel(),1);
    Assert.assertEquals(t.getDamage(),4);
    Assert.assertEquals(t.getUpgradeCost(),2);

        Assert.assertEquals(t.TowerToString(),"Basic Tower\nLevel: 1\nPower: 4\nRange: 65");
    t.setAttacked(true);
    MapElement m = new BasicTower(1,1);
    int range = t.getDistance(t,m);
    if(t.getRange()>range)
        Assert.assertEquals(t.checkInRange(m),true);
    else
        Assert.assertEquals(t.checkInRange(m),false);
    MapElement n = new BasicTower(1000,2000);
        int range2 = t.getDistance(t,n);
    if(t.getRange()>range2)
        Assert.assertEquals(t.checkInRange(n),true);
    else
        Assert.assertEquals(t.checkInRange(n),false);
    Assert.assertEquals(t.getAttacked(), true);
    t.upgrade();

    Assert.assertEquals(t.simpleInfo(),"Basic Tower at (0,0) ");

    }
    @Test
    public void testIceTower(){
      IceTower t = new IceTower(0,0);
        Assert.assertEquals(t.TowerToString(),"Ice Tower\nLevel: 1\nPower: 15\nRange: 120");
        Assert.assertEquals(t.simpleInfo(),"Ice Tower at (0,0) ");
    }
    @Test
    public void testLaserTower(){
        LaserTower t = new LaserTower(0,0);

        Assert.assertEquals(t.TowerToString(),"Laser Tower\nLevel: 1\nPower: 5\nRange: Infinity");
        Assert.assertEquals(t.simpleInfo(),"Laser Tower at (0,0) ");
    }

    @Test
    public void testCatapult(){
        Catapult t = new Catapult(0,0);
        Assert.assertEquals(t.TowerToString(),"Catapult\nLevel: 1\nPower: 6\nRange: 50 - 150\nCool Down Time Left: 0");
        Assert.assertEquals(t.ReloadTimeLeft(),0);
        t.Reload();
        Assert.assertEquals(t.ReloadTimeLeft(),5);

        for(int i = 0; i < 5; i++){
            t.coolDown();
        }
        t.coolDown();
        Assert.assertEquals(t.ReloadTimeLeft(),0);
        t.upgrade();
        t.upgrade();
        t.upgrade();
        t.upgrade();

        Assert.assertEquals(t.simpleInfo(),"Catapult at (0,0) ");
    }
    @Test
    public void testFox(){
        Fox m = new Fox();
        Assert.assertEquals(m.simpleInfo(),"Fox at (20,20)");
        Assert.assertEquals(m.getSteps(),0);
    }
    @Test
    public void testPenguin(){
        Penguin m = new Penguin();
        Assert.assertEquals(m.simpleInfo(),"Penguin at (20,20)");
        m.replenish();
        m.evolve();
        for(int i = 0; i < 4; i++) m.addSurvivedtime();
        m.evolve();
        m.setHP(1);
        m.replenish();
        m.move();
        m.setX_position(440);
        m.move();
        m.setX_position(180);
        m.setY_position(21);
        m.move();
        m.setX_position(260);
        m.move();


        Assert.assertEquals(m.getSpeed(),50);

    }
    @Test
    public void testUnicorn(){
        Unicorn m = new Unicorn();
        Assert.assertEquals(m.simpleInfo(),"Unicorn at (20,20)");
    }

    /*
    @Test
    public void testArena(){
        Arena a = new Arena();
        for(int i = 0; i <30; i++) a.spawnMonster();
        a.monsterMove();
        for(Monster m : a.monsters)
            m.setHP(0);
        a.monsterMove();
        a.removeDeadMonsters();
        Assert.assertEquals(a.checkGameOver(),false);
        a.spawnMonster();
        for(Monster m : a.monsters)
            m.setX_position(440);
        Assert.assertEquals(a.checkGameOver(),true);
        Assert.assertEquals(a.BuildTower('B',0,0),true);
        Assert.assertEquals(a.BuildTower('I',1,0),true);
        Assert.assertEquals(a.BuildTower('L',2,0),true);
        Assert.assertEquals(a.BuildTower('C',3,0),true);

        Assert.assertEquals(a.BuildTower('G',3,2),false);
        Assert.assertEquals(a.BuildTower('B',0,0),false);

        Assert.assertEquals(a.RemoveTower(0,0),true);
        Assert.assertEquals(a.RemoveTower(0,0),true);
        Assert.assertEquals(a.UpgradeTower(1,0),true);
        a.resetTowers();
        Assert.assertEquals("Laser Tower\nLevel: 1\nPower: 2\nRange: Infinity",a.TowerInfo(2,0));
        Assert.assertEquals(null,a.TowerInfo(0,0));





    }*/
}