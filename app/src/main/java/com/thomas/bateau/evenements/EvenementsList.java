package com.thomas.bateau.evenements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.thomas.bateau.TypeUtilisateurs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.thomas.bateau.BateauApplication.getStringFromInputStream;

public class EvenementsList extends ArrayList<Evenement> {

    private String JSON;
    private DownloadFilesTask downloadFilesTask=new DownloadFilesTask();

    public EvenementsList() {
        super();
    }

    public void downloadJSON(String JSONURL, Consumer<Boolean> onJSONDownloaded) {
        if(JSONURL == null || JSONURL.isEmpty()) {
            return;
        }
        try {
            downloadFilesTask.setOnPost(() -> {
                if(downloadFilesTask.strs.size() > 0 && downloadFilesTask.strs.get(0) != null) {
                    this.JSON = downloadFilesTask.strs.get(0);
                    downloadFilesTask.strs.clear();
                    try {
                        clear();
                        JSONArray jsonArray=new JSONArray(this.JSON);
                        for(int i=0; i<jsonArray.length(); i++) {
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            Evenement e=Evenement.parseJSONObject(jsonObject);
                            if(e != null) {
                                add(e);
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

    public void cancelDownloadJSON() {
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
