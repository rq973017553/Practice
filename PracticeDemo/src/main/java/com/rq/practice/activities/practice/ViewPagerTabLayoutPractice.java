package com.rq.practice.activities.practice;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.adapter.MyFragmentStatePagerAdapter;
import com.rq.practice.fragments.test.Fragment00;
import com.rq.practice.fragments.test.Fragment01;
import com.rq.practice.fragments.test.Fragment02;
import com.rq.practice.fragments.test.Fragment03;
import com.rq.practice.fragments.test.Fragment04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ViewPager&TabLayout练习
 * @author rock you
 * @version [1.0.0, 2018.8.16]
 */
public class ViewPagerTabLayoutPractice extends BaseToolBarActivity {

    private TabLayout mTabLayout;

    private ViewPager mViewPager;

    private static final String text[] = {"首页", "赛事", "会员", "直播", "用户"};

    @Override
    public int getLayoutID() {
        return R.layout.activity_viewpage_tablayout_pra;
    }

    @Override
    public void bindView() {
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
    }

    @Override
    public void initData() {
        List<Fragment> listData = new ArrayList<>();
        listData.add(Fragment00.newInstance(Fragment00.class));
        listData.add(Fragment01.newInstance(Fragment01.class));
        listData.add(Fragment02.newInstance(Fragment02.class));
        listData.add(Fragment03.newInstance(Fragment03.class));
        listData.add(Fragment04.newInstance(Fragment04.class));
        MyFragmentStatePagerAdapter adapter = new MyFragmentStatePagerAdapter(getSupportFragmentManager());
        adapter.setFragments(listData);
        adapter.setPageTitle(Arrays.asList(text));
        mViewPager.setAdapter(adapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
