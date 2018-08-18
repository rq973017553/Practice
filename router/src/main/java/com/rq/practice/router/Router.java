package com.rq.practice.router;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import java.util.Map;

/**
 * Router
 * @author rock you
 * @version [1.0.0, 2018.8.18]
 */
public class Router {

    private RouterImpl mRouterImpl;

    private String mRouterURL = null;

    private Bundle mBuild = null;

    private int mRequestCode = -1;

    private Router(){}

    private static class Holder{
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance(){
        return Holder.INSTANCE;
    }

    public Router init(RouterConfiguration configuration){
        this.mRouterImpl = configuration.getRouterImpl();
        return this;
    }

    public Router jump(String routerURL){
        if (TextUtils.isEmpty(routerURL)){
            throw new IllegalArgumentException("jump router url is null or empty!");
        }
        this.mRouterURL = routerURL;
        return this;
    }

    public Router withBundle(Bundle build){
        if (build == null){
            throw new IllegalArgumentException("build is null or empty!");
        }
        checkNull(build);
        this.mBuild = build;
        return this;
    }

    public Router withRequestCode(int requestCode){
        this.mRequestCode = requestCode;
        return this;
    }

    public Router go(Activity context){
        Map<String, Class<?>> map = this.mRouterImpl.getRouterMap();
        if (context != null){
            if (map.containsKey(mRouterURL)){
                Class<?> activity = map.get(mRouterURL);
                Intent intent = new Intent();
                bindData(intent);
                intent.setClass(context, activity);
                ActivityCompat.startActivityForResult(context, intent, mRequestCode, mBuild);
            }
        }
        return this;
    }

    private void bindData(Intent intent){
        if (mBuild != null){
            intent.putExtras(mBuild);
        }
    }

    private void checkNull(Object object){
        if (object == null){
            throw new IllegalArgumentException("argument is null!");
        }
    }
}
