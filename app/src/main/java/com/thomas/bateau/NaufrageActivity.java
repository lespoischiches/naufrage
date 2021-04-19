package com.thomas.bateau;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NaufrageActivity extends AppCompatActivity {


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
    }
}
