package com.rq.practice.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public abstract class HandlerUtils<T> extends Handler {

    // WeakReference持有Activity
    private WeakReference<T> mReference;

    public HandlerUtils(T reference) {
        this.mReference = new WeakReference<>(reference);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        T reference = null;
        if (mReference != null) {
            reference = mReference.get();
        }
        handleMessage(reference, msg);
    }

    public abstract void handleMessage(T reference, Message message);
}