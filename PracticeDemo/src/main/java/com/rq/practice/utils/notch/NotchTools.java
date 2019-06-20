package com.rq.practice.utils.notch;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * 适配刘海屏兼容Android P的刘海屏
 *
 * @author raoqiang
 * @version [版本号, 2019/6/20]
 */
public class NotchTools
{
    private final static IDeviceNotch mDevice = BaseNotch.get();

    public static boolean isNotchScreen(Activity activity)
    {
        if (Build.VERSION.SDK_INT <= 25)
        {
            return false;
        }
        return activity != null && mDevice.isNotchScreen(activity.getWindow());
    }

    public static Notch getNotchSize(Activity activity)
    {
        if (Build.VERSION.SDK_INT <= 25 || activity == null)
        {
            return new Notch();
        }
        return mDevice.getNotchSize(activity.getWindow());
    }

    /**
     * 设置刘海屏模式
     *
     * @param activity activity
     * @param mode mode
     */
    public static void setLayoutInDisplayCutoutMode(Activity activity, int mode)
    {
        PhoneBrand phoneBrand = new PhoneBrand();
        if (phoneBrand.isAndroidPSystem())
        {
            Window window = activity.getWindow();
            if (window != null)
            {
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                if (layoutParams != null)
                {
                    layoutParams.layoutInDisplayCutoutMode = mode;
                    window.setAttributes(layoutParams);
                }
            }
        }
    }

}
