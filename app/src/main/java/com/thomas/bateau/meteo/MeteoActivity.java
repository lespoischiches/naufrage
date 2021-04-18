package com.thomas.bateau.meteo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.thomas.bateau.R;

import java.util.Observable;
import java.util.Observer;

public class MeteoActivity extends AppCompatActivity implements Observer {

    private Button btnRetour, btnSwitchModeShoreOffShore;
    private TextView textTemp;
    private MeteoModel meteoModel=MeteoModel.getInstance();
    private Button meteoSharing; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);
        meteoModel.addObserver(this);
        btnRetour=findViewById(R.id.btn_retour_meteo);
        textTemp=findViewById(R.id.meteo_text_temp);
        btnSwitchModeShoreOffShore=findViewById(R.id.meteo_btn_switch_mode_shore_offshore);
        btnRetour.setOnClickListener(click -> {
            finish();
        });
        btnSwitchModeShoreOffShore.setOnClickListener(click -> {
            meteoModel.setMeteoType(MeteoModel.MeteoType.listTypes[(meteoModel.getMeteoType().getIndex()+1) % MeteoModel.MeteoType.listTypes.length]);
        });
        btnSwitchModeShoreOffShore.setText(meteoModel.getMeteoType().toString());
        textTemp.setText(String.valueOf(meteoModel.getTemperature()));
        
        //meteoSharing = findViewById(R.id.sharingMeteo);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        //
    }

    @Override
    public void update(Observable o, Object arg) {
        btnSwitchModeShoreOffShore.setText(meteoModel.getMeteoType().toString());
        textTemp.setText(String.valueOf(meteoModel.getTemperature()));
    }
}