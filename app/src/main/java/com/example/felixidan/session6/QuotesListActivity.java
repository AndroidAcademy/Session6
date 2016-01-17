package com.example.felixidan.session6;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class QuotesListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_list);

        ListView quotesLV = (ListView)findViewById(R.id.quotes_listview);

        String[] quotesArray = getResources().getStringArray(R.array.quotes);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotesArray);
        quotesLV.setAdapter(adapter);

        quotesLV.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = QuoteActivity.openQuote(this, position);
        startActivity(i);
    }
}
