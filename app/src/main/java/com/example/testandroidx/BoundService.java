package com.example.testandroidx;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

import androidx.annotation.Nullable;


public class BoundService extends Service {
    MediaPlayer mediaPlayer;
    MyServiceBinder iBinder = new MyServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e( "onBind: ", "bind");

        return iBinder;
    }

    class MyServiceBinder extends Binder{
        public BoundService getService(){

            return BoundService.this;
        }
    }


    @Override
    public void onRebind(Intent intent) {
        Log.e( "onRebind: ", "rebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e( "onUnbind: ", "unbind");
        return super.onUnbind(intent);
    }



    public void music()
    {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC); // play music online
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/mymuysick.appspot.com/o/Ketsuraku-Automation-ONE-OK-ROCK.mp3?alt=media&token=05c59590-3f5e-4de1-910b-ca2b91f8e006");

        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        mediaPlayer.prepareAsync();
        Log.e( "music: ", "chạy ngay đi");
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
