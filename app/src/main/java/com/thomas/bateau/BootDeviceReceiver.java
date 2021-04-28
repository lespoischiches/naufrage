package com.thomas.bateau;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.thomas.bateau.evenements.EvenementNotificationService;

public class BootDeviceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        EvenementNotificationService.scheduleJob(context);
    }
}


// https://www.dev2qa.com/how-to-start-android-service-automatically-at-boot-time/