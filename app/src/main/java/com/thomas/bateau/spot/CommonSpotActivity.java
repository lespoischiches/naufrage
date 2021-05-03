package com.thomas.bateau.spot;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.thomas.bateau.Character;
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

public  class CommonSpotActivity extends LocationAccessActivity {

    private Runnable runnable;
    private Double[] location;
    protected int activityCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_spot);
        activityCharacter = getIntent().getExtras().getInt("ID");

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
