package com.example.felixidan.session6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToNotificationsDemo(View view) {
        goToActivity(NotificationsDemoActivity.class);
    }

    public void goToAlarmsDemo(View view) {
        goToActivity(AlarmDemoActivity.class);
    }

    public void goToQuotesList(View view) {
        goToActivity(QuotesListActivity.class);
    }

    public void goToBroadcastsDemo(View view) {
        goToActivity(BroadcastsDemoActivity.class);
    }

    private void goToActivity(Class<?> target){
        Intent i = new Intent(this, target);
        startActivity(i);
    }

    public void goToServiceDemo(View view) {
        goToActivity(ServiceDemoActivity.class);
    }

    public void goToSyncAdapterDemo(View view) {
        goToActivity(SyncAdapterDemoActivity.class);
    }
}
