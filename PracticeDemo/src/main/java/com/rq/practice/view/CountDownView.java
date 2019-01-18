package com.rq.practice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.rq.practice.utils.EasyLog;

public class CountDownView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private Context mContext;

    private OnCountDownListener mListener;

    private SurfaceHolder mSurfaceHolder;

    private int mSurfaceWidth;

    private int mSurfaceHeight;

    private int mCurrentState;

    private Thread mMainThread;

    private int mText = 0;

    private Paint mPaint;

    public CountDownView(Context context) {
        super(context);
        initView(context);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CountDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        this.mCurrentState = STATE.STATE_IDLE;
        this.mSurfaceHolder = getHolder();
        this.mSurfaceHolder.addCallback(this);
        this.mPaint = new Paint();
        initPaint(mPaint);
    }

    private void initPaint(Paint paint) {
        if (paint != null) {
            paint.setColor(Color.RED);
            paint.setDither(true);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setTextSize(50);
        }
    }

    public void setCountDownListener(OnCountDownListener listener) {
        if (listener != null) {
            this.mListener = listener;
        }
    }

    public synchronized void start() {
        if (mMainThread == null && mCurrentState != STATE.STATE_RUNNING) {
            mCurrentState = STATE.STATE_RUNNING;
            mMainThread = new Thread(this);
            mMainThread.start();
        }
    }

    public synchronized void pause() {
        if (mCurrentState == STATE.STATE_RUNNING) {
            mCurrentState = STATE.STATE_PAUSED;
        }
    }

    public synchronized void stop() {
        if (mCurrentState == STATE.STATE_RUNNING) {
            mCurrentState = STATE.STATE_STOPED;
            mMainThread = null;
        }
    }

    public synchronized void resume() {
        if (mMainThread != null && mCurrentState == STATE.STATE_PAUSED) {
            mCurrentState = STATE.STATE_RUNNING;
        }
    }

    public synchronized void release() {
        if (mMainThread != null) {
            mCurrentState = STATE.STATE_IDLE;
            mMainThread = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        EasyLog.v("surfaceCreated!");
        if (mSurfaceHolder == null) {
            mSurfaceHolder = holder;
        }
        this.mCurrentState = STATE.STATE_INITIALIZE;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        EasyLog.v("surfaceChanged!");
        mSurfaceWidth = width;
        mSurfaceHeight = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        EasyLog.v("surfaceDestroyed!");
        release();
    }

    @Override
    public void run() {
        while (true) {
            judgeCurrentState(mCurrentState);
            if (mCurrentState != STATE.STATE_RUNNING) {
                if (mCurrentState == STATE.STATE_PAUSED) {
                    continue;
                }
                return;
            }
            canvas();
            sleep();
        }
    }

    private void sleep(){
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void judgeCurrentState(int currentState) {
        switch (currentState) {
            case STATE.STATE_IDLE:
                EasyLog.v("当前状态::IDLE!");
                break;
            case STATE.STATE_INITIALIZE:
                EasyLog.v("当前状态::初始化!");
                break;
            case STATE.STATE_PAUSED:
                EasyLog.v("当前状态::暂停!");
                break;
            case STATE.STATE_RUNNING:
                EasyLog.v("当前状态::运行中!");
                break;
            case STATE.STATE_STOPED:
                EasyLog.v("当前状态::停止!");
                break;
        }
    }

    private void canvas() {
        Canvas canvas = null;
        try {
            if (mSurfaceHolder == null) {
                return;
            }
            canvas = mSurfaceHolder.lockCanvas();
            drawCanvas(canvas);
        } finally {
            if (canvas != null && mSurfaceHolder != null) {
                mSurfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void drawCanvas(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        float textX = mSurfaceWidth / 2.0f;
        float textY = mSurfaceHeight / 2.0f;
        if (mListener != null) {
            mText = mListener.drawText();
            String text = String.valueOf(mText);
            canvas.drawText(text, textX, textY, mPaint);
        }
    }

    public int getCurrentState() {
        return mCurrentState;
    }

    public static class STATE {

        /**
         * IDLE
         */
        public static final int STATE_IDLE = 0;

        /**
         * 初始化
         */
        public static final int STATE_INITIALIZE = 1;

        /**
         * 播放中
         */
        public static final int STATE_RUNNING = 2;

        /**
         * 暂停
         */
        public static final int STATE_PAUSED = 3;

        /**
         * 停止
         */
        public static final int STATE_STOPED = 4;
    }

    public interface OnCountDownListener {
        void state(int currentState);

        int drawText();
    }
}
