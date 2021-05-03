package com.thomas.bateau.coins;

import androidx.appcompat.app.AppCompatActivity;

import com.thomas.bateau.BateauApplication;
import com.thomas.bateau.R;
import com.thomas.bateau.coins.kitesurfer.CoinKitesurferFactory;
import com.thomas.bateau.coins.pecheur.CoinPecheurFactory;
import com.thomas.bateau.coins.plongeur.CoinPlongeurFactory;
import com.thomas.bateau.coins.scientifique.CoinScientifiqueFactory;
import com.thomas.bateau.coins.skippeur.CoinSkippeurFactory;

import android.os.Bundle;
import android.widget.TextView;

public class CoinActivity extends AppCompatActivity {

    private CoinsFactory coinsFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);
        TextView text = findViewById(R.id.text_view_coin);
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
            case SCIENTIFIQUE:
                coinsFactory=new CoinScientifiqueFactory();
                break;
            case SKIPPER:
                coinsFactory=new CoinSkippeurFactory();
                break;
            default:
                text.setText("Veuillez relancer l'application ou modifier vos paramètre, vous êtes dans un cas impossible");
                break;
        }
        if(coinsFactory != null) {
            getFragmentManager().beginTransaction().replace(R.id.coin_result_fragment, (android.app.Fragment) coinsFactory.createFragmentResults()).addToBackStack(null).commit();
            getFragmentManager().beginTransaction().replace(R.id.coin_search_fragment, (android.app.Fragment) coinsFactory.createFragmentSearch()).addToBackStack(null).commit();
            text.setText(getString(coinsFactory.getTextPresentation()));
        }
        findViewById(R.id.coin_retour_bouton).setOnClickListener(c -> {
            finish();
        });
    }
}