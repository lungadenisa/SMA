package com.example.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_ID = "CHANNEL_ID";
    public static int notification = 0;
    private PowerConnectionReceiver powerReceiver = new PowerConnectionReceiver();
    IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onResume(){
        super.onResume();
        registerReceiver(powerReceiver, filter);
    }
    @Override
    protected void onPause(){
        unregisterReceiver(powerReceiver);
        super.onPause();
    }
}
