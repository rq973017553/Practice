package com.rq.practice.utils.notch;

import android.view.Window;

/**
 * <OPPO刘海屏设备>
 *
 * @author rockyou
 * @version [2019/06/20]
 */
public class DeviceOPPO extends BaseNotch
{

    @Override
    public boolean isNotchScreenDevice(Window window)
    {
        if (window == null)
        {
            return false;
        }
        return window.getContext().getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    @Override
    public Notch getNotchSizeDevice(Window window)
    {
        return new Notch();
    }
}
