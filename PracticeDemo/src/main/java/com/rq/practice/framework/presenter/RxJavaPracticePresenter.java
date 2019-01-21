package com.rq.practice.framework.presenter;

import com.rq.practice.framework.contract.RxJavaPracticeContract;
import com.rq.practice.framework.dagger.scope.ActivityScope;
import com.rq.practice.framework.presenter.base.CommonPresenter;
import com.rq.practice.utils.EasyLog;
import com.rq.practice.utils.RxJavaPractice;

import javax.inject.Inject;

/**
 * RxJavaPracticePresenter类
 *
 * @author rock you
 * @version [1.0.0 2018.8.3]
 */
public class RxJavaPracticePresenter extends CommonPresenter<RxJavaPracticeContract.View> implements RxJavaPracticeContract.Presenter {

    @Inject
    public RxJavaPracticePresenter(){
    }

    @Override
    public void startRxJavaPractice() {
        RxJavaPractice.getInstance().flowablePractice(new RxJavaPractice.PracticeListener() {
            @Override
            public void showRxJavaData(String data) {
                if(mView != null){
                   mView.showRxJavaResult(data);
                }
            }

            @Override
            public void showRxJavaDataError(Throwable e) {
                EasyLog.v(e.getMessage());
            }
        });
    }
}
