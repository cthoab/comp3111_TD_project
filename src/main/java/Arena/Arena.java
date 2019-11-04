package Arena;

public class Arena {
    Resources resources;
    Grid[][] arena;

    public Arena(int size){
        arena = new Grid[size][size];
        resources = new Resources(0);
    }

    public void RangeDamage(int center_x, int center_y, int radius, int damage){
        //TODO
    }

}
