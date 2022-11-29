package com.rq.practice.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Paint练习
 * @author rock you
 * @version [1.0.0, 2018.09.28]
 */
public class PaintPracticeView extends BaseDrawView {

    public PaintPracticeView(Context context) {
        super(context);
    }

    public PaintPracticeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintPracticeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void initDraw() {
        //不要滥用硬件加速，可能会使得手机性能下降
        setLayerType(View.LAYER_TYPE_SOFTWARE, null); //取消硬件加速
//        setLayerType(View.LAYER_TYPE_HARDWARE, null); // 开启硬件加速
//        setLayerType(View.LAYER_TYPE_NONE, null); // 清除之前的设置

        /**
         * 设置画笔
         * Paint.Style.FILL_AND_STROKE 填充内部和描边
         * Paint.Style.FILL 填充内部
         * Paint.Style.STROKE 仅描边
         */
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(Color.BLUE);// 设置颜色
        mPaint.setAntiAlias(true);// 防止锯齿
        mPaint.setDither(true); // 防止抖动
        mPaint.setStrokeWidth(5);// 设置画笔宽度
        mPaint.setTextSize(16); // 设置文字大小

        /**
         * 设置线帽样式
         * 可以设置三种样式
         * 1.Paint.Cap.ROUND(圆形线冒)
         * 2.Paint.Cap.SQUARE(方形线冒)
         * 3.Paint.Cap.BUTT(无线冒)
         */
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        /**
         * 设置线段连接处样式
         * 1.Join.MITER(结合处为锐角)
         * 2.Join.ROUND(结合处为圆弧)
         * 3.Join.BEVEL(结合处为直线)
         */
        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    public void drawCanvas(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float cx = (measuredWidth)/2.0f;
        float cy = (measuredHeight)/2.0f;
        canvas.drawCircle(cx, cy, 200, mPaint);
    }
}
