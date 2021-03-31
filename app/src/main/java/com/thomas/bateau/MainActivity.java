package com.thomas.bateau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnMeteo, btnCarte, btnReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMeteo=findViewById(R.id.btn_accueil_meteo);
        btnCarte=findViewById(R.id.btn_accueil_carte);
        btnReport=findViewById(R.id.btn_accueil_signaler_evenement);
        btnMeteo.setOnClickListener(click -> {
            Intent intent=new Intent(getApplicationContext(), MeteoActivity.class);
            startActivity(intent);
        });
        btnCarte.setOnClickListener(click -> {
            Intent intent=new Intent(getApplicationContext(), CarteActivity.class);
            startActivity(intent);
        });

        btnReport.setOnClickListener(click -> {
            Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
            startActivity(intent);
        });
    }
}