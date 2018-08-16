package com.rq.practice.framework.dagger.component;

import com.rq.practice.activities.practice.RxJavaPracticeActivity;
import com.rq.practice.framework.dagger.scope.ActivityScope;

import dagger.Component;

/**
 * Activity的Component
 * @author rock you
 * @version [1.0.0, 2018.8.15]
 */
@ActivityScope
@Component(dependencies = {AppComponent.class})
public interface ActivityComponent {

    void inject(RxJavaPracticeActivity activity);
}
