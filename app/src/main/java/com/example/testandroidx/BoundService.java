package com.example.testandroidx;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class BoundService extends Service {
    MediaPlayer mediaPlayer;
    MyServiceBinder iBinder = new MyServiceBinder();
    MainActivity mainActivity;
    String saysomethings;
//    ServiceCallbacks serviceCallbacks;



    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e( "onBind: ", "bind");

//        if (serviceCallbacks != null) {
//            serviceCallbacks.reponse();
//        }

        return iBinder;
    }



    class MyServiceBinder extends Binder{
        public BoundService getService(){
            music();

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

//    public interface ServiceCallbacks {
//        void reponse();
//    }

    /** method for clients */
    public void reponse(){

        saysomethings  = "thanhcong";

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

        Log.e( "music: ", "chạy ngay đi");

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
