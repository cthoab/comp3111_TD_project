package Arena;

import MapElement.Monster.Monster;
import MapElement.Tower.BasicTower;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArenaTests {

    private Arena a;

    @Test
    public void testMonster(){
        a = new Arena();
        for(int i=0; i<10; i++)
            a.spawnMonster();
        Assert.assertEquals(a.monsters.size(),10);

        for(int i=0; i<100; i++)
            a.monsterMove();
        Assert.assertEquals(a.checkGameOver(),true);

        for (Monster m : a.monsters)
            m.setHP(0);
        a.removeDeadMonsters();
        Assert.assertEquals(a.monsters.size(),0);
    }

    @Test
    public void testTower(){
        a = new Arena();
        a.BuildTower('B',1,0);
        a.BuildTower('C',1,1);
        a.BuildTower('I',1,2);
        a.BuildTower('L',1,3);
        a.BuildTower('D',1,4);
        a.BuildTower('L',1,0);
        Assert.assertEquals(a.towers.size(),4);

        BasicTower b = new BasicTower(1,0);
        Assert.assertEquals(a.TowerInfo(1,0), b.TowerToString());
        Assert.assertEquals(a.TowerInfo(2,2), null);

        Assert.assertEquals(a.RemoveTower(1,1),true);
        Assert.assertEquals(a.RemoveTower(2,2),false);

        Assert.assertEquals(a.UpgradeTower(1,2),true);
        a.Resources.set(0);
        Assert.assertEquals(a.UpgradeTower(1,2),false);

    }
}
