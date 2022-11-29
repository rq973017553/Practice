package com.rq.practice.activities.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.rq.practice.R;
import com.rq.practice.utils.SystemBarUtils;

/**
 * BaseToolBarActivity
 * Activity基类
 *
 * @author rock you
 * @version [1.0.0 2018.6.1]
 */
public abstract class BaseToolBarActivity extends BaseActivity {

    // R.id.toolbar
    protected Toolbar mToolBar;

    // R.id.add_layout
    protected FrameLayout mAddLayout;

    // LayoutInflater
    protected LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_toolbar_layout);
        initTemplateLayout();
        bindView();
        initData();
    }

    private void initTemplateLayout(){
        mLayoutInflater = LayoutInflater.from(this);

        // ToolBar
        mToolBar = findViewById(R.id.toolbar);
        initToolBar();

        // 添加类
        mAddLayout = findViewById(R.id.add_layout);
        int layoutID = getLayoutID();
        View view = mLayoutInflater.inflate(layoutID, null);
        if (view == null){
            throw new RuntimeException("inflter "+layoutID+"failure!");
        }
        mAddLayout.addView(view, new ViewGroup.LayoutParams(
                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                            ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void initToolBar(){
        setTitle("");
        mToolBar.setTitle("");
        if (mToolBar != null){
            setSupportActionBar(mToolBar);
            SystemBarUtils.setStatusBarFullTransparent(getWindow(), false);
        }
    }
}
