package com.example.myapplication.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;

public class IntentFilterActivity extends AppCompatActivity {
    private TextView tView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_filter);

        TextView tView = (TextView) findViewById(R.id.tView);
        Uri url = getIntent().getData();
        tView.setText(url.toString());
    }
}