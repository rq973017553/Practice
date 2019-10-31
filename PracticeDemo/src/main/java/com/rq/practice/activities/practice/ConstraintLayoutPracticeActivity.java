package com.rq.practice.activities.practice;

import android.net.Uri;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.utils.EasyLog;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ConstraintLayoutPracticeActivity extends BaseToolBarActivity {


    @Override
    public int getLayoutID() {
        return R.layout.activity_video_view_pra;
    }

    @Override
    public void bindView() {
        Uri uri = Uri.parse("https://www.baidu.com/s?wd=Uri%E7%9A%84getQueryParameter&rsv_spt=1&rsv_iqid=0xbc8efa8e000108a2&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=1&rsv_t=7be1l1F5SMVoUMgM6wHLQztp1VYmLk4ZvvOnyTtZvquENTzzeNkrOtXVUVulbU%2BsT%2FkM&oq=json&rsv_pq=c84791d300017088&inputT=3316&rsv_sug3=29&rsv_sug1=23&rsv_sug7=100&rsv_n=2&rsv_sug2=0&rsv_sug4=4088");
        String value = uri.getQueryParameter("wd");
        try {
            value = URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        EasyLog.v("scboy::value= "+value);
    }

    @Override
    public void initData() {
    }
}
