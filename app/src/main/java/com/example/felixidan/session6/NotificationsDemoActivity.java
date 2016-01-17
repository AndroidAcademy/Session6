package com.example.felixidan.session6;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import java.util.Random;

public class NotificationsDemoActivity extends ActionBarActivity {

    private static final int NOTIFICATION_ID_QUOTE = 123;
    private static final int NOTIFICATION_ID_NORMAL_ACTIVITY_QUOTE = 456;
    private static final int NOTIFICATION_ID_SPECIAL_ACTIVITY_QUOTE = 789;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_demo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void showSimpleNotification(View view) {

        String[] quotes = this.getResources().getStringArray(R.array.quotes);

        Random r = new Random();
        int quoteIndex = r.nextInt(quotes.length);

        Intent i = QuoteActivity.openQuote(this, quoteIndex);

        // DON'T DO THIS, THIS IS WRONG.
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification n = new NotificationCompat.Builder(this)
                                               .setContentTitle("Android Academy TLV")
                                               .setContentText(quotes[quoteIndex])
                                               .setSmallIcon(R.drawable.ic_androidacademy_notification)
                                               .setContentIntent(pi)
                                               .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID_QUOTE, n);
    }

    public void showNormalActivityNotification(View view) {
        String[] quotes = this.getResources().getStringArray(R.array.quotes);

        Random r = new Random();
        int quoteIndex = r.nextInt(quotes.length);

        Intent i = QuoteActivity.openQuote(this, quoteIndex);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addParentStack(QuoteActivity.class);
        taskStackBuilder.addNextIntent(i);
        PendingIntent pi = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification n = new NotificationCompat.Builder(this)
                .setContentTitle("Android Academy TLV")
                .setContentText(quotes[quoteIndex])
                .setSmallIcon(R.drawable.ic_androidacademy_notification)
                .setContentIntent(pi)
                .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID_NORMAL_ACTIVITY_QUOTE, n);
    }

    public void showSpecialActivityNotification(View view) {
        String[] quotes = this.getResources().getStringArray(R.array.quotes);

        Random r = new Random();
        int quoteIndex = r.nextInt(quotes.length);

        Intent i = SpecialQuoteActivity.openQuote(this, quoteIndex);

        // This is OK, because it's a special activity.
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification n = new NotificationCompat.Builder(this)
                .setContentTitle("Android Academy TLV")
                .setContentText(quotes[quoteIndex])
                .setSmallIcon(R.drawable.ic_androidacademy_notification)
                .setContentIntent(pi)
                .build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID_SPECIAL_ACTIVITY_QUOTE, n);    }
}
