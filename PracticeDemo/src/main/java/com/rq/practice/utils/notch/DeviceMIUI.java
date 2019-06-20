package com.rq.practice.utils.notch;

import android.text.TextUtils;
import android.view.Window;

/**
 * <小米刘海屏设备>
 *
 * @author rockyou
 * @version [2019/06/20]
 */
public class DeviceMIUI extends BaseNotch
{

    @Override
    public boolean isNotchScreenDevice(Window window)
    {
        if (TextUtils.equals("1", SystemProperties.getInstance().get("ro.miui.notch")))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public Notch getNotchSizeDevice(Window window)
    {
        return new Notch();
    }
}
