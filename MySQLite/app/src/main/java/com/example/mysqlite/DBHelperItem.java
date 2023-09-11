package com.example.mysqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperItem extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1; //版本
    public static final String DATABASE_NAME = "item_list.db"; //資料庫名稱
    public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS item_list";

    public DBHelperItem(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void addData(String name,int image){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("吉事漢堡",R.drawable.m_cheeseburger);
        values.put("大麥克",R.drawable.m_bigmac);
        values.put("1955美式培根牛肉堡",R.drawable.m_bacon);
        values.put("雙層四盎司牛肉堡",R.drawable.m_double_four_ounces);
        values.put("麥克雞塊(4塊)",R.drawable.m_nuggets);
        values.put("玉米濃湯",R.drawable.soup);
        values.put("冰炫風",R.drawable.ice);
        return;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_ITEM_SQL =
                "CREATE TABLE " +
                        "item_list (_id INTEGER PRIMARY KEY, title TEXT, price NUMERIC, " +
                        "image BLOB, created_time TIMESTAMP default CURRENT_TIMESTAMP);";
        db.execSQL(CREATE_ITEM_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        final String UPGRADE_ITEM_SQL = "DROP TABLE" + DROP_TABLE_SQL ;
        db.execSQL(DROP_TABLE_SQL);
    }
}
