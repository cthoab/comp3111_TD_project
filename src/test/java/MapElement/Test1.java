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
    Assert.assertEquals(t.getDamage(),2);
    Assert.assertEquals(t.getUpgradeCost(),2);

        Assert.assertEquals(t.TowerToString(),"Basic Tower\nLevel: 1\nPower: 2\nRange: 65");
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


    }
    @Test
    public void testIceTower(){
      IceTower t = new IceTower(0,0);
        Assert.assertEquals(t.TowerToString(),"Ice Tower\nLevel: 1\nPower: 15\nRange: 120");

    }
    @Test
    public void testLaserTower(){
        LaserTower t = new LaserTower(0,0);
        Assert.assertEquals(t.TowerToString(),"Laser Tower\nLevel: 1\nPower: 2\nRange: Infinity");

    }

    @Test
    public void testCatapult(){
        Catapult t = new Catapult(0,0);
        Assert.assertEquals(t.TowerToString(),"Catapult\nLevel: 1\nPower: 2\nRange: 50 - 150");
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

    }
}