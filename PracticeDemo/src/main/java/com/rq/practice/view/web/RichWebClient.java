package com.rq.practice.view.web;

import android.content.Context;
import android.webkit.WebViewClient;

public class RichWebClient extends WebViewClient {

    private Context mContext;

    private IRichWebClientListener mListener;

    public RichWebClient(Context context, IRichWebClientListener listener) {
        this.mContext = context;
        this.mListener = listener;
    }
}
