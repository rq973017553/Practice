package com.rq.practice.utils;

import android.content.Context;
import android.os.Vibrator;


/**
 * 震动管理类
 * */
public class VibratorUtils {

    private final static int VIBRATOR_TIME = 200;


    /**
     * 配置接口是否开启震动
     * */
    public static void setSettingVibrator(Context context){
        if(context == null){
            return;
        }
        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        if(vibrator != null){
            vibrator.vibrate(VIBRATOR_TIME);
        }
    }
}
