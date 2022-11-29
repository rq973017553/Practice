package com.rq.practice.activities.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rq.practice.utils.ActivityUtils;

/**
 * BaseActivity
 * @author qiangrao
 * @version [1.0.0, 2018.11.11]
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.onActivityCreate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityUtils.onActivityStart(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityUtils.onActivityResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityUtils.onActivityPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ActivityUtils.onActivityStop(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.onActivityDestroy(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ActivityUtils.onActivityRestart(this);
    }

    protected void startActivity(Class<? extends Activity> clazz){
        startActivity(new Intent(this, clazz));
    }

    public abstract int getLayoutID();

    public abstract void bindView();

    public abstract void initData();
}
