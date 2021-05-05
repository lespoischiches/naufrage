package com.thomas.bateau.reportActivity.alertActivity.alert;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thomas.bateau.LocationAccessActivity;
import com.thomas.bateau.R;
import com.thomas.bateau.SharingBehaviorActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NaufrageActivity extends LocationAccessActivity implements SharingBehaviorActivity {


    Button sharingNaufrage, buttonBack, buttonSharePosition, twitterButton;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_activity_naufrage);
        sharingNaufrage = findViewById(R.id.sharePosition);
        buttonBack = findViewById(R.id.returnButtonNaufrage);
        twitterButton=findViewById(R.id.activity_alert_naufrage_twitter);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sharingNaufrage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Double[] positions  = getLocation();
                    String shareBody = "ALERTE: naufrage a "+positions[0]+", "+ positions[1];
                    sharePlainTextWithUndefinedApp("Partager avec...", "Naufrage repéré", shareBody);
                }
        });

        twitterButton.setOnClickListener(c -> {
            Double[] positions  = getLocation();
            String shareBody = "ALERTE: naufrage a "+positions[0]+", "+ positions[1];
            String date = new SimpleDateFormat(" dd-MM-yyyy à HH:mm:ss").format(Calendar.getInstance().getTime());
            shareTweetLaunchBrowser(date +" - Alerte mon navire sombre, ma position est la suivante: " + shareBody);
        });

    }
}
