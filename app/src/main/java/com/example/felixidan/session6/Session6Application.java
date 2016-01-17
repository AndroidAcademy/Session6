package com.example.felixidan.session6;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;

public class Session6Application extends Application {
    private static Account account;

    public static Account getAccount(){
        return account;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        createFakeAccountIfNeeded();
    }

    private void createFakeAccountIfNeeded() {
        account = new Account(SyncAdapterDemoActivity.ACCOUNT, SyncAdapterDemoActivity.ACCOUNT_TYPE);
        AccountManager accountManager =
                (AccountManager) getSystemService(ACCOUNT_SERVICE);
        accountManager.addAccountExplicitly(account, null, null);


    }

}
