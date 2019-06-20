package com.rq.practice.utils.notch;

import android.view.Window;

public interface IDeviceNotch
{

    boolean isNotchScreen(Window window);

    Notch getNotchSize(Window window);
}
