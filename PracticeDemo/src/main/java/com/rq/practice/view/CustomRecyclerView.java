package com.rq.practice.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.util.AttributeSet;
import android.view.ViewGroup;

import java.io.IOException;

public class CustomRecyclerView extends ViewGroup {

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
