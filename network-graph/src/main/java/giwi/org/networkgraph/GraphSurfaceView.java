package giwi.org.networkgraph;

import net.xqhs.graphs.graph.Edge;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import giwi.org.networkgraph.beans.ArcUtils;
import giwi.org.networkgraph.beans.Dimension;
import giwi.org.networkgraph.beans.NetworkGraph;
import giwi.org.networkgraph.beans.Point2D;
import giwi.org.networkgraph.beans.Vertex;
import giwi.org.networkgraph.layout.FRLayout;

/**
 * The type NetworkGraph surface view.
 */
public class GraphSurfaceView extends SurfaceView {

    private ScaleGestureDetector mScaleDetector;

    private TypedArray attributes;

    private float mScaleFactor = 1.f;

    /**
     * Instantiates a new NetworkGraph surface view.
     *
     * @param context the context
     */
    public GraphSurfaceView(Context context) {
        super(context);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    /**
     * Instantiates a new Graph surface view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public GraphSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        attributes = getContext().obtainStyledAttributes(attrs, R.styleable.GraphSurfaceView);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    /**
     * Instantiates a new Graph surface view.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public GraphSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        attributes = getContext().obtainStyledAttributes(attrs, R.styleable.GraphSurfaceView);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }


    /**
     * Init.
     *
     * @param graph the graph
     */
    public void init(final NetworkGraph graph) {
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Canvas canvas = holder.lockCanvas(null);
                canvas.drawARGB(0, 225, 225, 255);
                drawGraph(canvas, graph);
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                // TODO Auto-generated method stub

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void drawGraph(final Canvas canvas, final NetworkGraph graph) {
        Paint paint = new Paint();
        Paint whitePaint = new Paint();
        paint.setAntiAlias(true);
        FRLayout layout = new FRLayout(graph, new Dimension(getWidth(), getHeight()));
        whitePaint.setColor(attributes.getColor(R.styleable.GraphSurfaceView_nodeBgColor, graph.getNodeBgColor()));
        whitePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        whitePaint.setStrokeWidth(2f);
        whitePaint.setShadowLayer(5, 0, 0, attributes.getColor(R.styleable.GraphSurfaceView_defaultColor, graph
                .getDefaultColor()));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(20f);
        paint.setColor(attributes.getColor(R.styleable.GraphSurfaceView_defaultColor, graph.getDefaultColor()));
        for (Edge edge : graph.getEdges()) {
            Point2D p1 = layout.transform(edge.getFrom());
            Point2D p2 = layout.transform(edge.getTo());
            paint.setStrokeWidth(Float.valueOf(edge.getLabel()) + 1f);
            paint.setColor(attributes.getColor(R.styleable.GraphSurfaceView_edgeColor, graph.getEdgeColor()));
            Paint curve = new Paint();
            curve.setAntiAlias(true);
            curve.setStyle(Paint.Style.STROKE);
            curve.setStrokeWidth(2);
            curve.setColor(attributes.getColor(R.styleable.GraphSurfaceView_edgeColor, graph.getEdgeColor()));
            PointF e1 = new PointF((float) p1.getX(), (float) p1.getY());
            PointF e2 = new PointF((float) p2.getX(), (float) p2.getY());
            ArcUtils.drawArc(e1, e2, 36f, canvas, curve, paint, whitePaint, Integer.parseInt(edge.getLabel()));
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(30f);
        paint.setStrokeWidth(0f);
        paint.setColor(attributes.getColor(R.styleable.GraphSurfaceView_nodeColor, graph.getNodeColor()));
        for (Vertex node : graph.getVertex()) {
            Point2D position = layout.transform(node.getNode());
            canvas.drawCircle((float) position.getX(), (float) position.getY(), 40, whitePaint);
            if (node.getIcon() != null) {
                Bitmap b = ((BitmapDrawable) node.getIcon()).getBitmap();
                Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
                Bitmap roundBitmap = getCroppedBitmap(bitmap, 75);
                canvas.drawBitmap(roundBitmap,
                        (float) position.getX() - 38f, (float) position.getY() - 38f, null);
            }
            canvas.drawRect(
                    (float) position.getX() - 20,
                    (float) position.getY() + 50,
                    (float) position.getX() + 20, (float) position.getY() + 10, whitePaint);
            canvas.drawText(node.getNode().getLabel(), (float) position.getX(),
                    (float) position.getY() + 40, paint);
        }
    }

    private Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        } else {
            sbmp = bmp;
        }
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(
                sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f, sbmp.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);
        return output;
    }

    /**
     * On touch event.
     *
     * @param ev the ev
     * @return the boolean
     */
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {
        mScaleDetector.onTouchEvent(ev);
        return true;
    }

    /**
     * Gets scale factor.
     *
     * @return the scale factor
     */
    public float getScaleFactor() {
        return mScaleFactor;
    }

    /**
     * The type Scale listener.
     */
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        /**
         * On scale.
         *
         * @param detector the detector
         * @return the boolean
         */
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
            invalidate();
            return true;
        }
    }

}
