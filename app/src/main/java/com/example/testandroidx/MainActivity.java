package com.example.testandroidx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static com.example.testandroidx.App.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity  {
    FirebaseAuth firebaseAuth;

    Button button,button2,button3,button4,button5,button6;
    EditText edit1,edit2;

    NotificationManagerCompat notificationCompat;

    ServiceConnection serviceConnection;
    boolean isServiceBound;
    BoundService boundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.editText);
        edit2 = findViewById(R.id.editText2);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        firebaseAuth = FirebaseAuth.getInstance();

        notificationCompat = NotificationManagerCompat.from(this);

        Intent broadIntent = new Intent(this, NotificationManager.class);
        broadIntent.putExtra("toastMessage",broadIntent);


        Intent intentnoti = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentnoti, 0);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edit1.getText().toString();
                String password = edit2.getText().toString();
                if(email == null )
                {
                    getCurrentFocus();
                }
                else if(password == null )
                {
                    getCurrentFocus();
                }
                firebaseAuth.createUserWithEmailAndPassword(email,password);

            }
        });


        final Intent intent = new Intent(this, BoundService.class);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BindService();

            }


        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnBindService();

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isServiceBound)
                {
                    if (boundService.mediaPlayer.isPlaying())
                    {

                        boundService.mediaPlayer.stop();
                    }
                    else
                    {
                        boundService.mediaPlayer.prepareAsync();
                    }

                }
            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isServiceBound)
                {
                    if (boundService.mediaPlayer.isPlaying())
                    {
                        boundService.mediaPlayer.stop();
                        Intent intent1 = getIntent();
                        String saysomethings = intent1.getStringExtra("1");
                        Toast.makeText(MainActivity.this,saysomethings,Toast.LENGTH_LONG).show();

                    }
                    Log.e( "onClick: ", "chạy đi đâu" );
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isServiceBound)
                {
                    if (boundService.mediaPlayer.isPlaying())
                    {
                        boundService.reponse();

                        button6.setText(boundService.saysomethings);

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"ko hien gi het",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }


    private void BindService() {
        if(serviceConnection == null)
        {
            serviceConnection = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    BoundService.MyServiceBinder myServiceBinder =(BoundService.MyServiceBinder)service;
                    boundService = myServiceBinder.getService();
                    isServiceBound = true;
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    isServiceBound = false;
                }
            };
        }
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);

    }

    private void UnBindService(){
        if (isServiceBound)
        {
            unbindService(serviceConnection);
            isServiceBound=false;
        }

    }

//    @Override
//    public void reponse()
//    {
//        if (boundService.mediaPlayer.isPlaying())
//        {
//            Intent intent = new Intent(this,Main2Activity.class);
//            startActivity(intent);
//        }
//        Log.e("reponse","chay di");
//    }
}
