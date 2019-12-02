package MapElement;

/**
 * Object that has x-coordinate and y-coordinate
 */
public abstract class MapElement {
    private int x_position;
    private int y_position;

    /**
     * Calculate the distance of two MapElements
     * @param a the first MapElement
     * @param b the second MapElement
     * @return the Distance
     */
    public static int getDistance(MapElement a, MapElement b) {
        Double d = Math.sqrt(Math.pow(a.x_position - b.x_position, 2) + Math.pow(a.y_position - b.y_position, 2));
        return (int) Math.round(d);
    }

    /**
     * Getter of x-coordinate
     * @return x-coordinate
     */
    public int getX_position() {
        return x_position;
    }

    /**
     * Setter of x-coordinate
     * @param x_position the value of x-coordinate that is going to be set
     */
    public void setX_position(int x_position) {
        this.x_position = x_position;
    }

    /**
     * Getter of y-coordinate
     * @return y-coordinate
     */
    public int getY_position() {
        return y_position;
    }

    /**
     * Setter of y-coordinate
     * @param y_position the value of y-coordinate that is going to be set
     */
    public void setY_position(int y_position) {
        this.y_position = y_position;
    }
}
