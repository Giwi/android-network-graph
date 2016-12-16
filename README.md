# android-network-graph

## Description 

Network graph based on :

- [https://github.com/andreiolaru-ro/AmIciTy-Grph](https://github.com/andreiolaru-ro/AmIciTy-Grph)
- [https://github.com/andreiolaru-ro/net.xqhs.Graphs](https://github.com/andreiolaru-ro/net.xqhs.Graphs)

Here's a screenshot :

![screen](http://i.imgur.com/kLq1mQ6.png)

## Usage

````groovy
repositories {
    jcenter()
}
dependencies {
    compile 'giwi.android:android-network-graph:0.0.1'
}
````

````xml
  <giwi.org.networkgraph.GraphSurfaceView
      android:id="@+id/mysurface"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:layout_centerHorizontal="true"
      android:layout_centerVertical="true"
      app:nodeBgColor="@android:color/white"
      app:defaultColor="@android:color/black"
      app:edgeColor="@android:color/holo_blue_light"
      app:nodeColor="@android:color/holo_blue_light"
      android:layout_margin="10dp"/>
````

````java
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
````

Setting colors programmatically

````java
graph.setDefaultColor(ContextCompat.getColor(this, android.R.color.black));
graph.setEdgeColor(ContextCompat.getColor(this, android.R.color.holo_blue_light));
graph.setNodeColor(ContextCompat.getColor(this, android.R.color.holo_blue_light));
graph.setNodeBgColor(ContextCompat.getColor(this, android.R.color.white));
````

