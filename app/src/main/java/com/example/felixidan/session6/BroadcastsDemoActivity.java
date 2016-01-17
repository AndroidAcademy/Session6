package com.example.felixidan.session6;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class BroadcastsDemoActivity extends ActionBarActivity {

    private static final String ACTION_CUSTOM = "session6.action.toast.in.activity";
    private DemoBroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcasts_demo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = IntentFilter.create(ACTION_CUSTOM, "*/*");
        broadcastReceiver = new DemoBroadcastReceiver();
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    public void sendBroadcast(View view) {
        Intent i = new Intent(DemoBroadcastReceiver.ACTION_TOAST);
        sendBroadcast(i);
    }

    public void sendBroadcastToActivity(View view) {
        Intent i = new Intent(ACTION_CUSTOM);
        i.setType("*/*");
        sendBroadcast(i);
    }
}
