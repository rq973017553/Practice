package com.rq.practice.activities.practice;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;

import com.rq.practice.R;
import com.rq.practice.activities.base.CommonActivity;
import com.rq.practice.view.web.IRichWebUIListener;
import com.rq.practice.view.web.RichWebView;

// https://blog.csdn.net/u012500046/article/details/50592174。
// https://www.jianshu.com/p/d2f5ae6b4927
// https://blog.csdn.net/qihigh/article/details/13777023
// https://blog.csdn.net/coder_pig/article/details/48468969
// https://blog.csdn.net/w_l_s/article/details/53055552
// https://www.jianshu.com/p/613391e267b2
// https://blog.csdn.net/carson_ho/article/details/64904635
// https://blog.csdn.net/wbwjx/article/details/54645313
// https://www.cnblogs.com/renhui/p/5893593.html
// https://www.jianshu.com/p/dd53094b580a
// https://www.jianshu.com/p/4aed5c1230dc
// WebView的相关知识

public class WebViewPlayerActivity extends CommonActivity {

    private RichWebView mRichWebView;

    @Override
    public int getLayoutID() {
        return R.layout.activity_webview_player_pra;
    }

    @Override
    public void bindView() {
        mRichWebView = findViewById(R.id.rich_web_view);
    }

    @Override
    public void initData() {

//        String url = "https://www.bilibili.com/video/av75085308";
        String url = "http://v.163.com/paike/V8H1BIE6U/VAG52A1KT.html";
//        String url = "https://www.baidu.com/";

        mRichWebView.setRichWebUIListener(listener);

        mRichWebView.loadUrl(url);

        mRichWebView.registerOrientationDetector(WebViewPlayerActivity.this);
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
        fullScreen(isFullScreenMode());
    }

    IRichWebUIListener listener = new IRichWebUIListener() {
        @Override
        public void onChangeScreen() {
            fullScreen(isFullScreenMode());
            if (isFullScreenMode()) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
            }
        }
    };

    private void fullScreen(boolean isFullScreen) {
        ActionBar supportActionBar = getSupportActionBar();
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        if (isFullScreen) {
            if (supportActionBar != null) {
                supportActionBar.hide();
            }
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        } else {
            if (supportActionBar != null) {
                supportActionBar.show();
            }
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    private int getOrientation() {
        return getResources().getConfiguration().orientation;
    }

    private boolean isFullScreenMode() {
        return getOrientation() == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRichWebView != null) {
            mRichWebView.destroy();
        }
        if (mRichWebView != null) {
            mRichWebView.unRegisterOrientationDetector();
        }
    }
}
