package com.rq.practice.activities.practice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.utils.PostSyncBarrierUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class HandlerBarrierActivity extends BaseToolBarActivity {

    private int token = -1;

    private int index = 0;

    private SyncBarrierHandler syncBarrierHandler;

    private Looper looper;

    private TextView showMsgData;

    private static final int MSG_ASYNCHRONOUS_WHAT = -1;

    private static final int MSG_WHAT = 0;

    private static final long MAX_DELAY_MILLIS = 800;

    private List<Integer> tokens = new ArrayList<>();

    @Override
    public int getLayoutID() {
        return R.layout.activity_handler_barrier_pra;
    }

    @Override
    public void bindView() {
        showMsgData = findViewById(R.id.show_msg_data);
        findViewById(R.id.post_sync_barrier).setOnClickListener(clickListener);
        findViewById(R.id.remove_sync_barrier).setOnClickListener(clickListener);
        findViewById(R.id.send_sync_msg).setOnClickListener(clickListener);
        findViewById(R.id.send_async_msg).setOnClickListener(clickListener);
    }

    @Override
    public void initData() {
        HandlerThread handlerThread = new HandlerThread("testSyncBarrier");
        handlerThread.start();
        looper = handlerThread.getLooper();
        syncBarrierHandler = new SyncBarrierHandler(this, looper);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Message message;
            switch (v.getId()){
                case R.id.post_sync_barrier:
                    token = PostSyncBarrierUtils.postSyncBarrier(looper);
                    tokens.add(token);
                    Log.e("scboy", "token "+token);
                    break;
                case R.id.remove_sync_barrier:
                    index = 0;
                    if (!tokens.isEmpty()){
                        for (int token: tokens){
                            PostSyncBarrierUtils.removeSyncBarrier(looper, token);
                        }
                    }
                    break;
                case R.id.send_sync_msg:
                    message = Message.obtain(syncBarrierHandler, MSG_WHAT);
                    syncBarrierHandler.sendMessageDelayed(message, MAX_DELAY_MILLIS);
                    message.obj = "普通消息:: "+ index;
                    index++;
                    break;
                case R.id.send_async_msg:
                    message = Message.obtain(syncBarrierHandler, MSG_ASYNCHRONOUS_WHAT);
                    message.obj = "异步消息";
                    PostSyncBarrierUtils.setAsynchronous(message, true);
                    syncBarrierHandler.sendMessageDelayed(message, MAX_DELAY_MILLIS);
                    break;
            }
        }
    };

    private static class SyncBarrierHandler extends Handler{

        private WeakReference<Activity> weakActivity;

        public SyncBarrierHandler(Activity activity, Looper looper) {
            super(looper);
            weakActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("scboy", "msg数据 "+msg.obj);
            Message uiMessage = Message.obtain();
            uiMessage.obj = msg.obj;
            HandlerBarrierActivity activity = (HandlerBarrierActivity)weakActivity.get();
            if (activity != null){
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.showMsgData.setText((String)msg.obj);
                    }
                });
            }
        }
    }

//    @SuppressLint("HandlerLeak")
//    private Handler sUIHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            showMsgData.setText((String)msg.obj);
//        }
//    };
}
