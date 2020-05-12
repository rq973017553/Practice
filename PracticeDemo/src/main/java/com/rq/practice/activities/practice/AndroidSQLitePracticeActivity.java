package com.rq.practice.activities.practice;

import com.rq.practice.R;
import com.rq.practice.activities.base.BaseToolBarActivity;
import com.rq.practice.database.dao.StudentDao;

public class AndroidSQLitePracticeActivity extends BaseToolBarActivity {

    private StudentDao studentDao;

    @Override
    public int getLayoutID() {
        return R.layout.activity_android_sqlite_pra;
    }

    @Override
    public void bindView() {

    }

    @Override
    public void initData() {
        studentDao = new StudentDao(AndroidSQLitePracticeActivity.this);
    }
}
