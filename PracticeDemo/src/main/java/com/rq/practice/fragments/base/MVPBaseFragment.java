package com.rq.practice.fragments.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rq.practice.PracticeApplication;
import com.rq.practice.framework.dagger.component.ActivityComponent;
import com.rq.practice.framework.dagger.component.DaggerActivityComponent;
import com.rq.practice.framework.dagger.component.DaggerFragmentComponent;
import com.rq.practice.framework.dagger.component.FragmentComponent;
import com.rq.practice.framework.dagger.module.ActivityModule;
import com.rq.practice.framework.presenter.base.BasePresenter;
import com.rq.practice.framework.view.BaseView;

import javax.inject.Inject;

/**
 * 带MVP的BaseFragment
 *
 * @author rock you
 * @version [1.0.0 2018.8.3]
 */
public abstract class MVPBaseFragment<P extends BasePresenter> extends BaseFragment implements BaseView{

    @Inject
    protected P mPresenter;

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        inject(getFragmentComponent());
        // 暂时这样写，后续加入Dagger2
        if (mPresenter == null){
            throw new RuntimeException("presenter is null!");
        }
        mPresenter.attachView(this);
        return view;
    }

    private FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(PracticeApplication.getAppComponent())
                .build();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    public abstract void inject(FragmentComponent activityComponent);
}
