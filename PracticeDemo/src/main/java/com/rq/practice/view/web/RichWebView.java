package com.rq.practice.view.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rq.practice.R;
import com.rq.practice.utils.orientation.VideoOrientationDetector;

public class RichWebView extends RelativeLayout {

    private RelativeLayout mRichWebTitle;

    private ImageView mRichWebBack;

    private ImageView mRichWebForward;

    private ImageView mRichWebRefresh;

    private WebView mWebView;

    private FrameLayout mFullVideoContainer;

    private Context mContext;

    private WebSettings mWebSettings;

    // 帮助WebView处理各种通知、请求事件的
    private RichWebChromeClient mWebChromeClient;

    // 主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度
    private RichWebClient mWebClient;

    private IRichWebUIListener mListener;

    private String mURL;

    private VideoOrientationDetector mOrientationDetector;

    private View mCustomView;

    public RichWebView(Context context) {
        this(context, null);
    }

    public RichWebView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RichWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
        initData();
    }

    public void setRichWebUIListener(IRichWebUIListener listener) {
        this.mListener = listener;
    }

    public void loadUrl(String url) {
        if (mWebView != null) {
            this.mURL = url;
            mWebView.loadUrl(url);
        }
    }

    private void initView() {
        inflate(mContext, R.layout.view_rich_web, this);
        mRichWebTitle = findViewById(R.id.rich_web_title);
        mRichWebBack = findViewById(R.id.web_back);
        mRichWebForward = findViewById(R.id.web_forward);
        mRichWebRefresh = findViewById(R.id.web_refresh);
        mWebView = findViewById(R.id.web_view);
        mFullVideoContainer = findViewById(R.id.fl_video_container);

        mRichWebRefresh.setOnClickListener(click);
    }

    private void initData() {
        initWebSetting();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebSetting() {

        mWebClient = new RichWebClient(mContext, listener);

        mWebChromeClient = new RichWebChromeClient(mContext, listener);

        // 获取WebSettings
        mWebSettings = mWebView.getSettings();

        // 设置是否启用JavaScript
        mWebSettings.setJavaScriptEnabled(true);

        // 设置是否支持html中的viewPoint标签，以实现网页自动适应手机界面
        mWebSettings.setUseWideViewPort(true);

        // 设置是否缩放至屏幕的大小，可以和"setUseWideViewPort"一起使用
        mWebSettings.setLoadWithOverviewMode(true);

        // 设置是否启用WebView访问文件数据
        mWebSettings.setAllowFileAccess(true);

        // 设置是否支持变焦
        mWebSettings.setSupportZoom(true);

        // 设置允许js弹出alert对话框
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 设置是否开启DOM Storage(数据缓存)
        mWebSettings.setDomStorageEnabled(true);

        mWebView.setWebViewClient(mWebClient);

        mWebView.setWebChromeClient(mWebChromeClient);
    }

    public void registerOrientationDetector(Activity activity) {
        mOrientationDetector = new VideoOrientationDetector(activity);
        if (mOrientationDetector.canDetectOrientation()) {
            mOrientationDetector.enable();
        }
    }

    public void unRegisterOrientationDetector() {
        if (mOrientationDetector != null) {
            mOrientationDetector.disable();
        }
    }

    OnClickListener click = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.web_refresh:
                    mWebView.loadUrl(mURL);
                    break;
            }
        }
    };

    IRichWebClientListener listener = new IRichWebClientListener() {
        @Override
        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
            mCustomView = view;
            showCustomView(view);
            if (mListener != null) {
                mListener.onChangeScreen();
            }
        }

        @Override
        public void onHideCustomView() {
            hideCustomView();
            if (mListener != null) {
                mListener.onChangeScreen();
            }
        }
    };

    private void showCustomView(View view){
        mWebView.setVisibility(View.GONE);
        mRichWebTitle.setVisibility(GONE);
        mFullVideoContainer.setVisibility(View.VISIBLE);
        mFullVideoContainer.addView(view);
    }

    private void hideCustomView(){
        mWebView.setVisibility(View.VISIBLE);
        mRichWebTitle.setVisibility(VISIBLE);
        mFullVideoContainer.removeAllViews();
        mFullVideoContainer.setVisibility(View.GONE);
    }

    public void destroy() {
        if (mWebView != null) {
            mWebView.setWebChromeClient(null);
            mWebView.setWebViewClient(null);
            mWebView.destroy();
        }
    }
}
