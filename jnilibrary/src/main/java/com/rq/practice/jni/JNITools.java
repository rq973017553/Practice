package com.rq.practice.jni;

/**
 * JNI工具
 *
 * @author rock you
 * @version [1.0.0, 20180827]
 */
public class JNITools {

    public native String  stringFromJNI();

    public native int add(int a, int b);

    static {
        System.loadLibrary("hello-jni");
    }
}
