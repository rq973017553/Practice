package com.rq.practice.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public abstract class HandlerUtils<T extends Activity> extends Handler {

    // WeakReference持有Activity
    private WeakReference<T> mWeakActivity;

    public HandlerUtils(T activity) {
        this.mWeakActivity = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        T activity = null;
        if (mWeakActivity != null) {
            activity = mWeakActivity.get();
        }
        handleMessage(activity, msg);
    }

    public abstract void handleMessage(T activity, Message message);
}