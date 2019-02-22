package com.stayli.app.ui.fragment.visibility;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public class CButton extends Button {

    public CButton(Context context) {
        super(context);
    }

    public CButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private static final String TAG = "CButton";


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG, "dispatchTouchEvent: " + event.getAction());
        setText("dis " + MotionEvent.actionToString(event.getAction()));
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        String s = MotionEvent.actionToString(action);
        Log.e(TAG, "onTouchEvent: " + s);
        setText("touch " + s);
        return super.onTouchEvent(event);
    }

    @Override
    protected boolean dispatchGenericPointerEvent(MotionEvent event) {
        Log.e(TAG, "dispatchGenericPointerEvent: " + MotionEvent.actionToString(event.getAction()));
        return super.dispatchGenericPointerEvent(event);
    }

    @Override
    protected boolean dispatchHoverEvent(MotionEvent event) {
        Log.e(TAG, "dispatchHoverEvent: " + MotionEvent.actionToString(event.getAction()));
        return super.dispatchHoverEvent(event);
    }
}
