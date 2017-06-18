package com.widyatama.aseplulu.aseplulu_volly.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by Asep Lulu A H on 18/06/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "DbUser.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME + " TEXT," +
                    FeedEntry.COLUMN_USERNAME + " TEXT," +
                    FeedEntry.COLUMN_EMAIL + " TEXT," +
                    FeedEntry.COLUMN_WEBSITE + " TEXT," +
                    FeedEntry.COLUMN_PHONE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    private static final String SQL_SELECT_ALL =  "SELECT * FROM " + FeedEntry.TABLE_NAME;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);

    }

    public void insertToDb(String name, String username, String email,String website,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME, name);
        values.put(FeedEntry.COLUMN_USERNAME, username);
        values.put(FeedEntry.COLUMN_EMAIL, email);
        values.put(FeedEntry.COLUMN_WEBSITE, website);
        values.put(FeedEntry.COLUMN_PHONE, phone);
        long newRowId = db.insert(FeedEntry.TABLE_NAME, null, values);
    }

    public void selectAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SQL_SELECT_ALL,null);

        if (cursor.moveToFirst()) {
            do{
                Log.d(TAG, cursor.getString(0));
                Log.d(TAG, cursor.getString(1));
                Log.d(TAG, cursor.getString(2));
                Log.d(TAG, cursor.getString(3));
                Log.d(TAG, cursor.getString(4));
                Log.d(TAG, cursor.getString(5));
            }while(cursor.moveToNext());
        }

    }
}
