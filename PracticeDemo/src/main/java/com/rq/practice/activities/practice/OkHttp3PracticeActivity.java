package com.rq.practice.activities.practice;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp3PracticeActivity extends BaseToolBarActivity {

    private static final String URL = "http://wwww.baidu.com";

    private OkHttpClient client;

    @Override
    public int getLayoutID() {
        return R.layout.activity_okhttp3_pra;
    }

    @Override
    public void bindView() {

    }

    @Override
    public void initData() {
        client = new OkHttpClient
                .Builder()
                .build();
    }

    private void get(){
        Request request = new Request.Builder()
                            .url(URL)
                            .get()
                            .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }
}
