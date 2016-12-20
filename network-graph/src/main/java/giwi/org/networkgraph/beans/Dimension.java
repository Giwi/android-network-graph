package giwi.org.networkgraph.beans;

/**
 * The type Dimension.
 */
public class Dimension {

    int width;

    int height;

    /**
     * Instantiates a new Dimension.
     *
     * @param w the w
     * @param h the h
     */
    public Dimension(int w, int h) {
        width = w;
        height = h;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Equals boolean.
     *
     * @param o the o
     * @return the boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Dimension) {
            Dimension other = (Dimension) o;
            return (width == other.width) && (height == other.height);
        }

        return false;
    }
}
