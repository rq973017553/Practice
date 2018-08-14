package com.rq.practice.framework.presenter.base;

import com.rq.practice.framework.view.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * RxPresenter
 * 带RxJava线程释放策略的Presenter
 * @author rock you
 * @version [1.0.0, 2018.8.15]
 */
public abstract class RxPresenter<V extends BaseView> extends CommonPresenter<V> {

    private CompositeDisposable mCompositeDisposable = null;

    public RxPresenter() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
    }

    public boolean subscribe(Disposable disposable) {
        return mCompositeDisposable.add(disposable);
    }

    public boolean unSubscribe(Disposable disposable) {
        return mCompositeDisposable.remove(disposable);
    }

    public void clear() {
        mCompositeDisposable.clear();
    }

    public boolean isDisposed() {
        return mCompositeDisposable.isDisposed();
    }
}
