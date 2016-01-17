package com.example.felixidan.session6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SampleDatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE = "CREATE TABLE " + SampleDatabaseContract.DB.TABLE_NAME + " ( " +
                                                SampleDatabaseContract.DB._ID + " INTEGER PRIMARY KEY, " +
                                                SampleDatabaseContract.DB.COLUMN_SYNC_TIMESTAMP + " text " +
                                             " ) ";
    public static final int DATABASE_VERSION = 1;

    public SampleDatabaseOpenHelper(Context context) {
        super(context, SampleDatabaseContract.DB.DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + SampleDatabaseContract.DB.TABLE_NAME);
        onCreate(db);
    }
}
