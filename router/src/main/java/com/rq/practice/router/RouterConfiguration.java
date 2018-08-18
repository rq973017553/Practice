package com.rq.practice.router;

import android.content.Context;

import java.util.List;
import java.util.Map;

/**
 * Router配置类
 * @author rock you
 * @version [1.0.0, 2018.8.18]
 */
public class RouterConfiguration {

    private RouterImpl mRouterImpl;

    private RouterConfiguration(){
        mRouterImpl = new RouterImpl();
    }

    public RouterImpl getRouterImpl() {
        return mRouterImpl;
    }

    public static class Builder{
        private RouterImpl mRouterImpl;

        private static volatile boolean sInitialization =false;

        public Builder(){
            mRouterImpl = new RouterImpl();
        }

        public Builder init(Context context){
            Map<String, Class<?>> map = mRouterImpl.createRouterMap();
            if (!sInitialization && map.isEmpty()){
                List<Class<?>> list = RouterTools.getActivities(context);
                for (Class<?> clazz :list){
                    Route route = clazz.getAnnotation(Route.class);
                    if (route != null){
                        String key = route.value();
                        map.put(key, clazz);
                    }
                }
                sInitialization = true;
            }
            return this;
        }

        public RouterConfiguration build(){
            RouterConfiguration configuration = new RouterConfiguration();
            configuration.mRouterImpl = mRouterImpl;
            return configuration;
        }
    }
}
