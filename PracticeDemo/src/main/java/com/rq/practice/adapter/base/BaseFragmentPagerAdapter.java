package com.rq.practice.adapter.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * FragmentPagerAdapter基类
 * @author rock you
 * @version [1.0.0, 2018.8.16]
 */
public class BaseFragmentPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> mFragments;

    public BaseFragmentPagerAdapter(FragmentManager manager, List<T> listData){
        this(manager);
        this.mFragments = listData;
    }

    public BaseFragmentPagerAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        if (mFragments == null){
            return null;
        }
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        if (mFragments == null){
            return 0;
        }
        return mFragments.size();
    }

    public void setFragments(List<T> fragments) {
        this.mFragments = fragments;
    }
}
