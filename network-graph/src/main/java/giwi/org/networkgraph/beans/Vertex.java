package giwi.org.networkgraph.beans;

import net.xqhs.graphs.graph.Node;

import android.graphics.drawable.Drawable;

/**
 * The type Vertex.
 */
public class Vertex {

    private Node node;

    private Drawable icon;

    /**
     * Instantiates a new Vertex.
     *
     * @param node the node
     * @param icon the icon
     */
    public Vertex(final Node node, final Drawable icon) {
        this.node = node;
        this.icon = icon;
    }

    /**
     * Gets node.
     *
     * @return the node
     */
    public Node getNode() {
        return node;
    }

    /**
     * Sets node.
     *
     * @param node the node
     */
    public void setNode(final Node node) {
        this.node = node;
    }

    /**
     * Gets icon.
     *
     * @return the icon
     */
    public Drawable getIcon() {
        return icon;
    }

    /**
     * Sets icon.
     *
     * @param icon the icon
     */
    public void setIcon(final Drawable icon) {
        this.icon = icon;
    }
}
