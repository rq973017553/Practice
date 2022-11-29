package com.rq.practice.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.rq.practice.R;
import com.rq.practice.utils.EasyLog;

public class ZeusService extends Service {

    private static final String TAG = "ZeusService::";

    private NotificationManager mManager = null;

    private ZenusControl mControl = null;

    @Override
    public void onCreate() {
        super.onCreate();
        EasyLog.v(TAG+"onCreate");
        mControl = new ZenusControl();
        mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = createNotificationChannel();
            mManager.createNotificationChannel(channel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        EasyLog.v(TAG+"onStartCommand");
        startForeground(createNotificationID(), createNotification());
        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        EasyLog.v(TAG+"onBind");
        return mControl;
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

    @RequiresApi(Build.VERSION_CODES.O)
    private NotificationChannel createNotificationChannel(){
        NotificationChannel channel = new NotificationChannel(createChannelID(), "ZeusService", NotificationManager.IMPORTANCE_HIGH);
        return channel;
    }

    private Notification createNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, createChannelID());
        builder.setShowWhen(true);
        builder.setContentTitle("ZeusService");
        builder.setContentText("后台服务运行中!");
        builder.setSmallIcon(R.drawable.xiaomai);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setAutoCancel(true);
        builder.setLights(Color.RED, 200, 200);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.xiaomai));
        Notification notification = builder.build();
        mManager.notify(createNotificationID(), notification);
        return notification;
    }

    private String createChannelID(){
        return "8848";
    }

    private int createNotificationID(){
        return R.mipmap.ic_launcher;
    }
}
