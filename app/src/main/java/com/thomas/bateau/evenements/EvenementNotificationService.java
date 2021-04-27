package com.thomas.bateau.evenements;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.os.Build;
import android.os.Handler;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.thomas.bateau.BootDeviceReceiver;
import com.thomas.bateau.R;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static com.thomas.bateau.BateauApplication.CHANNEL_1_ID;
import static com.thomas.bateau.BateauApplication.CHANNEL_2_ID;
import static com.thomas.bateau.BateauApplication.CHANNEL_3_ID;
import static com.thomas.bateau.BateauApplication.notificationManager;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class EvenementNotificationService extends IntentService {
    private int notificationId=0;
    Timer timer;
    TimerTask timerTask;
    String TAG = "Timers";
    int Your_X_SECS = 5;
    final Handler handler = new Handler();

    public EvenementNotificationService() {
        super("EvenementNotificationService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        createNotificationChannel();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder builder = new Notification.Builder(this, CHANNEL_1_ID).setContentTitle("wifi listener bla bla bla").setContentText("test").setSmallIcon(R.drawable.bateau).setAutoCancel(true);
            Notification notification = builder.build();
            startForeground(1, notification);
        }
        startTimer();
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

    public void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 5000, Your_X_SECS * 1000); //
    }

    public void stoptimertask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        sendNotificationOnChannel("NOTIF !!", "Hello from notification", CHANNEL_1_ID, NotificationCompat.PRIORITY_DEFAULT);
                    }
                });
            }
        };
    }

    private void sendNotificationOnChannel(String title, String message, String channelId, int priority) {
        if(getApplicationContext() == null) {
            return;
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), channelId).setContentTitle(title).setSmallIcon(R.drawable.cloud_icon).setContentText(message).setPriority(priority).setTimeoutAfter(5000);
        NotificationManagerCompat.from(getApplicationContext()).notify(++notificationId, notification.build());
        /*Log.d("S", "Service !!!");*/
    }
}