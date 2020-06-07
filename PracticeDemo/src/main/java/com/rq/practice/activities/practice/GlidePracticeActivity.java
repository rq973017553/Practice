package com.rq.practice.activities.practice;

import android.media.MediaMetadataRetriever;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
        Glide.with(this)
//                .asGif()
//                .asBitmap()
                .load("http://attach.bbs.miui.com/forum/201408/07/194456i55q58pqnb55fi88.jpg")
                .thumbnail(0.1f)
                .into(testGlide);
    }
}
