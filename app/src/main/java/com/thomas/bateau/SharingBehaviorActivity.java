package com.thomas.bateau;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public interface SharingBehaviorActivity<T extends Activity> {
    public default void sharePlainTextWithUndefinedApp(String intentTitle, String shareTitle, String shareBody) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareTitle);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        ((Activity)this).startActivity(Intent.createChooser(sharingIntent, intentTitle));
    }

    public default void shareTweetLaunchBrowser(String tweet) {
        try {
            String url = "https://twitter.com/intent/tweet?text=" + URLEncoder.encode(tweet, "utf-8");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            ((Activity)this).startActivity(intent);
        } catch (UnsupportedEncodingException e) {
            //
        }
    }
}
