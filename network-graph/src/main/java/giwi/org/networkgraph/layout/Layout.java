package giwi.org.networkgraph.layout;

import net.xqhs.graphs.graph.Node;

import org.apache.commons.collections4.Transformer;

import giwi.org.networkgraph.beans.Dimension;
import giwi.org.networkgraph.beans.NetworkGraph;
import giwi.org.networkgraph.beans.Point2D;

/**
 * The interface Layout.
 */
interface Layout extends Transformer<Node, Point2D> {

  /**
   * Initialize void.
   */
  void initialize();

  /**
   * provides initial locations for all vertices.
   *
   * @param initializer the initializer
   */
  void setInitializer(Transformer<Node, Point2D> initializer);

  /**
   * setter for graph
   *
   * @param graph the graph
   */
  void setGraph(NetworkGraph graph);

  /**
   * Returns the full graph (the one that was passed in at
   * construction time) that this Layout refers to.
   *
   * @return the graph
   */
  NetworkGraph getGraph();

  /**
   * Reset void.
   */
  void reset();

  /**
   * Sets size.
   *
   * @param d the d
   */
  void setSize(Dimension d);

  /**
   * Returns the current size of the visualization's space.
   *
   * @return the size
   */
  Dimension getSize();


  /**
   * Sets a flag which fixes this vertex in place.
   *
   * @param v     vertex
   * @param state the state
   */
  void lock(Node v, boolean state);

  /**
   * Returns <code>true</code> if the position of vertex <code>v</code>
   * is locked.
   *
   * @param v the v
   *
   * @return the boolean
   */
  boolean isLocked(Node v);

  /**
   * set the location of a vertex
   *
   * @param v        the v
   * @param location the location
   */
  void setLocation(Node v, Point2D location);
}
