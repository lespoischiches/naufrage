package com.thomas.bateau.spot;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.thomas.bateau.R;

public class DiverSpotActivity extends PostSpotActivity {

    Button post, buttonBack, addPhoto ;
    ImageView imageViewDiver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diver_spot);

        initReturnBack(R.id.diver_spot_return);
        post();

        imageViewDiver = findViewById(R.id.image_spot);
        addPicture(R.id.add_photo,R.id.image_spot);
    }
}