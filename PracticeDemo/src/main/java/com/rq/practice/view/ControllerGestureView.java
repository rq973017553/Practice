package com.rq.practice.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.rq.practice.utils.VibratorUtils;

/**
 * 播放器手势控制
 */
public class ControllerGestureView extends View
{
    private static final int DEFAULT_SEEK_TIME_UNIT = 1000 /* milliseconds */;

    private boolean mPositionChange;

    private float mCurrentVolumePercent;

    private float mCurrentBrightnessPercent;

    private boolean mVolumeDragging;

    private ControllerGestureCall call;

    private int mSeekTo;

    private boolean hasState;

    private boolean hasFastSeek;

    private boolean isInterceptView = false;

    private float downX = 0;

    private float downY = 0;

    public ControllerGestureView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setClickable(true);
    }

    public void setGestureCallback(ControllerGestureCall call)
    {
        this.call = call;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if(isInterceptView){
                    break;
                }
                float moveX = event.getX();
                float moveY = event.getY();
                float x = Math.abs(moveX - downX);
                float y = Math.abs(moveY - downY);
                if (x > 10 || y > 10 ) {
                    isInterceptView = x > y;
                }
                getParent().requestDisallowInterceptTouchEvent(isInterceptView);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isInterceptView = false;
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (call == null)
        {
            return false;
        }

        //H5广告点击范围，先响应广告点击
        if(call.inH5Area((int)event.getRawX(), (int)event.getRawY()))
        {
            return false;
        }

        call.onTouched();
        hasState = true;
        if(event.getPointerCount() == 2){
            try {
                scaleGestureDetector.onTouchEvent(event);
            }catch (IllegalArgumentException e){
                //@NULL
            }
            return true;
        }

        if (mGestureDetector.onTouchEvent(event))
        {
            return true;
        }

        int action = event.getAction();
        if (!call.isTouchEnable() || !call.gestureAvailable())
        {
            if (hasState)
            {
                resetStatus();
            }
            if (MotionEvent.ACTION_UP == action)
            {
                resetViews();
            }
            return false;
        }

        if (MotionEvent.ACTION_UP == action)
        {
            if (mPositionChange)
            {
                call.seekTo(mSeekTo, true);
            }
            resetStatus();
            resetViews();

            return true;
        }

        return false;
    }

    void resetViews()
    {
        if (hasFastSeek)
        {
            postDelayed(new Runnable()
            {

                @Override
                public void run()
                {
                    if (hasFastSeek)
                    {
                        call.resetViews();
                    }
                }
            }, 500);
        }
        else
        {
            call.resetViews();
        }
    }

    //两个手指的运动
    private ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(getContext(),
            new ScaleGestureDetector.OnScaleGestureListener() {

                private float beginSpan = 0;


                @Override
                public boolean onScale(ScaleGestureDetector detector) {

                    return false;
                }

                @Override
                public boolean onScaleBegin(ScaleGestureDetector detector) {
                    beginSpan = detector.getCurrentSpan();
                    return true;
                }

                @Override
                public void onScaleEnd(ScaleGestureDetector detector) {
                    float changeSpan = detector.getCurrentSpan() - beginSpan;
                    if(changeSpan > 50){
                        //放大
                        call.switchScreen(1);
                    }else if(changeSpan < -50){
                        //缩小
                        call.switchScreen(-1);
                    }
                }
            });

    private GestureDetector mGestureDetector = new GestureDetector(getContext(),
            new GestureDetector.SimpleOnGestureListener()
            {

                private static final int THRESHOLD_AXIS_X = 4;

                private static final int THRESHOLD_AXIS_Y = 2;

                private static final int MAX_SEEK_ABS_PERCENT = 5;

                private static final int MAX_VOLUME_CHANGE_ABS_PERCENT = 5;

                @Override
                public boolean onDown(MotionEvent e)
                {
                    if(!call.gestureAvailable())
                    {
                        return true;
                    }
                    mSeekTo = call.getCurrentPosition();
                    hasFastSeek = false;
                    return true;
                }

                @Override
                public boolean onSingleTapConfirmed(MotionEvent e)
                {
                    call.onSingleTap();
                    return true;
                }

                public boolean onDoubleTap(MotionEvent e)
                {
                    if(!call.gestureAvailable())
                    {
                        return true;
                    }

                    // 双击区域
//                    float maxWidth = getWidth();
//                    float nowPoint = e.getX();
//                    if (0 < nowPoint && nowPoint <= maxWidth * 0.3)
//                    {
//                        call.onDoubleTapLeft();
//                    }
//                    else if (maxWidth * 0.7 <= nowPoint && nowPoint <= maxWidth)
//                    {
//                        call.onDoubleTapRight();
//                    }
//                    else
//                    {
//                        call.onDoubleTap();
//                    }
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e)
                {
                    if(!call.gestureAvailable())
                    {
                        return;
                    }

                    // 长按区域
                    float maxWidth = getWidth();
                    float nowPoint = e.getX();
                    if (0 < nowPoint && nowPoint <= maxWidth * 0.3)
                    {
                        call.onDoubleTapLeft();
                    }
                    else if (maxWidth * 0.7 <= nowPoint && nowPoint <= maxWidth)
                    {
                        call.onDoubleTapRight();
                    }
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
                {
                    if(!call.gestureAvailable())
                    {
                        return true;
                    }

                    if (!call.isTouchEnable())
                    {
                        return false;
                    }
                    if (e2.getEventTime() - e1.getEventTime() < 150)
                    {
                        // 快进or快退15毫秒
                        float dis = e2.getX() - e1.getX();
                        int delta = 0;
                        int size = Math.min(getWidth(), getHeight());
                        if (dis < 0 && Math.abs(dis) > size / 8)
                        {
                            // 快退15
                            delta = -15;
                        }
                        else if (dis > size / 8)
                        {
                            // 快进15
                            delta = 15;
                        }

                        if (delta != 0)
                        {
                            hasFastSeek = true;
                            call.showQuickSeek(delta);
                        }
                    }
                    return super.onFling(e1, e2, velocityX, velocityY);
                }

                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
                {
                    if(!call.gestureAvailable())
                    {
                        return true;
                    }

                    if (!call.isTouchEnable())
                    {
                        return false;
                    }

                    if (e2.getEventTime() - e1.getDownTime() < 150)
                    {
                        return false;
                    }

                    float absDistanceX = Math.abs(distanceX);
                    float absDistanceY = Math.abs(distanceY);

                    float maxWidth = getWidth();

                    float rateX1 = e1.getX() / maxWidth;
                    float rateX2 = e2.getX() / maxWidth;

                    if (absDistanceX > absDistanceY && !mVolumeDragging)
                    {
                        double percent = -distanceX / THRESHOLD_AXIS_X;

                        if (rateX1 > 0.3 && rateX1 < 0.7 && rateX2 > 0.2 && rateX2 < 0.8)
                        {
                            double absPercent = Math.abs(percent);
                            percent = absPercent > MAX_SEEK_ABS_PERCENT ? absPercent / percent * MAX_SEEK_ABS_PERCENT
                                    : percent;
                        }

                        onPositionChange(percent);
                    }
                    else if (absDistanceY > absDistanceX && !mPositionChange)
                    {
                        float percent = distanceY / THRESHOLD_AXIS_Y;
                        float absPercent = Math.abs(percent);

                        if (absPercent > MAX_VOLUME_CHANGE_ABS_PERCENT)
                        {
                            percent = percent > 0 ? MAX_VOLUME_CHANGE_ABS_PERCENT : -MAX_VOLUME_CHANGE_ABS_PERCENT;
                        }

                        if (rateX1 > 0 && rateX1 < 0.3 && rateX2 > 0 && rateX2 < 0.3)
                        {
                            onBrightnessChange(percent);
                        }
                        else if (rateX1 > 0.7 && rateX1 < 1 && rateX2 > 0.7 && rateX2 < 1)
                        {
                            onVolumeChange(percent);
                        }
                    }

                    return true;
                }
            });

    private void onVolumeChange(float percent)
    {
        mVolumeDragging = true;
        call.setVolumeDragging(mVolumeDragging);

        mCurrentVolumePercent = call.getCurrentVolumePercent();

        float newPercent = mCurrentVolumePercent + percent;

        if (newPercent > 100f)
        {
            newPercent = 100f;
        }
        else if (newPercent < 0.0f)
        {
            newPercent = 0.0f;
        }
        call.updateVolumeBar((int) (newPercent));
    }

    private void onBrightnessChange(float percent)
    {

        mVolumeDragging = true;

        if (call.isViewLocked() || !call.canChangeBrightness())
        {
            return;
        }
        Activity act = (Activity) getContext();
        if(act.isFinishing()){
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && act.isDestroyed()) {
            return;
        }
        Window window = ((Activity) getContext()).getWindow();
        WindowManager.LayoutParams lps = window.getAttributes();

        if (mCurrentBrightnessPercent == 0)
        {
            if (lps.screenBrightness > 0)
            {
                mCurrentBrightnessPercent = lps.screenBrightness;
            }
            else
            {
                try
                {
                    // 0~255
                    mCurrentBrightnessPercent = Settings.System.getInt(
                            getContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS)
                            / 255f;
                }
                catch (Exception e)
                {
                    mCurrentBrightnessPercent = 0.5f;
                }
            }
        }

        float newBrightness = mCurrentBrightnessPercent + percent / 100;


        if (newBrightness > 1.0f)
        {
            newBrightness = 1.0f;
        }
        else if (newBrightness < 0.01f)
        {
            newBrightness = 0.01f;
        }

        mCurrentBrightnessPercent = newBrightness;
        lps.screenBrightness = newBrightness;
        if(window.isActive()){
            window.setAttributes(lps);
        }

        call.updateBrightnessBar((int) (newBrightness * 100));
    }

    private void onPositionChange(double percent)
    {
        if(!mPositionChange){
            //震动
            VibratorUtils.setSettingVibrator(getContext());
        }
        mPositionChange = true;
        call.setPositionDragging(mPositionChange);
        int seekTo = mSeekTo;
        int duration = call.getDuration();
        int delta = (int) (DEFAULT_SEEK_TIME_UNIT * percent);
        seekTo += delta;
        if (seekTo > duration)
        {
            seekTo = duration /* milliseconds */;
        }
        else if (seekTo < 0)
        {
            seekTo = 0;
        }
        mSeekTo = seekTo;
        call.showPosition(seekTo, delta);
    }

    private void resetStatus()
    {
        hasState = false;
        mCurrentBrightnessPercent = 0;
        mCurrentVolumePercent = 0;
        mSeekTo = 0;
        mPositionChange = false;
        mVolumeDragging = false;
        call.setPositionDragging(false);
        call.setVolumeDragging(false);
    }

    public interface ControllerGestureCall
    {
        void updateBrightnessBar(int i);

        boolean canChangeBrightness();

        int getCurrentVolumePercent();

        void seekTo(int mSeekTo, boolean b);

        void resetViews();

        boolean isTouchEnable();

        void updateVolumeBar(int i);

        //双击全部区域
        void onDoubleTap();

        //双击左边区域  30%
        void onDoubleTapLeft();

        //双击右边区域  30%
        void onDoubleTapRight();

        void onSingleTap();

        int getDuration();

        int getCurrentPosition();

        void showPosition(int seekTo, int i);

        /**
         * 快进快退秒数
         */
        void showQuickSeek(int delta);

        void setVolumeDragging(boolean drag);

        void setPositionDragging(boolean drag);

        void onTouched();

        boolean isViewLocked();

        boolean inH5Area(int left, int top);

        boolean gestureAvailable();

        //int 0缩小；1放大
        void switchScreen(int mode);
    }

}
