package com.rq.practice.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类工具
 * 对于'Proxy.newProxyInstance'的封装
 * @author rock you
 * @version 1.0.0
 */
public class ProxyTools {

    private ProxyInterceptor mInterceptor;

    private ProxyTools() {
    }

    private static final class Holder {
        private static final ProxyTools INSTANCE = new ProxyTools();
    }

    public static ProxyTools newInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 获取代理对象
     * @param object 被代理的对象
     * @param interceptor ProxyTools.ProxyInterceptor接口
     * @param <S> T类型的父接口
     * @param <T> 被代理的数据类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public <S, T extends S> S getProxy(T object, ProxyInterceptor interceptor) {
        return getProxy(object, null, interceptor);
    }

    /**
     * 获取代理对象
     * @param object 被代理的对象
     * @param interceptor ProxyTools.ProxyInterceptor接口
     * @param <S> T类型的父接口
     * @param interfaceArray 接口数组
     * @param <T> 被代理的数据类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public <S, T extends S> S getProxy(T object, Class[] interfaceArray, ProxyInterceptor interceptor) {
        if (object == null) {
            throw new NullPointerException("agent object is null!");
        }
        if (interceptor == null) {
            throw new NullPointerException("interceptor is null!");
        }
        this.mInterceptor = interceptor;
        if (interfaceArray == null || interfaceArray.length == 0){
            interfaceArray = object.getClass().getInterfaces();
        }
        Invocation<T> invocation = new Invocation<>(object);
        Object proxy = Proxy.newProxyInstance(ProxyTools.class.getClassLoader(),
                interfaceArray, invocation);
        return (T) proxy;
    }

    /**
     * InvocationHandler的实例化
     */
    private class Invocation<T> implements InvocationHandler {

        private T t;

        Invocation(T t) {
            this.t = t;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            return mInterceptor.interceptor(t, proxy, method, args);
        }
    }

    /**
     * 对外暴露的代理接口
     */
    public interface ProxyInterceptor {

        /**
         * 拦截接口
         * @param obj 原对象
         * @param proxy 代理
         * @param method 方法
         * @param args 方法参数
         * @return 生成的代理对象
         * @throws Throwable Throwable
         */
        Object interceptor(Object obj, Object proxy, Method method, Object[] args) throws Throwable;
    }
}
