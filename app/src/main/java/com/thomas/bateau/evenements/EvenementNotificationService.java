package com.thomas.bateau.evenements;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.thomas.bateau.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.thomas.bateau.BateauApplication.CHANNEL_1_ID;
import static com.thomas.bateau.BateauApplication.CHANNEL_2_ID;
import static com.thomas.bateau.BateauApplication.CHANNEL_3_ID;
import static com.thomas.bateau.BateauApplication.SHARED_PREFERENCES_FILE_NAME;
import static com.thomas.bateau.BateauApplication.eventsListURL;
import static com.thomas.bateau.BateauApplication.getStringFromInputStream;
import static com.thomas.bateau.BateauApplication.notificationManager;
import static com.thomas.bateau.BateauApplication.typeUtilisateurs;
import static com.thomas.bateau.evenements.Evenement.EVENEMENT;

public class EvenementNotificationService extends JobService {
    private int notificationId=0;
    public static boolean alreadyShown=false;
    private DownloadFilesTask downloadFilesTask=new DownloadFilesTask();
    private String JSON;
    List<Evenement> listEvenements=new ArrayList<>();

    public EvenementNotificationService() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i("JobServiceSample", "MainJobService start" );
        if(notificationManager == null) {
            createNotificationChannel();
        }

        downloadJSON(eventsListURL, (success) -> {
            listEvenements=listEvenements.stream().filter(e -> e.getTypeUtilisateur()==typeUtilisateurs).collect(Collectors.toList());
            if(!success || listEvenements.isEmpty()) {
                return;
            }
            if (!alreadyShown) {
                if(listEvenements.size() == 1) {
                    Evenement e=listEvenements.get(0);
                    Intent activityOnClickIntent = new Intent(this, EvenementViewActivity.class);
                    activityOnClickIntent.putExtra(EVENEMENT, e);
                    sendNotificationOnChannel(e.getTitle(), e.getDescription(), CHANNEL_1_ID, NotificationCompat.PRIORITY_DEFAULT, e.getTypeUtilisateur().getIcon(), activityOnClickIntent);
                } else {
                    Intent activityOnClickIntent = new Intent(this, EvenementsListActivity.class);
                    sendNotificationOnChannel(listEvenements.size()+" nouveaux événements", "Plusieurs nouveaux événements ont été signalés autour de votre position.", CHANNEL_1_ID, NotificationCompat.PRIORITY_DEFAULT, R.drawable.cloud_icon, activityOnClickIntent);
                }
                alreadyShown = true;
            }
        });

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

    private void sendNotificationOnChannel(String title, String message, String channelId, int priority, int icon, Intent activityOnClickIntent) {
        if(getApplicationContext() == null) {
            return;
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), channelId).setContentTitle(title).setSmallIcon(icon).setContentText(message).setPriority(priority).setAutoCancel(true);;
        if(activityOnClickIntent != null) {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addNextIntentWithParentStack(activityOnClickIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(resultPendingIntent);
        }
        NotificationManagerCompat.from(getApplicationContext()).notify(++notificationId, notification.build());
    }



    private void downloadJSON(String JSONURL, Consumer<Boolean> onJSONDownloaded) {
        if(JSONURL == null || JSONURL.isEmpty()) {
            return;
        }
        try {
            downloadFilesTask.setOnPost(() -> {
                if(downloadFilesTask.strs.size() > 0 && downloadFilesTask.strs.get(0) != null) {
                    this.JSON = downloadFilesTask.strs.get(0);
                    downloadFilesTask.strs.clear();
                    try {
                        listEvenements.clear();
                        JSONArray jsonArray=new JSONArray(this.JSON);
                        for(int i=0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            Evenement e=Evenement.parseJSONObject(jsonObject);
                            if(e != null) {
                                listEvenements.add(e);
                            }
                        }
                    } catch (JSONException e) {
                        if (onJSONDownloaded != null) {
                            onJSONDownloaded.accept(false);
                        }
                        return;
                    }
                    if (onJSONDownloaded != null) {
                        onJSONDownloaded.accept(this.JSON != null);
                    }
                }
            });
            downloadFilesTask.execute(new URL(JSONURL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void cancelDownloadJSON() {
        downloadFilesTask.cancel(true);
    }

    class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {

        private Runnable onPost;
        protected List<String> strs=new ArrayList<>();

        void setOnPost(Runnable onPostDownload) {
            this.onPost=onPostDownload;
        }

        @Override
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            for (int i = 0; i < count; i++) {
                strs.add(downloadStringFromURL(urls[i]));
                publishProgress((int) ((i / (float) count) * 100));
                if (isCancelled()) break;
            }
            return new Long(0);
        }

        @Override
        protected void onPostExecute(Long result) {
            if(onPost != null) {
                onPost.run();
            }
        }

        public String downloadStringFromURL(URL url) {
            try {
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                String str = getStringFromInputStream(input);
                connection.disconnect();
                return str;
            } catch (Exception e) {
                Log.d("Z", e.toString());
            }
            return null;
        }

    }

}