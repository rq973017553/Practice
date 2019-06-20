package com.rq.practice.utils.notch;

import android.view.Window;

import com.rq.practice.utils.EasyLog;

import java.lang.reflect.Method;

/**
 * <华为刘海屏设备>
 *
 * @author rockyou
 * @version [2019/06/20]
 */
public class DeviceHuaWei extends BaseNotch
{

    @Override
    public boolean isNotchScreenDevice(Window window)
    {
        boolean isNotchScreen = false;
        try
        {
            ClassLoader cl = window.getContext().getClassLoader();
            Class hwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = hwNotchSizeUtil.getMethod("hasNotchInScreen", null);
            isNotchScreen = (Boolean) get.invoke(hwNotchSizeUtil, null);
        }
        catch (ClassNotFoundException e)
        {
            EasyLog.e("hasNotchInScreen ClassNotFoundException");
        }
        catch (NoSuchMethodException e)
        {
            EasyLog.e("hasNotchInScreen NoSuchMethodException");
        }
        catch (Exception e)
        {
            EasyLog.e("hasNotchInScreen Exception");
        }
        return isNotchScreen;
    }

    @Override
    public Notch getNotchSizeDevice(Window window)
    {
        Notch notch = new Notch();
        try
        {
            ClassLoader cl = window.getContext().getClassLoader();
            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("getNotchSize", null);
            int[] result = (int[]) get.invoke(HwNotchSizeUtil, null);
            notch.setWidth(result[0]);
            notch.setHeight(result[1]);
        }
        catch (ClassNotFoundException e)
        {
            EasyLog.e("getNotchSize ClassNotFoundException");
        }
        catch (NoSuchMethodException e)
        {
            EasyLog.e("getNotchSize NoSuchMethodException");
        }
        catch (Exception e)
        {
            EasyLog.e("getNotchSize Exception");
        }
        return notch;
    }
}
