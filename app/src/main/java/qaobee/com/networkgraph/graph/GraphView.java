package qaobee.com.networkgraph.graph;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
import qaobee.com.networkgraph.graph.beans.Dimension;
import qaobee.com.networkgraph.graph.beans.Point2D;
import qaobee.com.networkgraph.graph.layout.FRLayout;

public class GraphView extends View {
    private Graph graph;
    boolean drawn = false;
    private FRLayout layout;

    public GraphView(Context context) {
        super(context);
    }

    public GraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init(Graph graph, Dimension size) {
        this.graph = graph;
        layout = new FRLayout(graph, size);

    }

    public void doLayout() {
        while (!layout.done())
            layout.step();
    }

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
            float midX = getMiddle((float) p1.getX(), (float) p2.getX());
            float midY = getMiddle((float) p1.getY(), (float) p2.getY());
            paint.setStrokeWidth(Float.valueOf(edge.getLabel()) + 1f);
            paint.setColor(resources.getColor(android.R.color.holo_blue_light));
            canvas.drawLine((float) p1.getX(), (float) p1.getY(), (float) p2.getX(), (float) p2.getY(), paint);
            canvas.drawRect(midX - 20, midY + 20, midX + 20, midY - 20, whitePaint);
            paint.setColor(resources.getColor(android.R.color.black));
            paint.setStrokeWidth(0f);
            canvas.drawText(edge.getLabel(), midX, midY + 10, paint);
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
            Bitmap b = ((BitmapDrawable) drawable).getBitmap();
            Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);
            Bitmap roundBitmap = getCroppedBitmap(bitmap, 75);
            canvas.drawBitmap(roundBitmap, (float) position.getX() - 38f, (float) position.getY() - 38f, null);
            canvas.drawRect((float) position.getX() - 20, (float) position.getY() + 50, (float) position.getX() + 20, (float) position.getY() + 10, whitePaint);
            canvas.drawText(node.getLabel(), (float) position.getX(), (float) position.getY() + 40, paint);
        }
    }

    private float getMiddle(float x1, float x2) {
        if (x1 > x2) {
            return x2 + ((x1 - x2) / 2f);
        } else {
            return x1 + ((x2 - x1) / 2f);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: consider storing these as member variables to reduce
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;

    }

    public void resize(Dimension newSize) {
        if (!newSize.equals(layout.getSize())) {
            layout.setSize(newSize);
            layout.reset();
        }
    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
                sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f,
                sbmp.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);


        return output;
    }
}
