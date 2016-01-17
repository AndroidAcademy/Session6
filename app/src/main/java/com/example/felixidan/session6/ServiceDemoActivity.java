package com.example.felixidan.session6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class ServiceDemoActivity extends ActionBarActivity {

    private BroadcastReceiver receiver;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);

        display = (TextView)findViewById(R.id.servicedemo_display);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter filter = IntentFilter.create(DemoIntentService.ACTION_COUNT_BROADCAST, "*/*");
        receiver = new ListenToCountsBroadcastReceiver();

        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void startCounting(View view) {
        DemoIntentService.startCounting(this);
    }

    public class ListenToCountsBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int count = intent.getIntExtra(DemoIntentService.EXTRA_COUNT_BROADVAST, -1);
            display.setText(Integer.toString(count));
        }
    }
}
