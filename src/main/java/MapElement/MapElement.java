package MapElement;

public abstract class MapElement {
    private int x_position;
    private int y_position;

    public int getX_position() {
        return x_position;
    }

    public int getY_position() {
        return y_position;
    }

    public void setX_position(int x_position) {
        this.x_position = x_position;
    }

    public void setY_position(int y_position) {
        this.y_position = y_position;
    }

    public static int getDistance(MapElement a, MapElement b){
        Double d = Math.sqrt(Math.pow(a.x_position - b.x_position,2) + Math.pow(a.y_position - b.y_position,2));
        return (int)Math.round(d);
    }
}
