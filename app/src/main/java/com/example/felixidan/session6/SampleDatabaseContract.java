package com.example.felixidan.session6;

import android.net.Uri;
import android.provider.BaseColumns;

public class SampleDatabaseContract {

    public static final String AUTHORITY = "com.felixidan.example.session6.syncadapter.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class DB implements BaseColumns {
        public static final String DATABASE_NAME = "synctimes.db";
        public static final String TABLE_NAME = "demo";
        public static final String COLUMN_SYNC_TIMESTAMP = "sync_timestamp";
    }



}
