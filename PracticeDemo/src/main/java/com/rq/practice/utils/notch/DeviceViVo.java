package com.rq.practice.utils.notch;

import android.view.Window;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <VIVO刘海屏设备>
 *
 * @author rockyou
 * @version [2019/06/20]
 */
public class DeviceViVo extends BaseNotch
{
    @Override
    public boolean isNotchScreenDevice(Window window)
    {
        boolean isNotchScreen;

        if (window == null)
        {
            return false;
        }

        ClassLoader classLoader = window.getContext().getClassLoader();
        try
        {
            Class mClass = classLoader.loadClass("android.util.FtFeature");
            Method mMethod = mClass.getMethod("isFeatureSupport", Integer.TYPE);
            isNotchScreen = (Boolean) mMethod.invoke(mClass, 0x00000020);
        }
        catch (ClassNotFoundException e)
        {
            return false;
        }
        catch (NoSuchMethodException e)
        {
            return false;
        }
        catch (IllegalAccessException e)
        {
            return false;
        }
        catch (InvocationTargetException e)
        {
            return false;
        }
        return isNotchScreen;
    }

    @Override
    public Notch getNotchSizeDevice(Window window)
    {
        return new Notch();
    }
}
