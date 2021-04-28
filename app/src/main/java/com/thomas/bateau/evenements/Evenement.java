package com.thomas.bateau.evenements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;

import com.thomas.bateau.BateauApplication;
import com.thomas.bateau.TypeUtilisateurs;

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

public class Evenement implements Parcelable {

    private Double[] location=new Double[2];
    private String titre, description, imageURL, texte="";
    private Bitmap image;
    private TypeUtilisateurs typeUtilisateur=TypeUtilisateurs.PECHEUR;
    public static final String EVENEMENT="evenement";
    private DownloadFilesTask downloadFilesTask=new DownloadFilesTask();
    private long timestamp;

    public Evenement() {
        super();
        titre="";
        description="";
        image=null;
        imageURL=null;
    }

    public Evenement(String title, String description, TypeUtilisateurs typeUtilisateur) {
        this.titre=title;
        this.description=description;
        this.imageURL="";
        this.image=null;
        this.typeUtilisateur=typeUtilisateur;
    }

    public Evenement(String title, String description, TypeUtilisateurs typeUtilisateur, String imageURL) {
        this.titre=title;
        this.description=description;
        this.imageURL=imageURL;
        this.image=null;
        this.typeUtilisateur=typeUtilisateur;
    }

    public Evenement(String title, String description, TypeUtilisateurs typeUtilisateur, Bitmap image) {
        this.titre=title;
        this.description=description;
        this.imageURL="";
        this.image=image;
        this.typeUtilisateur=typeUtilisateur;
    }

    protected Evenement(Parcel in) {
        titre=in.readString();
        description=in.readString();
        imageURL=in.readString();
        texte=in.readString();
        //image=in.readParcelable(Bitmap.class.getClassLoader());
        typeUtilisateur=TypeUtilisateurs.valueOf(in.readString());
        timestamp=in.readLong();
    }

    public String getTitle() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getTexte() {
        return texte;
    }

    public TypeUtilisateurs getTypeUtilisateur() {
        return typeUtilisateur;
    }

    public void setTitle(String title) {
        this.titre=title;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL=imageURL;
    }

    public void setImage(Bitmap image) {
        this.image=image;
        this.imageURL="";
    }

    public void setTexte(String texte) {
        this.texte=texte;
    }

    public void setTypeUtilisateur(TypeUtilisateurs typeUtilisateur) {
        this.typeUtilisateur=typeUtilisateur;
    }

    public void setTimestamp(long t) {
        timestamp=t;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titre);
        dest.writeString(description);
        dest.writeString(imageURL);
        dest.writeString(texte);
        //dest.writeParcelable(image, flags);
        dest.writeString(typeUtilisateur.name());
        dest.writeLong(timestamp);
        //Log.d(">A", typeUtilisateur.name());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Evenement> CREATOR = new Creator<Evenement>() {
        @Override
        public Evenement createFromParcel(Parcel in) {
            return new Evenement(in);
        }

        @Override
        public Evenement[] newArray(int size) {
            return new Evenement[size];
        }
    };

    public void downloadImage(Consumer<Boolean> onImageDownloaded) {
        if(imageURL == null || imageURL.isEmpty()) {
            if(image != null && onImageDownloaded != null) {
                onImageDownloaded.accept(true);
            }
            return;
        }
        try {
            downloadFilesTask.setOnPost(() -> {
                if(downloadFilesTask.bmps.size() > 0 && downloadFilesTask.bmps.get(0) != null) {
                    this.image = downloadFilesTask.bmps.get(0);
                    if (onImageDownloaded != null) {
                        onImageDownloaded.accept(this.image != null);
                    }
                }
            });
            downloadFilesTask.execute(new URL(imageURL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void cancelDownloadImage() {
        downloadFilesTask.cancel(true);
    }

    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {

        private Runnable onPost;
        protected List<Bitmap> bmps=new ArrayList<>();

        void setOnPost(Runnable onPostDownload) {
            this.onPost=onPostDownload;
        }

        @Override
        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            for (int i = 0; i < count; i++) {
                bmps.add(downloadBitmapFromURL(urls[i]));
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

        public Bitmap downloadBitmapFromURL(URL url) {
            try {
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap bmp = BitmapFactory.decodeStream(input);
                connection.disconnect();
                return bmp;
            } catch (Exception e) {
                Log.d("Z", e.toString());
            }
            return null;
        }
    }
}
