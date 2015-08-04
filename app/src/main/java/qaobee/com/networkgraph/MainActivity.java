package qaobee.com.networkgraph;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.RelativeLayout;

import net.xqhs.graphs.graph.Graph;
import net.xqhs.graphs.graph.Node;
import net.xqhs.graphs.graph.SimpleEdge;
import net.xqhs.graphs.graph.SimpleGraph;
import net.xqhs.graphs.graph.SimpleNode;

import qaobee.com.networkgraph.graph.GraphSurfaceView;
import qaobee.com.networkgraph.graph.GraphView;
import qaobee.com.networkgraph.graph.beans.Dimension;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements Runnable {
    /**
     * The Locker.
     */
    private boolean locker = true;
    /**
     * The Current graph view.
     */
    private GraphView currentGraphView;
    /**
     * The Graph surface.
     */
    private GraphSurfaceView graphSurface;
    /**
     * The Holder.
     */
    private SurfaceHolder holder;
    /**
     * The Thread.
     */
    private Thread thread;

    /**
     * On create.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Graph graph = new SimpleGraph();
        Node v1 = new SimpleNode("18");
        Node v2 = new SimpleNode("24");
        graph.addNode(v1);
        graph.addNode(v2);
        graph.addEdge(new SimpleEdge(v1, v2, "12"));
        Node v3 = new SimpleNode("7");
        graph.addNode(v3);
        graph.addEdge(new SimpleEdge(v2, v3, "23"));
        v1 = new SimpleNode("14");
        graph.addNode(v1);
        graph.addEdge(new SimpleEdge(v3, v1, "34"));
        v1 = new SimpleNode("10");
        graph.addNode(v1);
        graph.addEdge(new SimpleEdge(v3, v1, "35"));
        v1 = new SimpleNode("11");
        graph.addNode(v1);
        graph.addEdge(new SimpleEdge(v1, v3, "36"));
        graph.addEdge(new SimpleEdge(v3, v1, "6"));

        View surface = findViewById(R.id.mysurface);
        RelativeLayout parent = (RelativeLayout) surface.getParent();
        int index = parent.indexOfChild(surface);
        parent.removeView(surface);
        graphSurface = new GraphSurfaceView(this.getApplicationContext());
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.FILL_PARENT);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.buttonswap);
        parent.addView(graphSurface, index, layoutParams);
        holder = graphSurface.getHolder();
        currentGraphView = new GraphView(this);
        currentGraphView.init(graph, new Dimension(400, 400));
    }

    /**
     * Run void.
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (locker) {
            //checks if the lockCanvas() method will be success,and if not, will check this statement again
            if (!holder.getSurface().isValid()) {
                continue;
            }
            /** Start editing pixels in this surface.*/
            Canvas canvas = holder.lockCanvas();

            currentGraphView.resize(new Dimension(graphSurface.getWidth(), graphSurface.getHeight()));
            currentGraphView.doLayout();

            draw(canvas);


            // End of painting to canvas. system will paint with this canvas,to the surface.
            holder.unlockCanvasAndPost(canvas);
            locker = false;
        }
    }

    /**
     * This method deals with paint-works. Also will paint something in background
     *
     * @param canvas the canvas
     */
    private void draw(Canvas canvas) {
        float scaleFactor = graphSurface.getScaleFactor();

        canvas.drawColor(Color.WHITE);
        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);
        currentGraphView.draw(canvas, getResources());
        canvas.restore();
    }

    /**
     * On pause.
     */
    @Override
    protected void onPause() {
        super.onPause();
        pause();
    }

    /**
     * Pause void.
     */
    private void pause() {
        //CLOSE LOCKER FOR run();
        locker = false;
        while (true) {
            try {
                //WAIT UNTIL THREAD DIE, THEN EXIT WHILE LOOP AND RELEASE a thread
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        thread = null;
    }

    /**
     * On resume.
     */
    @Override
    protected void onResume() {
        super.onResume();
        resume();
    }

    /**
     * Resume void.
     */
    private void resume() {
        //RESTART THREAD AND OPEN LOCKER FOR run();
        locker = true;
        thread = new Thread(this);
        thread.start();
    }

}
