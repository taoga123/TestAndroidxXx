package com.example.testandroidx;

import android.app.Application;
import android.app.Notification;
import android.app.PendingIntent;
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
import androidx.core.app.NotificationCompat;

import static com.example.testandroidx.App.CHANNEL_1_ID;


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
            notifiCation();

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


    /** method for clients */
    public void reponse(){

        saysomethings  = "thanhcong";

    }
    //    public interface ServiceCallbacks {
//        void reponse();
//    }



    public void notifiCation()
    {
        Intent playintent = new Intent(this, MainActivity.class);
        playintent.setAction(Constants.ACTION.PLAY_ACTION);

        Intent closeintent = new Intent(this,BoundService.class);
        closeintent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);


        PendingIntent pendingIntent = PendingIntent.getService(this, 0, playintent, 0);

        PendingIntent pendingIntent1 = PendingIntent.getService(this,0, closeintent, 0);

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)

                .setContentTitle("android")

                .setSmallIcon(R.drawable.ic_exposure_plus_1_black_24dp)

                .addAction(R.drawable.ic_play_circle_outline_black_24dp,"play",pendingIntent1)
                .addAction(R.drawable.ic_play_circle_outline_black_24dp,"next",pendingIntent)
                .addAction(R.drawable.ic_play_circle_outline_black_24dp,"preview",pendingIntent)
                .addAction(R.drawable.ic_close_black_24dp,"close",pendingIntent1)

                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1,2)
                        .setShowCancelButton(true)

                )
                .setPriority(Notification.PRIORITY_MAX)

                .setContentIntent(pendingIntent)

                .build();
        music();
        startForeground(1, notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {

            Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();

        }

        else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
            Toast.makeText(this, "Clicked Previous", Toast.LENGTH_SHORT).show();
            Log.e("NS", "Clicked Previous");

        }

        else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
            if (mediaPlayer.isPlaying())
            {
                mediaPlayer.stop();

            }

            Log.e("NS", "Clicked Play");
        }

        else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {

            Toast.makeText(this, "Clicked Next", Toast.LENGTH_SHORT).show();
            Log.e("NS", "Clicked Next");

        }

        else if (intent.getAction().equals(
                Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.e("NS", "Received Stop Foreground Intent");
            Toast.makeText(this, "Service Stoped", Toast.LENGTH_SHORT).show();
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;

    }


    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
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
}
