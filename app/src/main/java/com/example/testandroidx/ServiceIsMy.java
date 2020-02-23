package com.example.testandroidx;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;



import static com.example.testandroidx.App.CHANNEL_1_ID;


public class ServiceIsMy extends Service {

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intentnoti = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentnoti, 0);

        Intent intent1 = new Intent(this,MediaButtonReceiver.class);


        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(this,0,intent1,0);




        Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)

                .setContentTitle("android")

                .setWhen(0)
                .setShowWhen(true)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setSmallIcon(R.drawable.ic_exposure_plus_1_black_24dp)

                .addAction(R.drawable.ic_play_circle_outline_black_24dp,"play",pendingIntent)
                .addAction(R.drawable.ic_play_circle_outline_black_24dp,"play",pendingIntent)
                .addAction(R.drawable.ic_play_circle_outline_black_24dp,"play",pendingIntent)
                .addAction(R.drawable.ic_close_black_24dp,"close",pendingIntent1)

                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(1,2)
                        .setShowCancelButton(true)


                )
                .setPriority(Notification.PRIORITY_MAX)

                .setContentIntent(pendingIntent)


                .build();

        startForeground(1, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
