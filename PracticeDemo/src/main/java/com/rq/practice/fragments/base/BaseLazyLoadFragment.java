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
 * @author rock you
 * @version [1.0.0 2018.8.16]
 */
public abstract class BaseLazyLoadFragment extends BaseFragment{

    private boolean isCreateView = false;

    private boolean isShowUI = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isCreateView = true;
        return super.onCreateView(inflater, container, savedInstanceState);
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
