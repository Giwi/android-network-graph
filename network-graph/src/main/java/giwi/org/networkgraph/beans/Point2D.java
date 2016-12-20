package giwi.org.networkgraph.beans;

/**
 * The type Point 2 d.
 */
public class Point2D {

    protected double x;

    protected double y;

    /**
     * Instantiates a new Point 2 d.
     *
     * @param x the x
     * @param y the y
     */
    Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiates a new Point 2 d.
     */
    public Point2D() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets location.
     *
     * @param x the x
     * @param y the y
     */
    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets location.
     *
     * @param p the p
     */
    public void setLocation(Point2D p) {
        this.x = p.x;
        this.y = p.y;
    }
}
