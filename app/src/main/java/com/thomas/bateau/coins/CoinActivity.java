package com.thomas.bateau.coins;

import androidx.appcompat.app.AppCompatActivity;

import com.thomas.bateau.BateauApplication;
import com.thomas.bateau.R;
import com.thomas.bateau.coins.kitesurfer.CoinKitesurferFactory;
import com.thomas.bateau.coins.pecheur.CoinPecheurFactory;
import com.thomas.bateau.coins.plongeur.CoinPlongeurFactory;

import android.os.Bundle;

public class CoinActivity extends AppCompatActivity {

    private CoinsFactory coinsFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);
        switch (BateauApplication.typeUtilisateurs) {
            case PECHEUR:
                coinsFactory=new CoinPecheurFactory();
                break;
            case PLONGEUR:
                coinsFactory=new CoinPlongeurFactory();
                break;
            case KITTER:
                coinsFactory=new CoinKitesurferFactory();
                break;
            default: // ???
                coinsFactory=new CoinPecheurFactory();
                break;
        }
         // Si pecheur, sinon CoinScientifiqueFactory, CoinSkipperFactory etc...
        getFragmentManager().beginTransaction().replace(R.id.coin_result_fragment, (android.app.Fragment)coinsFactory.createFragmentResults()).addToBackStack(null).commit();
        getFragmentManager().beginTransaction().replace(R.id.coin_search_fragment, (android.app.Fragment)coinsFactory.createFragmentSearch()).addToBackStack(null).commit();
        findViewById(R.id.coin_retour_bouton).setOnClickListener(c -> {
            finish();
        });
    }
}