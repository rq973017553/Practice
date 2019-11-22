package com.rq.practice.view.web;

import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;

class RichWebChromeClient extends WebChromeClient {

    private Context mContext;

    private IRichWebClientListener mListener;

    RichWebChromeClient(Context mContext, IRichWebClientListener listener) {
        this.mContext = mContext;
        this.mListener = listener;
    }

    @Override
    public void onShowCustomView(View view, CustomViewCallback callback) {
        super.onShowCustomView(view, callback);
        if (mListener != null) {
            mListener.onShowCustomView(view, callback);
        }
    }

    @Override
    public void onHideCustomView() {
        super.onHideCustomView();
        if (mListener != null) {
            mListener.onHideCustomView();
        }
    }
}
