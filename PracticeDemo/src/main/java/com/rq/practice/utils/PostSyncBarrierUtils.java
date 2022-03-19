package com.rq.practice.utils;

import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;

import java.lang.reflect.Method;

public class PostSyncBarrierUtils {

    public static int postSyncBarrier(Looper looper, long when){
        if (looper == null) {
            return -1;
        }
        MessageQueue messageQueue = getMessageQueue(looper);
        try {
            if (messageQueue == null){
                return -1;
            }
            Class<?> messageQueueClass = messageQueue.getClass();
            Method postSyncBarrier = messageQueueClass.getDeclaredMethod("postSyncBarrier", Long.class);
            postSyncBarrier.setAccessible(true);
            return (int) postSyncBarrier.invoke(messageQueue, when);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int postSyncBarrier(long when){
        Looper looper = Looper.myLooper();
        if (looper == null) {
            return -1;
        }
        return postSyncBarrier(looper, when);
    }

    public static int postSyncBarrier(){
        Looper looper = Looper.myLooper();
        if (looper == null) {
            return -1;
        }
        return postSyncBarrier(looper);
    }

    public static int postSyncBarrier(Looper looper){
        if (looper == null){
            return -1;
        }
        MessageQueue messageQueue = getMessageQueue(looper);
        try {
            if (messageQueue == null){
                return -1;
            }
            Class<?> messageQueueClass = messageQueue.getClass();
            Method postSyncBarrier = messageQueueClass.getMethod("postSyncBarrier");
            return (int) postSyncBarrier.invoke(messageQueue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void removeSyncBarrier(int token){
        Looper looper = Looper.myLooper();
        if (looper != null){
            removeSyncBarrier(looper, token);
        }
    }

    public static void removeSyncBarrier(Looper looper, int token){
        Class<?> messageQueueClass;
        if (looper != null) {
            MessageQueue messageQueue = getMessageQueue(looper);
            if (messageQueue != null){
                try {
                    messageQueueClass = messageQueue.getClass();
                    Method removeSyncBarrier = messageQueueClass.getMethod("removeSyncBarrier", int.class);
                    removeSyncBarrier.invoke(messageQueue, token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void setAsynchronous(Message message, boolean async){
        try {
            Class<?> messageClass = message.getClass();
            Method setAsynchronous = messageClass.getMethod("setAsynchronous", boolean.class);
            setAsynchronous.invoke(message, async);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MessageQueue getMessageQueue(Looper looper){
        try {
            Class<?> looperClass = looper.getClass();
            Method getQueue = looperClass.getMethod("getQueue");
            return (MessageQueue) getQueue.invoke(looper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
