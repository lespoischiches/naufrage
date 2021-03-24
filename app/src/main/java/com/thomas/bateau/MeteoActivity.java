package com.thomas.bateau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MeteoActivity extends AppCompatActivity {

    private Button btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);
        btnRetour=findViewById(R.id.btn_retour_meteo);
        btnRetour.setOnClickListener(click -> {
            finish();
        });
    }
}