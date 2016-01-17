package com.example.felixidan.session6;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class DemoSyncAdapterService extends Service {

    private static DemoSyncAdapter syncAdapter;
    private final static Object locket = new Object();

    @Override
    public void onCreate() {
        super.onCreate();

        if (syncAdapter == null){
            synchronized (locket){
                if (syncAdapter == null){
                    syncAdapter = new DemoSyncAdapter(getApplicationContext(), true);
                }
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return syncAdapter.getSyncAdapterBinder();
    }
}
