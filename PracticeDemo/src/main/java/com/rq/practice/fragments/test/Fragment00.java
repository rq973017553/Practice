package com.rq.practice.fragments.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rq.practice.R;
import com.rq.practice.fragments.base.BaseFragment;
import com.rq.practice.utils.CountDownTimerUtil;
import com.rq.practice.utils.EasyLog;
import com.rq.practice.view.CountDownTextView;

public class Fragment00 extends BaseFragment {

    private CountDownTextView mCountDownTextView;

    private TextView mTextView;

    private ImageView mImageView;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_00;
    }

    @Override
    public void bindView() {
        mCountDownTextView = findViewById(R.id.count_down_text_view);
        mImageView = findViewById(R.id.test_ima_00);
        mTextView = findViewById(R.id.text_view);
    }

    @Override
    public void initData() {
        mCountDownTextView.start(6);
        mCountDownTextView.setCountDownListener(countDown);
    }

    CountDownTextView.ICountDown countDown = new CountDownTextView.ICountDown() {

        @Override
        public void onStart(CountDownTimerUtil countDownTimerUtil) {
        }

        @Override
        public void onFinish() {
            EasyLog.e("scboy::onFinish!");
            mTextView.setTextColor(getResources().getColor(R.color.colorYellow));
            mTextView.setText("倒计时结束!");
            Toast.makeText(getContext(), "倒计时结束!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCountDownTextView != null){
            mCountDownTextView.cancel();
            mCountDownTextView = null;
        }
    }
}
