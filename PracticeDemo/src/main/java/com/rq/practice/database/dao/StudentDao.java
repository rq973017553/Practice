package com.rq.practice.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.rq.practice.database.base.DBHelper;
import com.rq.practice.utils.EasyLog;

import java.util.List;

/**
 * STUDENT_TABLE
 * @author rock you
 * @version 1.0.0
 */
public class StudentDao extends DBHelper {

    private static final String STUDENT_TABLE_NAME = "student_table";

    private static final String ID = "id";

    private static final String NAME = "name";

    private static final String AGE = "age";

    private static final String SEX = "sex";

    private static final String CHINESE_SCORE = "chinese_score";

    private static final String MATH_SCORE = "math_score";

    private static final String ENGLISH_SCORE = "english_score";

    public StudentDao(Context context){
        super(context);
    }

    @Override
    protected String onCreate() {
        EasyLog.e("Student onCreate");
//        CREATE TABLE IF NOT EXISTS
        return "CREATE TABLE IF NOT EXISTS "+
                STUDENT_TABLE_NAME+"("+
                ID+" integer primary key autoincrement,"+
                NAME+" text,"+
                AGE+" integer,"+
                SEX+" text,"+
                CHINESE_SCORE+" real,"+
                MATH_SCORE+" real,"+
                ENGLISH_SCORE+" real"+
                ");";
    }

    public long bulkInsertStudentData(List<ContentValues> list){
        return bulkInsert(list);
    }

    public long insertStudentData(ContentValues contentValues){
        return insert(contentValues);
    }

    public int deleteStudentInfo(String name){
        return delete("where = ?", new String[]{name});
    }

    public int updateChineseScore(float chineseScore, String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CHINESE_SCORE, chineseScore);
        return update(contentValues, "where = ?", new String[]{name});
    }

    public int updateMathScore(float mathScore, String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MATH_SCORE, mathScore);
        return update(contentValues, "where = ?", new String[]{name});
    }

    public int updateEnglishScore(float englishScore, String name){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ENGLISH_SCORE, englishScore);
        return update(contentValues, "where = ?", new String[]{name});
    }

    @Override
    protected void onUpgrade(SQLiteDatabase db, int version) {
        if (version < 3){
            // 数据库升级代码
            version = 3;
        }
        if (version < 6){
            // 数据库升级代码
            version = 6;
        }
    }

    @Override
    protected void onDropTable() {
        dropDataTable();
    }

    @Override
    protected String getTableName() {
        return STUDENT_TABLE_NAME;
    }
}
