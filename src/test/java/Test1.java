package test;
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


}