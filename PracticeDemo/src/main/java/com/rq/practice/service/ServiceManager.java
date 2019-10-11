package com.rq.practice.service;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

public class ServiceManager {

    private Context mContext;

    private volatile boolean isBind = false;

    public ServiceManager(Context context) {
        this.mContext = context;
    }

    public void startService(Intent intent) {
        mContext.startService(intent);
    }

    public void stopService(Intent intent) {
        mContext.stopService(intent);
    }

    public void bindService(Intent intent, ServiceConnection conn, int flag) {
        if (!isBind) {
            isBind = mContext.bindService(intent, conn, flag);
        }
    }

    public void unBindService(ServiceConnection conn) {
        if (isBind) {
            mContext.unbindService(conn);
            isBind = false;
        }
    }
}
