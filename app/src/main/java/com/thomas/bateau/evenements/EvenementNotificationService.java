package com.thomas.bateau.evenements;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.thomas.bateau.R;

import java.util.Objects;

import static com.thomas.bateau.BateauApplication.CHANNEL_1_ID;
import static com.thomas.bateau.BateauApplication.CHANNEL_2_ID;
import static com.thomas.bateau.BateauApplication.CHANNEL_3_ID;
import static com.thomas.bateau.BateauApplication.notificationManager;

public class EvenementNotificationService extends JobService {
    private int notificationId=0;

    public EvenementNotificationService() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i("JobServiceSample", "MainJobService start" );
        if(notificationManager == null) {
            createNotificationChannel();
        }
        sendNotificationOnChannel("NOTIF !!", "Hello from notification", CHANNEL_1_ID, NotificationCompat.PRIORITY_DEFAULT);
        EvenementNotificationService.scheduleJob(getApplicationContext());
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i("JobServiceSample", "MainJobService stop" );
        return true;
    }

    public static void scheduleJob(Context context) {
        ComponentName serviceComponent = new ComponentName(context, EvenementNotificationService.class);
        JobInfo jobInbo = new JobInfo.Builder(0, serviceComponent)
                .setMinimumLatency(5000)      // Temps d'attente minimal avant déclenchement
                .setOverrideDeadline(6000)    // Temps d'attente maximal avant déclenchement
                .build();

        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        jobScheduler.schedule(jobInbo);
    }


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

    private void sendNotificationOnChannel(String title, String message, String channelId, int priority) {
        if(getApplicationContext() == null) {
            return;
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), channelId).setContentTitle(title).setSmallIcon(R.drawable.cloud_icon).setContentText(message).setPriority(priority).setTimeoutAfter(5000);
        NotificationManagerCompat.from(getApplicationContext()).notify(++notificationId, notification.build());
    }

}