package com.example.felixidan.session6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class SpecialQuoteActivity extends ActionBarActivity {
    private static final String EXTRA_QUOTE_ID = "quote.id";

    public static Intent openQuote(Context c, int id){
        Intent i = new Intent(c, SpecialQuoteActivity.class);
        i.putExtra(EXTRA_QUOTE_ID, id);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_quote);

        TextView tv = (TextView) findViewById(R.id.quote_textview);

        Intent openingIntent = getIntent();

        int quoteId = openingIntent.getIntExtra(EXTRA_QUOTE_ID, -1);

        if (quoteId == -1) {
            return;
        }

        String[] quotes = getResources().getStringArray(R.array.quotes);
        String quote = quotes[quoteId];
        String extendedQuote = quote.replace("—", "\n\n—");

        tv.setText(extendedQuote);
    }
}
