package com.thomas.bateau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import com.thomas.bateau.coins.CoinActivity;
import com.thomas.bateau.evenements.EvenementNotificationService;
import com.thomas.bateau.evenements.EvenementsListActivity;
import com.thomas.bateau.meteo.MeteoActivity;
import com.thomas.bateau.reportActivity.alertActivity.AlertActivity;
import com.thomas.bateau.reportActivity.spotActivity.SpotActivity;

public class  MainActivity extends AppCompatActivity {

    private TextView btnMeteo, btnCarte, btnReport, btnAccueilCoin,buttonSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMeteo=findViewById(R.id.btn_accueil_meteo);
        btnCarte=findViewById(R.id.btn_accueil_carte);
        btnReport=findViewById(R.id.btn_accueil_signaler_evenement);
        btnAccueilCoin=findViewById(R.id.btn_accueil_coin);
        BateauApplication.mainActivityInstance=this;
        buttonSpot = findViewById(R.id.btn_accueil_spot);

        buttonSpot.setOnClickListener(click ->{
            Intent intent=new Intent(getApplicationContext(), SpotActivity.class);
            startActivity(intent);} );
        btnMeteo.setOnClickListener(click -> {
            Intent intent=new Intent(getApplicationContext(), MeteoActivity.class);
            startActivity(intent);
        });
        btnCarte.setOnClickListener(click -> {
            Intent intent=new Intent(getApplicationContext(), CarteActivity.class);
            startActivity(intent);
        });

        btnReport.setOnClickListener(click -> {
            Intent intent = new Intent(getApplicationContext(), AlertActivity.class);
            startActivity(intent);
        });
        btnAccueilCoin.setText("Coin des "+BateauApplication.typeUtilisateurs.getString());
        btnAccueilCoin.setOnClickListener(c -> {
            Intent intent=new Intent(getApplicationContext(), CoinActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.accueil_btn_params).setOnClickListener(c -> {
            Intent intent=new Intent(getApplicationContext(), ParamsActivity.class);
            startActivity(intent);
        });

        int ori = this.getResources().getConfiguration().orientation;
        if( ori==Configuration.ORIENTATION_LANDSCAPE) {
            findViewById(R.id.accueil_btn_actu).setOnClickListener(c -> {
                Intent intent = new Intent(getApplicationContext(), EvenementsListActivity.class);
                startActivity(intent);
            });
        }
        //EvenementNotificationService.scheduleJob(getApplicationContext());
    }

    public void reloadBtn() {
        btnAccueilCoin.setText("Coin des "+BateauApplication.typeUtilisateurs.getString());
    }
}