package com.thomas.bateau.spot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thomas.bateau.R;

public class KiterSpotActivity extends PostSpotActivity {


    Button post ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitter_spot);

        initReturnBack(R.id.kitter_spot_return);

        post();

        addPicture(R.id.add_photo,R.id.image_spot);
    }
}