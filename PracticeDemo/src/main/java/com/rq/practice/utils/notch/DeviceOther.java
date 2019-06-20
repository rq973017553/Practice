package com.rq.practice.utils.notch;

import android.view.Window;

/**
 * <其他刘海屏设备>
 *
 * @author rockyou
 * @version [2019/06/20]
 */
public class DeviceOther extends BaseNotch
{
    @Override
    public boolean isNotchScreenDevice(Window window)
    {
        return false;
    }

    @Override
    public Notch getNotchSizeDevice(Window window)
    {
        return new Notch();
    }
}
