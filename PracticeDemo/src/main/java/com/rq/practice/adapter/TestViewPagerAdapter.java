package com.rq.practice.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.rq.practice.R;
import com.rq.practice.bean.BannerBean;

import java.util.ArrayList;
import java.util.List;

public class TestViewPagerAdapter extends PagerAdapter {

    private List<BannerBean> bannerList = new ArrayList<>();

    private LayoutInflater layoutInflater;

    public TestViewPagerAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public TestViewPagerAdapter(Context context, List<BannerBean> beanList) {
        this(context);
        this.bannerList = beanList;
    }

    public void setBannerList(List<BannerBean> bannerList) {
        this.bannerList = bannerList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        BannerBean bannerBean = bannerList.get(position);
        View rootView = layoutInflater.inflate(R.layout.layout_banner, null, false);
        ImageView bannerImage = rootView.findViewById(R.id.banner_image);
        bannerImage.setImageResource(bannerBean.getResourceID());
        container.addView(rootView);
        return rootView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (bannerList == null){
            return "";
        }
        return bannerList.get(position).getTitle();
    }

    @Override
    public int getCount() {
        if (bannerList == null){
            return 0;
        }
        return bannerList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }
}
