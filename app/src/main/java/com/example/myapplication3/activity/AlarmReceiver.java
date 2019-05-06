package com.example.myapplication3.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Toi trong receiver","Xin chao");
        Intent myIntent = new Intent(context,Music.class);
        context.startService(intent);
    }

}
