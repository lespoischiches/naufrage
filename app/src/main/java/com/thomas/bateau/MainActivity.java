package com.thomas.bateau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.thomas.bateau.coins.CoinActivity;
import com.thomas.bateau.evenements.EvenementsListActivity;
import com.thomas.bateau.meteo.MeteoActivity;
import com.thomas.bateau.meteo.MeteoModel;
import com.thomas.bateau.reportActivity.alertActivity.AlertActivity;
import com.thomas.bateau.reportActivity.spotActivity.SpotActivity;

import java.util.Observable;
import java.util.Observer;

public class  MainActivity extends AppCompatActivity implements Observer {

    private TextView btnMeteo, btnCarte, btnReport, btnAccueilCoin,buttonSpot;
    private MeteoModel meteoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMeteo=findViewById(R.id.btn_accueil_meteo);
        btnCarte=findViewById(R.id.btn_accueil_carte);
        btnReport=findViewById(R.id.btn_accueil_signaler_evenement);
        btnAccueilCoin=findViewById(R.id.btn_accueil_coin);
        buttonSpot = findViewById(R.id.btn_accueil_spot);

        meteoModel=MeteoModel.getInstance();
        meteoModel.addObserver(this);

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
        //btnAccueilCoin.setCompoundDrawablesWithIntrinsicBounds(BateauApplication.typeUtilisateurs.getIcon(), 0, 0, 0);
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
        changeDataDisplayedFromMeteoModel();
    }

    @Override
    public void onResume() {
        super.onResume();
        btnAccueilCoin.setText("Coin des "+BateauApplication.typeUtilisateurs.getString());
    }

    @Override
    public void update(Observable o, Object arg) {
        changeDataDisplayedFromMeteoModel();
    }

    private void changeDataDisplayedFromMeteoModel() {
        btnMeteo.setText(meteoModel.getMeteoType().toString()+"\nTemp: "+meteoModel.getTemperature()+" °C\nHumidity: "+meteoModel.getHumidity()+" %");
        Drawable image = getResources().getDrawable(meteoModel.getMeteoType().getIcon());
        int h = image.getIntrinsicHeight();
        int w = image.getIntrinsicWidth();
        image.setBounds( 0, 0, w, h );
        btnMeteo.setCompoundDrawables(image, null, null, null);
    }

}