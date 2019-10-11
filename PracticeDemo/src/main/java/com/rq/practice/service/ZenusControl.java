package com.rq.practice.service;

import android.os.Parcel;
import android.os.RemoteException;

import com.rq.practice.control.IControl;
import com.rq.practice.control.IReceiveListener;
import com.rq.practice.control.Message;
import com.rq.practice.threads.WatchThread;
import com.rq.practice.utils.EasyLog;

public class ZenusControl extends IControl.Stub {

    private WatchThread mWatchThread;

    private IReceiveListener mIReceiveListener;

    public static class COMMAND{
        public static final int START_COMMAND = 0;

        public static final int PAUSE_COMMAND = 1;

        public static final int STOP_COMMAND = 2;
    }

    public ZenusControl() {
        mWatchThread = new WatchThread(this);
    }

    @Override
    public void control(Message msg){
        EasyLog.v("scboy::control!");
        if (mWatchThread == null){
            mWatchThread = new WatchThread(this);
        }
        if (msg != null){
            int command = msg.getCommand();
            if (command == COMMAND.START_COMMAND){
                if (mWatchThread.getReceiveListener() == null){
                    mWatchThread.setReceiveListener(mIReceiveListener);
                }
                mWatchThread.startWatch();
            }else if(command == COMMAND.STOP_COMMAND){
                mWatchThread.quit();
                mWatchThread = null;
            }else if (command == COMMAND.PAUSE_COMMAND){
                mWatchThread.pause();
            }
        }
    }


    @Override
    public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        try {
            return super.onTransact(code, data, reply, flags);
        } catch (Exception e) {
            EasyLog.e("onTransact::"+e);
            throw e;
        }
    }

    @Override
    public void setReceiveListener(IReceiveListener listener) throws RemoteException {
//        mWatchThread.setReceiveListener(listener);
        this.mIReceiveListener = listener;
    }
}
