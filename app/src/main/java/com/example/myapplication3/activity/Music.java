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
        try{
        int id = intent.getIntExtra("keyIDSong",0);
        mediaPlayer = MediaPlayer.create(this,R.raw.song4);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stop();
                }
            });
            mediaPlayer.start();
        return START_NOT_STICKY;
        }catch (Exception ex){
            System.out.println(ex.getStackTrace());
        }
        return START_NOT_STICKY;
    }

}
