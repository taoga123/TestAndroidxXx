package com.example.testandroidx;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    Button button;

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String message = intent.getStringExtra("toastMessage");

    }
}
