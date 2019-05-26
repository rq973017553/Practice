package com.rq.practice.activities.practice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.control.IControl;
import com.rq.practice.control.Message;

public class ServicePracticeActivity extends BaseToolBarActivity {

    private Button mStartServices;

    private Button mStopServices;

    private Button mStartTiming;

    private Button mStopTiming;

    private IControl control;

    private TextView mShowMsg;

    @Override
    public int getLayoutID() {
        return R.layout.activity_service_pra;
    }

    @Override
    public void bindView() {
        mStartServices = findViewById(R.id.start_service);
        mStopServices = findViewById(R.id.stop_service);
        mStartTiming = findViewById(R.id.start_timing);
        mStopTiming = findViewById(R.id.stop_timing);
        mShowMsg = findViewById(R.id.show_msg);
        mStartTiming.setOnClickListener(click);
        mStopTiming.setOnClickListener(click);
        mStartServices.setOnClickListener(click);
        mStopServices.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.start_service:
                    Intent intent = new Intent();
                    intent.setAction("com.rq.practice.zeus.action");
                    intent.setPackage("com.rq.practice");
                    bindService(intent, connection, BIND_AUTO_CREATE);
                    break;
                case R.id.stop_service:
                    unbindService(connection);
                    break;
                case R.id.start_timing:
                    Message message = new Message();
                    message.setCommand("test");
                    try {
                        if (control != null){
                            control.control(message);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.stop_timing:
                    break;
            }
        }
    };

    @Override
    public void initData() {

    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            control = IControl.Stub.asInterface(iBinder);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (control != null){
                        String msg = "null";
                        try {
                            msg = control.receive();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        mShowMsg.setText(msg);
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
