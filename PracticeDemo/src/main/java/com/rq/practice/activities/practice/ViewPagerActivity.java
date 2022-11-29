package com.rq.practice.activities.practice;

import androidx.viewpager.widget.ViewPager;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.adapter.TestViewPagerAdapter;
import com.rq.practice.bean.BannerBean;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends BaseToolBarActivity {

    private ViewPager viewPager;

    private TestViewPagerAdapter pagerAdapter;

    @Override
    public int getLayoutID() {
        return R.layout.activity_view_pager;
    }

    @Override
    public void bindView() {
        viewPager = findViewById(R.id.view_pager);
    }

    @Override
    public void initData() {
        List<BannerBean> bannerBeans = new ArrayList<>();
        for (int i = 0; i < 5; i ++){
            BannerBean bannerBean = new BannerBean();
            bannerBean.setResourceID(R.drawable.star_craft_00+i);
            bannerBean.setTitle("图片"+i);
            bannerBeans.add(bannerBean);
        }
        pagerAdapter = new TestViewPagerAdapter(ViewPagerActivity.this, bannerBeans);
        viewPager.setAdapter(pagerAdapter);
    }
}
