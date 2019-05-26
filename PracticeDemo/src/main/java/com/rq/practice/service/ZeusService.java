package com.rq.practice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.rq.practice.control.IControl;
import com.rq.practice.control.Message;
import com.rq.practice.utils.EasyLog;

public class ZeusService extends Service {

    private static final String TAG = "ZeusService::";

    @Override
    public void onCreate() {
        super.onCreate();
        EasyLog.v(TAG+"onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        EasyLog.v(TAG+"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        EasyLog.v(TAG+"onBind");
        return stub;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        EasyLog.v(TAG+"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        EasyLog.v(TAG+"onDestroy");
        super.onDestroy();
    }

    IControl.Stub stub = new IControl.Stub() {

        Message msg;

        @Override
        public void control(Message msg) throws RemoteException {
            this.msg = msg;
            String command = msg.getCommand();
            EasyLog.v(TAG+"command= "+command);
        }

        @Override
        public String receive() throws RemoteException {
            if (msg == null){
                return "null";
            }
            return TextUtils.isEmpty(msg.getCommand())? "null": "receive!";
        }
    };
}
