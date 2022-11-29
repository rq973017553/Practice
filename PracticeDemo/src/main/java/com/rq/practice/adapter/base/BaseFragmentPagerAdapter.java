package com.rq.practice.adapter.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * FragmentPagerAdapter基类
 * 适用于相对静态的页面,数据不多,占用内存小的情况
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
