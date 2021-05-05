package com.thomas.bateau.spot;

import android.os.Bundle;

import com.thomas.bateau.LocationAccessActivity;
import com.thomas.bateau.R;

public  class CommonSpotActivity extends LocationAccessActivity {

    private Runnable runnable;
    private Double[] location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_spot);
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
