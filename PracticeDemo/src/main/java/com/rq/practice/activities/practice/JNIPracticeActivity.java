package com.rq.practice.activities.practice;

import android.text.TextUtils;
import android.widget.TextView;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.jni.JNITools;
//import com.rq.practice.jni.JNITools;

/**
 * JNI练习Activity
 * @author rock you
 * @version [1.0.0, 2018.8.28]
 */
public class JNIPracticeActivity extends BaseToolBarActivity {

    private TextView mShowJNIResult;

    @Override
    public int getLayoutID() {
        return R.layout.activity_jni_pra;
    }

    @Override
    public void bindView() {
        mShowJNIResult = findViewById(R.id.show_jni_result);
    }

    @Override
    public void initData() {
        String text;
        JNITools jniTools = new JNITools();
        int result = jniTools.add(23, 34);
        text = String.valueOf(result);
        if (!TextUtils.isEmpty(text)){
            mShowJNIResult.setText(text);
        }else {
            mShowJNIResult.setText("null");
        }
    }
}
