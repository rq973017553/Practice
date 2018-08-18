package com.rq.practice;

import android.app.Application;

import com.rq.practice.framework.dagger.component.AppComponent;
import com.rq.practice.framework.dagger.component.DaggerAppComponent;
import com.rq.practice.framework.dagger.module.AppModule;
import com.rq.practice.router.Router;
import com.rq.practice.router.RouterConfiguration;
import com.rq.practice.utils.EasyLog;

/**
 * Application类
 *
 * @author rock you
 * @version [1.0.0 2018.6.4]
 */
public class PracticeApplication extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        EasyLog.v("onCreate");
        initConfiguration();
    }

    private void initConfiguration(){
        // Dagger构建全局Component
        sAppComponent = createAppComponent();

        // Router初始化配置
        RouterConfiguration configuration = new RouterConfiguration.Builder()
                                .init(this)
                                .build();
        Router.getInstance().init(configuration);
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    private AppComponent createAppComponent() {
        AppModule appModule = new AppModule();
        appModule.setApplication(this);
        return DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        EasyLog.v("onTerminate");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        EasyLog.v("onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        EasyLog.v("onTrimMemory");
    }
}
