package com.thomas.bateau;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

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
        TextView t = findViewById(R.id.textview_paramPenche);
        switch (BateauApplication.typeUtilisateurs) {
            case PECHEUR:
                rbPecheur.setChecked(true);
                t.setText(getString(R.string.TextPecheurParam));
                break;
            case KITTER:
                rbKitter.setChecked(true);
                t.setText(getString(R.string.TextKitterParam));
                break;
            case SKIPPER:
                rbSkipper.setChecked(true);
                t.setText(getString(R.string.TextSkipperParam));
                break;
            case PLONGEUR:
                rbPlongeur.setChecked(true);
                t.setText(getString(R.string.TextPlongeurParam));
                break;
            case SCIENTIFIQUE:
                rbScientifique.setChecked(true);
                t.setText(getString(R.string.TextScientifiqueParam));
                break;
        }
        findViewById(R.id.params_btn_retour).setOnClickListener(c -> {
            finish();
        });
    }

    public void onChangeRoleClicked(View view) {
        TextView t = findViewById(R.id.textview_paramPenche);
        boolean checked = ((RadioButton) view).isChecked();
        if(!checked) {
            return;
        }
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.params_radio_pecheur:
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.PECHEUR;
                t.setText(getString(R.string.TextPecheurParam));
                break;
            case R.id.params_radio_scientifique:
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.SCIENTIFIQUE;
                t.setText(getString(R.string.TextScientifiqueParam));
                break;
            case R.id.params_radio_plongeur:
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.PLONGEUR;
                t.setText(getString(R.string.TextPlongeurParam));
                break;
            case R.id.params_radio_kitter:
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.KITTER;
                t.setText(getString(R.string.TextKitterParam));
                break;
            case R.id.params_radio_skipper:
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.SKIPPER;
                t.setText(getString(R.string.TextSkipperParam));
                break;
        }
        if(BateauApplication.mainActivityInstance != null) {
            BateauApplication.mainActivityInstance.reloadBtn();
        }
    }
}