package org.giwi.networkgraph;

import net.xqhs.graphs.graph.Node;
import net.xqhs.graphs.graph.SimpleEdge;
import net.xqhs.graphs.graph.SimpleNode;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import giwi.org.networkgraph.GraphSurfaceView;
import giwi.org.networkgraph.beans.NetworkGraph;
import giwi.org.networkgraph.beans.Vertex;


/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkGraph graph = new NetworkGraph();

        Node v1 = new SimpleNode("18");
        Node v2 = new SimpleNode("24");
        graph.getVertex().add(new Vertex(v1, ContextCompat.getDrawable(this, R.drawable.avatar)));
        graph.getVertex().add(new Vertex(v2, ContextCompat.getDrawable(this, R.drawable.avatar)));
        graph.addEdge(new SimpleEdge(v1, v2, "12"));

        Node v3 = new SimpleNode("7");
        graph.getVertex().add(new Vertex(v3, ContextCompat.getDrawable(this, R.drawable.avatar)));
        graph.addEdge(new SimpleEdge(v2, v3, "23"));

        v1 = new SimpleNode("14");
        graph.getVertex().add(new Vertex(v1, ContextCompat.getDrawable(this, R.drawable.avatar)));
        graph.addEdge(new SimpleEdge(v3, v1, "34"));

        v1 = new SimpleNode("10");
        graph.getVertex().add(new Vertex(v1, ContextCompat.getDrawable(this, R.drawable.avatar)));
        graph.addEdge(new SimpleEdge(v3, v1, "35"));

        v1 = new SimpleNode("11");
        graph.getVertex().add(new Vertex(v1, ContextCompat.getDrawable(this, R.drawable.avatar)));
        graph.addEdge(new SimpleEdge(v1, v3, "36"));
        graph.addEdge(new SimpleEdge(v3, v1, "6"));

        GraphSurfaceView surface = (GraphSurfaceView) findViewById(R.id.mysurface);
        surface.init(graph);

    }
}
