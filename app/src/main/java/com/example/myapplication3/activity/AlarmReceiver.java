package com.example.myapplication3.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Boolean check = intent.getExtras().getBoolean("music_flag");
        if(check==true){
            Intent intentMusic = new Intent(context,Music.class);
            context.startService(intentMusic);
        }else{
            Intent intentMusic = new Intent(context,Music.class);
            context.stopService(intentMusic);
        }

        Intent myIntent = new Intent(context,Music.class);
        context.startService(intent);
    }

}
