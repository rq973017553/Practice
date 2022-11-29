package com.rq.practice.view.draw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

import com.rq.practice.R;

/**
 * 练习drawXXX的View
 *
 * @author rock you
 * @version [1.0.0, 2018.10.10]
 */
public class DrawPracticeView extends BaseDrawView {

    private int mCanvasClassify;

    private Bitmap mBitmap;

    private BitmapFactory.Options mOptions;

    public static class CanvasClassify {
        public static final int DRAW_LINE = 0;

        public static final int DRAW_LINES = 1;

        public static final int DRAW_CIRCLE = 2;

        public static final int DRAW_RECT = 3;

        public static final int DRAW_ROUND_POINT = 4;

        public static final int DRAW_SQUARE_POINT = 5;

        public static final int DRAW_BUTT_POINT = 6;

        public static final int DRAW_BITMAP = 7;

        public static final int DRAW_ZOOM_BITMAP = 8;

        public static final int DRAW_SAVE_RESTORE = 9;

        public static final int DRAW_SCALE = 10;
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

    private void initTypedArray() {
        TypedArray array = mContext.obtainStyledAttributes(mAttrs, R.styleable.DrawPracticeView);
        mCanvasClassify = array.getInt(R.styleable.DrawPracticeView_canvas_classify, -1);
        array.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public void drawCanvas(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float cx = measuredWidth * 0.5f;
        float cy = measuredHeight * 0.5f;
        switch (mCanvasClassify) {
            case CanvasClassify.DRAW_LINE:
                drawLine(canvas);
                break;
            case CanvasClassify.DRAW_LINES:
                drawLines(canvas);
                break;
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
            case CanvasClassify.DRAW_SAVE_RESTORE:
                drawCanvasSaveRestore(canvas);
                break;
            case CanvasClassify.DRAW_SCALE:
                drawScale(canvas);
                break;
            default:
                drawError(cx, cy, canvas);
                break;
        }
    }

    private void drawScale(Canvas canvas) {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, mOptions);
        float centerX = (getWidth() - mBitmap.getWidth()) / 2.0f;
        float centerY = (getHeight() - mBitmap.getHeight()) / 2.0f;
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.save();
        canvas.translate(centerX, centerY);
        canvas.scale(2.0f, 2.0f);
//        canvas.scale(2.0f, 2.0f, getWidth() / 2.0f, getHeight() / 2.0f);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.restore();
    }

    private void drawCanvasSaveRestore(Canvas canvas) {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, mOptions);
        //第一张
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.save();
        //旋转45
        canvas.rotate(45, getWidth() / 2.0f, getHeight() / 2.0f);
        //第二张
        canvas.drawBitmap(mBitmap,
                getWidth() / 2.0f - mBitmap.getWidth() / 2.0f,
                getHeight() / 2.0f - mBitmap.getHeight() / 2.0f,
                mPaint
        );
        canvas.restore();
        //第三张
        Log.e("scboy", "height " + mBitmap.getHeight());
        canvas.drawBitmap(mBitmap, 0, getHeight() - 300, mPaint);
    }

    private void drawZoomBitmap(float cx, float cy, Canvas canvas) {
        // Empty Method
    }

    private void drawBitmap(float cx, float cy, Canvas canvas) {
        mOptions.inJustDecodeBounds = true;
        int dWidth = mOptions.outWidth;
        int dHeight = mOptions.outHeight;
        int vWidth = getMeasuredWidth();
        int vHeight = getMeasuredHeight();
        float radioX = dWidth / (vWidth * 1.0f);
        float radioY = dHeight / (vHeight * 1.0f);
        if (radioX > 1 || radioY > 1) {
            mOptions.inSampleSize = (int) (Math.max(radioX, radioY));
        }
        mOptions.inJustDecodeBounds = false;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xiaomai, mOptions);
        cx = cx - mBitmap.getWidth() * 0.5f;
        cy = cy - mBitmap.getHeight() * 0.5f;
        canvas.drawBitmap(mBitmap, cx, cy, null);
    }

    private void drawLine(Canvas canvas) {
        float startX = 0;
        float startY = getHeight() / 2.0f;
        float endX = startX + getWidth() / 2.0f;
        float endY = getHeight() / 2.0f;
        canvas.drawLine(startX, startY, endX, endY, mPaint);
    }

    private void drawLines(Canvas canvas) {
        float[] pts = {
                100, 50, 500, 50,
                500, 50, 500, 200,
                500, 200, 100, 200,
                100, 200, 100, 50
        };
        mPaint.setStrokeWidth(35);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawLines(pts, mPaint);
    }

    private void drawCircle(float cx, float cy, Canvas canvas) {
        canvas.drawCircle(cx, cy, 150, mPaint);
    }

    private void drawRect(float cx, float cy, Canvas canvas) {
        float left = cx - 150;
        float right = cx + 150;
        float top = cy - 50;
        float bottom = cy + 50;
        canvas.drawRect(left, top, right, bottom, mPaint);
    }

    /**
     * 圆形点
     *
     * @param cx     cx
     * @param cy     cy
     * @param canvas canvas
     */
    private void drawRoundPoint(float cx, float cy, Canvas canvas) {
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(cx, cy, mPaint);
    }

    /**
     * 方形点
     *
     * @param cx     cx
     * @param cy     cy
     * @param canvas canvas
     */
    private void drawSquarePoint(float cx, float cy, Canvas canvas) {
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(cx, cy, mPaint);
    }

    /**
     * 无样式的点
     *
     * @param cx     cx
     * @param cy     cy
     * @param canvas canvas
     */
    private void drawButtPoint(float cx, float cy, Canvas canvas) {
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(cx, cy, mPaint);
    }

    /**
     * 显示文字"错误"
     *
     * @param cx     cx
     * @param cy     cy
     * @param canvas canvas
     */
    private void drawError(float cx, float cy, Canvas canvas) {
        String text = "错误";
        float textWidth = mPaint.measureText(text);
        cx = cx - textWidth / 2.0f;
        canvas.drawText(text, cx, cy, mPaint);
    }
}
