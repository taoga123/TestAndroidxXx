package com.example.testandroidx;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.service.media.MediaBrowserService;

import java.io.IOException;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MediaButtonReceiver extends BroadcastReceiver {
    MediaPlayer mediaPlayer;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.cancel(1);



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

    }

}
