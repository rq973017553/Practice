package com.rq.practice.framework.contract;

import com.rq.practice.framework.presenter.base.BasePresenter;
import com.rq.practice.framework.view.BaseView;

/**
 * RxJavaPractice的Contract
 *
 * @author rock you
 * @version [1.0.0 2018.8.3]
 */
public interface RxJavaPracticeContract {

    interface View extends BaseView{

        // 显示RxJava结果
        void showRxJavaResult(String result);
    }

    interface Presenter extends BasePresenter<View>{

        // 启动RXJava练习
        void startRxJavaPractice();
    }
}
