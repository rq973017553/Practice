package com.rq.practice.utils.notch;

import android.os.Build;
import android.text.TextUtils;

/**
 * <获取手机品牌>
 *
 * @author rockyou
 * @version [2019/06/20]
 */
public class PhoneBrand
{
    public final boolean isHuaWei() {
        String manufacturer = Build.MANUFACTURER;
        if (!TextUtils.isEmpty(manufacturer)){
            if (manufacturer.contains("HUAWEI")) return true;
        }
        return false;
    }

    public final boolean isMIUI() {
        String manufacturer = getSystemProperty("ro.miui.ui.version.name");
        if (!TextUtils.isEmpty(manufacturer)){
            return true;
        }
        return false;
    }

    public final boolean isOPPO() {
        String manufacturer = getSystemProperty("ro.build.version.opporom");
//        String manufacturer = getSystemProperty("ro.product.brand");
        if (!TextUtils.isEmpty(manufacturer)){
            return true;
        }
        return false;
    }

    public final boolean isViVo() {
        String manufacturer = this.getSystemProperty("ro.vivo.os.name");
        if (!TextUtils.isEmpty(manufacturer)){
            return true;
        }
        return false;
    }

    /**
     * 判断android P系统
     *
     * @return true 是android P,false 不是android P
     */
    public boolean isAndroidPSystem()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
        {
            return true;
        }
        return false;
    }

    private  String getSystemProperty(String propName) {
        return SystemProperties.getInstance().get(propName);
    }
}
