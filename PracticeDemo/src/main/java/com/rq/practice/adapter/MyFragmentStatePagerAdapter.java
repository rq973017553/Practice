package com.rq.practice.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.rq.practice.adapter.base.BaseFragmentStatePagerAdapter;

import java.util.List;


/**
 * FragmentStatePagerAdapter
 *
 * @author rock you
 * @version [1.0.0, 2018.8.16]
 */
public class MyFragmentStatePagerAdapter extends BaseFragmentStatePagerAdapter<Fragment> {

    private List<String> mTitles;

    public MyFragmentStatePagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public void setPageTitle(List<String> titles){
        this.mTitles = titles;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
