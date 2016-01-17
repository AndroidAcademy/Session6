package com.example.felixidan.session6;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoSyncAdapter extends AbstractThreadedSyncAdapter {

    ContentResolver resolver;
    public static final String ACTION_BROADCAST_SYNC_COMPLETED = "com.felixidan.example.action.sync.completed.broadcast";

    public DemoSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        resolver = context.getContentResolver();
    }

    public DemoSyncAdapter(Context context, boolean autoInitialize, boolean allowParallelSyncs) {
        super(context, autoInitialize, allowParallelSyncs);
        resolver = context.getContentResolver();
    }

    @Override
    public void onPerformSync(Account account,
                              Bundle extras,
                              String authority,
                              ContentProviderClient provider,
                              SyncResult syncResult) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(SampleDatabaseContract.DB.COLUMN_SYNC_TIMESTAMP, getCurrentTimeStamp());
        resolver.insert(SampleDatabaseContract.CONTENT_URI, initialValues);
        sendSyncedBroadcast();
}

    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    private void sendSyncedBroadcast() {
        Intent intent = new Intent(ACTION_BROADCAST_SYNC_COMPLETED);
        intent.setType("*/*");
        getContext().sendBroadcast(intent);
    }

}
