package Arena;

import MapElement.Monster.*;
import MapElement.Tower.Tower;

import java.util.ArrayList;

public class Arena {
    Resources resources;
    ArrayList<Monster> monsters = new ArrayList<>();
    ArrayList<Tower> towers = new ArrayList<>();





    public Arena(int size){
        resources = new Resources(0);
    }

    public void RangeDamage(int center_x, int center_y, int radius, int damage){
        //TODO
    }

}
