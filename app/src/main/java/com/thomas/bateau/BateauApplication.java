package com.thomas.bateau;

import android.app.ActivityManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import java.util.Objects;

public class BateauApplication extends Application {

    public static final String CHANNEL_1_ID="channel low";
    public static final String CHANNEL_2_ID="channel default";
    public static final String CHANNEL_3_ID="channel high";
    public static NotificationManager notificationManager;
    public static TypeUtilisateurs typeUtilisateurs=TypeUtilisateurs.PECHEUR;
    public static MainActivity mainActivityInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        /*if(!isMyServiceRunning(EvenementNotificationService.class)) {
            Intent startServiceIntent = new Intent(getApplicationContext(), EvenementNotificationService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                getApplicationContext().startForegroundService(startServiceIntent);
            } else {
                getApplicationContext().startService(startServiceIntent);
            }
        }*/
    }

}
