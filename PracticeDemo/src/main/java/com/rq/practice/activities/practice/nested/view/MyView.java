package com.rq.practice.activities.practice.nested.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class MyView extends AppCompatTextView {

    private final String className = getClass().getSimpleName();

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
