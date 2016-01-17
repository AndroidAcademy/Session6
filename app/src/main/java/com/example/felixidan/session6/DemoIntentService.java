package com.example.felixidan.session6;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

public class DemoIntentService extends IntentService {

    public static final String ACTION_COUNT_BROADCAST = "com.example.felixidan.session6.action.broadcast.count";
    public static final String EXTRA_COUNT_BROADVAST = "count";
    private static final String ACTION_COUNT = "com.example.felixidan.session6.action.count";
    public static final int ITERATIONS = 10;
    public static final int WAIT_TIME_BETWEEN_ITERATIONS = 1000;
    private int counter = 0;

    public DemoIntentService() {
        super("DemoIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        counter = 0;
        broadcastCount();
    }

    private void broadcastCount() {
        Intent broadcast = new Intent(ACTION_COUNT_BROADCAST);
        broadcast.setType("*/*");
        broadcast.putExtra(EXTRA_COUNT_BROADVAST, counter);
        sendBroadcast(broadcast);
    }

    public static void startCounting(Context context) {
        Intent intent = new Intent(context, DemoIntentService.class);
        intent.setAction(ACTION_COUNT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < ITERATIONS; i++){
            try {
                Thread.sleep(WAIT_TIME_BETWEEN_ITERATIONS);
            } catch (InterruptedException e) {

            }

            counter++;
            broadcastCount();
        }
    }
}
