package com.thomas.bateau;

import android.app.ActivityManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import com.thomas.bateau.evenements.EvenementNotificationService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Objects;

public class BateauApplication extends Application {

    public static final String CHANNEL_1_ID="channel low";
    public static final String CHANNEL_2_ID="channel default";
    public static final String CHANNEL_3_ID="channel high";
    public static NotificationManager notificationManager;
    public static TypeUtilisateurs typeUtilisateurs=TypeUtilisateurs.PECHEUR;
    public static MainActivity mainActivityInstance;
    public static final String eventsListURL="https://vps-e690be27.vps.ovh.net/ihm/events.txt";
    public static final String SHARED_PREFERENCES_FILE_NAME = "BateauSharedPreferences";


    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences settings=getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0);
        Log.i("Bat", "We're here");
        typeUtilisateurs=TypeUtilisateurs.valueOf(settings.getString("typeutilisateur", TypeUtilisateurs.PECHEUR.name()));
        EvenementNotificationService.scheduleJob(getApplicationContext());
    }

    public static String getStringFromInputStream(InputStream stream) throws IOException
    {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, "UTF8");
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }

}
