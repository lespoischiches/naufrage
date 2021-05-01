package com.thomas.bateau.alert;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thomas.bateau.LocationAccessActivity;
import com.thomas.bateau.R;

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
                    String shareBody = " "+positions[0]+", "+ positions[1];
                    String url = "https://twitter.com/intent/tweet?text=Alert%20mon%20embarcation%20sombre,%20ma%20position%20est%20la%20suivante%20:%20" + shareBody;
                    Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                    startActivity(intent);
                }
        });

        /* Le code de William */
//        sharingNaufrage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                Double[] positions  = getLocation();
//                String shareBody = " "+positions[0]+", "+ positions[1];
//                String shareSub = "Share Position";
//                intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
//                intent.putExtra(Intent.EXTRA_TEXT,shareBody);
//                startActivity(Intent.createChooser(intent,"Share Using"));
//            }
//        });

    }
}
