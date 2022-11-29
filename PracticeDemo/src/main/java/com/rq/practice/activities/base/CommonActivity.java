package com.rq.practice.activities.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class CommonActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        bindView();
        initData();
    }
}
