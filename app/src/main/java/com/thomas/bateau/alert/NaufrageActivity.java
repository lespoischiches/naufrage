package com.thomas.bateau.alert;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thomas.bateau.LocationAccessActivity;
import com.thomas.bateau.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NaufrageActivity extends LocationAccessActivity {


    Button sharingNaufrage, buttonBack, buttonSharePosition;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_activity_naufrage);
        sharingNaufrage = findViewById(R.id.sharePosition);

        buttonBack = findViewById(R.id.returnButtonNaufrage);
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
                    /*String date = new SimpleDateFormat(" dd-MM-yyyy à HH:mm:ss").format(Calendar.getInstance().getTime());
                    String url = "https://twitter.com/intent/tweet?text= "+ date +" - Alerte%20mon%20navire%20sombre, %20ma%20position%20est%20la%20suivante%20:%20" + shareBody;
                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                    startActivity(intent);*/
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Naufrage repéré");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }
        });

    }
}
