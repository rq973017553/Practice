package com.rq.practice.utils.notch;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;

import java.util.List;

/**
 * <刘海屏Base类>
 *
 * @author rockyou
 * @version [2019/06/20]
 */
public abstract class BaseNotch implements IDeviceNotch
{
    private static final PhoneBrand DEVICE_BRAND = new PhoneBrand();

    public static IDeviceNotch get()
    {
        return findDevice();
    }

    private static IDeviceNotch findDevice()
    {
        try
        {
            if (DEVICE_BRAND.isMIUI())
            {
                return new DeviceMIUI();
            }
            else if (DEVICE_BRAND.isHuaWei())
            {
                return new DeviceHuaWei();
            }
            else if (DEVICE_BRAND.isOPPO())
            {
                return new DeviceOPPO();
            }
            else if (DEVICE_BRAND.isViVo())
            {
                return new DeviceViVo();
            }
        }
        catch (Exception ignored)
        {
            // Empty
        }
        return new DeviceOther();
    }

    @TargetApi(28)
    @Override
    public boolean isNotchScreen(Window window)
    {
        boolean isNotchScreen = false;
        if (DEVICE_BRAND.isAndroidPSystem())
        {
            View decorView = window.getDecorView();
            WindowInsets windowInsets = decorView.getRootWindowInsets();
            if (windowInsets != null)
            {
                DisplayCutout displayCutout = windowInsets.getDisplayCutout();

                if (displayCutout != null)
                {
                    List<Rect> rects = displayCutout.getBoundingRects();
                    // 通过判断是否存在rects来确定是否刘海屏手机
                    if (rects != null && rects.size() > 0)
                    {
                        isNotchScreen = true;
                    }
                }
            }
        }
        else
        {
            isNotchScreen = isNotchScreenDevice(window);
        }

        return isNotchScreen;
    }

    @TargetApi(28)
    @Override
    public Notch getNotchSize(Window window)
    {
        Notch notch = new Notch();
        if (DEVICE_BRAND.isAndroidPSystem())
        {
            View decorView = window.getDecorView();
            WindowInsets windowInsets = decorView.getRootWindowInsets();
            if (windowInsets != null)
            {
                DisplayCutout displayCutout = windowInsets.getDisplayCutout();
                if (displayCutout != null)
                {
                    List<Rect> list = displayCutout.getBoundingRects();
                    if (list != null && list.size() > 0)
                    {
                        Rect rect = list.get(0);
                        int width = rect.right - rect.left;
                        int height = rect.bottom - rect.top;
                        notch.setWidth(width);
                        notch.setHeight(height);
                    }
                }
            }
        }
        else
        {
            notch = getNotchSizeDevice(window);
        }
        return notch;
    }

    public abstract boolean isNotchScreenDevice(Window window);

    public abstract Notch getNotchSizeDevice(Window window);
}
