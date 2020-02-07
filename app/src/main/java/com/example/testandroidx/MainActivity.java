package com.example.testandroidx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import static com.example.testandroidx.App.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    Button button,button2,button3;
    EditText edit1,edit2;
    MediaPlayer mediaPlayer;

    NotificationManagerCompat notificationCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = findViewById(R.id.editText);
        edit2 = findViewById(R.id.editText2);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        firebaseAuth = FirebaseAuth.getInstance();

        notificationCompat = NotificationManagerCompat.from(this);

        Intent broadIntent = new Intent(this, NotificationManager.class);
        broadIntent.putExtra("toastMessage",broadIntent);


        Intent intentnoti = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentnoti, 0);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String email = edit1.getText().toString();
//                String password = edit2.getText().toString();
//                if(email == null )
//                {
//                    getCurrentFocus();
//                }
//                else if(password == null )
//                {
//                    getCurrentFocus();
//                }
//                firebaseAuth.createUserWithEmailAndPassword(email,password);

            }
        });

        final Intent intent = new Intent(this, ServiceIsMy.class);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
    }


}
