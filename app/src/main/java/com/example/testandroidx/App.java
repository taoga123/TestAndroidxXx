package com.example.testandroidx;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.widget.Button;

public class App extends Application {

    public static final String CHANNEL_1_ID = "1";
    String CHANNEL_2_ID;
    ServiceConnection serviceConnection;
    boolean isServiceBound;
    BoundService boundService;


    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChanel();


    }


    private void createNotificationChanel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Chanel 1",
                    NotificationManager.IMPORTANCE_HIGH

            );
            channel1.setDescription("This is my chanel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Chanel 1",
                    NotificationManager.IMPORTANCE_LOW
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);



//            manager.createNotificationChannel(channel2);

        }
    }



}
