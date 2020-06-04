package com.rq.practice.activities.practice;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;

public class GlidePracticeActivity extends BaseToolBarActivity {
    // https://www.jianshu.com/p/791ee473a89b
    private static final String URL = "http://cn.bing.com/az/hprichbg/rb/TOAD_ZH-CN7336795473_1920x1080.jpg";

    private ImageView testGlide;

    @Override
    public int getLayoutID() {
        return R.layout.activity_glide_pra;
    }

    @Override
    public void bindView() {
        testGlide = findViewById(R.id.test_glide_ima);
    }

    @Override
    public void initData() {
        Glide.with(GlidePracticeActivity.this)
             .load(URL)
             .placeholder(R.drawable.xiaomai)
             .into(testGlide);
    }
}
