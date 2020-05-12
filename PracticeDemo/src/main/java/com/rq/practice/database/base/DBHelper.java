package com.rq.practice.database.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.rq.practice.utils.EasyLog;

import java.util.Arrays;
import java.util.List;

/**
 * DBHelper
 * 1.NULL：空值。
 * 2.INTEGER：带符号的整型，具体取决有存入数字的范围大小。
 * 3.REAL：浮点数字，存储为8-byte IEEE浮点数。
 * 4.TEXT：字符串文本。
 * 5.BLOB：二进制对象。
 *
 * @author rock you
 * @version 1.0.0
 */
public abstract class DBHelper extends SQLiteOpenHelper {

    // 数据库名称，自己修改
    private static final String DB_NAME = "practice.db";

    // 数据库版本，自己修改
    private static final int DB_VERSION = 1;

    // SQLiteDatabase
    private SQLiteDatabase mSQLiteDatabase;

    public DBHelper(Context context) {
        this(new DataBaseContext(context), null);
    }


    private DBHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DB_NAME, factory, DB_VERSION);
        // 调用getReadableDatabase或者getWritableDatabase方法才算真正创建表
        // 内部会执行onCreate,onUpgrade,onDowngrade方法
        mSQLiteDatabase = getSQLiteDatabase();
    }

    /**
     * SQLiteOpenHelper的onCreate
     * 通过onCreate返回创建表的SQL语句
     *
     * @param db SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = onCreate();
        if (!TextUtils.isEmpty(sql)) {
            if (db != null && !db.isReadOnly()) {
                db.execSQL(sql);
            } else {
                EasyLog.e("create database fail!");
            }
        } else {
            throw new IllegalArgumentException("sql sentence is null or empty!");
        }
    }

    /**
     * SQLiteOpenHelper的onCreate
     * 通过oldVersion和newVersion实现升级和降级
     *
     * @param db         SQLiteDatabase
     * @param oldVersion 老版本
     * @param newVersion 新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            // 老版本升级为新版本
            onUpgrade(db, oldVersion);
        } else {
            // 低版本覆盖安装高版本
            onDropTable(); // 先摧毁老表
            onCreate(db); // 再创建新表
        }
    }

    /**
     * 创建表结构
     *
     * @return 创建表结构语句
     */
    protected abstract String onCreate();

    /**
     * 更新表结构
     */
    protected abstract void onUpgrade(SQLiteDatabase db, int version);

    /**
     * 摧毁表结构
     */
    protected abstract void onDropTable();

    /**
     * 获取表名
     *
     * @return 表名
     */
    protected abstract String getTableName();

    /**
     * 摧毁表结构
     */
    protected void dropDataTable() {
        dropDataTable(getTableName());
    }

    /**
     * 摧毁表结构
     *
     * @param tableName 表名
     */
    protected void dropDataTable(String tableName) {
        if (mSQLiteDatabase != null && !mSQLiteDatabase.isReadOnly()) {
            mSQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
        }
    }

    /**
     * query语句
     *
     * @param columns       列名
     * @param selection     selection
     * @param selectionArgs selectionArgs
     * @param groupBy       分组
     * @param having        having
     * @param orderBy       排序
     * @param limit         分页
     * @return {@link Cursor}
     */
    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy, String limit) {
        return query(getTableName(), columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    /**
     * query语句
     *
     * @param columns       列名
     * @param selection     selection
     * @param selectionArgs selectionArgs
     * @param groupBy       分组
     * @param having        having
     * @param orderBy       排序
     * @return {@link Cursor}
     */
    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        return query(getTableName(), columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * query语句
     *
     * @param columns       列名
     * @param selection     selection
     * @param selectionArgs selectionArgs
     * @return {@link Cursor}
     */
    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs) {
        return query(getTableName(), columns, selection, selectionArgs);
    }

    /**
     * query语句
     *
     * @param columns       列名
     * @param selection     selection
     * @param selectionArgs selectionArgs
     * @param limit         分页
     * @return {@link Cursor}
     */
    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String limit) {
        return query(getTableName(), columns, selection, selectionArgs, limit);
    }

    /**
     * query语句
     *
     * @param table         表名
     * @param columns       列名
     * @param selection     selection
     * @param selectionArgs selectionArgs
     * @param groupBy       分组
     * @param having        having
     * @param orderBy       排序
     * @param limit         分页
     * @return {@link Cursor}
     */
    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy, String limit) {
        if (mSQLiteDatabase == null) {
            return null;
        }
        return mSQLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    /**
     * query语句
     *
     * @param table         表名
     * @param columns       列名
     * @param selection     selection
     * @param selectionArgs selectionArgs
     * @param groupBy       分组
     * @param having        having
     * @param orderBy       排序
     * @return {@link Cursor}
     */
    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        if (mSQLiteDatabase == null) {
            return null;
        }
        return mSQLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * query语句
     *
     * @param table         表名
     * @param columns       列名
     * @param selection     selection
     * @param selectionArgs selectionArgs
     * @return {@link Cursor}
     */
    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs) {
        return query(table, columns, selection, selectionArgs, null, null, null);
    }

    /**
     * query语句
     *
     * @param table         表名
     * @param columns       列名
     * @param selection     selection
     * @param selectionArgs selectionArgs
     * @param limit         排序
     * @return {@link Cursor}
     */
    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs, String limit) {
        return query(table, columns, selection, selectionArgs, null, null, null, limit);
    }

    /**
     * 执行SQL语句
     *
     * @param sql SQL语句
     */
    public void execSQL(String sql) {
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.execSQL(sql);
        }
    }

    /**
     * 执行SQL语句
     *
     * @param sql      SQL语句
     * @param bindArgs 未知
     */
    public void execSQL(String sql, Object[] bindArgs) {
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.execSQL(sql, bindArgs);
        }
    }

    /**
     * 批量插入
     *
     * @param values 插入的数据
     * @return 数据插入是否成功，-1失败，>0成功，代表插入了多少数据
     */
    public int bulkInsert(ContentValues[] values) {
        return bulkInsert(getTableName(), values);
    }

    /**
     * 批量插入
     *
     * @param table  表名
     * @param values 插入的数据
     * @return 数据插入是否成功，-1失败，>0成功，代表插入了多少数据
     */
    public int bulkInsert(String table, ContentValues[] values) {
        return bulkInsert(table, null, values);
    }

    /**
     * 批量插入
     *
     * @param values 插入的数据
     * @return 数据插入是否成功，-1失败，>0成功，代表插入了多少数据
     */
    public int bulkInsert(List<ContentValues> values) {
        return bulkInsert(getTableName(), values);
    }

    /**
     * 批量插入
     *
     * @param table  表名
     * @param values 插入的数据
     * @return 数据插入是否成功，-1失败，>0成功，代表插入了多少数据
     */
    public int bulkInsert(String table, List<ContentValues> values) {
        return bulkInsert(table, null, values);
    }

    /**
     * 批量插入
     *
     * @param table          表名
     * @param nullColumnHack 字段为空的默认值
     * @param values         插入的数据
     * @return 数据插入是否成功，-1失败，>0成功，代表插入了多少数据
     */
    public int bulkInsert(String table, String nullColumnHack, ContentValues[] values) {
        List<ContentValues> list = Arrays.asList(values);
        return bulkInsert(table, nullColumnHack, list);
    }

    /**
     * 批量插入
     *
     * @param table          表名
     * @param nullColumnHack 字段为空的默认值
     * @param values         插入的数据
     * @return 数据插入是否成功，-1失败，>0成功，代表插入了多少数据
     */
    public int bulkInsert(String table, String nullColumnHack, List<ContentValues> values) {
        if (mSQLiteDatabase == null || mSQLiteDatabase.isReadOnly()) {
            return -1;
        }
        try {
            beginTransaction();
            for (ContentValues contentValue : values) {
                if (mSQLiteDatabase.insert(table, nullColumnHack, contentValue) < 0) {
                    return -1;
                }
            }
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
        return values.size();
    }

    /**
     * 插入
     *
     * @param table          表名
     * @param nullColumnHack 字段为空的默认值
     * @param value          插入的数据
     * @return 数据插入是否成功，-1失败
     */
    public long insert(String table, String nullColumnHack, ContentValues value) {
        if (mSQLiteDatabase == null || mSQLiteDatabase.isReadOnly()) {
            return -1;
        }
        return mSQLiteDatabase.insert(table, nullColumnHack, value);
    }

    /**
     * 插入
     *
     * @param table 表名
     * @param value 插入的数据
     * @return 数据插入是否成功，-1失败
     */
    public long insert(String table, ContentValues value) {
        return insert(table, null, value);
    }

    /**
     * 插入
     *
     * @param value 插入的数据
     * @return 数据插入是否成功，-1失败
     */
    public long insert(ContentValues value) {
        return insert(getTableName(), value);
    }

    /**
     * 更新
     *
     * @param table       表名
     * @param values      更新的数据
     * @param whereClause whereClause
     * @param whereArgs   whereArgs
     * @return
     */
    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        if (mSQLiteDatabase == null || mSQLiteDatabase.isReadOnly()) {
            return -1;
        }
        return mSQLiteDatabase.update(table, values, whereClause, whereArgs);
    }

    /**
     * 更新
     *
     * @param values      更新的数据
     * @param whereClause whereClause
     * @param whereArgs   whereArgs
     * @return
     */
    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return update(getTableName(), values, whereClause, whereArgs);
    }

    /**
     * 删除
     *
     * @param table
     * @param whereClause
     * @param whereArgs
     * @return
     */
    public int delete(String table, String whereClause, String[] whereArgs) {
        if (mSQLiteDatabase == null || mSQLiteDatabase.isReadOnly()) {
            return -1;
        }
        return mSQLiteDatabase.delete(table, whereClause, whereArgs);
    }

    /**
     * 删除
     *
     * @param whereClause
     * @param whereArgs
     * @return
     */
    public int delete(String whereClause, String[] whereArgs) {
        return delete(getTableName(), whereClause, whereArgs);
    }

    public boolean isOpen() {
        if (mSQLiteDatabase == null) {
            return false;
        }
        return mSQLiteDatabase.isOpen();
    }

    public boolean isReadOnly() {
        if (mSQLiteDatabase == null) {
            return false;
        }
        return mSQLiteDatabase.isReadOnly();
    }

    /**
     * 使用完关闭数据库
     */
    public void close() {
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.close();
        }
    }

    /**
     * 开始一个事物
     * 以后执行SQL
     */
    public void beginTransaction() {
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.beginTransaction();
        }
    }

    /**
     * 结束一个事物
     * 释放，Close之前
     */
    public void endTransaction() {
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.endTransaction();
        }
    }

    /**
     * 执行一个事物
     * 之前执行所有的SQL
     */
    public void setTransactionSuccessful() {
        if (mSQLiteDatabase != null) {
            mSQLiteDatabase.setTransactionSuccessful();
        }
    }

    /**
     * 获取SQLiteDatabase
     *
     * @return SQLiteDatabase
     */
    private SQLiteDatabase getSQLiteDatabase() {
        return getReadableDatabase();
    }
}
