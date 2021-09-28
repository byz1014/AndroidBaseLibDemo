package com.luculent.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * BaseDbHelperUtil
 *
 * @blame Android Team
 */
public class BaseDbHelperUtil {
    SQLiteOpenHelper mSqLiteOpenHelper;
    SQLiteDatabase mSqLiteDatabase;
    String tableName;

    public BaseDbHelperUtil(SQLiteOpenHelper mSqLiteOpenHelper, String tableName) {
        this.mSqLiteOpenHelper = mSqLiteOpenHelper;
        this.tableName = tableName;
    }

    public int delete(String whereClause, String[] whereArgs) {
        mSqLiteDatabase = mSqLiteOpenHelper.getWritableDatabase();
        int index = mSqLiteDatabase.delete(tableName, whereClause, whereArgs);
        mSqLiteDatabase.close();
        return index;
    }

    public long insert(ContentValues cValue) {
        mSqLiteDatabase = mSqLiteOpenHelper.getWritableDatabase();
        long index = mSqLiteDatabase.insert(tableName, null, cValue);
        mSqLiteDatabase.close();
        return index;
    }

    public int update(ContentValues cValue, String whereClause, String[] whereArgs) {
        mSqLiteDatabase = mSqLiteOpenHelper.getWritableDatabase();
        int index = mSqLiteDatabase.update(tableName, cValue, whereClause, whereArgs);
        mSqLiteDatabase.close();
        return index;
    }

    public void select(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy,SelectCallBacllListener selectCallBacllListener) {
        mSqLiteDatabase = mSqLiteOpenHelper.getReadableDatabase();
        Cursor mCursor = mSqLiteDatabase.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy);
        selectCallBacllListener.onCallBack(mCursor);
       mCursor.close();
       mSqLiteDatabase.close();
    }

    public void selectAll(SelectCallBacllListener selectCallBacllListener){
        mSqLiteDatabase = mSqLiteOpenHelper.getReadableDatabase();
        Cursor mCursor = mSqLiteDatabase.query(tableName,null,null,null,null,null,null);
        selectCallBacllListener.onCallBack(mCursor);
        mCursor.close();
        mSqLiteDatabase.close();
    }

    public interface SelectCallBacllListener{
        void onCallBack(Cursor mCursor);
    }

}
