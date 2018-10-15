package com.rq.practice.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.rq.practice.adapter.base.BaseFragmentStatePagerAdapter;

import java.util.List;

/**
 * &lt;一句话功能简述&gt;
 * &lt;功能详细描述&gt;
 *
 * @author ${user}
 * @version [版本号, ${date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CanvasPracticeAdapter extends BaseFragmentStatePagerAdapter<Fragment> {

    List<String> mTitles;

    public CanvasPracticeAdapter(FragmentManager manager) {
        super(manager);
    }

    public void setPageTitle(List<String> titles){
        this.mTitles = titles;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
