package com.thomas.bateau;

import android.app.Activity;
import android.content.Intent;


public interface SharingBehaviorActivity<T extends Activity> {
    public default void sharePlainTextWithUndefinedApp(String intentTitle, String shareTitle, String shareBody) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareTitle);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        ((Activity)this).startActivity(Intent.createChooser(sharingIntent, intentTitle));
    }
}
