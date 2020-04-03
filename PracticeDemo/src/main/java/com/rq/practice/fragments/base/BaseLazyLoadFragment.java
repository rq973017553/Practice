package com.rq.practice.fragments.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 懒加载BaseFragment
 * (1)配合ViewPager使用的时候，由于ViewPager的预加载机制。
 * 导致无法配合onResume和onStop统计用户查看
 * (2)有可能预加载的页面需要加载大量图片，加载比较耗时。
 * (3)如果出现页面空白的情况，可以复现ViewPager的destroyItem，同时去掉super方法
 * 参考链接:https://www.jianshu.com/p/254dc5ddffea
 * @author rock you
 * @version [1.0.0 2018.8.16]
 */
public abstract class BaseLazyLoadFragment extends BaseFragment{

    private boolean isCreateView = false;

    private boolean isShowUI = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // onCreateView -> onViewCreated -> onActivityCreated
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mView != null) {
            isCreateView = true;
        }
    }

    @Override
    public void initData() {
        if (isShowUI && isCreateView){
            lazyLoad();
            isCreateView = false;
            isShowUI = false;
        }
    }

    /**
     *
     * @param isVisibleToUser
     * true代表该fragment可见，false代表fragment不可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (mView == null) {
            return;
        }
        if (isVisibleToUser){
            // fragment可见
            isShowUI = true;
            initData();
        }else {
            // fragment不可见
            isShowUI = false;
        }
    }

    public abstract void lazyLoad();
}
