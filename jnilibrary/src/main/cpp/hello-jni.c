/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
#include <jni.h>
#include "log.h"

/**
 * long -> J
 * boolean -> Z
 *
 * long[] -> [J
 * boolean[] -> [Z
 *
 * 函数
 * int aa(int a, int b) -> (II)I
 *
 * L${java类路径，其中"."换成"/"}结尾加上;
 * 如果该类是内部类在类似如下
 * Landroid/os/FileUtils$FileStatus;
 */
JNINativeMethod jniNativeMethods[]={
        {"add","(II)I",(void*) add}
};

/**
 * 加法
 * @param env
 * @param thiz
 * @param a
 * @param b
 * @return
 */
jint add(JNIEnv* env, jobject thiz, jint a, jint b){
    int result = a + b;
    return result;
}

/* This is a trivial JNI example where we use a native method
 * to return a new VM String. See the corresponding Java source
 * file located at:
 *
 *   hello-jni/app/src/main/java/com/example/hellojni/HelloJni.java
 */
JNIEXPORT jstring JNICALL
Java_com_rq_practice_jni_JNITools_stringFromJNI( JNIEnv* env,
                                                  jobject thiz )
{
#if defined(__arm__)
    #if defined(__ARM_ARCH_7A__)
    #if defined(__ARM_NEON__)
      #if defined(__ARM_PCS_VFP)
        #define ABI "armeabi-v7a/NEON (hard-float)"
      #else
        #define ABI "armeabi-v7a/NEON"
      #endif
    #else
      #if defined(__ARM_PCS_VFP)
        #define ABI "armeabi-v7a (hard-float)"
      #else
        #define ABI "armeabi-v7a"
      #endif
    #endif
  #else
   #define ABI "armeabi"
  #endif
#elif defined(__i386__)
#define ABI "x86"
#elif defined(__x86_64__)
#define ABI "x86_64"
#elif defined(__mips64)  /* mips64el-* toolchain defines __mips__ too */
#define ABI "mips64"
#elif defined(__mips__)
#define ABI "mips"
#elif defined(__aarch64__)
#define ABI "arm64-v8a"
#else
#define ABI "unknown"
#endif

    return (*env)->NewStringUTF(env, "Hello from JNI !  Compiled with ABI " ABI ".");
}

static char* CLASS_NAME = "com/rq/practice/jni/JNITools";

int registerNativeMethods(JNIEnv *env, char *className, JNINativeMethod *methods, int nativeMethodSize){
    jclass  clazz;
    clazz = (*env) -> FindClass(env, className);
    if (clazz == NULL){
        return JNI_FALSE;
    }
    if ((*env) -> RegisterNatives(env, className, methods, nativeMethodSize) < 0){
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

/**
 *
 * @param jvm
 * @param reserved
 * @return
 */
JNIEXPORT jint JNI_OnLoad(JavaVM *jvm, void *reserved){
    LOGD("OnLoad!");
    JNIEnv *env;
    if ((*jvm) -> GetEnv(jvm, (void**) &env, JNI_VERSION_1_6) != JNI_OK)
    {
        return JNI_ERR;
    }
    int size = sizeof(jniNativeMethods) / sizeof(jniNativeMethods[0]);
    if (!registerNativeMethods(env, CLASS_NAME, jniNativeMethods, size)){
        return JNI_ERR;
    }

    return JNI_VERSION_1_6;
}

JNIEXPORT void JNI_UnOnload(JavaVM *jvm, void *reserved){
    LOGD("unOnLoad!");
}