package com.rq.practice.threads;

import android.os.RemoteException;

import com.rq.practice.control.IReceiveListener;
import com.rq.practice.control.Receive;
import com.rq.practice.service.ZenusControl;

import java.util.UUID;

public class WatchThread extends Thread {

    private volatile boolean isRunning = false;

    private volatile boolean isPause = false;

    private ZenusControl mControl;

    private IReceiveListener mListener;

    public WatchThread(ZenusControl control) {
        this.mControl = control;
    }

    public void setReceiveListener(IReceiveListener listener) {
        this.mListener = listener;
    }

    public IReceiveListener getReceiveListener() {
        return mListener;
    }

    @Override
    public void run() {
        int index = 0;
        while (true) {

            if (isPause) {
                continue;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                if (!isRunning) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            if (mControl != null) {
                Receive receive = createReceive(++index);
                try {
                    if (mListener != null) {
                        mListener.onReceive(receive);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void startWatch() {
        if (isPause()) {
            isPause = false;
        } else if (!isRunning()) {
            start();
            isRunning = true;
        }
    }

    public synchronized void quit() {
        isRunning = false;
        interrupt();
        if (mListener != null) {
            Receive receive = createReceive(0);
            try {
                mListener.onReceive(receive);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void pause() {
        isPause = true;
    }

    public boolean isPause() {
        return isPause;
    }

    public boolean isRunning() {
        return isRunning;
    }

    private Receive createReceive(int data) {
        Receive receive = new Receive();
        receive.setID(UUID.randomUUID().toString());
        receive.setReceive(data);
        return receive;
    }
}
