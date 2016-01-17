package com.example.felixidan.session6;

import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SyncAdapterDemoActivity extends ActionBarActivity
                                     implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final String ACCOUNT = "stub.account";
    public static final String ACCOUNT_TYPE = "example.felixidan.com";

    private static final int LOADER_ID = 123;
    SimpleCursorAdapter adapter;
    private SyncCompletedBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_adapter_demo);

        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null,
                                          new String[]{
                                                SampleDatabaseContract.DB._ID,
                                                SampleDatabaseContract.DB.COLUMN_SYNC_TIMESTAMP
                                          },
                                          new int[]{
                                                android.R.id.text2,
                                                android.R.id.text1
                                          }, 0);

        ListView listView = (ListView)findViewById(R.id.syncadapter_listview);
        listView.setAdapter(adapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = IntentFilter.create(DemoSyncAdapter.ACTION_BROADCAST_SYNC_COMPLETED, "*/*");
        receiver = new SyncCompletedBroadcastReceiver();
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, SampleDatabaseContract.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    public void syncNow(View view) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);

        getContentResolver().requestSync(Session6Application.getAccount(), SampleDatabaseContract.AUTHORITY, bundle);
    }

    public class SyncCompletedBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            getLoaderManager().restartLoader(LOADER_ID, null, SyncAdapterDemoActivity.this);
        }
    }
}
