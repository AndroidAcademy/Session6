package com.example.felixidan.session6;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class FakeAuthenticatorService extends Service {

    private FakeAuthenticator authenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        authenticator = new FakeAuthenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return authenticator.getIBinder();
    }
}
