package com.rq.practice.activities.practice;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseActivity;
import com.rq.practice.adapter.CanvasPracticeAdapter;
import com.rq.practice.fragments.canvas.DrawPracticeFragment;
import com.rq.practice.fragments.canvas.PaintPracticeFragment;
import com.rq.practice.view.draw.PaintPracticeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 练习Canvas和相关的技术的Activity
 * @author rock you
 * @version [1.0.0, 2018.09.13]
 */
public class CanvasPracticeActivity extends BaseActivity {

    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    private CanvasPracticeAdapter mCanvasPraAdapter;

    private List<Fragment> mFragments;

    private static final String text[] = {"drawXXX练习", "Paint练习"};

    @Override
    public int getLayoutID() {
        return R.layout.activity_canvas_pra;
    }

    @Override
    public void bindView() {
        mTabLayout = findViewById(R.id.canvas_pra_tab_layout);
        mViewPager = findViewById(R.id.canvas_pra_view_pager);
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(DrawPracticeFragment.newInstance(DrawPracticeFragment.class));
        mFragments.add(PaintPracticeFragment.newInstance(PaintPracticeFragment.class));
        mCanvasPraAdapter = new CanvasPracticeAdapter(getSupportFragmentManager());
        mCanvasPraAdapter.setPageTitle(Arrays.asList(text));
        mCanvasPraAdapter.setFragments(mFragments);
        mViewPager.setAdapter(mCanvasPraAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
