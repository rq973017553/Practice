package com.rq.practice.framework.dagger.component;

import com.rq.practice.framework.dagger.scope.FragmentScope;

import dagger.Component;

/**
 * Fragment的Component
 * @author rock you
 * @version [1.0.0, 2018.8.15]
 */
@FragmentScope
@Component(dependencies = {AppComponent.class})
public interface FragmentComponent {
}
