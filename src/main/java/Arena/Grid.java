package Arena;

import MapElement.MapElement;
import MapElement.Monster.Monster;
import MapElement.Tower.Tower;

import java.util.Stack;

public class Grid {
    final public int x_pos;
    final public int y_pos;
    final public Color color;
    private Stack<MapElement> mapElements = new Stack<MapElement>();

    Grid(int X,int Y, Color c){
        x_pos = X;
        y_pos = Y;
        color = c;
    }
    public boolean BuildTower(Tower t){
        if(color == Color.GREEN && mapElements.empty())
            mapElements.push(t);
        else
            return false;
        return true;
    }
    public boolean MonsterAppear(Monster m){
        if(color == Color.GREEN)
            return false;
        mapElements.push(m);
        return true;
    }
    public boolean DestroyTower(){
        if(color == Color.GREEN && !mapElements.empty()){
            mapElements.pop();
            return true;
        }
        return false;
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
