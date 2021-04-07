package com.thomas.bateau;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class ParamsActivity extends AppCompatActivity {

    private RadioButton rbPecheur, rbScientifique, rbPlongeur, rbKitter, rbSkipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params);
        rbPecheur=findViewById(R.id.params_radio_pecheur);
        rbScientifique=findViewById(R.id.params_radio_scientifique);
        rbPlongeur=findViewById(R.id.params_radio_plongeur);
        rbKitter=findViewById(R.id.params_radio_kitter);
        rbSkipper=findViewById(R.id.params_radio_skipper);
        switch (BateauApplication.typeUtilisateurs) {
            case PECHEUR:
                rbPecheur.setChecked(true);
                break;
            case KITTER:
                rbKitter.setChecked(true);
                break;
            case SKIPPER:
                rbSkipper.setChecked(true);
                break;
            case PLONGEUR:
                rbPlongeur.setChecked(true);
                break;
            case SCIENTIFIQUE:
                rbScientifique.setChecked(true);
                break;
        }
        findViewById(R.id.params_btn_retour).setOnClickListener(c -> {
            finish();
        });
    }

    public void onChangeRoleClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if(!checked) {
            return;
        }
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.params_radio_pecheur:
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.PECHEUR;
                break;
            case R.id.params_radio_scientifique:
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.SCIENTIFIQUE;
                break;
            case R.id.params_radio_plongeur:
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.PLONGEUR;
                break;
            case R.id.params_radio_kitter:
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.KITTER;
                break;
            case R.id.params_radio_skipper:
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.SKIPPER;
                break;
        }
        if(BateauApplication.mainActivityInstance != null) {
            BateauApplication.mainActivityInstance.reloadBtn();
        }
    }
}