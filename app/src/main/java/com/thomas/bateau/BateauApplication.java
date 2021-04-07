package com.thomas.bateau;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.util.Objects;

public class BateauApplication extends Application {

    public static final String CHANNEL_1_ID="channel low";
    public static final String CHANNEL_2_ID="channel default";
    public static final String CHANNEL_3_ID="channel high";
    private static NotificationManager notificationManager;
    public static TypeUtilisateurs typeUtilisateurs=TypeUtilisateurs.PECHEUR;
    public static MainActivity mainActivityInstance;

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1=new NotificationChannel(CHANNEL_1_ID, "channel 1", NotificationManager.IMPORTANCE_LOW);
            NotificationChannel channel2=new NotificationChannel(CHANNEL_2_ID, "channel 2", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel channel3=new NotificationChannel(CHANNEL_3_ID, "channel 3", NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("Low priority");
            channel2.setDescription("Default priority");
            channel3.setDescription("High priority");
            notificationManager=getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel1);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel2);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel3);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }
}
