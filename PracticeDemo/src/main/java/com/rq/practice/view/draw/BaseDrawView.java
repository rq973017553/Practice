package com.rq.practice.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * DrawView的基类
 * @author rock you
 * @version [1.0.0, 2018.09.13]
 */
public abstract class BaseDrawView extends View {

    protected Paint mPaint;

    protected Context mContext;

    protected AttributeSet mAttrs;

    protected int mDefStyleAttr;

    public BaseDrawView(Context context) {
        this(context, null);
    }

    public BaseDrawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.mAttrs = attrs;
        this.mDefStyleAttr = defStyleAttr;
        initViews();
    }

    private void initViews(){
        mPaint = new Paint();
        initDraw();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCanvas(canvas);
    }

    /**
     * draw初始化
     */
    public abstract void initDraw();

    /**
     * draw
     * @param canvas canvas
     */
    public abstract void drawCanvas(Canvas canvas);

}
