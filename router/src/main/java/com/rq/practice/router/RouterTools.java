package com.rq.practice.router;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Router工具类
 * @author rock you
 * @version [1.0.0, 2018.8.18]
 */
public class RouterTools {

    /**
     * 获取当前应用的所有Activity
     * @param context context
     * @return 包含Activity的列表
     */
    public static List<Class<?>> getActivities(Context context){
        List<Class<?>> list = new ArrayList<>();
        PackageInfo packageInfo;
        String packageName = context.getPackageName();
        PackageManager packageManager = context.getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            ActivityInfo activityInfos [] = packageInfo.activities;
            if (activityInfos != null){
                for (ActivityInfo activityInfo :activityInfos){
                    String activityName = activityInfo.name;
                    Class<?> clazz = Class.forName(activityName);
                    list.add(clazz);
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return list;
    }
}
