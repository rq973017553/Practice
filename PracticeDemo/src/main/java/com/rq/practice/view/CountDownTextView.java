package com.rq.practice.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.rq.practice.R;
import com.rq.practice.utils.CountDownTimerUtil;
import com.rq.practice.utils.EasyLog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CountDownTextView extends TextView
{

    private CountDownTimerUtil mCountDownTimerUtil;

    private ICountDown mCountDownListener;

    private Date date = new Date();

    private SimpleDateFormat simpleDateFormat;

    private volatile boolean isRunning = false;

    private volatile boolean isAutoDetach = false;

    public CountDownTextView(Context context)
    {
        this(context, null);
    }

    public CountDownTextView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CountDownTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        if (attrs != null)
        {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CountDownTextView);
            String format = ta.getString(R.styleable.CountDownTextView_countdownFormat);
            if (format == null)
            {
                format = "mm:ss";
            }
            ta.recycle();
            simpleDateFormat = new SimpleDateFormat(format);
        }
    }

    public void setCountDownListener(ICountDown listener)
    {
        this.mCountDownListener = listener;
    }

    public synchronized void start(long countDown)
    {
        isRunning = true;

        if (mCountDownTimerUtil != null)
        {
            mCountDownTimerUtil.cancel();
            mCountDownTimerUtil = null;
        }
        mCountDownTimerUtil = new CountDownTimerUtil(countDown * 1000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                invalidate();
                date.setTime(millisUntilFinished);
                setText(simpleDateFormat.format(date));
                EasyLog.e("scboy::onTick!");
            }

            @Override
            public void onFinish()
            {
                isRunning = false;
                if (mCountDownListener != null)
                {
                    mCountDownListener.onFinish();
                }
            }
        };

        if (mCountDownListener != null){
            mCountDownListener.onStart(mCountDownTimerUtil);
        }

        mCountDownTimerUtil.start();
    }

    public synchronized void cancel()
    {
        if (mCountDownTimerUtil != null)
        {
            isRunning = false;
            mCountDownTimerUtil.cancel();
            mCountDownTimerUtil = null;
        }
    }

    public synchronized void setAutoDetach(boolean isAutoDetach) {
        this.isAutoDetach = isAutoDetach;
    }

    public synchronized boolean isRunning()
    {
        return isRunning;
    }

    public interface ICountDown
    {
        void onStart(CountDownTimerUtil countDownTimerUtil);

        void onFinish();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (isAutoDetach) {
            // View被移除的时候调用一次cancel
            cancel();
        }
    }
}
