package com.thomas.bateau.evenements;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class Evenement implements Parcelable {

    private Double[] location=new Double[2];
    private String titre, description, imageURL;
    private Bitmap image;
    public static final String EVENEMENT="evenement";

    public Evenement() {
        super();
        titre="";
        description="";
        image=null;
        imageURL=null;
    }

    public Evenement(String title, String description, String imageURL) {
        this.titre=title;
        this.description=description;
        this.imageURL=imageURL;
        this.image=null;
        //downloadBitmapFromURL();
        //new Thread(() -> downloadBitmapFromURL()).run();
    }

    public Evenement(String title, String description, Bitmap image) {
        this.titre=title;
        this.description=description;
        this.imageURL="";
        this.image=image;
    }

    protected Evenement(Parcel in) {
        titre=in.readString();
        description=in.readString();
        imageURL=in.readString();
        image=in.readParcelable(Bitmap.class.getClassLoader());
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

    public void setTitle(String title) {
        this.titre=title;
    }

    public void setDescription(String description) {
        this.description=description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL=imageURL;
        //new Thread(() -> downloadBitmapFromURL()).run();
    }

    public void setImage(Bitmap image) {
        this.image=image;
        this.imageURL="";
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titre);
        dest.writeString(description);
        dest.writeString(imageURL);
        dest.writeValue(image);
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

    private void downloadBitmapFromURL() {
        if(this.imageURL == null) {
            return;
        }
        try {
            java.net.URL url = new java.net.URL(this.imageURL);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            image = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            //
        }
    }
}
