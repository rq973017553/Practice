package com.rq.practice.activities.practice;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rq.practice.R;
import com.rq.practice.activities.base.MVPBaseActivity;
import com.rq.practice.framework.contract.RxJavaPracticeContract;
import com.rq.practice.framework.dagger.component.ActivityComponent;
import com.rq.practice.framework.presenter.RxJavaPracticePresenter;

/**
 * RxJava练习Activity
 * @author rock you
 * @version [1.0.0, 2018.8.14]
 */
public class RxJavaPracticeActivity extends MVPBaseActivity<RxJavaPracticePresenter> implements RxJavaPracticeContract.View{

    private Button mRunRxjavaBtn;

    private TextView mShowRxJavaRunResult;

    @Override
    public int getLayoutID() {
        return R.layout.activity_rxjava_pra;
    }

    @Override
    public void bindView() {
        mRunRxjavaBtn = findViewById(R.id.start_rx_java_btn);
        mRunRxjavaBtn.setOnClickListener(clickListener);
        mShowRxJavaRunResult = findViewById(R.id.show_rx_java_run_result);
    }

    @Override
    public void initData() {
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.start_rx_java_btn:
                    mPresenter.startRxJavaPractice();
                    break;
            }
        }
    };

    @Override
    public void showRxJavaResult(String result) {
        mShowRxJavaRunResult.setText(result);
    }

    @Override
    public void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
