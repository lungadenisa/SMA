package com.example.myapplication.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;

import java.net.URL;

public class MainIntentActivity extends AppCompatActivity {

    private Button buttonGoogleMSA;
    private Button buttonGoogleView;
    private Button buttonTelMSA;
    private Button buttonTelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intent);

        Button buttonGoogleMSA = (Button) findViewById(R.id.buttonGoogleMSA);
        Button buttonGoogleView = (Button) findViewById(R.id.buttonGoogleView);
        Button buttonTelMSA = (Button) findViewById(R.id.buttonTelMSA);
        Button buttonTelView = (Button) findViewById(R.id.buttonTelView);

        buttonGoogleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent view1 = new Intent();
                String url = "http://www.google.com";
                view1.setAction(Intent.ACTION_VIEW);
                view1.setData(Uri.parse(url));
                startActivity(view1);
            }
        });

        buttonTelView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent view2 = new Intent();
                String number = "00401213456";
                view2.setAction(Intent.ACTION_VIEW);
                view2.setData(Uri.parse("tel:"+number));
                startActivity(view2);
            }
        });

        buttonGoogleMSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent msa1 = new Intent();
                String url = "http://www.google.com";
                msa1.setAction(Intent.CATEGORY_LAUNCHER);
                msa1.setData(Uri.parse(url));
                msa1.setClass(getApplicationContext(),IntentFilterActivity.class);
                startActivity(msa1);
            }
        });

        buttonTelMSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent msa2 = new Intent();
                String number = "00401213456";
                msa2.setAction(Intent.CATEGORY_LAUNCHER);
                msa2.setData(Uri.parse("tel:"+number));
                msa2.setClass(getApplicationContext(),IntentFilterActivity.class);
                startActivity(msa2);
            }
        });
    }
}