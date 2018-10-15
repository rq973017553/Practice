package com.rq.practice.view.draw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import com.rq.practice.R;
import com.rq.practice.utils.EasyLog;

/**
 * 练习drawXXX的View
 * @author rock you
 * @version [1.0.0, 2018.10.10]
 */
public class DrawPracticeView extends BaseDrawView {

    private int mCanvasClassify;

    private Bitmap mBitmap;

    private BitmapFactory.Options mOptions;

    public static class CanvasClassify{
        public static final int DRAW_CIRCLE = 0;

        public static final int DRAW_RECT = 1;

        public static final int DRAW_ROUND_POINT = 2;

        public static final int DRAW_SQUARE_POINT = 3;

        public static final int DRAW_BUTT_POINT = 4;

        public static final int DRAW_BITMAP = 5;

        public static final int DRAW_ZOOM_BITMAP = 6;
    }

    public DrawPracticeView(Context context) {
        super(context);
    }

    public DrawPracticeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawPracticeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void initDraw() {
        initTypedArray();
        mOptions = new BitmapFactory.Options();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(75);
    }

    private void initTypedArray(){
        TypedArray array = mContext.obtainStyledAttributes(mAttrs, R.styleable.DrawPracticeView);
        mCanvasClassify = array.getInt(R.styleable.DrawPracticeView_canvas_classify, -1);
        array.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public void drawCanvas(Canvas canvas){
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float cx = measuredWidth*0.5f;
        float cy = measuredHeight*0.5f;
        switch (mCanvasClassify){
            case CanvasClassify.DRAW_CIRCLE:
                drawCircle(cx, cy, canvas);
                break;
            case CanvasClassify.DRAW_RECT:
                drawRect(cx, cy, canvas);
                break;
            case CanvasClassify.DRAW_ROUND_POINT:
                drawRoundPoint(cx, cy, canvas);
                break;
            case CanvasClassify.DRAW_SQUARE_POINT:
                drawSquarePoint(cx, cy, canvas);
                break;
            case CanvasClassify.DRAW_BUTT_POINT:
                drawButtPoint(cx, cy, canvas);
                break;
            case CanvasClassify.DRAW_BITMAP:
                drawBitmap(cx, cy, canvas);
                break;
            case CanvasClassify.DRAW_ZOOM_BITMAP:
                drawZoomBitmap(cx, cy, canvas);
                break;
            default:
                drawError(cx, cy, canvas);
                break;
        }
    }

    private void drawZoomBitmap(float cx, float cy, Canvas canvas){
        // Empty Method
    }

    private void drawBitmap(float cx, float cy, Canvas canvas){
        mOptions.inJustDecodeBounds = true;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xiaomai, mOptions);
        int dwidth = mOptions.outWidth;
        int dheight = mOptions.outHeight;
        int vwidth = getMeasuredWidth();
        int vheight = getMeasuredHeight();
        float radioX = dwidth/(vwidth*1.0f);
        float radioY = dheight/(vheight*1.0f);
        if (radioX > 1 || radioY > 1){
            int scale = (int)(radioX > radioY? radioX:radioY);
            mOptions.inSampleSize = scale;
        }
        mOptions.inJustDecodeBounds = false;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xiaomai, mOptions);
        cx = cx - mBitmap.getWidth()*0.5f;
        cy = cy - mBitmap.getHeight()*0.5f;
        canvas.drawBitmap(mBitmap, cx, cy, null);
    }

    private void drawCircle(float cx, float cy, Canvas canvas){
        canvas.drawCircle(cx, cy, 150, mPaint);
    }

    private void drawRect(float cx, float cy, Canvas canvas){
        float left = cx - 150;
        float right = cx + 150;
        float top = cy - 50;
        float bottom = cy + 50;
        canvas.drawRect(left, top, right, bottom, mPaint);
    }

    /**
     * 圆形点
     * @param cx cx
     * @param cy cy
     * @param canvas canvas
     */
    private void drawRoundPoint(float cx, float cy, Canvas canvas){
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(cx, cy, mPaint);
    }

    /**
     * 方形点
     * @param cx cx
     * @param cy cy
     * @param canvas canvas
     */
    private void drawSquarePoint(float cx, float cy, Canvas canvas){
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(cx, cy, mPaint);
    }

    /**
     * 无样式的点
     * @param cx cx
     * @param cy cy
     * @param canvas canvas
     */
    private void drawButtPoint(float cx, float cy, Canvas canvas){
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(cx, cy, mPaint);
    }

    /**
     * 显示文字"错误"
     * @param cx cx
     * @param cy cy
     * @param canvas canvas
     */
    private void drawError(float cx, float cy, Canvas canvas){
        String text = "错误";
        float textWidth = mPaint.measureText(text);
        cx = cx - textWidth/2.0f;
        canvas.drawText(text, cx, cy, mPaint);
    }
}
