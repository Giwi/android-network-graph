package giwi.org.networkgraph.beans;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;


/**
 * The type Arc utils.
 */
public class ArcUtils {

    /**
     * Instantiates a new Arc utils.
     */
    private ArcUtils() {
    }

    /**
     * https://www.tbray.org/ongoing/When/200x/2009/01/02/Android-Draw-a-Curved-Line
     * <p>
     * Draw arc.
     *
     * @param e1        the e 1
     * @param e2        the e 2
     * @param radius    the radius
     * @param canvas    the canvas
     * @param paint     the paint
     * @param textPaint the text paint
     * @param recPaint  the rec paint
     * @param value     the value
     */
    public static void drawArc(PointF e1, PointF e2, float radius, Canvas canvas, Paint paint, Paint textPaint, Paint recPaint, int value) {
        double a1 = Math.toRadians(radius + 5);
        // l1 is half the length of the line from e1 to e2
        double dx = e2.x - e1.x, dy = e2.y - e1.y;
        double l = Math.sqrt((dx * dx) + (dy * dy));
        double l1 = l / 2.0;
        // h is length of the line from the middle of the connecting line to the center of the circle.
        double h = l1 / (Math.tan(a1 / 2.0));
        // r is the radius of the circle
        double r = l1 / (Math.sin(a1 / 2.0));
        // a2 is the angle at which L intersects the x axis
        double a2 = Math.atan2(dy, dx);
        // a3 is the angle at which H intersects the x axis
        double a3 = (Math.PI / 2.0) - a2;
        // m is the midpoint of the line from e1 to e2
        double mX = (e1.x + e2.x) / 2.0;
        double mY = (e1.y + e2.y) / 2.0;

        // c is the the center of the circle
        double cY = mY + (h * Math.sin(a3));
        double cX = mX - (h * Math.cos(a3));
        // rect is the square RectF that bounds the "oval"
        RectF oval = new RectF((float) (cX - r), (float) (cY - r), (float) (cX + r), (float) (cY + r));

        // a4 is the starting sweep angle
        double rawA4 = Math.atan2(e1.y - cY, e1.x - cX);
        float a4 = (float) Math.toDegrees(rawA4);
        paint.setStrokeWidth(value + 1);
        drawArrow(e2.x, e2.y, a4 + radius + 45f, paint, canvas);
        canvas.drawArc(oval, a4, radius, false, paint);
        double deltay = -Math.sin(a3) * (r - h);
        double deltax = Math.cos(a3) * (r - h);
        canvas.drawRect(
                (float) (mX + deltax) - 20f,
                (float) (mY + deltay) + 20f,
                (float) (mX + deltax) + 20f, (float) (mY + deltay) - 20f, recPaint);

        canvas.drawText(String.valueOf(value), (float) (mX + deltax),
                (float) (mY + deltay) + 10, textPaint);
    }

    /**
     * Draw arrow.
     *
     * @param x       the x
     * @param y       the y
     * @param degrees the degrees
     * @param paint   the paint
     * @param canvas  the canvas
     */
    private static void drawArrow(float x, float y, float degrees, Paint paint, Canvas canvas) {
        canvas.save();
        canvas.rotate(degrees, x, y);
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(x - 40f, y - 40f);
        path.lineTo(x - 60f, y - 40f);
        path.lineTo(x - 40f, y - 60f);
        path.lineTo(x - 40f, y - 40f);
        path.close();
        canvas.drawPath(path, paint);
        canvas.restore();
        paint.setStyle(Paint.Style.STROKE);
    }
}
