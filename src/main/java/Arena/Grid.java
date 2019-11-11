package Arena;

import MapElement.MapElement;
import MapElement.Monster.Monster;
import MapElement.Tower.Tower;

import java.util.Stack;

public class Grid {
    final public int x_pos;
    final public int y_pos;
    final public Color color;
    private boolean hasTower;
    private Stack<MapElement> mapElements = new Stack<MapElement>();

    Grid(int X,int Y, Color c){
        x_pos = X;
        y_pos = Y;
        color = c;
        hasTower = false;
    }

    public boolean isHasTower(){
        return hasTower;
    }

    public boolean BuildTower(Tower t){
        if(color == Color.GREEN && !hasTower)
            mapElements.push(t);
        else
            return false;
        hasTower = true;
        return true;
    }

    public boolean DestroyTower(){
        if(color == Color.GREEN && hasTower){
            mapElements.pop();
            hasTower = false;
            return true;
        }
        return false;
    }

    public boolean MonsterAppear(Monster m){
        if(color == Color.GREEN)
            return false;
        mapElements.push(m);
        return true;
    }

    public Monster monster_peek() {
       if (color == Color.WHITE && !mapElements.empty())
           return (Monster) mapElements.peek();
       return null;
    }

    public Monster monster_pop() {
        if (color == Color.WHITE && !mapElements.empty())
            return (Monster) mapElements.pop();
        return null;
    }


    //The functions above may return null, using optional instead maybe better

    enum Color {WHITE,GREEN};


}
