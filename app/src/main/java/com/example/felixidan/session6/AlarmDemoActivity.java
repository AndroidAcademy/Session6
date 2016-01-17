package com.example.felixidan.session6;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AlarmDemoActivity extends ActionBarActivity {

    public static final String ACTION_BROADCAST = "com.felixidan.example.broadcast.alarm";
    private TextView display;
    private AlarmManager alarmMgr;
    private AlarmBroadcastReciever receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_demo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // ...

        display = (TextView)findViewById(R.id.alarm_display_textview);
        alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = IntentFilter.create(ACTION_BROADCAST, "*/*");
        receiver = new AlarmBroadcastReciever();

        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    private PendingIntent createIntent(){
        Intent i = new Intent(ACTION_BROADCAST);
        i.setType("*/*");

        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        return pi;
    }

    public void doFiveSecondsInterval(View view) {
        display.setText("Ping");

        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                     SystemClock.elapsedRealtime() + 30 * 1000,
                     createIntent());
    }

    private class AlarmBroadcastReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            display.setText("Pong");
        }
    }
}
