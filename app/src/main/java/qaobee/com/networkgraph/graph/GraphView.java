package qaobee.com.networkgraph.graph;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import net.xqhs.graphs.graph.Edge;
import net.xqhs.graphs.graph.Graph;
import net.xqhs.graphs.graph.Node;

import qaobee.com.networkgraph.R;
import qaobee.com.networkgraph.graph.beans.ArcUtils;
import qaobee.com.networkgraph.graph.beans.Dimension;
import qaobee.com.networkgraph.graph.beans.Point2D;
import qaobee.com.networkgraph.graph.layout.FRLayout;

/**
 * The type Graph view.
 */
public class GraphView extends View {
    /**
     * The Graph.
     */
    private Graph graph;
    /**
     * The Layout.
     */
    private FRLayout layout;

    /**
     * Instantiates a new Graph view.
     *
     * @param context the context
     */
    public GraphView(Context context) {
        super(context);
    }

    /**
     * Instantiates a new Graph view.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Instantiates a new Graph view.
     *
     * @param context  the context
     * @param attrs    the attrs
     * @param defStyle the def style
     */
    public GraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Init void.
     *
     * @param graph the graph
     * @param size  the size
     */
    public void init(Graph graph, Dimension size) {
        this.graph = graph;
        layout = new FRLayout(graph, size);

    }

    /**
     * Do layout.
     */
    public void doLayout() {
        while (!layout.done()) {
            layout.step();
        }
    }

    /**
     * Draw void.
     *
     * @param canvas    the canvas
     * @param resources the resources
     */
    public void draw(Canvas canvas, Resources resources) {
        Paint paint = new Paint();
        Paint whitePaint = new Paint();
        paint.setAntiAlias(true);

        whitePaint.setColor(resources.getColor(android.R.color.white));
        whitePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        whitePaint.setStrokeWidth(2f);
        whitePaint.setShadowLayer(5, 0, 0, resources.getColor(android.R.color.black));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(20f);
        paint.setColor(resources.getColor(android.R.color.black));
        for (Edge edge : graph.getEdges()) {
            Point2D p1 = layout.transform(edge.getFrom());
            Point2D p2 = layout.transform(edge.getTo());

            paint.setStrokeWidth(Float.valueOf(edge.getLabel()) + 1f);
            paint.setColor(resources.getColor(android.R.color.holo_blue_light));
            Paint curve = new Paint();
            curve.setAntiAlias(true);
            curve.setStyle(Paint.Style.STROKE);
            curve.setStrokeWidth(2);
            curve.setColor(resources.getColor(android.R.color.holo_blue_light));
            PointF e1 = new PointF((float) p1.getX(), (float) p1.getY());
            PointF e2 = new PointF((float) p2.getX(), (float) p2.getY());
            ArcUtils.drawArc(e1, e2, 36.0f, canvas, curve, paint, whitePaint, Integer.parseInt(edge.getLabel()));
            //    canvas.drawLine((float) p1.getX(), (float) p1.getY(), (float) p2.getX(), (float) p2.getY(), paint);


        }
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(30f);
        paint.setStrokeWidth(0f);
        paint.setColor(resources.getColor(android.R.color.holo_blue_light));
        for (Node node : graph.getNodes()) {
            Point2D position = layout.transform(node);
            canvas.drawCircle((float) position.getX(), (float) position.getY(), 40, whitePaint);
            Drawable drawable = resources.getDrawable(R.drawable.avatar);
            if(drawable != null) {
                Bitmap b = ((BitmapDrawable) drawable).getBitmap();
                Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
                Bitmap roundBitmap = getCroppedBitmap(bitmap, 75);
                canvas.drawBitmap(roundBitmap, (float) position.getX() - 38f, (float) position.getY() - 38f, null);
            }
            canvas.drawRect((float) position.getX() - 20, (float) position.getY() + 50, (float) position.getX() + 20, (float) position.getY() + 10, whitePaint);
            canvas.drawText(node.getLabel(), (float) position.getX(), (float) position.getY() + 40, paint);
        }
    }


    /**
     * On draw.
     *
     * @param canvas the canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * Resize void.
     *
     * @param newSize the new size
     */
    public void resize(Dimension newSize) {
        if (!newSize.equals(layout.getSize())) {
            layout.setSize(newSize);
            layout.reset();
        }
    }

    /**
     * Gets cropped bitmap.
     *
     * @param bmp    the bmp
     * @param radius the radius
     * @return the cropped bitmap
     */
    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
                sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = Color.BLACK;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f,
                sbmp.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);


        return output;
    }
}