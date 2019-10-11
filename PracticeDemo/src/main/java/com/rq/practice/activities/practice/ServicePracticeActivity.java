package com.rq.practice.activities.practice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.control.IControl;
import com.rq.practice.control.IReceiveListener;
import com.rq.practice.control.Message;
import com.rq.practice.control.Receive;
import com.rq.practice.service.ServiceManager;
import com.rq.practice.service.ZenusControl;

public class ServicePracticeActivity extends BaseToolBarActivity {

    private Intent intent = null;

    private ServiceManager serviceManager = null;

    private Button mStartTiming;

    private Button mPauseTiming;

    private Button mStopTiming;

    private IControl mControl;

    private TextView mShowMsg;

    @Override
    public int getLayoutID() {
        return R.layout.activity_service_pra;
    }

    @Override
    public void bindView() {
        mStartTiming = findViewById(R.id.start_timing);
        mPauseTiming = findViewById(R.id.pause_timing);
        mStopTiming = findViewById(R.id.stop_timing);
        mShowMsg = findViewById(R.id.show_msg);
        mStartTiming.setOnClickListener(click);
        mPauseTiming.setOnClickListener(click);
        mStopTiming.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Message message = new Message();
            switch (view.getId()){
                case R.id.start_timing:
                    message.setCommand(ZenusControl.COMMAND.START_COMMAND);
                    try {
                        if (mControl != null){
                            mControl.control(message);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.pause_timing:
                    message.setCommand(ZenusControl.COMMAND.PAUSE_COMMAND);
                    try {
                        if (mControl != null){
                            mControl.control(message);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.stop_timing:
                    message.setCommand(ZenusControl.COMMAND.STOP_COMMAND);
                    try {
                        if (mControl != null){
                            mControl.control(message);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Override
    public void initData() {
        serviceManager = new ServiceManager(this);
        intent = new Intent("com.rq.practice.zeus.action");
        intent.setPackage("com.rq.practice");
        serviceManager.startService(intent);
        serviceManager.bindService(intent, connection, BIND_AUTO_CREATE);
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Toast.makeText(ServicePracticeActivity.this,
                    "已经连接!", Toast.LENGTH_SHORT).show();
            mControl = IControl.Stub.asInterface(iBinder);
            if (mControl != null){
                try {
                    mControl.setReceiveListener(new IReceiveListener.Stub() {
                        @Override
                        public void onReceive(final Receive receive){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (mControl != null) {
                                        if (receive != null) {
                                            int text = receive.getReceive();
                                            mShowMsg.setText(String.valueOf(text));
                                        } else {
                                            mShowMsg.setText("无数据!");
                                        }
                                    }
                                }
                            });
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Toast.makeText(ServicePracticeActivity.this,
                    "连接断开!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        serviceManager.unBindService(connection);
        serviceManager.stopService(intent);
    }
}
