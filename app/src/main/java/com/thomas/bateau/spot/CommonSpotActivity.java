package com.thomas.bateau.spot;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.thomas.bateau.LocationAccessActivity;
import com.thomas.bateau.R;
import com.thomas.bateau.alert.AlertMeteoActivity;
import com.thomas.bateau.alert.HelpActivity;
import com.thomas.bateau.alert.NaufrageActivity;
import com.thomas.bateau.alert.RemainsActivity;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public abstract class CommonSpotActivity extends LocationAccessActivity {


    Button btnBack;
    private Runnable runnable;
    private Double[] location;



    protected void init(int backID)
    {
        getFragmentManager().beginTransaction().add(R.id.photo_fragment, new PhotoFragment()).commit();
        initReturnBack(backID);
    }
    protected void initReturnBack(int backID)
    {
        btnBack = findViewById(backID);
        btnBack.setOnClickListener(click -> finish());
    }

    @Override
    public void onNewLocationAvailable() {
        super.onNewLocationAvailable();
        location =getLocation();

        if(runnable!=null){runnable.run();}
    }

    protected void setOnNewLocationCallBack(Runnable callback) { runnable=callback; }


    public Double[] getSavedLocation() {
        return location;
    }
}
