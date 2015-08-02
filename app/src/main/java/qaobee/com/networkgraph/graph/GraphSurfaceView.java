package qaobee.com.networkgraph.graph;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceView;

/**
 * The type Graph surface view.
 */
public class GraphSurfaceView extends SurfaceView {

    /**
     * The M scale detector.
     */
    private ScaleGestureDetector mScaleDetector;
    /**
     * The M scale factor.
     */
    private float mScaleFactor = 1.f;

    /**
     * Instantiates a new Graph surface view.
     *
     * @param context the context
     */
    public GraphSurfaceView(Context context) {
        super(context);
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }


    /**
     * On touch event.
     *
     * @param ev the ev
     * @return the boolean
     */
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent ev) {
        // Let the ScaleGestureDetector inspect all events.
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
    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        /**
         * On scale.
         *
         * @param detector the detector
         * @return the boolean
         */
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }

}
