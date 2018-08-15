package com.rq.practice.framework.dagger.component;

import android.app.Activity;

import com.rq.practice.activities.practice.RxJavaPracticeActivity;
import com.rq.practice.framework.dagger.module.ActivityModule;
import com.rq.practice.framework.dagger.scope.ActivityScope;

import dagger.Component;

/**
 * Activity的Component
 * @author rock you
 * @version [1.0.0, 2018.8.15]
 */
@ActivityScope
@Component(dependencies = {AppComponent.class},
           modules = {ActivityModule.class})
public interface ActivityComponent {

//    Activity getActivity();

    void inject(RxJavaPracticeActivity activity);
}
