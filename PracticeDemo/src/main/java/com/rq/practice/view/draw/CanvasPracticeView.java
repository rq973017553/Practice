package com.rq.practice.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

/**
 * 用来练习Canvas和相关的技术的View
 * @author rock you
 * @version [1.0.0, 2018.09.13]
 */
public class CanvasPracticeView extends BaseDrawView {

    public CanvasPracticeView(Context context) {
        this(context, null);
    }

    public CanvasPracticeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasPracticeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initDraw() {
    }

    @Override
    public void drawCanvas(Canvas canvas) {
    }
}
