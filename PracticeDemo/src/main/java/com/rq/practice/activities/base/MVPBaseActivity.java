package com.rq.practice.activities.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.rq.practice.PracticeApplication;
import com.rq.practice.framework.dagger.component.ActivityComponent;
import com.rq.practice.framework.dagger.component.DaggerActivityComponent;
import com.rq.practice.framework.presenter.base.BasePresenter;
import com.rq.practice.framework.view.BaseView;

import javax.inject.Inject;

/**
 * 带MVP架构的BaseActivity
 *
 * @author rock you
 * @version [1.0.0 2018.8.3]
 */
public abstract class MVPBaseActivity<P extends BasePresenter> extends BaseToolBarActivity implements BaseView {

    @Inject
    protected P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        inject(getActivityComponent());
        super.onCreate(savedInstanceState);
        if (mPresenter == null){
            throw new RuntimeException("presenter is null!");
        }
        mPresenter.attachView(this);
    }

    private ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(PracticeApplication.getAppComponent())
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }

    @Override
    public void showErrorMsg(String msg) {
        // Empty Method
    }

    @Override
    public void showError() {
        // Empty Method
    }

    @Override
    public void showEmpty() {
        // Empty Method
    }

    @Override
    public void startLoading() {
        // Empty Method
    }

    @Override
    public void stopLoading() {
        // Empty Method
    }

    public abstract void inject(ActivityComponent activityComponent);
}
