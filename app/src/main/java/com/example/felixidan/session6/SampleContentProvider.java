package com.example.felixidan.session6;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class SampleContentProvider extends ContentProvider {
    private SampleDatabaseOpenHelper dbHelper;

    public SampleContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String getType(Uri uri) {
        return "vnd.android.cursor.dir/vnd.com.felixidan.example.session6.syncadapter.provider.synctimes";
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long newId = db.insert(SampleDatabaseContract.DB.TABLE_NAME, null, values);

        Uri itemUri = uri.buildUpon().appendPath(Long.toString(newId)).build();
        getContext().getContentResolver().notifyChange(itemUri, null);
        return itemUri;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new SampleDatabaseOpenHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor query = db.query(SampleDatabaseContract.DB.TABLE_NAME, null, null, null, null, null, null);
        return query;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
