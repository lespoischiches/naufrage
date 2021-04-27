package com.thomas.bateau;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.thomas.bateau.evenements.EvenementNotificationService;

public class BootDeviceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("BAT", "START RECEIVER");
        Intent startServiceIntent = new Intent(context, EvenementNotificationService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(startServiceIntent);
        } else {
            context.startService(startServiceIntent);
        }
    }
}


// https://www.dev2qa.com/how-to-start-android-service-automatically-at-boot-time/