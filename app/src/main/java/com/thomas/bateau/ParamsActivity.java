package com.thomas.bateau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class ParamsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params);
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
                Log.d("D", "Pecheur");
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.PECHEUR;
                break;
            case R.id.params_radio_scientifique:
                Log.d("D", "Scientifique");
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.SCIENTIFIQUE;
                break;
            case R.id.params_radio_plongeur:
                BateauApplication.typeUtilisateurs=TypeUtilisateurs.PLONGEUR;
        }
    }
}