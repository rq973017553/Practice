package com.rq.practice.activities.practice;

import android.view.View;
import android.widget.Button;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.view.CountDownView;

public class SurfaceViewPracticeActivity extends BaseToolBarActivity {

    private CountDownView mCountDownView;

    private Button mStartPause;

    private Button mStop;

    private boolean isRunning = false;

    int mNum = 0;

    @Override
    public int getLayoutID() {
        return R.layout.activity_surface_view_pra;
    }

    @Override
    public void bindView() {
        mCountDownView = findViewById(R.id.count_down_view);
        mStartPause = findViewById(R.id.start_pause);
        mStop = findViewById(R.id.stop);
        mStartPause.setOnClickListener(click);
        mStop.setOnClickListener(click);
    }

    @Override
    public void initData() {
        mCountDownView.setCountDownListener(countDownListener);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.start_pause:
                    if (mCountDownView != null && !isRunning) {
                        isRunning = true;
                        mCountDownView.start();
                        mStartPause.setText(R.string.activity_surface_view_pra_pause);
                    } else if (mCountDownView != null && mCountDownView.getCurrentState() == CountDownView.STATE.STATE_PAUSED) {
                        mCountDownView.resume();
                        mStartPause.setText(R.string.activity_surface_view_pra_pause);
                    } else if (mCountDownView != null) {
                        mCountDownView.pause();
                        mStartPause.setText(R.string.activity_surface_view_pra_start);
                    }
                    break;
                case R.id.stop:
                    if (mCountDownView != null && mCountDownView.getCurrentState() == CountDownView.STATE.STATE_RUNNING) {
                        isRunning = false;
                        mCountDownView.stop();
                        mStartPause.setText(R.string.activity_surface_view_pra_restart);
                    }
                    break;
            }
        }
    };

    CountDownView.OnCountDownListener countDownListener = new CountDownView.OnCountDownListener() {

        @Override
        public void state(int currentState) {
        }

        @Override
        public int drawText() {
            mNum = mNum + 1;
            return mNum;
        }
    };
}
