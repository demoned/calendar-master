package com.demons.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

public class GestureView extends LinearLayout {
    public GestureView(Context context) {
        this(context, null);
    }

    public GestureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private static final int mWidth = 500;
    private float mDisplacementX;
    private float mDisplacementY;
    private float mInitialTx;
    private boolean mTracking;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mDisplacementX = event.getRawX();
                mDisplacementY = event.getRawY();
                mInitialTx = getTranslationX();
                break;
            case MotionEvent.ACTION_MOVE:
                // get the delta distance in X and Y direction
                float deltaX = event.getRawX() - mDisplacementX;
                float deltaY = event.getRawY() - mDisplacementY;
                // set the touch and cancel event
                if ((Math.abs(deltaX) > ViewConfiguration.get(getContext())
                        .getScaledTouchSlop() * 2 && Math.abs(deltaY) < Math
                        .abs(deltaX) / 2)
                        || mTracking) {

                    mTracking = true;

                    if (getTranslationX() <= mWidth / 2
                            && getTranslationX() >= -(mWidth / 2)) {

                        setTranslationX(mInitialTx + deltaX);
                        break;
                    }
                }

                break;

            case MotionEvent.ACTION_UP:

                if (mTracking) {
                    mTracking = false;
                    float currentTranslateX = getTranslationX();
                    if (currentTranslateX > mWidth / 4) {
                        onSwipeListener.rightSwipe();
                    } else if (currentTranslateX < -(mWidth / 4)) {
                        onSwipeListener.leftSwipe();
                    }
                    setTranslationX(0);
                    break;
                } else {
                    setTranslationX(0);
                }
                break;
            default:
                break;
        }
        return false;
    }


    public interface OnSwipeListener {
        void rightSwipe();

        void leftSwipe();
    }


    private OnSwipeListener onSwipeListener;

    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        this.onSwipeListener = onSwipeListener;
    }
}
