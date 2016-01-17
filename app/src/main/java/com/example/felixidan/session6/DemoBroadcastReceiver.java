package com.example.felixidan.session6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DemoBroadcastReceiver
        extends BroadcastReceiver {

    public static final String ACTION_TOAST = "session6.action.toast";

    @Override
    public void onReceive(Context context, Intent intent){
        String message = "Got a " + intent.getAction();
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}