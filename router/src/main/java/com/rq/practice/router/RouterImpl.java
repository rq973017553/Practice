package com.rq.practice.router;

import java.util.HashMap;
import java.util.Map;

/**
 * Router帮助类
 * 主要负责Router和RouterConfiguration之间的数据传递
 * @author rock you
 * @version [1.0.0, 2018.8.18]
 */
class RouterImpl {
    private Map<String, Class<?>> mRouterMap = null;

    public Map<String, Class<?>> createRouterMap() {
        if (mRouterMap == null){
            mRouterMap = new HashMap<>();
        }else {
            mRouterMap.clear();
        }
        return mRouterMap;
    }

    public Map<String, Class<?>> getRouterMap() {
        return mRouterMap;
    }
}
