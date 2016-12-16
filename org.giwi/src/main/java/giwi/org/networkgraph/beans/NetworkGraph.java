package giwi.org.networkgraph.beans;

import java.util.ArrayList;
import java.util.List;

public class NetworkGraph extends net.xqhs.graphs.graph.SimpleGraph {

  private List<Vertex> vertex = new ArrayList<>();

  private int defaultColor = android.R.color.black;

  private int edgeColor = android.R.color.holo_blue_light;

  private int nodeColor = android.R.color.holo_blue_light;

  private int nodeBgColor = android.R.color.white;

  public List<Vertex> getVertex() {
    return vertex;
  }

  public void setVertex(final List<Vertex> vertex) {
    this.vertex = vertex;
  }

  public int getDefaultColor() {
    return defaultColor;
  }

  public void setDefaultColor(final int defaultColor) {
    this.defaultColor = defaultColor;
  }

  public int getEdgeColor() {
    return edgeColor;
  }

  public void setEdgeColor(final int edgeColor) {
    this.edgeColor = edgeColor;
  }

  public int getNodeColor() {
    return nodeColor;
  }

  public void setNodeColor(final int nodeColor) {
    this.nodeColor = nodeColor;
  }

  public int getNodeBgColor() {
    return nodeBgColor;
  }

  public void setNodeBgColor(final int nodeBgColor) {
    this.nodeBgColor = nodeBgColor;
  }
}
