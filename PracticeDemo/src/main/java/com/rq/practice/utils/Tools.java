package com.rq.practice.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;

import com.rq.practice.utils.EasyLog;
import com.rq.practice.utils.ShellUtils;

import java.lang.reflect.Method;

/**
 * Created by antiy2015 on 2016/5/19.
 */
public class Tools {

    @Deprecated
    public static void setMobileDataEnabled(Context context, boolean flag){
        Method setMobileDataEnabledMethod;
        EasyLog.v("SDK_INT::"+Build.VERSION.SDK_INT);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            ConnectivityManager gprsCM = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            try {
                setMobileDataEnabledMethod = gprsCM.getClass()
                        .getDeclaredMethod("setMobileDataEnabled", boolean.class);
                setMobileDataEnabledMethod.setAccessible(true);
                setMobileDataEnabledMethod.invoke(gprsCM, flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            TelephonyManager teleManager = (TelephonyManager)context.
                    getSystemService(Context.TELEPHONY_SERVICE);
            try{
                setMobileDataEnabledMethod = teleManager.getClass().
                        getDeclaredMethod("setDataEnabled", boolean.class);
                setMobileDataEnabledMethod.setAccessible(true);
                setMobileDataEnabledMethod.invoke(teleManager, flag);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void setMobileDataEnabled(boolean enable){
        EasyLog.v("SDK_INT::"+Build.VERSION.SDK_INT);
        String cmd = enable? "svc data enable" : "svc data disable";
        ShellUtils.CommandResult result = ShellUtils.execCommand(cmd, false, true);
        EasyLog.v("svc data 结果::"+
                  "\nresult::"+result.result+
                  "\n::errorMsg::"+result.errorMsg+
                  "\n::successMsg::"+result.successMsg);
    }

    /**
     *
     * @param fromUri
     *            源图片地址
     * @param aspectX
     *            剪切宽高比(宽度)
     * @param aspectY
     *            剪切宽高比(高度)
     * @param outputX
     *            剪切的宽度
     * @param outputY
     *            剪切的高度
     * @return Intent
     */
    public static Intent doCropPhoto(Uri fromUri, Uri toUri, int aspectX, int aspectY,
                                     int outputX, int outputY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(fromUri, "image/*");
        intent.putExtra("crop", "true");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);

        // 去掉截图太小时黑边的问题
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("scale", true);

        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, toUri);
        return intent;
    }
}
