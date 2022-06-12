package com.rq.practice.activities.practice.nested.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyViewGroup0 extends LinearLayout {

    private final String className = getClass().getSimpleName();

    public MyViewGroup0(Context context) {
        this(context, null);
    }

    public MyViewGroup0(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup0(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("scboy", className+" onInterceptTouchEvent::ACTION_DOWN !!!");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("scboy", className+" onInterceptTouchEvent::ACTION_MOVE !!!");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("scboy", className+" onInterceptTouchEvent::ACTION_UP !!!");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e("scboy", className+" onTouchEvent::ACTION_DOWN !!!");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("scboy", className+" onTouchEvent::ACTION_MOVE !!!");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("scboy", className+" onTouchEvent::ACTION_UP !!!");
                break;
        }
        return true;
    }
}
