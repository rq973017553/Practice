package com.rq.practice.database.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * SampleDBHelper
 * @author rock you
 * @version 1.0.0
 */
public class SampleDBHelper extends DBHelper {

    public SampleDBHelper(Context context){
        super(context);
    }

    @Override
    protected String onCreate() {
        return null;
    }

    @Override
    protected void onUpgrade(SQLiteDatabase db, int version) {

    }

    @Override
    protected void onDropTable() {

    }

    @Override
    protected String getTableName() {
        return null;
    }
}
