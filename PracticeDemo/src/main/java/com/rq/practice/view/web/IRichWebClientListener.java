package com.rq.practice.view.web;

import android.view.View;
import android.webkit.WebChromeClient;

public interface IRichWebClientListener {

    void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback);

    void onHideCustomView();
}
