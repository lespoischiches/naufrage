package com.thomas.bateau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import static com.thomas.bateau.BateauApplication.SHARED_PREFERENCES_FILE_NAME;
import static com.thomas.bateau.BateauApplication.typeUtilisateurs;

public class ParamsActivity extends AppCompatActivity {

    private RadioButton rbPecheur, rbScientifique, rbPlongeur, rbKitter, rbSkipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int ori = this.getResources().getConfiguration().orientation;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params);
        rbPecheur=findViewById(R.id.params_radio_pecheur);
        rbScientifique=findViewById(R.id.params_radio_scientifique);
        rbPlongeur=findViewById(R.id.params_radio_plongeur);
        rbKitter=findViewById(R.id.params_radio_kitter);
        rbSkipper=findViewById(R.id.params_radio_skipper);
        TextView t = findViewById(R.id.textview_paramPenche);
        if(ori == Configuration.ORIENTATION_LANDSCAPE) {
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
        }
        else{
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
        }
        findViewById(R.id.params_btn_retour).setOnClickListener(c -> {
            finish();
        });
    }

    public void onChangeRoleClicked(View view) {
        int ori = this.getResources().getConfiguration().orientation;
        TextView t = findViewById(R.id.textview_paramPenche);
        boolean checked = ((RadioButton) view).isChecked();
        if(!checked) {
            return;
        }
        // Check which radio button was clicked
        if(ori == Configuration.ORIENTATION_LANDSCAPE) {
            switch (view.getId()) {
                case R.id.params_radio_pecheur:
                    BateauApplication.typeUtilisateurs = TypeUtilisateurs.PECHEUR;
                    t.setText(getString(R.string.TextPecheurParam));
                    break;
                case R.id.params_radio_scientifique:
                    BateauApplication.typeUtilisateurs = TypeUtilisateurs.SCIENTIFIQUE;
                    t.setText(getString(R.string.TextScientifiqueParam));
                    break;
                case R.id.params_radio_plongeur:
                    BateauApplication.typeUtilisateurs = TypeUtilisateurs.PLONGEUR;
                    t.setText(getString(R.string.TextPlongeurParam));
                    break;
                case R.id.params_radio_kitter:
                    BateauApplication.typeUtilisateurs = TypeUtilisateurs.KITTER;
                    t.setText(getString(R.string.TextKitterParam));
                    break;
                case R.id.params_radio_skipper:
                    BateauApplication.typeUtilisateurs = TypeUtilisateurs.SKIPPER;
                    t.setText(getString(R.string.TextSkipperParam));
                    break;
            }
        }
        else{
            switch (view.getId()) {
                case R.id.params_radio_pecheur:
                    BateauApplication.typeUtilisateurs = TypeUtilisateurs.PECHEUR;
                    break;
                case R.id.params_radio_scientifique:
                    BateauApplication.typeUtilisateurs = TypeUtilisateurs.SCIENTIFIQUE;
                    break;
                case R.id.params_radio_plongeur:
                    BateauApplication.typeUtilisateurs = TypeUtilisateurs.PLONGEUR;
                    break;
                case R.id.params_radio_kitter:
                    BateauApplication.typeUtilisateurs = TypeUtilisateurs.KITTER;
                    break;
                case R.id.params_radio_skipper:
                    BateauApplication.typeUtilisateurs = TypeUtilisateurs.SKIPPER;
                    break;
            }
        }
        SharedPreferences settings=getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("typeutilisateur", typeUtilisateurs.name());
        editor.commit();
        if(BateauApplication.mainActivityInstance != null) {
            BateauApplication.mainActivityInstance.reloadBtn();
        }
    }
}