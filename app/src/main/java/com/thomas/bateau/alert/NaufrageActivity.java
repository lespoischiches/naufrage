package com.thomas.bateau.alert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.thomas.bateau.LocationAccessActivity;
import com.thomas.bateau.R;

public class NaufrageActivity extends LocationAccessActivity {


    Button sharingNaufrage, buttonBack ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naufrage);

        buttonBack = findViewById(R.id.returnButtonNaufrage);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sharingNaufrage =findViewById(R.id.sharePosition);
        sharingNaufrage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        Double[] positions  = getLocation();
                        String shareBody = " "+positions[0]+", "+ positions[1];
                        String shareSub = "Share Position";
                        intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                        intent.putExtra(Intent.EXTRA_TEXT,shareBody);
                        startActivity(Intent.createChooser(intent,"Share Using"));
                    }
                });

    }


}
