package com.rq.practice.fragments.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 懒加载BaseFragment
 * (1)配合ViewPager使用的时候，由于ViewPager的预加载机制。
 * 导致无法配合onResume和onStop统计用户查看
 * (2)有可能预加载的页面需要加载大量图片，加载比较耗时。
 * (3)如果出现页面空白的情况，可以复现ViewPager的destroyItem，同时去掉super方法
 * 参考链接:https://www.jianshu.com/p/0e2d746e3a3d
 * 参考链接:https://www.jianshu.com/p/2201a107d5b5
 * @author rock you
 * @version [1.0.0 2018.8.16]
 */
public abstract class BaseLazyLoadFragment extends BaseFragment{

    private volatile boolean isCreateView = false;

    private volatile boolean isShowUI = false;

    // onCreateView-->onViewCreated-->onActivityCreated
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isCreateView = true;
        doLazyLoad();
    }

    public void doLazyLoad(){
        if (isShowUI && isCreateView){
            lazyLoad();
        }
    }

    /**
     * 该方法在add+hide+show的情形未测试懒加载
     * @param hidden 是否隐藏
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        this.isShowUI = !hidden;
        doLazyLoad();
    }

    /**
     *
     * @param isVisibleToUser
     * true代表该fragment可见，false代表fragment不可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isShowUI = isVisibleToUser;
        doLazyLoad();
    }

    public abstract void lazyLoad();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isShowUI = false;
        isCreateView = false;
    }
}
