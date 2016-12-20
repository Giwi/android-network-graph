package giwi.org.networkgraph.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Network graph.
 */
public class NetworkGraph extends net.xqhs.graphs.graph.SimpleGraph {

    private List<Vertex> vertex = new ArrayList<>();

    private int defaultColor = android.R.color.black;

    private int edgeColor = android.R.color.holo_blue_light;

    private int nodeColor = android.R.color.holo_blue_light;

    private int nodeBgColor = android.R.color.white;

    /**
     * Gets vertex.
     *
     * @return the vertex
     */
    public List<Vertex> getVertex() {
        return vertex;
    }

    /**
     * Sets vertex.
     *
     * @param vertex the vertex
     */
    public void setVertex(final List<Vertex> vertex) {
        this.vertex = vertex;
    }

    /**
     * Gets default color.
     *
     * @return the default color
     */
    public int getDefaultColor() {
        return defaultColor;
    }

    /**
     * Sets default color.
     *
     * @param defaultColor the default color
     */
    public void setDefaultColor(final int defaultColor) {
        this.defaultColor = defaultColor;
    }

    /**
     * Gets edge color.
     *
     * @return the edge color
     */
    public int getEdgeColor() {
        return edgeColor;
    }

    /**
     * Sets edge color.
     *
     * @param edgeColor the edge color
     */
    public void setEdgeColor(final int edgeColor) {
        this.edgeColor = edgeColor;
    }

    /**
     * Gets node color.
     *
     * @return the node color
     */
    public int getNodeColor() {
        return nodeColor;
    }

    /**
     * Sets node color.
     *
     * @param nodeColor the node color
     */
    public void setNodeColor(final int nodeColor) {
        this.nodeColor = nodeColor;
    }

    /**
     * Gets node bg color.
     *
     * @return the node bg color
     */
    public int getNodeBgColor() {
        return nodeBgColor;
    }

    /**
     * Sets node bg color.
     *
     * @param nodeBgColor the node bg color
     */
    public void setNodeBgColor(final int nodeBgColor) {
        this.nodeBgColor = nodeBgColor;
    }
}
