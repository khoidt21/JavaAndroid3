package com.example.myapplication3.activity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import com.example.myapplication3.R;
import android.content.Intent;

public class Music extends Service {

    MediaPlayer mediaPlayer;

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            mediaPlayer = MediaPlayer.create(this,R.raw.song4);
            mediaPlayer.start();
            return START_NOT_STICKY;

    }

}
