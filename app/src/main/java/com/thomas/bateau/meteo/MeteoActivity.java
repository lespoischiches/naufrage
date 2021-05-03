package com.thomas.bateau.meteo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.thomas.bateau.R;

import java.util.Observable;
import java.util.Observer;

public class MeteoActivity extends AppCompatActivity implements Observer {

    private Button btnRetour, btnSwitchModeShoreOffShore;
    private TextView textTemp, textHumidity;
    private MeteoModel meteoModel=MeteoModel.getInstance();
    private ImageView imageType;
    private Button meteoSharing; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);
        meteoModel.addObserver(this);
        btnRetour=findViewById(R.id.btn_retour_meteo);
        textTemp=findViewById(R.id.meteo_text_temp);
        textHumidity=findViewById(R.id.meteo_text_humidity);
        imageType=findViewById(R.id.meteo_icon_type);
        btnSwitchModeShoreOffShore=findViewById(R.id.meteo_btn_switch_mode_shore_offshore);
        btnRetour.setOnClickListener(click -> {
            finish();
        });
        btnSwitchModeShoreOffShore.setOnClickListener(click -> {
            meteoModel.setMeteoLocation(MeteoModel.MeteoLocation.listTypes[(meteoModel.getMeteoLocation().getIndex()+1) % MeteoModel.MeteoLocation.listTypes.length]);
        });
        changeDataDisplayedFromMeteoModel();
        
        //meteoSharing = findViewById(R.id.sharingMeteo);

    }

    @Override
    public void update(Observable o, Object arg) {
        changeDataDisplayedFromMeteoModel();
    }

    private void changeDataDisplayedFromMeteoModel() {
        btnSwitchModeShoreOffShore.setText(meteoModel.getMeteoLocation().toString());
        textTemp.setText("Température:" + String.valueOf(meteoModel.getTemperature()));
        textHumidity.setText("Humidité: " + String.valueOf(meteoModel.getHumidity()));
        imageType.setImageResource(meteoModel.getMeteoType().getIcon());
    }
}