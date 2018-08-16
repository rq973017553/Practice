package com.rq.practice.framework.dagger.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * AppModule
 * @author rock you
 * @version [1.0.0, 2018.8.16]
 */
@Module
public class AppModule {

    private Application mApplication;

    public void setApplication(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext(){
        return mApplication.getApplicationContext();
    }
}
