package com.rq.practice.framework.dagger.component;

import android.app.Application;
import android.content.Context;

import com.rq.practice.framework.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App的Component
 * @author rock you
 * @version [1.0.0, 2018.8.15]
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Application getApplication();

    Context getContext();
}
